package com.g.cn.test2sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelp extends SQLiteOpenHelper {
    private  String dbcreate="create table user("+"id INTEGER PRIMARY KEY ,"+ "name text default \"\","+"password text default \"\" ,"+
            "phone text default \"\","+"email text default \"\","+"sex text default \"\")";//新增Id"Id INTEGER PRIMARY KEY ,"+
    public SQLiteHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sd) {
        System.out.println(dbcreate);
        sd.execSQL(dbcreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sd, int oldVersion, int newVersion) {

    }
}
