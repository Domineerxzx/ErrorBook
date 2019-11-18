package com.domineer.triplebro.mistakebook.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.controllers.DataInitController;
import com.domineer.triplebro.mistakebook.handlers.AdPictureHandler;
import com.domineer.triplebro.mistakebook.services.NetworkConnectionService;


public class SplashActivity extends Activity implements View.OnClickListener {

    private TextView tv_skip;
    private ImageView iv_ad;
    private AdPictureHandler adPictureHandler;
    private DataInitController dataInitController;
    /*private AdPictureHandler adPictureHandler;
    private DataInitManager dataInitController;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        tv_skip = (TextView) findViewById(R.id.tv_skip);
        iv_ad = (ImageView) findViewById(R.id.iv_ad);
    }

    private void initData() {
        adPictureHandler = new AdPictureHandler(this, iv_ad);
        dataInitController = new DataInitController(this, adPictureHandler);
        adPictureHandler.setDataInitController(dataInitController);
        Intent intent = new Intent(this, NetworkConnectionService.class);
        startService(intent);
        dataInitController.getAdPicture();
    }

    private void setOnClickListener() {
        tv_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                adPictureHandler.setDataInitController(null);
                unbindService(dataInitController);
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
                finish();
                break;
        }
    }
}
