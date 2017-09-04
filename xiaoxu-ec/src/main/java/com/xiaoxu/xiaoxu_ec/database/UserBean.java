package com.xiaoxu.xiaoxu_ec.database;

import android.support.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xiaoxu on 2017/9/4.
 */
@Entity(nameInDb = "user_bean")
public class UserBean {

    @Nullable
    private long id = 0;
    @Nullable
    private String userName = null;
    private String email = null;
    private String phone = null;
    private int role = 0;
    private long createTime = 0;
    private long updateTime = 0;
    @Generated(hash = 1321759788)
    public UserBean(long id, String userName, String email, String phone, int role,
            long createTime, long updateTime) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getRole() {
        return this.role;
    }
    public void setRole(int role) {
        this.role = role;
    }
    public long getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    public long getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
