package com.congdanh.androidbaseproject.view.activity.demo_database.items;

import com.congdanh.androidbaseproject.database.entity.User;

/**
 * Created by congdanh on 2/25/2018.
 */

public class DemoItemData {
    private User user;

    public DemoItemData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
