package com.domineer.triplebro.mistakebook.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/15,22:30
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class AnswerInfo implements Serializable {

    private int _id;
    private String answerTitle;
    private int errorId;

    public AnswerInfo() {
    }

    public AnswerInfo(int _id, String answerTitle, int errorId ) {
        this._id = _id;
        this.answerTitle = answerTitle;
        this.errorId = errorId;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }


    @Override
    public String toString() {
        return "AnswerInfo{" +
                "_id=" + _id +
                ", answerTitle='" + answerTitle + '\'' +
                ", errorId=" + errorId +
                '}';
    }
}
