package com.github.xrapalexandra.kr.model;

import com.google.common.base.Objects;

public class User {

    private int userId;
    private String login;
    private String pass;
    private Role role;

    public User() {
    }

    public User(String login, Role role, String pass) {
        this.login = login;
        this.pass = pass;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(login, user.login) &&
                Objects.equal(pass, user.pass) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(login, pass, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", role=" + role +
                '}';
    }
}
