package com.dong.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.dong.bean.OutAccount;
import com.dong.dao.OutAccountDao;
import com.dong.mymoney.OutaccountHandlerActivity;
import com.dong.mymoney.R;
import com.dong.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutaccountFragment extends Fragment {

    private ListView listView;
    private List<OutAccount> list;
    private FragmentActivity mfragment;
    public OutaccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outaccount, container, false);
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mfragment=getActivity();
        listView = (ListView)view.findViewById(R.id.listview);
        DBHelper dbHelper=new DBHelper(mfragment);
        final OutAccountDao outAccountDao = new OutAccountDao(dbHelper);
        Cursor cursor = outAccountDao.selectALLOutAccount();
        OutAccount outAccount =new OutAccount();
        list = new ArrayList<OutAccount>();
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String mark = cursor.getString(cursor.getColumnIndex("mark"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            outAccount = new OutAccount(_id, money, time, type, address, mark);
            list.add(outAccount);
        }
        listView.setAdapter(new SimpleCursorAdapter(mfragment, R.layout.infoaccount_item,
                cursor, new String[]{"_id", "type", "money", "time"}, new int[]{R.id.it_id, R.id.it_type, R.id.it_money, R.id.it_time}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int _id = list.get(position).get_id();
                String type = list.get(position).getType();
                Log.i("520out", "**********" + _id);
                Intent intent = new Intent();
                intent.putExtra("id", _id);
                intent.putExtra("type", type);
                intent.setClass(mfragment, OutaccountHandlerActivity.class);
                startActivity(intent);
                mfragment.finish();


            }
        });
    }
}
