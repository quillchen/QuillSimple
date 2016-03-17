package quill.simple.ui.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import quill.simple.R;
import quill.simple.utils.ActivityManager;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG="lifescycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "BaseActivity onBackPressed Invoke...");
        ActivityManager.getInstance().removeActivity(this);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "BaseActivity onLowMemory Invoke...");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG, "BaseActivity onTrimMemory level="+level);
    }

    protected void openActivity(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    protected void showToast(String pMsg) {
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();
    }
}
