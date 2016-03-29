package quill.design.mvp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import quill.design.utils.UiUtils;

/**
 * Created by Quill on 2016/3/28.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper{
    public static final String  DB_NAEM="Mvp1.db";
    public static final String  TABLE="UserInfo";

    public static final String CREATE_BOOK="create table UserInfo("
            +"id integer primary key autoincrement,"
            +"Uid integer,"
            +"name text,"
            +"info text)";


    private final Context context;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        UiUtils.showToast("数据库创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //更新SQLiteDatabase
//        db.execSQL("drop table if exists UserInfo");
//        onCreate(db);
    }
}
