package com.clan.bean;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 未使用java8中的时间类型
 */
public class User {

    private Integer uid;
    private Integer calnId;
    private String name;
    private String phone;
    private String password;
    private Integer sex;
    private String area;
    private String avatar;
    private String autograph;
    private Integer grade;
    private Date birthday;
    private Timestamp registTime;
    private Timestamp delTime;
    private Integer vip;
    private Timestamp vipExpires;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCalnId() {
        return calnId;
    }

    public void setCalnId(Integer calnId) {
        this.calnId = calnId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Timestamp getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Timestamp registTime) {
        this.registTime = registTime;
    }

    public Timestamp getDelTime() {
        return delTime;
    }

    public void setDelTime(Timestamp delTime) {
        this.delTime = delTime;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public Timestamp getVipExpires() {
        return vipExpires;
    }

    public void setVipExpires(Timestamp vipExpires) {
        this.vipExpires = vipExpires;
    }
}
