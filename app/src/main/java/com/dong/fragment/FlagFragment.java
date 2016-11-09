package com.dong.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.dong.bean.Flag;
import com.dong.dao.FlagDao;
import com.dong.mymoney.FlagHanderActivity;
import com.dong.mymoney.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlagFragment extends Fragment {
    private ListView listView;
    private List<Flag> list;
    private FragmentActivity mfragment;

    public FlagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flag, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mfragment = getActivity();
        listView = (ListView) view.findViewById(R.id.listview);
        FlagDao flagDao = new FlagDao(mfragment);
        Cursor cursor = flagDao.selectAllFlag();
        list = new ArrayList<Flag>();
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String content = cursor.getString(cursor.getColumnIndex("flag"));
            Flag flag = new Flag(_id, content);
            list.add(flag);
        }
        listView.setAdapter(new SimpleCursorAdapter(mfragment, R.layout.flag_item, cursor, new String[]{"flag"}, new
                int[]{R.id.it_flag}));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int _id = list.get(position).get_id();
                Intent intent = new Intent(mfragment, FlagHanderActivity.class);
                intent.putExtra("id", _id);
                startActivity(intent);
                mfragment.finish();
            }
        });
    }

}
