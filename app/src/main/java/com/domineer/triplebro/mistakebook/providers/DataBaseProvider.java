package com.domineer.triplebro.mistakebook.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.domineer.triplebro.mistakebook.database.MistakeBookDataBase;
import com.domineer.triplebro.mistakebook.models.AnswerInfo;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.models.TypeInfo;
import com.domineer.triplebro.mistakebook.models.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Domineer
 * @data 2019/8/15,23:26
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class DataBaseProvider implements DataProvider {

    private Context context;
    private final MistakeBookDataBase mistakeBookDataBase;

    public DataBaseProvider(Context context) {
        this.context = context;
        mistakeBookDataBase = new MistakeBookDataBase(context);
    }


    public List<TypeInfo> findTypeInfoList() {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        Cursor typeInfoCursor = db.query("typeInfo", null, null, null, null, null, null);
        List<TypeInfo> typeInfoList = new ArrayList<>();
        if (typeInfoCursor != null && typeInfoCursor.getCount() > 0) {
            while (typeInfoCursor.moveToNext()) {
                TypeInfo typeInfo = new TypeInfo(typeInfoCursor.getInt(0), typeInfoCursor.getString(1), typeInfoCursor.getString(2));
                typeInfoList.add(typeInfo);
            }
        }
        if (typeInfoCursor != null) {
            typeInfoCursor.close();
        }
        db.close();
        return typeInfoList;
    }

    public Map<String, Integer> getTypeInfoMap() {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        List<TypeInfo> typeInfoList = findTypeInfoList();
        HashMap<String, Integer> typeInfoMap = new HashMap<>();
        for (TypeInfo typeInfo : typeInfoList) {
            typeInfoMap.put(typeInfo.getTypeName(), typeInfo.get_id());
        }
        db.close();
        return typeInfoMap;
    }

    public int addErrorInfo(String errorContentTitle, int typeId, int user_id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("error_title", errorContentTitle);
        contentValues.put("type_id", typeId);
        contentValues.put("user_id", user_id);
        long errorInfo = db.insert("errorInfo", null, contentValues);
        db.close();
        return (int) errorInfo;
    }

    public List<ErrorInfo> getErrorListByTypeIdAndUserId(int user_id, int id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        Cursor errorInfoCursor = db.query("errorInfo", null, "user_id = ? and type_id = ?", new String[]{String.valueOf(user_id), String.valueOf(id)}, null, null, "_id ASC");
        List<ErrorInfo> errorInfoList = new ArrayList<>();
        if (errorInfoCursor != null && errorInfoCursor.getCount() > 0) {
            while (errorInfoCursor.moveToNext()) {
                ErrorInfo errorInfo = new ErrorInfo(errorInfoCursor.getInt(0), errorInfoCursor.getString(1), id, user_id);
                errorInfoList.add(errorInfo);
            }
        }
        if (errorInfoCursor != null) {
            errorInfoCursor.close();
        }
        db.close();
        return errorInfoList;
    }

    public List<String> getErrorImageInfoList(int id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        Cursor errorImageInfoCursor = db.query("errorImageInfo", new String[]{"error_image"}, "error_id = ?", new String[]{String.valueOf(id)}, null, null, "_id ASC");
        List<String> errorImageList = new ArrayList<>();
        if (errorImageInfoCursor != null && errorImageInfoCursor.getCount() > 0) {
            while (errorImageInfoCursor.moveToNext()) {
                errorImageList.add(errorImageInfoCursor.getString(0));
            }
        }
        if (errorImageInfoCursor != null) {
            errorImageInfoCursor.close();
        }
        db.close();
        return errorImageList;
    }

    public boolean getIsCollect(int id, int user_id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        Cursor collectInfoCursor = db.query("collectInfo", null, "collect_error_Id = ? and user_id = ?", new String[]{String.valueOf(id), String.valueOf(user_id)}, null, null, "_id ASC");
        if (collectInfoCursor != null && collectInfoCursor.getCount() > 0) {
            db.close();
            collectInfoCursor.close();
            return true;
        } else {
            if (collectInfoCursor != null) {
                collectInfoCursor.close();
            }
            db.close();
            return false;
        }
    }

    public void deleteCollect(int id, int user_id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        db.delete("collectInfo", "collect_error_Id = ? and user_id = ?", new String[]{String.valueOf(id), String.valueOf(user_id)});
        db.close();
    }

    public void addCollect(int id, int user_id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", user_id);
        contentValues.put("collect_error_Id", id);
        db.insert("collectInfo", null, contentValues);
        db.close();
    }

    public Map<String, Integer> getErrorInfoMap(int user_id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        Cursor errorInfoCursor = db.query("errorInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, "_id ASC");
        List<ErrorInfo> errorInfoList = new ArrayList<>();
        if (errorInfoCursor != null && errorInfoCursor.getCount() > 0) {
            while (errorInfoCursor.moveToNext()) {
                ErrorInfo errorInfo = new ErrorInfo(errorInfoCursor.getInt(0), errorInfoCursor.getString(1), errorInfoCursor.getInt(3), user_id);
                errorInfoList.add(errorInfo);
            }
        }
        if (errorInfoCursor != null) {
            errorInfoCursor.close();
        }
        db.close();
        HashMap<String, Integer> errorInfoMap = new HashMap<>();
        for (ErrorInfo errorInfo : errorInfoList) {
            errorInfoMap.put(errorInfo.getErrorTitle(), errorInfo.get_id());
        }
        return errorInfoMap;
    }

    public int addAnswerInfo(String answerContentTitle, int errorId, int user_id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("answer", answerContentTitle);
        contentValues.put("error_id", errorId);
        long answerInfo = db.insert("answerInfo", null, contentValues);
        db.close();
        return (int) answerInfo;
    }

    public void addErrorImageInfo(int errorInfoId, List<String> data) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        for (String image : data) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("error_id", errorInfoId);
            contentValues.put("error_image", image);
            db.insert("errorImageInfo", null, contentValues);
        }
        db.close();
    }

    public void addAnswerImageInfo(int answerInfoId, List<String> data) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        for (String image : data) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("answer_id", answerInfoId);
            contentValues.put("answer_image", image);
            db.insert("answerImageInfo", null, contentValues);
        }
        db.close();
    }

    public AnswerInfo getAnswerInfo(int id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        Cursor answerInfoCursor = db.query("answerInfo", null, "error_id = ?", new String[]{String.valueOf(id)}, null, null, "_id DESC");
        AnswerInfo answerInfo = new AnswerInfo();
        if (answerInfoCursor != null && answerInfoCursor.getCount() > 0) {
            answerInfoCursor.moveToNext();
            answerInfo.set_id(answerInfoCursor.getInt(0));
            answerInfo.setAnswerTitle(answerInfoCursor.getString(1));
            answerInfo.setErrorId(id);
        }
        if (answerInfoCursor != null) {
            answerInfoCursor.close();
        }
        db.close();
        return answerInfo;
    }

    public List<String> getAnswerImageInfo(int id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        Cursor answerImageInfoCursor = db.query("answerImageInfo", new String[]{"answer_image"}, "answer_id = ?", new String[]{String.valueOf(id)}, null, null, "_id ASC");
        List<String> answerImageList = new ArrayList<>();
        if (answerImageInfoCursor != null && answerImageInfoCursor.getCount() > 0) {
            while (answerImageInfoCursor.moveToNext()) {
                answerImageList.add(answerImageInfoCursor.getString(0));
            }
        }
        if (answerImageInfoCursor != null) {
            answerImageInfoCursor.close();
        }
        db.close();
        return answerImageList;
    }

    public List<ErrorInfo> getCollectErrorListByUserId(int user_id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        Cursor collectInfoCursor = db.query("collectInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        List<Integer> errorIdList = new ArrayList<>();
        if (collectInfoCursor != null && collectInfoCursor.getCount() > 0) {
            while (collectInfoCursor.moveToNext()) {
                errorIdList.add(collectInfoCursor.getInt(2));
            }
        }
        if (collectInfoCursor != null) {
            collectInfoCursor.close();
        }
        List<ErrorInfo> errorInfoList = new ArrayList<>();
        for (int error_id : errorIdList) {
            Cursor errorInfoCursor = db.query("errorInfo", null, "user_id = ? and _id = ?", new String[]{String.valueOf(user_id), String.valueOf(error_id)}, null, null, "_id ASC");
            if (errorInfoCursor != null && errorInfoCursor.getCount() > 0) {
                errorInfoCursor.moveToNext();
                ErrorInfo errorInfo = new ErrorInfo(error_id, errorInfoCursor.getString(1), errorInfoCursor.getInt(2), user_id);
                errorInfoList.add(errorInfo);
            }
            if (errorInfoCursor != null) {
                errorInfoCursor.close();
            }
        }
        db.close();
        return errorInfoList;
    }

    public UserInfo getUserInfo(int user_id) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        UserInfo userInfo = new UserInfo();
        Cursor userInfoCursor = db.query("userInfo", null, "_id = ?", new String[]{String.valueOf(user_id)}, null, null, null);
        if (userInfoCursor != null && userInfoCursor.getCount() > 0) {
            userInfoCursor.moveToNext();
            userInfo = new UserInfo(user_id, userInfoCursor.getString(1), userInfoCursor.getString(2), userInfoCursor.getString(3), userInfoCursor.getString(4), userInfoCursor.getString(5), userInfoCursor.getString(6));
        }
        if (userInfoCursor != null) {
            userInfoCursor.close();
        }
        db.close();
        return userInfo;
    }

    public List<ErrorInfo> getErrorListByClass(String classNumber, String gradeNumber) {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        Cursor userInfoCursor = db.query("userInfo", null, "classNumber = ? and gradeNumber = ?", new String[]{classNumber, gradeNumber}, null, null, null);
        List<Integer> userIdList = new ArrayList<>();
        if (userInfoCursor != null && userInfoCursor.getCount() > 0) {
            while (userInfoCursor.moveToNext()) {
                userIdList.add(userInfoCursor.getInt(0));
            }
        }
        if (userInfoCursor != null) {
            userInfoCursor.close();
        }
        List<ErrorInfo> errorInfoList = new ArrayList<>();
        for (int user_id : userIdList) {
            Cursor errorInfoCursor = db.query("errorInfo", null, "user_id = ?", new String[]{String.valueOf(user_id)}, null, null, "_id ASC");
            if (errorInfoCursor != null && errorInfoCursor.getCount() > 0) {
                errorInfoCursor.moveToNext();
                ErrorInfo errorInfo = new ErrorInfo(errorInfoCursor.getInt(0), errorInfoCursor.getString(1), errorInfoCursor.getInt(2), user_id);
                errorInfoList.add(errorInfo);
            }
            if (errorInfoCursor != null) {
                errorInfoCursor.close();
            }
        }
        db.close();
        return errorInfoList;
    }

    public Map<Integer,Integer> getUserInfoList() {
        SQLiteDatabase db = mistakeBookDataBase.getWritableDatabase();
        /*Cursor userInfoCursor = db.query("userInfo", null, null, null, null, null, null);
        List<UserInfo> userInfoList = new ArrayList<>();
        if (userInfoCursor != null && userInfoCursor.getCount() > 0) {
            while (userInfoCursor.moveToNext()) {
                UserInfo userInfo = new UserInfo(userInfoCursor.getInt(0), userInfoCursor.getString(1), userInfoCursor.getString(2), userInfoCursor.getString(3), userInfoCursor.getString(4), userInfoCursor.getString(5), userInfoCursor.getString(6));
                userInfoList.add(userInfo);
            }
        }
        if (userInfoCursor != null) {
            userInfoCursor.close();
        }
        for (UserInfo userInfo : userInfoList) {
            int id = userInfo.get_id();*/
        Cursor errorInfoCursor = db.query("errorInfo", new String[]{"count(*)","user_id"}, null, null, "user_id", null, "count(*) DESC");
        Map<Integer,Integer> userInfo = new HashMap<>();
        if (errorInfoCursor != null && errorInfoCursor.getCount() > 0) {
            while (errorInfoCursor.moveToNext()) {
                userInfo.put(errorInfoCursor.getInt(1),errorInfoCursor.getInt(0));
            }
        }
        if (errorInfoCursor != null) {
            errorInfoCursor.close();
        }
        //}
        db.close();
        return userInfo;
    }
}
