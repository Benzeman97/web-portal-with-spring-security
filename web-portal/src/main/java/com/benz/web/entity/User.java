package com.benz.web.entity;

import com.benz.web.config.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="USERS",schema = Schema.HR)
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USERID")
    private int userId;
    @Column(name="ACTIVE",nullable = false)
    private String active;
    @Column(name="PASSWORD",nullable = false)
    private String password;
    @Column(name = "ROLES",nullable = false)
    private String roles;
    @Id
    @Column(name = "USERNAME",nullable = false)
    private String userName;

    public int getUserId() {
        return userId;
    }

    public String getActive() {
        return active;
    }

    public String getPassword() {
        return password;
    }

    public String getRoles() {
        return roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
