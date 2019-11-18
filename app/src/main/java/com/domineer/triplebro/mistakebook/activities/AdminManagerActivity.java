package com.domineer.triplebro.mistakebook.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.mistakebook.R;

public class AdminManagerActivity extends Activity implements View.OnClickListener {

    private TextView tv_error_write;
    private TextView tv_error;
    private TextView tv_change_answer;
    private ImageView iv_close_admin_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_error_write = (TextView) findViewById(R.id.tv_error_write);
        tv_error = (TextView) findViewById(R.id.tv_error);
        tv_change_answer = (TextView) findViewById(R.id.tv_change_answer);
        iv_close_admin_manager = (ImageView) findViewById(R.id.iv_close_admin_manager);
    }

    private void initData() {

    }

    private void setOnClickListener() {
        tv_error.setOnClickListener(this);
        tv_error_write.setOnClickListener(this);
        tv_change_answer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_admin_manager:
                finish();
                break;
            case R.id.tv_error_write:
                Intent error_write = new Intent(this, ErrorWriteActivity.class);
                startActivity(error_write);
                break;
            case R.id.tv_error:
            case R.id.tv_change_answer:
                Intent error = new Intent(this, ErrorActivity.class);
                startActivity(error);
                break;
        }
    }
}
