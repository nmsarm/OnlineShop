package com.sarmiento.daneahmarelle.onlineshop;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Upload {
    private String id, email;

    public Upload() {
    }

    public Upload(String id, String email) {
        this.id = id;   // Primary key and key
        this.email = email;
    }

    public String getid() {
        return id;
    }

    public void setUid(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }
}