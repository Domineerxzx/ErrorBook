package com.domineer.triplebro.mistakebook.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.adapters.ClassAdapter;
import com.domineer.triplebro.mistakebook.controllers.ClassController;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.models.UserInfo;
import com.domineer.triplebro.mistakebook.utils.dialogUtils.ChooseClassDialogUtils;
import com.domineer.triplebro.mistakebook.views.MyListView;

import java.util.List;

public class ClassFragment extends Fragment implements View.OnClickListener {

    private View fragment_class;
    private SharedPreferences userInfo;
    private int user_id;
    private ClassController classController;
    private UserInfo user;
    private TextView tv_title;
    private MyListView lv_error_list;
    private RelativeLayout rl_image_large;
    private ImageView iv_image_large;
    private ImageView iv_close_image_large;
    private String classNumber;
    private String gradeNumber;
    private List<ErrorInfo> errorInfoList;
    private ClassAdapter classAdapter;
    private ImageView iv_setting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_class = inflater.inflate(R.layout.fragment_class, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_class;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initView() {
        tv_title = (TextView) fragment_class.findViewById(R.id.tv_title);
        lv_error_list = (MyListView) fragment_class.findViewById(R.id.lv_error_list);
        rl_image_large = (RelativeLayout) fragment_class.findViewById(R.id.rl_image_large);
        iv_image_large = (ImageView) fragment_class.findViewById(R.id.iv_image_large);
        iv_close_image_large = (ImageView) fragment_class.findViewById(R.id.iv_close_image_large);
        iv_setting = (ImageView) fragment_class.findViewById(R.id.iv_setting);
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", -1);
        if (user_id == -1) {
            Toast.makeText(getActivity(), "还没登录呢，快去登录吧", Toast.LENGTH_SHORT).show();
            return;
        }
        classController = new ClassController(getActivity());
        user = classController.getUserInfo(user_id);
        classNumber = user.getClassNumber();
        gradeNumber = user.getGradeNumber();
        if (classNumber != null && classNumber.length() != 0 && gradeNumber != null && gradeNumber.length() != 0) {
            tv_title.setText(gradeNumber + classNumber);
        } else {
            ChooseClassDialogUtils.showDialog(this, user_id,tv_title);
        }
        errorInfoList = classController.getErrorListByClass(classNumber,gradeNumber);
        classAdapter = new ClassAdapter(getActivity(), errorInfoList);
        classAdapter.setViewInfo(rl_image_large,iv_image_large,iv_close_image_large);
        lv_error_list.setAdapter(classAdapter);
    }

    private void setOnClickListener() {
        iv_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_setting:
                ChooseClassDialogUtils.showDialog(this, user_id, tv_title);
                break;
        }
    }
}
