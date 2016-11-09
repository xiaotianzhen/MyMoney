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

import com.dong.bean.InAccount;
import com.dong.dao.InAccountDao;
import com.dong.mymoney.InaccountHandlerActivity;
import com.dong.mymoney.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InaccountFragment extends Fragment {

    private ListView listView;
    private List<InAccount> list;

    private FragmentActivity mfragment;

    public InaccountFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inaccount, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mfragment = getActivity();
        listView = (ListView) view.findViewById(R.id.listview);
        final InAccountDao inAccountDao = new InAccountDao(mfragment);
        Cursor cursor = inAccountDao.selectALLInAccount();
        InAccount inAccount = new InAccount();
        list = new ArrayList<InAccount>();
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            float money = cursor.getFloat(cursor.getColumnIndex("money"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String mark = cursor.getString(cursor.getColumnIndex("mark"));
            String handler = cursor.getString(cursor.getColumnIndex("handler"));
            inAccount = new InAccount(_id, money, time, type, handler, mark);
            list.add(inAccount);
        }
        listView.setAdapter(new SimpleCursorAdapter(mfragment, R.layout.infoaccount_item,
                cursor, new String[]{"_id", "type", "money", "time"}, new int[]{R.id.it_id, R.id.it_type, R.id.it_money, R.id.it_time}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int _id = list.get(position).get_id();
                String type = list.get(position).getType();
                Log.i("520", "**********" + _id);
                Intent intent = new Intent();
                intent.putExtra("id", _id);
                intent.putExtra("type", type);
                intent.setClass(mfragment, InaccountHandlerActivity.class);
                startActivity(intent);
                mfragment.finish();


            }
        });
    }

}
