package quill.okhttputils.request;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;
import quill.okhttputils.callback.Callback;
import quill.okhttputils.utils.Exceptions;

/**
 * Created by Quill on 2016/3/22.
 */
public abstract class OkHttpRequest {

    protected  String url;
    protected  Object tag;
    protected  Map<String, String> params;
    protected  Map<String, String> headers;

    protected Request.Builder builder = new Request.Builder();

    public OkHttpRequest(String url, Object tag, Map<String,String>params
    , Map<String,String>headers) {

        this.url = url;
        this.tag = tag;
        this.params = params;
        this.headers = headers;
        if (url == null)
        {
            Exceptions.illegalArgument("url can not be null.");
        }

        initBuilder();
    }

    protected  void initBuilder(){
        builder.url(url).tag(tag);
        appendHeaders();
    }

    protected  void appendHeaders(){
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key:headers.keySet()){
            headerBuilder.add(key,headers.get(key));
        }
        builder.headers(headerBuilder.build());

    };


    protected abstract RequestBody buildRequestBody();
    public RequestBody wrapRequestBody(RequestBody requestBody,Callback callback){
        return null;
    }
    public void buildRequest(){

    }

    public Request generateRequest(String url){
        Request.Builder builder = new Request.Builder();
        return builder.url(url).build();
    }

}
