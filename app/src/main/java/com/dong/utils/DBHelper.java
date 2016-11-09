package com.dong.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 川东 on 2016/8/7.
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static String DBNAME = "account.db";
    private final static int VERSON = 1;

    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql1 = "create table  tb_outaccount(_id integer primary key autoincrement, "
                + "money decimal not null,"
                + "time varchar(10) not null,"
                + "type varchar(10) not null,"
                + "address varchar(100) not null,"
                + "mark varchar(200) )";
       String sql2="create table tb_inaccount(_id integer primary key autoincrement," +
               "money decimal  not null," +
               "time varchar(10) not null," +
               "type varchar(10) not null," +
               "handler varchar(100) not null," +
               "mark varchar(200))";

        String sql3="create table tb_flag(_id integer primary key autoincrement," +
                "flag varchar(200) not null)";
        String sql4="create table tb_pwd(password varchar(20) not null)";

            db.execSQL(sql1);
            db.execSQL(sql2);
            db.execSQL(sql3);
            db.execSQL(sql4);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql1="drop table if exists tb_outaccount ";
        String sql2="drop table if exists tb_inaccount ";
        String sql3="drop table if exists tb_flag ";
        String sql4="drop table if exists tb_pwd ";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        this.onCreate(db);
    }

}
