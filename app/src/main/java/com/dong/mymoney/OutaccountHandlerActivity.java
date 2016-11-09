package com.dong.mymoney;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dong.bean.InAccount;
import com.dong.bean.OutAccount;
import com.dong.dao.InAccountDao;
import com.dong.dao.OutAccountDao;
import com.dong.utils.DBHelper;

public class OutaccountHandlerActivity extends AppCompatActivity {
    private EditText et_outaccount,  et_address, et_mark;
    private Button btn_update, btn_delete;
    private Spinner spinner;
    private int year, month, day;
    private ImageView lv_time;
    private TextView tv_time;
    private long fistkeydowm = 0;
    private void initView() {
        et_outaccount = (EditText) findViewById(R.id.et_outaccount);
        et_address = (EditText) findViewById(R.id.et_address);
        et_mark = (EditText) findViewById(R.id.et_mark);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        spinner = (Spinner) findViewById(R.id.spinner);
        lv_time= (ImageView) findViewById(R.id.iv_time);
        tv_time= (TextView) findViewById(R.id.tv_time);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outaccount_handler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OutaccountHandlerActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        String type = intent.getStringExtra("type");
        initView();
        DBHelper dbHelper = new DBHelper(getApplication());
        final OutAccountDao outAccountDao = new OutAccountDao(dbHelper);
        OutAccount outAccount = outAccountDao.selectFromId(id);
        String Time = outAccount.getTime().toString();//2016年08月
        Log.i("520year", "*********" + Time);
        String nian = Time.substring(0, 4);
        year = Integer.valueOf(nian);
        Log.i("520year", "*********" + year);
        int yueindex = Time.lastIndexOf("月");
        Log.i("520yueindex", "*********" + yueindex);
        String yue = Time.substring(5, yueindex);
        Log.i("520yue", "*********" + yue);
        month = Integer.valueOf(yue);
        String ri = Time.substring(yueindex + 1, Time.lastIndexOf("日"));
        day = Integer.valueOf(ri);
        tv_time.setText(year + "年" + month + "月" + day + "日");
        lv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(OutaccountHandlerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        OutaccountHandlerActivity.this.year = year;
                        OutaccountHandlerActivity.this.month = month;
                        OutaccountHandlerActivity.this.day = day;
                        tv_time.setText(year + "年" + (month + 1) + "月" + day + "日");
                    }
                }, year, month, day).show();
            }
        });
        et_outaccount.setText(String.valueOf(outAccount.getMoney()));

        String[] Type = new String[]{"早餐", "午餐", "晚餐", "夜宵", "水果零食", "交通费", "房租", "日用品", "家电", "其他"};
        int a = 0;
        for (a = 0; a < Type.length; a++) {
            if (type.equals(Type[a])) {
                spinner.setSelection(a);
            }
        }
        et_address.setText(outAccount.getAddress());
        et_mark.setText(outAccount.getMark());

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outAccountDao.deleteOutAccount(id);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(et_outaccount.getText())) {
                    et_outaccount.setError("请输入支出金额");
                    et_outaccount.requestFocus();
                }
                if (TextUtils.isEmpty(et_address.getText())) {
                    et_address.setError("请输入支出地址");
                    et_address.requestFocus();
                }
                if (TextUtils.isEmpty(et_mark.getText())) {
                    et_mark.setError("请输入备注");
                    et_mark.requestFocus();
                }
                try {
                    String type = spinner.getSelectedItem().toString();
                    float money = Float.valueOf(et_outaccount.getText().toString());
                    String time = year + "年" + (month + 1) + "月" + day + "日";
                    Log.i("520it", "*********" + time);
                    String address = et_address.getText().toString();
                    Log.i("520it", "*********" + address);
                    String mark = et_mark.getText().toString();
                    Log.i("520it", "*********" + mark);

                    if (!TextUtils.isEmpty(et_outaccount.getText()) && !TextUtils.isEmpty(et_address.getText())
                            && !TextUtils.isEmpty(et_mark.getText())) {


                        DBHelper dbHelper = new DBHelper(getApplication());
                        OutAccountDao outAccountDao = new OutAccountDao(dbHelper);
                        OutAccount outAccount = new OutAccount();
                        outAccount.set_id(id);
                        outAccount.setAddress(address);
                        outAccount.setMoney(money);
                        outAccount.setTime(time);
                        outAccount.setMark(mark);
                        outAccount.setType(type);
                        outAccountDao.updateOutAccount(outAccount);
                        Toast.makeText(getApplication(), "修改成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(getApplication(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){

            if(System.currentTimeMillis()-fistkeydowm>2000){
                fistkeydowm=System.currentTimeMillis();
                Intent intent = new Intent();
                intent.setClass(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                finish();
            }
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
