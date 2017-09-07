/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myPackage;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Account implements Serializable{
    private int id;
    private String website;
    private String username;
    private String password;
    private Date  date_created;
    private int status;

    public Account() {
    }

    public Account(int id, String website, String username, String password, Date date_created, int status) {
        this.id = id;
        this.website = website;
        this.username = username;
        this.password = password;
        this.date_created = date_created;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
