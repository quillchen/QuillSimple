package quill.simple.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quill.simple.R;
import quill.simple.ui.base.BaseActivity;
import quill.simple.view.MultiViewGroup;

public class MultiActivity extends BaseActivity {
    private static final String TAG="MultiViewGroup";
    @Bind(R.id.tv_hello)
    TextView tvHello;
    @Bind(R.id.multi_viewgroup)
    MultiViewGroup multiViewgroup;
    @Bind(R.id.bt_scrollLeft)
    Button btScrollLeft;
    @Bind(R.id.bt_scrollRight)
    Button btScrollRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        ButterKnife.bind(this);
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int screenWidth = metrics.widthPixels;
//        int screenHeight = metrics.heightPixels;
//        Log.d(TAG,"activity screenWidth="+screenWidth+" screenHeight="+screenHeight);
    }

    @OnClick({R.id.bt_scrollLeft, R.id.bt_scrollRight})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_scrollLeft:

                break;
            case R.id.bt_scrollRight:

                break;
        }
    }
}
