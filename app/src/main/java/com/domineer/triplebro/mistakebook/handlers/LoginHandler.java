package com.domineer.triplebro.mistakebook.handlers;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;

import com.domineer.triplebro.mistakebook.activities.AdminManagerActivity;
import com.domineer.triplebro.mistakebook.activities.LoginActivity;
import com.domineer.triplebro.mistakebook.properties.ProjectProperties;


public class LoginHandler extends Handler {

    private Context context;
    private ServiceConnection serviceConnection;

    public LoginHandler(Context context, ServiceConnection serviceConnection) {
        this.context = context;
        this.serviceConnection = serviceConnection;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case ProjectProperties.LOGIN_SUCCESS:
                context.unbindService(serviceConnection);
                ((LoginActivity)context).finish();
                break;
            case ProjectProperties.LOGIN_FAILED:
                context.unbindService(serviceConnection);
                break;
            case ProjectProperties.LOGIN_ADMIN_SUCCESS:
                context.unbindService(serviceConnection);
                Intent adminManagerActivity = new Intent(context, AdminManagerActivity.class);
                context.startActivity(adminManagerActivity);
                ((LoginActivity)context).finish();
                break;
            case ProjectProperties.LOGIN_ADMIN_FAILED:
                context.unbindService(serviceConnection);
                break;
        }
    }
}
