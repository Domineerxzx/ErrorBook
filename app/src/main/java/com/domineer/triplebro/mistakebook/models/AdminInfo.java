package com.domineer.triplebro.mistakebook.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/15,22:26
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AdminInfo implements Serializable {

    private int _id;
    private String phoneNumber;
    private String password;
    private String adminHead;
    private String nickName;

    public AdminInfo() {
    }

    public AdminInfo(int _id, String phoneNumber, String password, String adminHead, String nickName) {
        this._id = _id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.adminHead = adminHead;
        this.nickName = nickName;
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

    public String getAdminHead() {
        return adminHead;
    }

    public void setAdminHead(String adminHead) {
        this.adminHead = adminHead;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "_id=" + _id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", adminHead='" + adminHead + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
