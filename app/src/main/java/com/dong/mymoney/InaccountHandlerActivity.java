package com.dong.mymoney;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.dong.dao.InAccountDao;


public class InaccountHandlerActivity extends AppCompatActivity {

    private EditText et_inaccount, et_handler, et_mark;
    private Button btn_update, btn_delete;
    private Spinner spinner;
    private  int year,month,day;
    private ImageView lv_time;
    private TextView tv_time;
    private long fistkeydowm = 0;
    private Toolbar toolbar;
    private void initView() {
        et_inaccount = (EditText) findViewById(R.id.et_inaccount);
        et_handler = (EditText) findViewById(R.id.et_handler);
        et_mark = (EditText) findViewById(R.id.et_mark);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        spinner = (Spinner) findViewById(R.id.spinner);
        lv_time= (ImageView) findViewById(R.id.iv_time);
        tv_time= (TextView) findViewById(R.id.tv_time);
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inaccount_handler);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(InaccountHandlerActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        String type = intent.getStringExtra("type");
        initView();
        final    InAccountDao inAccountDao =new InAccountDao(getApplicationContext());
       InAccount inAccount= inAccountDao.selectFromId(id);
       String Time= inAccount.getTime().toString();//2016年08月
        String nian=Time.substring(0, 4);
        year=Integer.valueOf(nian);
        Log.i("year", "*********" + year);
        int yueindex=Time.lastIndexOf("月");
        String yue=Time.substring(5, yueindex);
        month=Integer.valueOf(yue);
        String ri=Time.substring(yueindex+1,Time.lastIndexOf("日"));
        day=Integer.valueOf(ri);
        tv_time.setText(year + "年" + month + "月" + day + "日");
        lv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(InaccountHandlerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        InaccountHandlerActivity.this.year = year;
                        InaccountHandlerActivity.this.month = month;
                        InaccountHandlerActivity.this.day = day;
                        tv_time.setText(year + "年" + (month + 1) + "月" + day + "日");
                    }
                }, year, month, day).show();
            }
        });


        et_inaccount.setText(String.valueOf(inAccount.getMoney()));
        String[] Type=new String[]{"工资","兼职","股票","奖金","租金","销售款","报销款","其他"};
        int a=0;
        for(a=0 ;a<Type.length;a++){
            if(type.equals(Type[a])){
                spinner.setSelection(a);
            }
        }
        et_handler.setText(inAccount.getHandler());
        et_mark.setText(inAccount.getMark());

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inAccountDao.deleteInAccount(id);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
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
                    float money = Float.valueOf(et_inaccount.getText().toString());
                    String time = year + "年" + (month + 1) + "月" + day + "日";
                    Log.i("520it", "*********" + time);
                    String handler = et_handler.getText().toString();
                    Log.i("520it", "*********" + handler);
                    String mark = et_mark.getText().toString();
                    Log.i("520it", "*********" + mark);

                    if (!TextUtils.isEmpty(et_inaccount.getText()) && !TextUtils.isEmpty(et_handler.getText())
                            && !TextUtils.isEmpty(et_mark.getText())) {


                        InAccountDao inAccountDao = new InAccountDao(getApplication());
                        InAccount inAccount = new InAccount();
                        inAccount.set_id(id);
                        inAccount.setHandler(handler);
                        inAccount.setMoney(money);
                        inAccount.setTime(time);
                        inAccount.setMark(mark);
                        inAccount.setType(type);
                        inAccountDao.updateInAccount(inAccount);
                        Toast.makeText(getApplication(), "修改成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(getApplication(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }catch (Exception e){
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
