package com.domineer.triplebro.mistakebook.models;

import java.io.Serializable;

/**
 * @author Domineer
 * @data 2019/11/15,22:28
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class TypeInfo implements Serializable {

    private int _id;
    private String typeName;
    private String typeImage;

    public TypeInfo() {
    }

    public TypeInfo(int _id, String typeName, String typeImage) {
        this._id = _id;
        this.typeName = typeName;
        this.typeImage = typeImage;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

    @Override
    public String toString() {
        return "TypeInfo{" +
                "_id=" + _id +
                ", typeName='" + typeName + '\'' +
                ", typeImage='" + typeImage + '\'' +
                '}';
    }
}
