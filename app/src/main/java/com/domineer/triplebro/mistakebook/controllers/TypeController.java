package com.domineer.triplebro.mistakebook.controllers;

import android.content.Context;

import com.domineer.triplebro.mistakebook.models.TypeInfo;
import com.domineer.triplebro.mistakebook.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/17,15:49
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class TypeController {
    private Context context;
    private final DataBaseProvider dataBaseProvider;

    public TypeController(Context context) {
        this.context = context;
        dataBaseProvider = new DataBaseProvider(context);
    }

    public List<TypeInfo> findTypeInfoList() {
        List<TypeInfo> typeInfoList = dataBaseProvider.findTypeInfoList();
        return typeInfoList;
    }
}
