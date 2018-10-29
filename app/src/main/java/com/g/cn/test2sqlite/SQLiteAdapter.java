package com.g.cn.test2sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SQLiteAdapter {
    private Context mContext;
    public SQLiteAdapter(Context mContext) {
        this.mContext = mContext;
    }
    void Insert(SQLiteDatabase dbdatabase,String strname,String strpassword,String strphone,String stremail,String strsex)//
    {

        //db dbhelper=new db(this);
       //SQLiteDatabase dbdatabase=dbhelper.getWritableDatabase();//创建dbdatabase数据库
        ContentValues values = new ContentValues();
        values.put("name", strname);
        values.put("password",strpassword);
        values.put("phone",strphone);
        values.put("email",stremail);
        values.put("sex",strsex);//
       Toast.makeText(mContext, strname+" "+strphone+" "+strpassword+" "+stremail+" "+strsex, Toast.LENGTH_SHORT).show();//
        long f = dbdatabase.insert("user", null, values);
        String h=String.valueOf(f);
        Toast.makeText(mContext, "f"+h, Toast.LENGTH_SHORT).show();
      // dbdatabase.close();
       //SQLiteDatabase dbRead=dbhelper.getReadableDatabase();
       // Cursor cs= dbRead.query("user",null,null,null,null,null,null);
       /* while(cs.moveToNext()) {
            String name = cs.getString(cs.getColumnIndex("name"));
            String phone = cs.getString(cs.getColumnIndex("phone"));
            String password=cs.getString(cs.getColumnIndex("password"));
            String email=cs.getString(cs.getColumnIndex("email"));
            Toast.makeText(mContext, name+phone, Toast.LENGTH_SHORT).show();

        }
        dbdatabase.close();
        dbRead.close();*/

    }
   /* public Map<String ,String> Read(SQLiteDatabase dbRead)
    {

        Cursor cs= dbRead.query("user",null,null,null,null,null,null);
        Map<String, String> data = new HashMap<String, String>();
        while(cs.moveToNext()) {
            String name = cs.getString(cs.getColumnIndex("name"));
            String password = cs.getString(cs.getColumnIndex("password"));
            String phone=cs.getString(cs.getColumnIndex("phone"));
            String email=cs.getString(cs.getColumnIndex("email"));
           // String sex=cs.getString(cs.getColumnIndex("sex"));
            data.put("username", name);
            data.put("passwd", password);
            data.put("phone", phone);
            data.put("email", email);
            break;
            //data.put("sex", sex);//
        }
            //Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        return data;

        }*/
   public String [] Read(SQLiteDatabase dbRead)
   {

       Cursor cs= dbRead.query("user",null,null,null,null,null,null);
       int i=0;
       int j=0;
       String b="";
       while(cs.moveToNext()) {
            i++;
       }
       String a[]=new String[i];
       cs.moveToFirst();
       String  name = cs.getString(cs.getColumnIndex("name"));
       a[j]=name;
       j++;
       while(cs.moveToNext()) {
       name = cs.getString(cs.getColumnIndex("name"));
           b=b+name;
           a[j]=name;
            j++;
          // Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
       }
       Toast.makeText(mContext, b+i, Toast.LENGTH_SHORT).show();
       return a;

   }






    public void Update(SQLiteDatabase dbdatabase,String strname,String strpassword,String strphone,String stremail,String strsex,int strid)//
    {

        ContentValues values = new ContentValues();
        values.put("name", strname);//key为字段名，value为值
        values.put("password", strpassword);//key为字段名，value为值
        values.put("phone", strphone);//key为字段名，value为值
        values.put("email", stremail);//key为字段名，value为值
        values.put("sex",strsex);
        //long f =dbdatabase.update("user", values, "name=?", new String[]{strname});//
        long f = dbdatabase.update("user", values, "id=?", new String[]{String.valueOf(strid)});//


        String h=String.valueOf(f);
        Toast.makeText(mContext, "f"+h, Toast.LENGTH_SHORT).show();


       // Toast.makeText( mContext,strname+""+strpassword+" "+strphone+" "+stremail, Toast.LENGTH_SHORT).show();//
       // dbdatabase.close();


    }

    public void Delete(SQLiteDatabase dbdatabase,int strid)////String strname
    {
        //dbdatabase.delete("user", "name=?", new String[]{strname});////
       // dbdatabase.delete("user", "id=?", new int[]{id},null,null,null);
       dbdatabase.delete ("user","id=?",new String[]{String.valueOf(strid)});//null,null,null,null
    }


    public Map<String ,String>Read2(SQLiteDatabase dbRead,int strid1)////String strname
    {
        Map<String, String> data = new HashMap<String, String>();////
       // Cursor cs= dbRead.query("user", new String[]{"password","phone","email","sex","id"},"name=?", new String[]{strname},null,null,null);


        Cursor cs= dbRead.query("user", new String[]{"name","password","phone","email","sex"},"id"+"="+strid1,null,null,null,null);
////


        String strname="";////

        String strpassword="";
        String strphone="";
        String stremail="";
        String strsex="";
        String  strid;////
        while(cs.moveToNext())
        {
            //name=cs.getString(cs.getColumnIndex("name"));
            strname=cs.getString(cs.getColumnIndex("name"));////
            strpassword=cs.getString(cs.getColumnIndex("password"));
            strphone=cs.getString(cs.getColumnIndex("phone"));
            stremail=cs.getString(cs.getColumnIndex("email"));
            strsex=cs.getString(cs.getColumnIndex("sex"));

            data.put("name", strname);
            data.put("password",strpassword );
            data.put("phone",strphone);
            data.put("email", stremail);
            data.put("sex", strsex);//

          //  Toast.makeText( mContext,strname+""+strpassword+" "+strphone+" "+stremail, Toast.LENGTH_SHORT).show();
            break;
        }

        return data;

    }



}
