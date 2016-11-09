package com.dong.mymoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.dong.adpater.FragmentAdapter;
import com.dong.fragment.FlagFragment;
import com.dong.fragment.HandFragment;
import com.dong.fragment.InaccountFragment;
import com.dong.fragment.OutaccountFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private ImageView iv_inaccount, iv_outaccount, iv_flag, iv_hand;
    public List<Fragment> list;
    private Toolbar toolbar;

    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.viewpage);
        iv_inaccount = (ImageView) findViewById(R.id.iv_inaccount);
        iv_outaccount = (ImageView) findViewById(R.id.iv_outaccount);
        iv_flag = (ImageView) findViewById(R.id.iv_flag);
        iv_hand = (ImageView) findViewById(R.id.iv_hand);
        iv_inaccount.setOnClickListener(this);
        iv_outaccount.setOnClickListener(this);
        iv_flag.setOnClickListener(this);
        iv_hand.setOnClickListener(this);
        InaccountFragment inaccountFragment = new InaccountFragment();
        OutaccountFragment outaccountFragment = new OutaccountFragment();
        FlagFragment flagFragment = new FlagFragment();
        HandFragment handFragment = new HandFragment();
        list = new ArrayList<Fragment>();
        list.add(inaccountFragment);
        list.add(outaccountFragment);
        list.add(flagFragment);
        list.add(handFragment);
        selectitme(0);
        toolbar.setTitle("我的收入");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        viewPager.setOffscreenPageLimit(4);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                changbottom(position);
                //android.support.v7.app.ActionBar actionBar=getSupportActionBar();

                switch (position) {
                    case 0:
                        toolbar.setTitle("我的Money");
                        break;
                    case 1:
                        toolbar.setTitle("我的支出");
                        break;
                    case 2:
                        toolbar.setTitle("我的便签");
                        break;
                    case 3:
                        toolbar.setTitle("保险箱");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void resetbottom(int position) {
        iv_inaccount.setImageResource(R.drawable.inaccount);
        iv_outaccount.setImageResource(R.drawable.outaccount);
        iv_flag.setImageResource(R.drawable.flag);
        iv_hand.setImageResource(R.drawable.hand);
    }

    public void changbottom(int position) {
        resetbottom(position);
        switch (position) {
            case 0:
                iv_inaccount.setImageResource(R.drawable.ck_inaccount);
                break;
            case 1:
                iv_outaccount.setImageResource(R.drawable.ck_outaccount);
                break;
            case 2:
                iv_flag.setImageResource(R.drawable.ck_flag);
                break;
            case 3:
                iv_hand.setImageResource(R.drawable.ck_hand);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent  intent=new Intent();
        int id = item.getItemId();
        switch (id) {
            case R.id.action_addInaccount:

                intent.setClass(MainActivity.this,AddInaccountActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.action_addOutaccount:
                intent.setClass(MainActivity.this,AddOutaccountActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.action_addFlag:
                intent.setClass(MainActivity.this,NewFlagActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case  R.id.action_settings:
                selectitme(3);
                break;
            case R.id.action_back:
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void selectitme(int position) {

        viewPager.setCurrentItem(position);
        changbottom(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_inaccount:
                selectitme(0);
                break;
            case R.id.iv_outaccount:
                selectitme(1);
                break;
            case R.id.iv_flag:
                selectitme(2);
                break;
            case R.id.iv_hand:
                selectitme(3);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            moveTaskToBack(false);
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
