package quill.design.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import quill.design.R;
import quill.design.utils.LogUtils;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        LogUtils.isDebug=true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("onDestroy");
    }

    public void openActivity(Class<?> clazz){
        startActivity(new Intent(BaseActivity.this,clazz));
    }
}
