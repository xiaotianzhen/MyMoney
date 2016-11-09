package com.dong.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dong.bean.Password;
import com.dong.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 川东 on 2016/8/9.
 */
public class PasswordDao {
    private DBHelper dbHelper;

    public PasswordDao(Context context) {
        dbHelper = new DBHelper(context);

    }


    public long addPassword(Password password) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password.getPassword());
        long count = 0;
        db.beginTransaction();
        try {
            count = db.insert("tb_pwd", null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return count;
    }

    public long deletePassword(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long count = 0;
        count = db.delete("tb_pwd", "id=?", new String[]{String.valueOf(id)});
        return count;
    }

    public long updatePassword(Password password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password.getPassword());
        long count = 0;
        count = db.update("tb_pwd", values, null, null);
        return count;
    }

    public Password selectPassword() {
        Password password = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_pwd";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String pwd = cursor.getString(cursor.getColumnIndex("password"));
            password = new Password(pwd);
        }
        return password;
    }
    public Boolean selectExistsPassword() {
        Password password = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_pwd";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String pwd = cursor.getString(cursor.getColumnIndex("password"));
            password = new Password(pwd);
            return true;
        }
        return false;
    }
}
