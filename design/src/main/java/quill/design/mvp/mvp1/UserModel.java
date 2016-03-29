package quill.design.mvp.mvp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Quill on 2016/3/24.
 */
public class UserModel {

    private MyDatabaseHelper dbHelper;

    public UserModel(Context context){
        if (dbHelper==null)
        dbHelper = new MyDatabaseHelper(context,MyDatabaseHelper.DB_NAEM,null,1);
    }

    public void insert(String uid,String name,String info) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Uid",uid);
        values.put("name",name);
        values.put("info",info);
        db.insert(MyDatabaseHelper.TABLE,null,values);
    }

    public void query(UserBean bean){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select*from UserInfo where Uid like ?",
                new String[]{"%" + bean.getId() + "%"});
        if (cursor.moveToNext()){
            bean.setName(cursor.getString(1));
            bean.setInfo(cursor.getString(2));
            Log.d("Mvp1","cursor.getColumnName(0)="+cursor.getColumnName(0)
            +"cursor.getColumnName(1)="+cursor.getColumnName(1));
        }

    }

    public void create(Context context) {
        dbHelper.getWritableDatabase();
    }

}
