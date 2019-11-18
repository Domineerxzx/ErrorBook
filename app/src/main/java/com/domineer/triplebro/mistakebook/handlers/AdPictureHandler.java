package com.domineer.triplebro.mistakebook.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.domineer.triplebro.mistakebook.activities.MainActivity;
import com.domineer.triplebro.mistakebook.activities.SplashActivity;
import com.domineer.triplebro.mistakebook.controllers.DataInitController;
import com.domineer.triplebro.mistakebook.properties.ProjectProperties;

public class AdPictureHandler extends Handler {

    private Context context;
    private ImageView iv_ad;
    private DataInitController dataInitController;

    public AdPictureHandler(Context context, ImageView iv_ad) {
        this.context = context;
        this.iv_ad = iv_ad;
    }

    public DataInitController getDataInitController() {
        return dataInitController;
    }

    public void setDataInitController(DataInitController dataInitController) {
        this.dataInitController = dataInitController;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case ProjectProperties.AD_PICTURE:
                String adPicture = (String) msg.obj;
                Glide.with(context).load(adPicture).into(iv_ad);
                break;
            case ProjectProperties.SKIP:
                if(dataInitController == null){
                    break;
                }else{
                    context.unbindService(dataInitController);
                    Intent main = new Intent(context, MainActivity.class);
                    context.startActivity(main);
                    ((SplashActivity) context).finish();
                    break;
                }

        }
    }
}
