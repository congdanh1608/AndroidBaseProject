package com.danhtran.androidbaseproject.database.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by danhtran on 2/25/2018.
 */
@Entity
//@Entity(tableName = "users")                          --> set name for table
//@Entity(primaryKeys = {"firstName", "lastName"})      --> using many fields are primaryKey
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    //    @ColumnInfo(name = "user_name")               --> set field name
    private String username;
    private String fullName;
    private String phone;

    //Don't want to persist
    @Ignore
    private Bitmap avatar;
    @Ignore
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
