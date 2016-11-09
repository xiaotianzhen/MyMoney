package com.dong.mymoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dong.bean.Flag;
import com.dong.dao.FlagDao;

public class FlagHanderActivity extends AppCompatActivity {
    private EditText et_flag;
    private Button btn_update, btn_delete;
    private long fistkeydowm = 0;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_hander);
       toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FlagHanderActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        et_flag = (EditText) findViewById(R.id.et_flag);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        final FlagDao flagDao = new FlagDao(getApplication());
        final Flag flag = flagDao.selectFromIdFlag(id);
        et_flag.setText(flag.getFlag().toString());
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Flag flag = new Flag();
                flag.setFlag(et_flag.getText().toString());
                flagDao.updateFlag(flag);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagDao.deleteFlag(id);
                Intent intent = new Intent(getApplication(), MainActivity.class);
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
