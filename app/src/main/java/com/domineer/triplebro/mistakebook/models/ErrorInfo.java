package com.domineer.triplebro.mistakebook.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/15,22:30
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ErrorInfo implements Serializable {

    private int _id;
    private String errorTitle;
    private int typeId;
    private int user_id;

    public ErrorInfo() {
    }

    public ErrorInfo(int _id, String errorTitle, int typeId, int user_id) {
        this._id = _id;
        this.errorTitle = errorTitle;
        this.typeId = typeId;
        this.user_id = user_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "_id=" + _id +
                ", errorTitle='" + errorTitle + '\'' +
                ", typeId=" + typeId +
                ", user_id=" + user_id +
                '}';
    }
}
