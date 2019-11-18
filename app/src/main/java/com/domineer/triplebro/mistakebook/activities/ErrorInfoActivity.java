package com.domineer.triplebro.mistakebook.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.adapters.PhotoWallAdapter;
import com.domineer.triplebro.mistakebook.controllers.ErrorListController;
import com.domineer.triplebro.mistakebook.interfaces.OnItemClickListener;
import com.domineer.triplebro.mistakebook.models.AnswerInfo;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;

import java.util.List;

public class ErrorInfoActivity extends Activity implements OnItemClickListener, View.OnClickListener {

    private TextView tv_error_title;
    private TextView tv_error_title_id;
    private TextView tv_answer_title;
    private RecyclerView rv_error_list;
    private RecyclerView rv_answer_list;
    private ErrorInfo errorInfo;
    private ErrorListController errorListController;
    private SharedPreferences userInfo;
    private int user_id;
    private List<String> errorImageInfoList;
    private PhotoWallAdapter errorPhotoWallAdapter;
    private AnswerInfo answerInfo;
    private List<String> answerImageInfoList;
    private PhotoWallAdapter answerPhotoWallAdapter;
    private ImageView iv_image_large;
    private ImageView iv_close_image_large;
    private RelativeLayout rl_image_large;
    private ImageView iv_close_error_info;
    private boolean isCollect;
    private ImageView iv_collect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_info);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_collect = (ImageView) findViewById(R.id.iv_collect);
        tv_error_title = (TextView) findViewById(R.id.tv_error_title);
        tv_error_title_id = (TextView) findViewById(R.id.tv_error_title_id);
        tv_answer_title = (TextView) findViewById(R.id.tv_answer_title);
        rv_error_list = (RecyclerView) findViewById(R.id.rv_error_list);
        rv_answer_list = (RecyclerView) findViewById(R.id.rv_answer_list);
        rv_error_list.setLayoutManager(new GridLayoutManager(this,3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rv_answer_list.setLayoutManager(new GridLayoutManager(this,3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        iv_image_large = (ImageView) findViewById(R.id.iv_image_large);
        iv_close_image_large = (ImageView) findViewById(R.id.iv_close_image_large);
        rl_image_large = (RelativeLayout) findViewById(R.id.rl_image_large);
        iv_close_error_info = (ImageView) findViewById(R.id.iv_close_error_Info);
    }

    private void initData() {
        Intent intent = getIntent();
        errorInfo = (ErrorInfo) intent.getSerializableExtra("errorInfo");
        errorListController = new ErrorListController(this);
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        errorImageInfoList = errorListController.getErrorImageInfoList(errorInfo.get_id());
        errorPhotoWallAdapter = new PhotoWallAdapter(this, errorImageInfoList);
        rv_error_list.setAdapter(errorPhotoWallAdapter);
        answerInfo = errorListController.getAnswerInfo(errorInfo.get_id());
        answerImageInfoList = errorListController.getAnswerImageInfo(answerInfo.get_id());
        answerPhotoWallAdapter = new PhotoWallAdapter(this, answerImageInfoList);
        rv_answer_list.setAdapter(answerPhotoWallAdapter);
        tv_error_title.setText(errorInfo.getErrorTitle());
        tv_error_title_id.setText(String.valueOf(errorInfo.get_id()));
        tv_answer_title.setText(answerInfo.getAnswerTitle());
        isCollect = errorListController.getIsCollect(errorInfo.get_id(), user_id);
        if(isCollect){
            iv_collect.setBackgroundResource(R.mipmap.collection_click);
        }else{
            iv_collect.setBackgroundResource(R.mipmap.collection);
        }
        iv_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_id == -1){
                    Toast.makeText(ErrorInfoActivity.this, "还没登录呢，快去登录吧！！！", Toast.LENGTH_SHORT).show();
                }
                if(isCollect){
                    iv_collect.setBackgroundResource(R.mipmap.collection);
                    errorListController.deleteCollect(errorInfo.get_id(),user_id);
                    isCollect = false;
                }else{
                    iv_collect.setBackgroundResource(R.mipmap.collection_click);
                    errorListController.addCollect(errorInfo.get_id(),user_id);
                    isCollect = true;
                }
            }
        });
    }

    private void setOnClickListener() {
        errorPhotoWallAdapter.setOnItemClickListener(this);
        answerPhotoWallAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(answerImageInfoList.get(position).length() == 0){
                    return;
                }
                rl_image_large.setVisibility(View.VISIBLE);
                Glide.with(ErrorInfoActivity.this).load(answerImageInfoList.get(position)).into(iv_image_large);
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
        iv_close_image_large.setOnClickListener(this);
        iv_close_error_info.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        if(errorImageInfoList.get(position).length() == 0){
            return;
        }
        rl_image_large.setVisibility(View.VISIBLE);
        Glide.with(this).load(errorImageInfoList.get(position)).into(iv_image_large);

    }

    @Override
    public void onItemLongClick(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_error_Info:
                finish();
                break;
            case R.id.iv_close_image_large:
                rl_image_large.setVisibility(View.GONE);
                break;
        }
    }
}
