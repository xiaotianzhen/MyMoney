package com.dong.mymoney;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dong.bean.Flag;
import com.dong.dao.FlagDao;

public class NewFlagActivity extends AppCompatActivity {
    private EditText et_flag;
    private Button btn_save, btn_back;
    private long fistkeydowm = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flag);
        et_flag= (EditText) findViewById(R.id.et_flag);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_back = (Button) findViewById(R.id.btn_back);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlagDao flagDao=new FlagDao(getApplication());
                Flag flag=new Flag();
                flag.setFlag(et_flag.getText().toString());
                flagDao.addFlag(flag);
                Intent intent=new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplication(),MainActivity.class);
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
