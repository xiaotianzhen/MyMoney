package com.dong.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dong.bean.Flag;
import com.dong.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 川东 on 2016/8/9.
 */
public class FlagDao {

    private DBHelper dbHelper;

    public FlagDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long addFlag(Flag flag) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long count = 0;
        ContentValues values = new ContentValues();
        values.put("flag", flag.getFlag());
        db.beginTransaction();
        try {
            count = db.insert("tb_flag", null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return count;
    }

    public long deleteFlag(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long count = 0;
        count = db.delete("tb_flag", "_id=?", new String[]{String.valueOf(id)});
        return count;
    }

    public long updateFlag(Flag flag) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", flag.get_id());
        values.put("flag", flag.getFlag());
        long count = 0;
        count = db.update("tb_flag", values, "_id=?", new String[]{String.valueOf(flag.get_id())});
        return count;
    }

    public Flag selectFromIdFlag(int id) {
        Flag flag = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_flag where _id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String content = cursor.getString(cursor.getColumnIndex("flag"));
            flag = new Flag(_id, content);
        }
        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }
        return flag;
    }

    public Cursor selectAllFlag() {
       /* Flag flag = null;
       *//* List<Flag> list = new ArrayList<Flag>();*/
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_flag ";
        Cursor cursor = db.rawQuery(sql, null);
       /* while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String content = cursor.getString(cursor.getColumnIndex("flag"));
            flag = new Flag(_id, content);
            list.add(flag);
        }
        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }*/
        return cursor;
    }
}
