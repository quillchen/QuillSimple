package quill.design.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import quill.design.R;

public class Mvp1Activity extends AppCompatActivity implements IUserView {

    @Bind(R.id.mvp1_id)
    EditText etId;
    @Bind(R.id.mvp1_name)
    EditText etName;
    @Bind(R.id.mvp1_info)
    EditText etInfo;
    @Bind(R.id.mvp1_insert)
    Button insert;
    @Bind(R.id.mvp1_query)
    Button query;
    @Bind(R.id.mvp1_show)
    TextView show;
    @Bind(R.id.mvp1_create)
    Button create;
    private Presenter1 presenter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp1);
        ButterKnife.bind(this);
        presenter1 = new Presenter1(this);
    }

    @OnClick({R.id.mvp1_insert, R.id.mvp1_query,R.id.mvp1_create})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mvp1_create:
                presenter1.create(this);
                break;
            case R.id.mvp1_insert:

                break;
            case R.id.mvp1_query:

                break;
        }
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setName() {

    }

    @Override
    public void setInfo() {

    }


}
