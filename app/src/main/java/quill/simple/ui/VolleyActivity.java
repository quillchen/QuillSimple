package quill.simple.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import quill.simple.R;
import quill.simple.ui.base.BaseActivity;

public class VolleyActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG=VolleyActivity.class.getName();
    private Button btnStr,btnJson,btnGson,btnGet,btnPost,btnPostParams;
    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        initView();
    }

    private void initView() {
        btnStr = (Button) findViewById(R.id.btn_string);
        btnJson = (Button) findViewById(R.id.btn_json);
        btnGson = (Button) findViewById(R.id.btn_gson);
        btnGet = (Button) findViewById(R.id.btn_get_params);
        btnPost = (Button) findViewById(R.id.btn_post);
        btnPostParams = (Button) findViewById(R.id.btn_post_params);
        tvResponse = (TextView) findViewById(R.id.tv_response);

        btnStr.setOnClickListener(this);
        btnJson.setOnClickListener(this);
        btnGson.setOnClickListener(this);
        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnPostParams.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_string:
                clickStr();
                break;
            case R.id.btn_json:
                clickJson();
                break;
            case R.id.btn_gson:
                break;
            case R.id.btn_get_params:
                break;
            case R.id.btn_post:
                break;
            case R.id.btn_post_params:
                break;
            default:
                break;
        }
    }

    private void clickJson() {
        Log.d(TAG,"JSONObject response=");
        String url="http://m.weather.com.cn/data/101010100.html";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG,"JSONObject response="+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"JSONObject error="+error);
            }
        });
    }

    private void clickStr() {
        RequestQueue requestQueue = Volley.newRequestQueue(VolleyActivity.this);

        String url="http://www.baidu.com";
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvResponse.setText(response);
                Log.d(TAG,"response="+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"error="+error);
            }
        });
        requestQueue.add(stringRequest);
    }
}
