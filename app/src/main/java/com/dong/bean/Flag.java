package com.dong.bean;

/**
 * Created by 川东 on 2016/8/8.
 */
public class Flag {
   private int _id;
   private   String flag;

    public Flag(int _id, String flag) {
        this._id = _id;
        this.flag = flag;
    }

    public Flag() {

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Flag{" +
                "_id=" + _id +
                ", flag='" + flag + '\'' +
                '}';
    }
}
