package com.dong.mymoney;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dong.bean.InAccount;
import com.dong.dao.InAccountDao;

import java.io.BufferedReader;
import java.util.Calendar;

public class AddInaccountActivity extends AppCompatActivity {
    private EditText et_inaccount, et_handler, et_mark;
    private Button btn_save, btn_back;
    private Spinner spinner;
    private DatePicker ap_time;
    private int year, month, day;
    private ImageView lv_time;
    private TextView tv_time;
    private long fistdowm = 0;

    private void initView() {
        et_inaccount = (EditText) findViewById(R.id.et_inaccount);
        et_handler = (EditText) findViewById(R.id.et_handler);
        et_mark = (EditText) findViewById(R.id.et_mark);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_back = (Button) findViewById(R.id.btn_back);
        spinner = (Spinner) findViewById(R.id.spinner);
        lv_time = (ImageView) findViewById(R.id.iv_time);
        tv_time = (TextView) findViewById(R.id.tv_time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inaccount);
        initView();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        tv_time.setText(year + "年" + (month + 1) + "月" + day + "日");
        lv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddInaccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        AddInaccountActivity.this.year = year;
                        AddInaccountActivity.this.month = month;
                        AddInaccountActivity.this.day = day;
                        tv_time.setText(year + "年" + (month + 1) + "月" + day + "日");
                    }
                }, year, month, day).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(et_inaccount.getText())) {
                    et_inaccount.setError("请输入收入金额");
                    et_inaccount.requestFocus();
                }
                if (TextUtils.isEmpty(et_handler.getText())) {
                    et_handler.setError("请输入收入付款方");
                    et_handler.requestFocus();
                }
                if (TextUtils.isEmpty(et_mark.getText())) {
                    et_mark.setError("请输入收入备注");
                    et_mark.requestFocus();
                }


                try {
                    String type = spinner.getSelectedItem().toString();
                    float inaccount = Float.valueOf(et_inaccount.getText().toString());
                    String time = year + "年" + (month + 1) + "月" + day + "日";
                    Log.i("520it", "*********" + time);
                    String handler = et_handler.getText().toString();
                    Log.i("520it", "*********" + handler);
                    String mark = et_mark.getText().toString();
                    Log.i("520it", "*********" + mark);


                    if (!TextUtils.isEmpty(et_inaccount.getText()) && !TextUtils.isEmpty(et_handler.getText())
                            && !TextUtils.isEmpty(et_mark.getText())) {

                        InAccountDao inAccountDao = new InAccountDao(AddInaccountActivity.this);
                        InAccount inAccount = new InAccount();
                        inAccount.setMoney(inaccount);
                        inAccount.setTime(time);
                        inAccount.setHandler(handler);
                        inAccount.setMark(mark);
                        inAccount.setType(type);
                        inAccountDao.addInAccount(inAccount);
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

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - fistdowm > 2000) {
                fistdowm = System.currentTimeMillis();
                Intent intent = new Intent();
                intent.setClass(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
