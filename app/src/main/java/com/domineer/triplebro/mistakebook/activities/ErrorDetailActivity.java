package com.domineer.triplebro.mistakebook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.adapters.PhotoWallAdapter;
import com.domineer.triplebro.mistakebook.controllers.ErrorListController;
import com.domineer.triplebro.mistakebook.interfaces.OnItemClickListener;
import com.domineer.triplebro.mistakebook.models.AnswerInfo;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;

import java.util.List;

public class ErrorDetailActivity extends Activity implements OnItemClickListener, View.OnClickListener {

    private TextView tv_error_title;
    private TextView tv_error_title_id;
    private TextView tv_answer_title;
    private RecyclerView rv_error_list;
    private RecyclerView rv_answer_list;
    private ErrorInfo errorInfo;
    private ErrorListController errorListController;
    private List<String> errorImageInfoList;
    private PhotoWallAdapter errorPhotoWallAdapter;
    private AnswerInfo answerInfo;
    private List<String> answerImageInfoList;
    private PhotoWallAdapter answerPhotoWallAdapter;
    private ImageView iv_image_large;
    private ImageView iv_close_image_large;
    private RelativeLayout rl_image_large;
    private ImageView iv_close_error_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_detail);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
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
                Glide.with(ErrorDetailActivity.this).load(answerImageInfoList.get(position)).into(iv_image_large);
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
