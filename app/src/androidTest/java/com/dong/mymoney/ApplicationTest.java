package com.dong.mymoney;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.dong.bean.Flag;
import com.dong.bean.InAccount;
import com.dong.bean.OutAccount;
import com.dong.bean.Password;
import com.dong.dao.FlagDao;
import com.dong.dao.InAccountDao;
import com.dong.dao.OutAccountDao;
import com.dong.dao.PasswordDao;
import com.dong.utils.DBHelper;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    public void testCreateaccount() {
        DBHelper dbHelper = new DBHelper(getContext());
        dbHelper.getWritableDatabase();

    }

    public void testAddOutAccount() {
        DBHelper dbHelper = new DBHelper(getContext());
        OutAccountDao outAccountDao = new OutAccountDao(dbHelper);
        OutAccount outAccount = new OutAccount();
        outAccount.setAddress("广州");
        outAccount.setMoney(50);
        outAccount.setTime("2016-03-10");
        outAccount.setMark("我的生日");
        outAccount.setType("吃喝");
        outAccountDao.addOutAccount(outAccount);
    }

    public void testDeleteOutAccount() {
        DBHelper dbHelper = new DBHelper(getContext());
        OutAccountDao outAccountDao = new OutAccountDao(dbHelper);
        outAccountDao.deleteOutAccount(1);
    }

    public void testUpdateOutAccount() {
        DBHelper dbHelper = new DBHelper(getContext());
        OutAccountDao outAccountDao = new OutAccountDao(dbHelper);
        OutAccount outAccount = new OutAccount();
        outAccount.set_id(1);
        outAccount.setTime("2016年03年10");
        outAccount.setAddress("广州");
        outAccount.setMoney(100);
        outAccount.setMark("我的生日");
        outAccount.setType("吃喝玩勒");
        outAccountDao.updateOutAccount(outAccount);

    }

    public void testSelectFromIdOutAccouont() {
        DBHelper dbHelper = new DBHelper(getContext());
        OutAccountDao outAccountDao = new OutAccountDao(dbHelper);
        OutAccount outaccount = outAccountDao.selectFromId(1);

            Log.i("520it", "*************" + outaccount);

    }
    public  void testSelectAllOutAccount(){

    }
    public void testAddInAccount() {

        InAccountDao inAccountDao = new InAccountDao(getContext());
        InAccount inaccount = new InAccount();
        inaccount.setHandler("我");
        inaccount.setMoney(200);
        inaccount.setTime("2016-03-10");
        inaccount.setMark("外快");
        inaccount.setType("工资");
        inAccountDao.addInAccount(inaccount);
    }

    public void testDeleteInAccount() {

        InAccountDao inAccountDao = new InAccountDao(getContext());
        inAccountDao.deleteInAccount(1);
    }

    public void testUpdateInAccount() {
        InAccountDao inAccountDao = new InAccountDao(getContext());
        InAccount inAccount = new InAccount();
        inAccount.set_id(5);
        inAccount.setHandler("父母");
        inAccount.setMoney(1000);
        inAccount.setTime("2016-03-10");
        inAccount.setMark("生活费");
        inAccount.setType("亲情");
        inAccountDao.updateInAccount(inAccount);

    }

    public void testSelectFromIdInAccount() {
        InAccountDao inAccountDao = new InAccountDao(getContext());
        InAccount inAccount = inAccountDao.selectFromId(1);

        Log.i("520it", "*************" + inAccount);

    }


    public  void testAddFlag(){
        FlagDao flagDao=new FlagDao(getContext());
        Flag flag=new Flag();
        flag.set_id(1);
        flag.setFlag("akdjnakjgkajng");
        flagDao.addFlag(flag);
    }
    public  void testAddPassword(){
        PasswordDao passwordDao=new PasswordDao(getContext());
        Password password=new Password();
        password.setPassword("123456");
        passwordDao.addPassword(password);
    }
}