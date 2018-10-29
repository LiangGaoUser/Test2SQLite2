package com.g.cn.test2sqlite;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

//import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity {
    //基本实现功能，但是性别需要加上，以及禁止添加相同名字的数据，修改保存时，不能修改姓名切保不能保存不存在的记录，删除时不饿能删除不存在的数据，

    private Context mContext;
    private EditText editname;
    private EditText editpasswd;
    private EditText  editphone;
    private EditText  editemail;
    private String strname;
    private  String strpasswd;
    private  String strphone;
    private  String stremail;
    private RadioGroup Rgsex;
    private RadioButton Rgman;
    private RadioButton Rgwoman;
    private Button BtnInsert;
    private Button BtnSave;
    private Button BtnDelete;
    private Button BtnClear;
    private  Spinner sg_gender;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    private Spinner Spinner1;
    private SQLiteAdapter sad;
    private SQLiteHelp dbhelper;
    private SQLiteDatabase dbdatabase;
    private  SQLiteDatabase dbReader;
    private  RadioGroup rgp;//
    private  String strsex;//
    private RadioButton rb1;
    private  int  strid;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper=new SQLiteHelp(this,"firstdemo14",null,1);
        dbdatabase=dbhelper.getWritableDatabase();//创建dbdatabase数据库放到具体的函数中打开关闭
        dbReader=dbhelper.getReadableDatabase();/////
        mContext = getApplicationContext();
        sad=new SQLiteAdapter(mContext);
       SQLiteStudioService.instance().start(this);//
        bindViews();









      /* SQLiteHelp dbhelper=new SQLiteHelp(this,"firstdemo3",null,1);//这段代码能独立运行，不要函数
        SQLiteDatabase dbdatabase=dbhelper.getWritableDatabase();//创建dbdatabase数据库
        //dbhelper.onCreate(dbdatabase);
        ContentValues values = new ContentValues();
        values.put("name", "梁高");
        values.put("password", "123456");
        values.put("phone", "13667297888");
        values.put("email", "123456@qq.com");
        long f = dbdatabase.insert("user", null, values);
        String h=String.valueOf(f);
        Toast.makeText( getApplicationContext(),h, Toast.LENGTH_SHORT).show();
        dbdatabase.close();
        SQLiteDatabase dbRead=dbhelper.getReadableDatabase();
        Cursor cs= dbRead.query("user",null,null,null,null,null,null);
        String a="";
        while(cs.moveToNext()) {
            String name=  cs.getString(cs.getColumnIndex("name"));
            String password= cs.getString(cs.getColumnIndex("password"));
            String phone= cs.getString(cs.getColumnIndex("phone"));
            String email= cs.getString(cs.getColumnIndex("email"));
            a=a+name;
            //Toast.makeText( getApplicationContext(),a, Toast.LENGTH_SHORT).show();//name+password+phone+email
        }
       Toast.makeText( getApplicationContext(),a, Toast.LENGTH_SHORT).show();
        dbRead.close();*/
    }
    private void bindViews()
    {
        editname = (EditText)findViewById(R.id.editText);
        editpasswd =(EditText)findViewById(R.id.editText2);
        editphone=(EditText)findViewById(R.id.editText3);
        editemail=(EditText)findViewById(R.id.editText4);
        BtnInsert=(Button)findViewById(R.id.button);
        BtnClear=(Button) findViewById(R.id.button4);
        BtnSave=(Button) findViewById(R.id.button2);
        BtnDelete=(Button)findViewById(R.id.button3);
        sg_gender=(Spinner) findViewById(R.id.gender);
        rgp=(RadioGroup) findViewById(R.id.radiogroupsex);//

        BtnInsert.setOnClickListener(new View.OnClickListener() {//添加
            @Override
            public void onClick(View v) {

                strname = editname.getText().toString();
                strpasswd = editpasswd.getText().toString();
                strphone = editphone.getText().toString();
                stremail = editemail.getText().toString();
                selectRadioBtn();


//               // radiogroup本身有监听的方法可以直接设置监听，这个监听需要一个回调接口OnCheckedChangeListener，这个接口里面的回调方法给我们返回了两个参数其中int型的参数就是当前你选中的RadioButton的ID
//                        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
//                        {
//                            public void onCheckedChanged(RadioGroup group ,int checkedId)
//                            {
//                                selectRadioBtn();
//                            }
//                        });







                Toast.makeText( getApplicationContext(),strname+""+strpasswd+" "+strphone+" "+stremail+""+strsex, Toast.LENGTH_SHORT).show();
                //dbdatabase=dbhelper.getWritableDatabase();//创建dbdatabase数据库//
               sad.Insert(dbdatabase,strname, strpasswd, strphone, stremail,strsex);//

                updateSpinner();


            }
        });







        BtnClear.setOnClickListener(new View.OnClickListener() {//清空
            @Override
            public void onClick(View v) {

              /*  Map<String,String> data =sad.Read(dbReader);;
                editname.setText(data.get("username"));
                editpasswd.setText(data.get("passwd"));
                editphone.setText(data.get("phone"));
                editemail.setText(data.get("email"));*/
                editname.setText("");
                editpasswd.setText("");
                editphone.setText("");
                editemail.setText("");
               rgp.check(R.id.radioButton3);//默认选中第一个
                // sad.Read(dbReader);
                //SQLiteStudioService.instance().stop(this);//


                //Toast.makeText(mContext, "请填写完整信息", Toast.LENGTH_SHORT).show();
            }
        });

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname = editname.getText().toString();
                strpasswd = editpasswd.getText().toString();
                strphone = editphone.getText().toString();
                stremail = editemail.getText().toString();
                selectRadioBtn();//
                //dbdatabase=dbhelper.getWritableDatabase();//
                sad.Update(dbdatabase,strname,strpasswd,strphone,stremail,strsex,strid);//

            }
        });



        BtnDelete.setOnClickListener(new View.OnClickListener() {//
            @Override
            public void onClick(View v) {
                strname = editname.getText().toString();
                sad.Delete(dbdatabase,strid);////strname
                updateSpinner();
            }
        });

        sg_gender.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
             //   // 将所选mySpinner 的值带入myTextView 中
             //   Cursor cs= dbReader.query("user",new String[]{"name,password,phone,email"},"name=",null,null,null,null);
              //  int i=0;
                Map<String,String> data=new HashMap<String, String>();
               //data= sad.Read2(dbReader,adapter.getItem(arg2));////
                data= sad.Read2(dbReader,arg2+1);////
                strid=arg2+1;////
                editname.setText(data.get("name"));
                editpasswd.setText(data.get("password"));
                editphone.setText(data.get("phone"));
                editemail.setText(data.get("email"));

                String sex2 =data.get("sex");
               // Toast.makeText(mContext, "man"+strid+sex2, Toast.LENGTH_SHORT).show();
               try {
                   if (sex2.equals("男"))//==好像不能用
                   {
                       rgp.check(R.id.radioButton3);//默认选中第一个
                       Toast.makeText(mContext, "man" + strid, Toast.LENGTH_SHORT).show();
                       //  Toast.makeText(mContext,adapter.getItem(arg2) , Toast.LENGTH_SHORT).show();
                   } else {

                       rgp.check(R.id.radioButton4);//默认选中第二个
                       Toast.makeText(mContext, "woman" + strid, Toast.LENGTH_SHORT).show();
                       // Toast.makeText(mContext,adapter.getItem(arg2) , Toast.LENGTH_SHORT).show();
                   }
               }
               catch (Exception e)
               {
                   Toast.makeText(mContext, "某个值可能为空", Toast.LENGTH_SHORT).show();

               }







               // Toast.makeText(mContext,adapter.getItem(arg2), Toast.LENGTH_SHORT).show();
        }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
               // Toast.makeText(mContext, b+i, Toast.LENGTH_SHORT).show();
            }
        });








        }

    protected void onStart() {
        super.onStart();
        try {
            sad.Read(dbReader);

            updateSpinner();
        }
        catch ( Exception e)
        {
            Toast.makeText(mContext, "数据库为空", Toast.LENGTH_SHORT).show();
        }



    }

 private  void updateSpinner()
 {

     sg_gender = (Spinner) findViewById(R.id.gender);
     adapter = new ArrayAdapter<String>
             (this,android.R.layout.simple_expandable_list_item_1, sad.Read(dbReader));// ArrayAdapter<String>
     sg_gender.setAdapter(adapter);






 }

    private void selectRadioBtn()//
    {
        rb1=(RadioButton) findViewById(rgp.getCheckedRadioButtonId());//
        strsex=rb1.getText().toString();

    }

}
