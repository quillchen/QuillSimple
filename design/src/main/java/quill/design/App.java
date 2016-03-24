package quill.design;

import android.app.Application;

/**
 * Created by Quill on 2016/3/24.
 */
public class App extends Application {
    public static Application instance;

    public static Application getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
