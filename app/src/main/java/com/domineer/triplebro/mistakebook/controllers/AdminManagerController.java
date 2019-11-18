package com.domineer.triplebro.mistakebook.controllers;

import android.content.Context;

import com.domineer.triplebro.mistakebook.providers.DataBaseProvider;

import java.util.Map;

/**
 * @author Domineer
 * @data 2019/11/18,12:42
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AdminManagerController {

    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public AdminManagerController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public Map<Integer, Integer> getUserInfoList() {
        return dataBaseProvider.getUserInfoList();
    }
}
