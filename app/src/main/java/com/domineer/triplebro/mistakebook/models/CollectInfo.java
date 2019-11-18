package com.domineer.triplebro.mistakebook.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/15,22:33
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class CollectInfo implements Serializable {

    private int _id;
    private int userId;
    private int collectErrorId;

    public CollectInfo() {
    }

    public CollectInfo(int _id, int userId, int collectErrorId) {
        this._id = _id;
        this.userId = userId;
        this.collectErrorId = collectErrorId;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCollectErrorId() {
        return collectErrorId;
    }

    public void setCollectErrorId(int collectErrorId) {
        this.collectErrorId = collectErrorId;
    }

    @Override
    public String toString() {
        return "CollectInfo{" +
                "_id=" + _id +
                ", userId=" + userId +
                ", collectErrorId=" + collectErrorId +
                '}';
    }
}
