package com.domineer.triplebro.mistakebook.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.adapters.ClassAdapter;
import com.domineer.triplebro.mistakebook.adapters.ErrorAdapter;
import com.domineer.triplebro.mistakebook.adapters.ErrorListAdapter;
import com.domineer.triplebro.mistakebook.controllers.AdminManagerController;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.views.MyListView;

import java.util.List;

public class ErrorActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_error_list;
    private ListView lv_error_list;
    private RelativeLayout rl_image_large;
    private ImageView iv_image_large;
    private ImageView iv_close_image_large;
    private AdminManagerController adminManagerController;
    List<ErrorInfo> errorInfoList;
    private ErrorAdapter errorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_error_list = (ImageView) findViewById(R.id.iv_close_error_list);
        lv_error_list = (ListView) findViewById(R.id.lv_error_list);
        rl_image_large = (RelativeLayout) findViewById(R.id.rl_image_large);
        iv_image_large = (ImageView) findViewById(R.id.iv_image_large);
        iv_close_image_large = (ImageView) findViewById(R.id.iv_close_image_large);
    }

    private void initData() {
        adminManagerController = new AdminManagerController(this);
        errorInfoList = adminManagerController.getErrorList();
        errorAdapter = new ErrorAdapter(this,errorInfoList);
        errorAdapter.setViewInfo(rl_image_large,iv_image_large,iv_close_image_large);
        lv_error_list.setAdapter(errorAdapter);
    }

    private void setOnClickListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
