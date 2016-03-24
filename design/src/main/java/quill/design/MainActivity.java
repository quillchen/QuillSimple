package quill.design;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quill.design.builder.CustomDialog;
import quill.design.mvp.Mvp1Activity;
import quill.design.ui.BaseActivity;
import quill.design.ui.MvpActivity;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.main_listview)
    ListView listview;
    @Bind(R.id.btn_dialog)
    Button btnDialog;
    private Class<?>[] datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        listview.setOnItemClickListener(this);
    }

    private void init() {
        datas = new Class<?>[]{MvpActivity.class, Mvp1Activity.class};
        listview.setAdapter(new ArrayAdapter<Class<?>>(this, android.R.layout.simple_expandable_list_item_1, datas));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        openActivity(datas[position]);
    }

    @OnClick(R.id.btn_dialog)
    public void onClick() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
        builder.setTitle("this is custom dialog title")
                .setMessage("this is message")
                .setPositiveButton("确认",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();


    }
}
