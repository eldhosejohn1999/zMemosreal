package com.devhopeslabs.zmemos.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Memo {

    @Id
    private String id;
    private String name;
    private String createdDate;
    private String location;
    private String data;

    @ManyToOne
    private User user;

    private String status;

    public Memo() {
    }

    public Memo(String id, String name, String createdDate, String location, User user,String data,String status) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.location = location;
        this.user=user;
        this.data=data;
        this.status=status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user_id) {
        this.user = user_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
