package com.domineer.triplebro.mistakebook.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.fragments.BottomFragment;
import com.domineer.triplebro.mistakebook.fragments.HomeFragment;
import com.domineer.triplebro.mistakebook.utils.PermissionUtil;

public class MainActivity extends Activity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.requestPower(this, this, "android.permission.CAMERA"); //请求权限
        PermissionUtil.requestPower(this, this, "android.permission.WRITE_EXTERNAL_STORAGE");
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, new HomeFragment());
        transaction.replace(R.id.fl_bottom, new BottomFragment());
        transaction.commit();
    }
}
