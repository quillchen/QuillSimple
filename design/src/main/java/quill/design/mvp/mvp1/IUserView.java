package quill.design.mvp.mvp1;

/**
 * Created by Quill on 2016/3/24.
 */
public interface IUserView {
    int getId();
    String getName();
    String getInfo();
    void setName(String name);
    void setInfo(String info);
}
