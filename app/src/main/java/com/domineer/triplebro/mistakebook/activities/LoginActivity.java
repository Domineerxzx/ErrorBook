package com.domineer.triplebro.mistakebook.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.controllers.LoginController;
import com.domineer.triplebro.mistakebook.properties.ProjectProperties;


public class LoginActivity extends Activity implements View.OnClickListener {

    private Button bt_create;
    private ImageView iv_close_login;
    private Button bt_login;
    private LoginController loginController;
    private EditText et_phone_number;
    private EditText et_password;
    private Button bt_admin_login;
    private Button bt_user_login;
    private int userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
        setOnClickListener();
    }

    private void initData() {
        loginController = new LoginController(this);
    }

    private void setOnClickListener() {
        bt_create.setOnClickListener(this);
        iv_close_login.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        bt_admin_login.setOnClickListener(this);
        bt_user_login.setOnClickListener(this);
    }

    private void initView() {
        bt_create = findViewById(R.id.bt_create);
        iv_close_login = findViewById(R.id.iv_close_login);
        bt_login = (Button) findViewById(R.id.bt_login);
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_admin_login = (Button) findViewById(R.id.bt_admin_login);
        bt_user_login = (Button) findViewById(R.id.bt_user_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_create:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_close_login:
                finish();
                break;
            case R.id.bt_login:
                String phone_number = et_phone_number.getText().toString();
                String password = et_password.getText().toString();
                if (phone_number.length() != 0 && password.length() != 0) {
                    loginController.login(phone_number, password, userType);
                } else {
                    Toast.makeText(this, "手机号或密码不能为空！！！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_admin_login:
                bt_admin_login.setBackgroundResource(R.drawable.shape_user_button);
                bt_user_login.setBackgroundResource(R.drawable.shape_alpha_card);
                userType = ProjectProperties.ADMIN;
                break;
            case R.id.bt_user_login:
                bt_admin_login.setBackgroundResource(R.drawable.shape_alpha_card);
                bt_user_login.setBackgroundResource(R.drawable.shape_user_button);
                userType = ProjectProperties.USER;
                break;
            default:
                break;
        }
    }
}
