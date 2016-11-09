package com.dong.mymoney;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
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

import java.util.Calendar;

public class AddOutaccountActivity extends AppCompatActivity {
    private EditText et_outaccount, et_address, et_mark;
    private Button btn_save, btn_back;
    private Spinner spinner;
    private int year, month, day;
    private ImageView lv_time;
    private TextView tv_time;
    private long fistkeydowm = 0;

    private void initView() {
        et_outaccount = (EditText) findViewById(R.id.et_outaccount);
        et_address = (EditText) findViewById(R.id.et_address);
        et_mark = (EditText) findViewById(R.id.et_mark);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_back = (Button) findViewById(R.id.btn_back);
        spinner = (Spinner) findViewById(R.id.spinner);
        lv_time = (ImageView) findViewById(R.id.iv_time);
        tv_time= (TextView) findViewById(R.id.tv_time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outaccount);
        initView();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        tv_time.setText(year + "年" + (month + 1) + "月" + day + "日");
        lv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddOutaccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        AddOutaccountActivity.this.year = year;
                        AddOutaccountActivity.this.month = month;
                        AddOutaccountActivity.this.day = day;
                        tv_time.setText( year + "年" + (month + 1) + "月" + day + "日");
                    }
                }, year, month, day).show();
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(et_outaccount.getText())) {
                    et_outaccount.setError("请输入支出金额");
                    et_outaccount.requestFocus();
                }
                if (TextUtils.isEmpty(et_address.getText())) {
                    et_address.setError("请输入地址");
                    et_address.requestFocus();
                }
                if (TextUtils.isEmpty(et_mark.getText())) {
                    et_mark.setError("请输入备注");
                    et_mark.requestFocus();
                }

                try {
                    String type = spinner.getSelectedItem().toString();
                    float inaccount = Float.valueOf(et_outaccount.getText().toString());
                    String time = year + "年" + (month + 1) + "月" + day + "日";
                    Log.i("520it", "*********" + time);
                    String address = et_address.getText().toString();
                    Log.i("520it", "*********" + address);
                    String mark = et_mark.getText().toString();
                    Log.i("520it", "*********" + mark);
                    if (!TextUtils.isEmpty(et_outaccount.getText()) && !TextUtils.isEmpty(et_address.getText())
                            && !TextUtils.isEmpty(et_mark.getText())) {
                        DBHelper dbHelper = new DBHelper(getApplicationContext());
                        OutAccountDao outAccountDao = new OutAccountDao(dbHelper);
                        OutAccount outAccount = new OutAccount();
                        outAccount.setMoney(inaccount);
                        outAccount.setTime(time);
                        outAccount.setAddress(address);
                        outAccount.setMark(mark);
                        outAccount.setType(type);
                        outAccountDao.addOutAccount(outAccount);
                        Toast.makeText(getApplication(), "添加成功", Toast.LENGTH_LONG).show();
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
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
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
