package com.domineer.triplebro.mistakebook.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/15,22:22
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class UserInfo implements Serializable {

    private int _id;
    private String phoneNumber;
    private String password;
    private String nickName;
    private String userHead;
    private String classNumber;
    private String gradeNumber;

    public UserInfo() {
    }

    public UserInfo(int _id, String phoneNumber, String password, String nickName, String userHead, String classNumber, String gradeNumber) {
        this._id = _id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.nickName = nickName;
        this.userHead = userHead;
        this.classNumber = classNumber;
        this.gradeNumber = gradeNumber;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(String gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "_id=" + _id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userHead='" + userHead + '\'' +
                ", classNumber=" + classNumber +
                ", gradeNumber=" + gradeNumber +
                '}';
    }
}



