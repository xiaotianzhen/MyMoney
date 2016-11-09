package com.dong.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dong.bean.InAccount;
import com.dong.bean.OutAccount;
import com.dong.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 川东 on 2016/8/8.
 */
public class InAccountDao {
    private DBHelper dbHelper;

    public  InAccountDao(Context context){

        dbHelper=new DBHelper(context);
    }

    public long addInAccount(InAccount inAccount){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        long count=0;
        ContentValues values=new ContentValues();
        values.put("money", inAccount.getMoney());
        values.put("time", inAccount.getTime());
        values.put("type", inAccount.getType());
        values.put("mark", inAccount.getMark());
        values.put("handler", inAccount.getHandler());
        db.beginTransaction();
        try {
            count = db.insert("tb_inaccount", null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
            db.close();
        }
        return  count;
    }
    public long deleteInAccount(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long count = 0;
        count = db.delete("tb_inaccount", "_id=?", new String[]{String.valueOf(id)});
        return count;
    }



    public long updateInAccount(InAccount inAccount) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", inAccount.get_id());
        values.put("money", inAccount.getMoney());
        values.put("time", inAccount.getTime());
        values.put("type", inAccount.getType());
        values.put("mark", inAccount.getMark());
        values.put("handler", inAccount.getHandler());
        long count = 0;
        count = db.update("tb_inaccount", values, "_id=?", new String[]{String.valueOf( inAccount.get_id())});
        return count;
    }

    public InAccount selectFromId(int id) {
        InAccount inAccount = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_inaccount where _id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String mark = cursor.getString(cursor.getColumnIndex("mark"));
            String handler = cursor.getString(cursor.getColumnIndex("handler"));
            inAccount = new InAccount(_id, money, time, type, handler, mark);
        }
        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }
        return inAccount;
    }

    public Cursor selectALLInAccount() {
        List<InAccount> list = new ArrayList<InAccount>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_inaccount";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
        }


}
