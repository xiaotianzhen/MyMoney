package com.dong.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dong.bean.Password;
import com.dong.dao.PasswordDao;
import com.dong.mymoney.LoginActivity;
import com.dong.mymoney.MainActivity;
import com.dong.mymoney.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandFragment extends Fragment {
    private EditText et_password;
    private Button btn_hand, btn_back;
    private FragmentActivity mActivity;

    public HandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hand, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_password = (EditText) view.findViewById(R.id.et_password);
        btn_hand = (Button) view.findViewById(R.id.btn_hand);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        mActivity = getActivity();


        btn_hand.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            PasswordDao passwordDao = new PasswordDao(getActivity());
                                            if (passwordDao.selectExistsPassword()) {
                                                Password password = new Password();
                                                password.setPassword(et_password.getText().toString());
                                                passwordDao.updatePassword(password);
                                                Intent intent = new Intent(mActivity, LoginActivity.class);
                                                startActivity(intent);
                                                mActivity.finish();
                                            } else {
                                                Password password = new Password();
                                                password.setPassword(et_password.getText().toString());
                                                passwordDao.addPassword(password);
                                                Intent intent = new Intent(mActivity, LoginActivity.class);
                                                startActivity(intent);
                                                mActivity.finish();
                                            }
                                        }
                                    }
        );

        btn_back.setOnClickListener(new View.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(mActivity, MainActivity.class);
                                            startActivity(intent);
                                            mActivity.finish();
                                        }
                                    }

        );

    }
}

