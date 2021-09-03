package com.example.test0;

public class comment {
    private String userName;
    private String time;
    private String cmt;
    private String id;
    private int eva,ship,ser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public comment(String userName, String time, String cmt, int eva, int ship, int ser, String id) {
        this.userName = userName;
        this.time = time;
        this.cmt = cmt;
        this.eva = eva;
        this.ship = ship;
        this.ser = ser;
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public int getEva() {
        return eva;
    }

    public void setEva(int eva) {
        this.eva = eva;
    }

    public int getShip() {
        return ship;
    }

    public void setShip(int ship) {
        this.ship = ship;
    }

    public int getSer() {
        return ser;
    }

    public void setSer(int ser) {
        this.ser = ser;
    }

    @Override
    public String toString() {
        return "comment{" +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                ", cmt='" + cmt + '\'' +
                ", eva=" + eva +
                ", ship=" + ship +
                ", ser=" + ser +
                '}';
    }
}
