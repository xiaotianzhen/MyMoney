package com.dong.bean;

/**
 * Created by 川东 on 2016/8/8.
 */
public class OutAccount {
    private int _id;
    private float money;
    private String time;
    private String type;
    private String address;
    private String mark;

    public OutAccount() {
    }

    public OutAccount(int _id, float money, String time, String type, String address, String mark) {
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.address = address;
        this.mark = mark;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "OutAccount{" +
                "_id=" + _id +
                ", money=" + money +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
