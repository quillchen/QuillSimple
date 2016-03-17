package quill.simple;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import quill.simple.ui.ItemListActivity;
import quill.simple.ui.SettingsActivity;
import quill.simple.ui.VolleyActivity;
import quill.simple.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private Class[] classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler);
        initRecycler();

    }

    private void initRecycler() {
        classes = new Class[]{VolleyActivity.class,  SettingsActivity.class,
                ItemListActivity.class};
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter();
        adapter.setOnRecyclerListener(new OnRecyclerListener() {
            @Override
            public void OnItem(int position) {
                openActivity(classes[position]);
            }
        });
        recyclerView.setAdapter(adapter);

    }



    public interface OnRecyclerListener{
            void OnItem(int position);
        };
    private class MyAdapter extends RecyclerView.Adapter{
        private OnRecyclerListener listener;

        public void setOnRecyclerListener(OnRecyclerListener listener){
            this.listener = listener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_activity_main, null);

            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            MyHolder myHolder= (MyHolder) holder;
            myHolder.textView.setText(classes[position]+"");
            myHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItem(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return classes.length;
        }
        class MyHolder extends RecyclerView.ViewHolder{
            TextView textView;

            public MyHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_item_activity_main);
            }
        }

    }
}
