package com.dong.bean;

/**
 * Created by 川东 on 2016/8/8.
 */
public class InAccount {
    private int _id;
    private float money;
    private String time;
    private String type;
    private String handler;
    private String mark;

    public InAccount(int _id, float money, String time, String type, String handler, String mark) {
        this._id = _id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.handler = handler;
        this.mark = mark;
    }

    public InAccount() {
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

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "InAccount{" +
                "_id=" + _id +
                ", money=" + money +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", handler='" + handler + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}
