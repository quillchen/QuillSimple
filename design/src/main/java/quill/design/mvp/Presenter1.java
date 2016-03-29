package quill.design.mvp;

import android.content.Context;

/**
 * Created by Quill on 2016/3/24.
 */
public class Presenter1 {
    private IUserView view;
    private final UserModel userModel;

    public Presenter1(Context context,IUserView view) {
        this.view = view;
        userModel = new UserModel(context);
    }

    public void create(Context context){
        userModel.create(context);
    }

    public void insert(String uid,String name,String info){
        userModel.insert(uid,name,info);
    }

    public void query(UserBean bean){
        userModel.query(bean);
        view.setName(bean.getName());
    }




}
