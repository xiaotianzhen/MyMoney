package com.dong.mymoney;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dong.bean.Password;
import com.dong.dao.PasswordDao;
import com.dong.fragment.HandFragment;
import com.dong.utils.DBManager;


public class LoginActivity extends AppCompatActivity {
    public DBManager dbHelper;
    private EditText et_password;
    private Button btn_login, btn_back;
   private android.support.v7.widget.Toolbar toolbar;

    private void initView() {
        et_password = (EditText) findViewById(R.id.et_password);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_login = (Button) findViewById(R.id.btn_login);
        toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("登录");
        toolbar.setTitleTextColor(Color.WHITE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        //首次执行导入.db文件
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        dbHelper.closeDatabase();

        PasswordDao passwordDao = new PasswordDao(getApplicationContext());
        if (passwordDao.selectExistsPassword()) {
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PasswordDao passwordDao = new PasswordDao(getApplicationContext());
                    Password password = passwordDao.selectPassword();
                    String passd = password.getPassword();
                    Log.i("520it", "*********" + passd);
                    String pwd = et_password.getText().toString();
                    if (passd.equals(pwd)) {
                        et_password.setText("");
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        et_password.setError("密码输入错误");
                        et_password.requestFocus();
                    }
                }
            });

            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else{
            Intent intent = new Intent();
            HandFragment handFragment=new HandFragment();
            intent.setClass(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
