package com.domineer.triplebro.mistakebook.utils.dialogUtils;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.adapters.ClassAdapter;
import com.domineer.triplebro.mistakebook.controllers.ClassController;
import com.domineer.triplebro.mistakebook.database.MistakeBookDataBase;
import com.domineer.triplebro.mistakebook.fragments.ClassFragment;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.models.UserInfo;
import com.domineer.triplebro.mistakebook.properties.ProjectProperties;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/18,2:09
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ChooseClassDialogUtils {

    private static ClassController classController;
    private static UserInfo user;

    public static void showDialog(final ClassFragment classFragment, final int user_id, final TextView tv_title, final ListView lv_error_list, final RelativeLayout rl_image_large, final ImageView iv_image_large, final ImageView iv_close_image_large) {
        View view = View.inflate(classFragment.getActivity(), R.layout.dialog_select_class, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(classFragment.getActivity());
        final AlertDialog dialog = builder.setView(view).create();
        TextView tv_select_class = (TextView) view.findViewById(R.id.tv_select_class);
        TextView tv_select_grade = (TextView) view.findViewById(R.id.tv_select_grade);
        TextView tv_close_select = (TextView) view.findViewById(R.id.tv_close_select);
        tv_select_class.setOnClickListener(new View.OnClickListener() {


            private SingleChooseDialog singleChooseDialog;
            @Override
            public void onClick(final View v) {
                singleChooseDialog = new SingleChooseDialog();
                singleChooseDialog.show("选择班级", ProjectProperties.classNumber, new DialogInterface.OnClickListener() {

                    private MistakeBookDataBase mistakeBookDataBase;

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mistakeBookDataBase = new MistakeBookDataBase(classFragment.getActivity());
                        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("classNumber",ProjectProperties.classNumber[which]);
                        db.update("userInfo", values,"_id = ?",new String[]{String.valueOf(user_id)});
                        db.close();
                        dialog.dismiss();
                    }
                },classFragment.getFragmentManager());
            }
        });
        tv_select_grade.setOnClickListener(new View.OnClickListener() {

            private SingleChooseDialog singleChooseDialog;

            @Override
            public void onClick(View v) {
                singleChooseDialog = new SingleChooseDialog();
                singleChooseDialog.show("选择年级", ProjectProperties.gradeNumber, new DialogInterface.OnClickListener() {

                    private MistakeBookDataBase mistakeBookDataBase;

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mistakeBookDataBase = new MistakeBookDataBase(classFragment.getActivity());
                        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("gradeNumber",ProjectProperties.gradeNumber[which]);
                        db.update("userInfo", values,"_id = ?",new String[]{String.valueOf(user_id)});
                        db.close();
                        dialog.dismiss();
                    }
                },classFragment.getFragmentManager());
            }
        });
        tv_close_select.setOnClickListener(new View.OnClickListener() {

            private ClassAdapter classAdapter;
            private List<ErrorInfo> errorInfoList;
            private String gradeNumber;
            private String classNumber;

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                classController = new ClassController(classFragment.getActivity());
                user = classController.getUserInfo(user_id);
                classNumber = user.getClassNumber();
                gradeNumber = user.getGradeNumber();
                if (classNumber != null && classNumber.length() != 0 && gradeNumber != null && gradeNumber.length() != 0) {
                    tv_title.setText(gradeNumber + classNumber);
                }
                errorInfoList = classController.getErrorListByClass(classNumber,gradeNumber);
                classAdapter = new ClassAdapter(classFragment.getActivity(), errorInfoList);
                classAdapter.setViewInfo(rl_image_large,iv_image_large,iv_close_image_large);
                lv_error_list.setAdapter(classAdapter);
            }
        });
        dialog.show();
    }
}
