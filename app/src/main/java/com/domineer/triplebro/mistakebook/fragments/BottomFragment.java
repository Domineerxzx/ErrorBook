package com.domineer.triplebro.mistakebook.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.domineer.triplebro.mistakebook.R;

public class BottomFragment extends Fragment implements View.OnClickListener {

    private View fragment_bottom;
    private LinearLayout ll_home;
    private LinearLayout ll_type;
    private LinearLayout ll_class;
    private LinearLayout ll_myself;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Button bt_home;
    private Button bt_type;
    private Button bt_class;
    private Button bt_myself;
    private TextView tv_home;
    private TextView tv_type;
    private TextView tv_class;
    private TextView tv_myself;

    private Button lastFunctionButton;
    private TextView lastFunctionTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_bottom = inflater.inflate(R.layout.fragment_bottom, null);
        initView();
        setOnClick();
        fragmentManager = getActivity().getFragmentManager();
        return fragment_bottom;
    }

    private void initView() {
        ll_home = fragment_bottom.findViewById(R.id.ll_home);
        ll_type = fragment_bottom.findViewById(R.id.ll_type);
        ll_class = fragment_bottom.findViewById(R.id.ll_class);
        ll_myself = fragment_bottom.findViewById(R.id.ll_myself);
        bt_home = fragment_bottom.findViewById(R.id.bt_home);
        bt_type = fragment_bottom.findViewById(R.id.bt_type);
        bt_class = fragment_bottom.findViewById(R.id.bt_class);
        bt_myself = fragment_bottom.findViewById(R.id.bt_myself);
        tv_home = fragment_bottom.findViewById(R.id.tv_home);
        tv_type = fragment_bottom.findViewById(R.id.tv_type);
        tv_class = fragment_bottom.findViewById(R.id.tv_class);
        tv_myself = fragment_bottom.findViewById(R.id.tv_myself);

        if (lastFunctionTextView == null) {
            lastFunctionTextView = tv_home;
        }
        if (lastFunctionButton == null) {
            lastFunctionButton = bt_home;
        }

    }

    public void setOnClick() {

        ll_home.setOnClickListener(this);
        ll_type.setOnClickListener(this);
        ll_class.setOnClickListener(this);
        ll_myself.setOnClickListener(this);
        bt_home.setOnClickListener(this);
        bt_type.setOnClickListener(this);
        bt_class.setOnClickListener(this);
        bt_myself.setOnClickListener(this);
        tv_home.setOnClickListener(this);
        tv_type.setOnClickListener(this);
        tv_class.setOnClickListener(this);
        tv_myself.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
            case R.id.bt_home:
            case R.id.tv_home:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new HomeFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_home);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_home.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_home;
                break;
            case R.id.ll_type:
            case R.id.bt_type:
            case R.id.tv_type:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new TypeFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_type);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_type.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_type;
                break;
            case R.id.ll_class:
            case R.id.bt_class:
            case R.id.tv_class:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new ClassFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_class);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_class.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_class;
                break;
            case R.id.ll_myself:
            case R.id.bt_myself:
            case R.id.tv_myself:
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fl_content, new MyselfFragment());
                transaction.commit();
                changeImageForButton(lastFunctionButton, bt_myself);
                lastFunctionTextView.setTextColor(Color.GRAY);
                tv_myself.setTextColor(Color.BLACK);
                lastFunctionTextView = tv_myself;
                break;
        }
    }

    private void changeImageForButton(Button lastFunctionButton, Button onClickButton) {
        switch (lastFunctionButton.getId()) {
            case R.id.bt_home:
                lastFunctionButton.setBackgroundResource(R.mipmap.home_unclick);
                break;
            case R.id.bt_type:
                lastFunctionButton.setBackgroundResource(R.mipmap.type_unclick);
                break;
            case R.id.bt_class:
                lastFunctionButton.setBackgroundResource(R.mipmap.class_unclick);
                break;
            case R.id.bt_myself:
                lastFunctionButton.setBackgroundResource(R.mipmap.myself_unclick);
                break;
        }
        switch (onClickButton.getId()) {
            case R.id.bt_home:
                onClickButton.setBackgroundResource(R.mipmap.home_click);
                break;
            case R.id.bt_type:
                onClickButton.setBackgroundResource(R.mipmap.type_click);
                break;
            case R.id.bt_class:
                onClickButton.setBackgroundResource(R.mipmap.class_click);
                break;
            case R.id.bt_myself:
                onClickButton.setBackgroundResource(R.mipmap.myself_click);
                break;
        }
    this.lastFunctionButton =onClickButton;
    }

}
