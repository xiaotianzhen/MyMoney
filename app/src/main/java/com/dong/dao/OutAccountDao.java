package com.dong.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dong.bean.OutAccount;
import com.dong.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 川东 on 2016/8/8.
 */
public class OutAccountDao {

    private DBHelper dbHelper;

    public OutAccountDao(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public long addOutAccount(OutAccount outAccount) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("money", outAccount.getMoney());
        values.put("time", outAccount.getTime());
        values.put("type", outAccount.getType());
        values.put("mark", outAccount.getMark());
        values.put("address", outAccount.getAddress());
        long count = 0;
        db.beginTransaction();
        try {
            count = db.insert("tb_outaccount", null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return count;
    }

    public long deleteOutAccount(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long count = 0;
        count = db.delete("tb_outaccount", "_id=?", new String[]{String.valueOf(id)});
        return count;
    }

    public long updateOutAccount(OutAccount outAccount) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", outAccount.get_id());
        values.put("money", outAccount.getMoney());
        values.put("time", outAccount.getTime());
        values.put("type", outAccount.getType());
        values.put("mark", outAccount.getMark());
        values.put("address", outAccount.getAddress());
        long count = 0;
        count = db.update("tb_outaccount", values, "_id=?", new String[]{String.valueOf(outAccount.get_id())});
        return count;
    }

    public OutAccount selectFromId(int id) {
        OutAccount outAccount = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_outaccount where _id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String mark = cursor.getString(cursor.getColumnIndex("mark"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            outAccount = new OutAccount(_id, money, time, type, address, mark);
        }
        if (cursor != null) {
            cursor.close();
        }
        if (db != null) {
            db.close();
        }
        return outAccount;
    }

    public Cursor selectALLOutAccount() {
        List<OutAccount> list = new ArrayList<OutAccount>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from tb_outaccount";
        Cursor cursor = db.rawQuery(sql, null);
       /* while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String mark = cursor.getString(cursor.getColumnIndex("mark"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            OutAccount outAccount = new OutAccount(_id, money, time, type, address, mark);
            list.add(outAccount);
        }*/

        return cursor;
    }
}
