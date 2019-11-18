package com.domineer.triplebro.mistakebook.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.adapters.ErrorListAdapter;
import com.domineer.triplebro.mistakebook.controllers.ErrorListController;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.views.MyListView;

import java.util.List;

public class CollectActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_error_list;
    private MyListView lv_error_list;
    private RelativeLayout rl_image_large;
    private ImageView iv_image_large;
    private ImageView iv_close_image_large;
    private ErrorListController errorListController;
    private SharedPreferences userInfo;
    private int user_id;
    private ImageView iv_close_collect;
    private List<ErrorInfo> errorInfoList;
    private ErrorListAdapter errorListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_collect = (ImageView) findViewById(R.id.iv_close_collect);
        lv_error_list = (MyListView) findViewById(R.id.lv_error_list);
        rl_image_large = (RelativeLayout) findViewById(R.id.rl_image_large);
        iv_image_large = (ImageView) findViewById(R.id.iv_image_large);
        iv_close_image_large = (ImageView) findViewById(R.id.iv_close_image_large);
    }

    private void initData() {
        errorListController = new ErrorListController(this);
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        if(user_id == -1){
            Toast.makeText(this, "还没登录呢，不能查看错题列表！！！", Toast.LENGTH_SHORT).show();
            finish();
        }
        errorInfoList = errorListController.getCollectErrorListByUserId(user_id);
        errorListAdapter = new ErrorListAdapter(this, errorInfoList);
        errorListAdapter.setViewInfo(rl_image_large,iv_image_large,iv_close_image_large);
        lv_error_list.setAdapter(errorListAdapter);
    }

    private void setOnClickListener() {
        iv_close_collect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_collect:
                finish();
                break;
        }
    }
}
