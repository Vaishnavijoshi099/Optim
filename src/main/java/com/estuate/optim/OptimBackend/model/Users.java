package com.estuate.optim.OptimBackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    private String userId;
    private String password;
    private String optimdir;
    private String dbname;
    private String connectionStr;
    private boolean failLogin;

    public Users() {}

    public Users(String userId, String password, String optimdir, String dbname, String connectionStr, boolean failLogin) {
        this.userId = userId;
        this.password = password;
        this.optimdir = optimdir;
        this.dbname = dbname;
        this.connectionStr = connectionStr;
        this.failLogin = failLogin;
    }

    public Users(Users users) {
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getOptimdir() { return optimdir; }
    public void setOptimdir(String optimdir) { this.optimdir = optimdir; }

    public String getDbname() { return dbname; }
    public void setDbname(String dbname) { this.dbname = dbname; }

    public String getConnectionStr() { return connectionStr; }
    public void setConnectionStr(String connectionStr) { this.connectionStr = connectionStr; }

    public boolean isFailLogin() { return failLogin; }
    public void setFailLogin(boolean failLogin) { this.failLogin = failLogin; }
}
