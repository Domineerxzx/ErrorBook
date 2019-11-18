package com.domineer.triplebro.mistakebook.controllers;

import android.content.Context;

import com.domineer.triplebro.mistakebook.models.AnswerInfo;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/17,16:41
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ErrorListController {

    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public ErrorListController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<ErrorInfo> getErrorListByTypeIdAndUserId(int user_id, int id) {
        return dataBaseProvider.getErrorListByTypeIdAndUserId(user_id,id);
    }

    public List<String> getErrorImageInfoList(int id) {
        return dataBaseProvider.getErrorImageInfoList(id);
    }

    public boolean getIsCollect(int id, int user_id) {
        return dataBaseProvider.getIsCollect(id,user_id);
    }

    public void deleteCollect(int id, int user_id) {
        dataBaseProvider.deleteCollect(id,user_id);
    }

    public void addCollect(int id, int user_id) {
        dataBaseProvider.addCollect(id,user_id);
    }

    public AnswerInfo getAnswerInfo(int id) {
        return dataBaseProvider.getAnswerInfo(id);
    }

    public List<String> getAnswerImageInfo(int id) {
        return dataBaseProvider.getAnswerImageInfo(id);
    }

    public List<ErrorInfo> getCollectErrorListByUserId(int user_id) {
        return dataBaseProvider.getCollectErrorListByUserId(user_id);
    }
}
