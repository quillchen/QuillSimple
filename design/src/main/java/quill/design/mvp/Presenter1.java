package quill.design.mvp;

import android.content.Context;

/**
 * Created by Quill on 2016/3/24.
 */
public class Presenter1 {
    private IUserView view;
    private final UserModel userModel;

    public Presenter1(IUserView view) {
        this.view = view;
        userModel = new UserModel();
    }

    public void create(Context context){
        userModel.create(context);
    }

    public void insert(UserBean bean){
        userModel.insert(bean);
    }


}
