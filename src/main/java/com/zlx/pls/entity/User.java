package com.zlx.pls.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user")
public class User {
//    声明哪个是主键，并且自增(依赖数据库的自增)
    @TableId(value = "userId",type = IdType.AUTO)
    private int userId;
    @TableField(value = "name")
    private String userName;
    @TableField(value = "age")
    private String userAge;

    public User() {
    }

    public User(String userName, String userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

    public User(int userId, String userName, String userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge='" + userAge + '\'' +
                '}';
    }
}
