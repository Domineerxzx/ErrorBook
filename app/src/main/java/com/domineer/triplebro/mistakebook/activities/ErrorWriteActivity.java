package com.domineer.triplebro.mistakebook.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.adapters.ErrorWriteAdapter;
import com.domineer.triplebro.mistakebook.controllers.AdminManagerController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorWriteActivity extends Activity implements View.OnClickListener {

    private ListView lv_error_write;
    private ImageView iv_close_error_write;
    private AdminManagerController adminManagerController;
    private Map<Integer, Integer> userInfoIdMap;
    private ErrorWriteAdapter errorWriteAdapter;
    private List<Integer> userIdList;
    private List<Integer> countList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_write);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_error_write = (ImageView) findViewById(R.id.iv_close_error_write);
        lv_error_write = (ListView) findViewById(R.id.lv_error_write);
    }

    private void initData() {
        adminManagerController = new AdminManagerController(this);
        userInfoIdMap = adminManagerController.getUserInfoList();
        userIdList = new ArrayList<>();
        countList = new ArrayList<>();
        for (Integer user_id : userInfoIdMap.keySet()) {
            userIdList.add(user_id);
            countList.add(userInfoIdMap.get(user_id));
        }
        errorWriteAdapter = new ErrorWriteAdapter(this, userIdList,countList);
        lv_error_write.setAdapter(errorWriteAdapter);
    }

    private void setOnClickListener() {
        iv_close_error_write.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_error_write:
                finish();
                break;
        }
    }
}
