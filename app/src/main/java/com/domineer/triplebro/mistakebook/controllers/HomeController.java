package com.domineer.triplebro.mistakebook.controllers;

import android.content.Context;

import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.providers.DataBaseProvider;

import java.util.List;
import java.util.Map;

/**
 * @author Domineer
 * @data 2019/11/17,13:54
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class HomeController {
    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public HomeController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<ErrorInfo> getAllErrorInfo() {
        return null;
    }

    public int addErrorInfo(String errorContentTitle, int typeId, int user_id) {
        return dataBaseProvider.addErrorInfo(errorContentTitle,typeId,user_id);
    }

    public int addAnswerInfo(String answerContentTitle, int errorId, int user_id) {
        return dataBaseProvider.addAnswerInfo(answerContentTitle,errorId,user_id);
    }

    public Map<String, Integer> getErrorInfoMap(int user_id) {
        Map<String, Integer> errorInfoMap = dataBaseProvider.getErrorInfoMap(user_id);
        return errorInfoMap;
    }

    public Map<String, Integer> getTypeInfoMap() {
        Map<String, Integer> typeInfoMap = dataBaseProvider.getTypeInfoMap();
        return typeInfoMap;
    }

    public void addErrorImageInfo(int errorInfoId, List<String> data) {
        dataBaseProvider.addErrorImageInfo(errorInfoId,data);
    }

    public void addAnswerImageInfo(int answerInfoId, List<String> data) {
        dataBaseProvider.addAnswerImageInfo(answerInfoId,data);
    }
}
