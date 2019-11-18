package com.domineer.triplebro.mistakebook.controllers;

import android.content.Context;

import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.models.UserInfo;
import com.domineer.triplebro.mistakebook.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/18,3:11
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ClassController {

    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public ClassController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }


    public UserInfo getUserInfo(int user_id) {
        return dataBaseProvider.getUserInfo(user_id);
    }

    public List<ErrorInfo> getErrorListByClass(String classNumber, String gradeNumber) {
        return dataBaseProvider.getErrorListByClass(classNumber,gradeNumber);
    }
}
