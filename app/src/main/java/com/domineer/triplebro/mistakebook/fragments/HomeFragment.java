package com.domineer.triplebro.mistakebook.fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.adapters.SubmitAdapter;
import com.domineer.triplebro.mistakebook.controllers.HomeController;
import com.domineer.triplebro.mistakebook.interfaces.OnItemClickListener;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.properties.ProjectProperties;
import com.domineer.triplebro.mistakebook.utils.RealPathFromUriUtils;
import com.domineer.triplebro.mistakebook.utils.dialogUtils.ChooseUserHeadDialogUtil;
import com.domineer.triplebro.mistakebook.utils.dialogUtils.SingleChooseDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements View.OnClickListener, OnItemClickListener {

    private View fragment_home;
    private EditText et_answer_content_title;
    private EditText et_error_content_title;
    private TextView tv_submit_error;
    private TextView tv_submit_true_answer;
    private TextView tv_submit;
    private TextView tv_submit_answer;
    private RelativeLayout rl_error;
    private RelativeLayout rl_answer;
    private RelativeLayout rl_choose_button;
    private RecyclerView rv_submit;
    private RecyclerView rv_submit_answer;
    private ImageView iv_close_error;
    private ImageView iv_close_answer;
    private TextView tv_error_content_type_content;
    private TextView tv_answer_content_type_content;
    private HomeController homeController;
    private List<ErrorInfo> errorInfoList;
    private SubmitAdapter submitErrorAdapter;
    private SubmitAdapter submitAnswerAdapter;
    private SharedPreferences userInfo;
    private int user_id;
    private String phone_number;
    private long timeStamp;
    private Map<String, Integer> typeInfoMap;
    private Map<String, Integer> errorInfoMap;
    private SingleChooseDialog singleChooseDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_home = inflater.inflate(R.layout.fragment_home, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_home;
    }

    private void initView() {
        et_answer_content_title = (EditText) fragment_home.findViewById(R.id.et_answer_content_title);
        et_error_content_title = (EditText) fragment_home.findViewById(R.id.et_error_content_title);
        tv_submit_error = (TextView) fragment_home.findViewById(R.id.tv_submit_error);
        tv_submit_true_answer = (TextView) fragment_home.findViewById(R.id.tv_submit_true_answer);
        tv_submit = (TextView) fragment_home.findViewById(R.id.tv_submit);
        tv_submit_answer = (TextView) fragment_home.findViewById(R.id.tv_submit_answer);
        tv_error_content_type_content = (TextView) fragment_home.findViewById(R.id.tv_error_content_type_content);
        tv_answer_content_type_content = (TextView) fragment_home.findViewById(R.id.tv_answer_content_type_content);
        rl_error = (RelativeLayout) fragment_home.findViewById(R.id.rl_error);
        rl_answer = (RelativeLayout) fragment_home.findViewById(R.id.rl_answer);
        rl_choose_button = (RelativeLayout) fragment_home.findViewById(R.id.rl_choose_button);
        rv_submit = (RecyclerView) fragment_home.findViewById(R.id.rv_submit);
        rv_submit_answer = (RecyclerView) fragment_home.findViewById(R.id.rv_submit_answer);
        rv_submit.setLayoutManager(new GridLayoutManager(getActivity(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rv_submit_answer.setLayoutManager(new GridLayoutManager(getActivity(), 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        iv_close_error = (ImageView) fragment_home.findViewById(R.id.iv_close_error);
        iv_close_answer = (ImageView) fragment_home.findViewById(R.id.iv_close_answer);
    }

    private void initData() {
        userInfo = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", 0);
        homeController = new HomeController(getActivity());
        errorInfoList = homeController.getAllErrorInfo();
        submitErrorAdapter = new SubmitAdapter(getActivity(), new ArrayList<String>());
        submitAnswerAdapter = new SubmitAdapter(getActivity(), new ArrayList<String>());
        rv_submit.setAdapter(submitErrorAdapter);
        rv_submit_answer.setAdapter(submitAnswerAdapter);
        typeInfoMap = homeController.getTypeInfoMap();
        errorInfoMap = homeController.getErrorInfoMap(user_id);
    }

    private void setOnClickListener() {
        tv_submit.setOnClickListener(this);
        tv_submit_answer.setOnClickListener(this);
        tv_submit_error.setOnClickListener(this);
        tv_submit_true_answer.setOnClickListener(this);
        tv_answer_content_type_content.setOnClickListener(this);
        tv_error_content_type_content.setOnClickListener(this);
        iv_close_error.setOnClickListener(this);
        iv_close_answer.setOnClickListener(this);
        submitErrorAdapter.setOnItemClickListener(this);
        submitAnswerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                phone_number = userInfo.getString("phone_number", "");
                timeStamp = System.currentTimeMillis();
                ChooseUserHeadDialogUtil.showSelectSubmitDialog(HomeFragment.this, phone_number, timeStamp);
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_error:
            case R.id.iv_close_answer:
                rl_choose_button.setVisibility(View.VISIBLE);
                rl_error.setVisibility(View.GONE);
                rl_answer.setVisibility(View.GONE);
                break;
            case R.id.tv_submit:
                String errorContentTitle = et_error_content_title.getText().toString().trim();
                String errorContentType = tv_error_content_type_content.getText().toString().trim();
                int typeId = typeInfoMap.get(errorContentType);
                if (errorContentTitle.length() == 0) {
                    Toast.makeText(getActivity(), "错题题目不能为空！！！", Toast.LENGTH_SHORT).show();
                } else if (errorContentType.length() == 0) {
                    Toast.makeText(getActivity(), "请选择错题科目！！！", Toast.LENGTH_SHORT).show();
                }
                int errorInfoId = homeController.addErrorInfo(errorContentTitle, typeId, user_id);
                homeController.addErrorImageInfo(errorInfoId,submitErrorAdapter.getData());
                Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                et_error_content_title.setText("");
                tv_error_content_type_content.setText("");
                submitErrorAdapter.setData(new ArrayList<String>());
                break;
            case R.id.tv_submit_answer:
                String answerContentTitle = et_answer_content_title.getText().toString().trim();
                String answerContentType = tv_answer_content_type_content.getText().toString().trim();
                int errorId = errorInfoMap.get(answerContentType);
                if (answerContentTitle.length() == 0) {
                    Toast.makeText(getActivity(), "正确答案不能为空！！！", Toast.LENGTH_SHORT).show();
                } else if (answerContentType.length() == 0) {
                    Toast.makeText(getActivity(), "请选择错题题目！！！", Toast.LENGTH_SHORT).show();
                }
                int answerInfoId = homeController.addAnswerInfo(answerContentTitle, errorId, user_id);
                homeController.addAnswerImageInfo(answerInfoId,submitAnswerAdapter.getData());
                Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                et_answer_content_title.setText("");
                tv_answer_content_type_content.setText("");
                submitAnswerAdapter.setData(new ArrayList<String>());
                break;
            case R.id.tv_answer_content_type_content:
                errorInfoMap = homeController.getErrorInfoMap(user_id);
                singleChooseDialog = new SingleChooseDialog();
                final String[] errorTitles = new String[errorInfoMap.size()];
                int j = 0;
                for (String errorTitle : errorInfoMap.keySet()) {
                    errorTitles[j] = errorTitle;
                    j++;
                }
                singleChooseDialog.show("错题题目", errorTitles, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        tv_answer_content_type_content.setText(errorTitles[which]);
                    }
                },getFragmentManager());
                break;
            case R.id.tv_error_content_type_content:
                singleChooseDialog = new SingleChooseDialog();
                final String[] typeNames = new String[typeInfoMap.size()];
                int i = 0;
                for (String typeName : typeInfoMap.keySet()) {
                    typeNames[i] = typeName;
                    i++;
                }
                singleChooseDialog.show("错题科目", typeNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        tv_error_content_type_content.setText(typeNames[which]);
                    }
                },getFragmentManager());
                break;
            case R.id.tv_submit_error:
                rl_choose_button.setVisibility(View.GONE);
                rl_error.setVisibility(View.VISIBLE);
                rl_answer.setVisibility(View.GONE);
                submitErrorAdapter.setData(new ArrayList<String>());
                submitAnswerAdapter.setData(new ArrayList<String>());
                break;
            case R.id.tv_submit_true_answer:
                rl_choose_button.setVisibility(View.GONE);
                rl_error.setVisibility(View.GONE);
                rl_answer.setVisibility(View.VISIBLE);
                submitErrorAdapter.setData(new ArrayList<String>());
                submitAnswerAdapter.setData(new ArrayList<String>());
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        userInfo = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        user_id = userInfo.getInt("user_id", 0);
        phone_number = userInfo.getString("phone_number", "");
        timeStamp = System.currentTimeMillis();
        ChooseUserHeadDialogUtil.showSelectSubmitDialog(this, phone_number, timeStamp);
    }

    @Override
    public void onItemLongClick(View view) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean isCheck = true;
        String s = "";
        switch (requestCode) {
            case ProjectProperties.FROM_GALLERY:
                if (resultCode == RESULT_OK) {
                    s = RealPathFromUriUtils.getRealPathFromUri(getActivity(), data.getData());
                } else {
                    isCheck = false;
                }
                break;
            case ProjectProperties.FROM_CAMERA:
                if (resultCode == RESULT_OK) {
                    s = getActivity().getFilesDir() + File.separator + "images" + File.separator + phone_number + timeStamp + ".jpg";
                } else {
                    isCheck = false;
                }
                break;
            default:
                break;
        }
        if (isCheck) {
            List<String> answerAdapterData = submitAnswerAdapter.getData();
            if (answerAdapterData.size() == 0) {
                answerAdapterData.add(s);
            } else {
                answerAdapterData.remove(answerAdapterData.size() - 1);
                answerAdapterData.add(s);
            }
            if (answerAdapterData.size() != 9) {
                answerAdapterData.add("");
            }
            submitAnswerAdapter.setData(answerAdapterData);
            List<String> errorAdapterData = submitErrorAdapter.getData();
            if (errorAdapterData.size() == 0) {
                errorAdapterData.add(s);
            } else {
                errorAdapterData.remove(errorAdapterData.size() - 1);
                errorAdapterData.add(s);
            }
            if (errorAdapterData.size() != 9) {
                errorAdapterData.add("");
            }
            submitErrorAdapter.setData(errorAdapterData);
        } else {
            Toast.makeText(getActivity(), "取消选择", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
