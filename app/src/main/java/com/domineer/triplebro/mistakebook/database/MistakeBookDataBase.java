package com.domineer.triplebro.mistakebook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author Domineer
 * @data 2019/8/11,23:39
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class MistakeBookDataBase extends SQLiteOpenHelper {
    public MistakeBookDataBase(@Nullable Context context) {
        super(context, "MistakeBookDataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户表
        db.execSQL("create table userInfo(_id Integer primary key autoincrement,phone_number varchar(20),password varchar(20)" +
                ",nickname varchar(20),user_head varchar(200),classNumber varchar(20),gradeNumber varchar(20))");

        //管理员表
        db.execSQL("create table adminInfo(_id Integer primary key autoincrement,phone_number varchar(20),password varchar(20),nickname varchar(20),user_head varchar(200))");

        //分类信息表
        db.execSQL("create table typeInfo(_id Integer primary key autoincrement,typeName varchar(200),typeImage varchar(200))");

        //错题信息表
        db.execSQL("create table errorInfo(_id Integer primary key autoincrement,error_title varchar(2000),type_id Integer,user_id Integer,FOREIGN KEY (type_id) REFERENCES typeInfo(_id),FOREIGN KEY (user_id) REFERENCES userInfo(_id))");

        //错题图片信息表
        db.execSQL("create table errorImageInfo(_id Integer primary key autoincrement,error_image varchar(200),error_id Integer,FOREIGN KEY (error_id) REFERENCES errorInfo(_id))");

        //答案信息表
        db.execSQL("create table answerInfo(_id Integer primary key autoincrement,answer varchar(2000),error_id Integer,FOREIGN KEY (error_id) REFERENCES errorInfo(_id))");

        //答案图片信息表
        db.execSQL("create table answerImageInfo(_id Integer primary key autoincrement,answer_image varchar(200),answer_id Integer,FOREIGN KEY (answer_id) REFERENCES answerInfo(_id))");

        //收藏表
        db.execSQL("create table collectInfo(_id Integer primary key autoincrement,user_id number,collect_error_Id number,FOREIGN KEY (user_id) REFERENCES userInfo(_id),FOREIGN KEY (collect_error_id) REFERENCES errorInfo(_id))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints 开启外键约束
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }
}
