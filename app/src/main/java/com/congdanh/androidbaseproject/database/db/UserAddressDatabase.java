package com.congdanh.androidbaseproject.database.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.congdanh.androidbaseproject.database.dao.AddressDAO;
import com.congdanh.androidbaseproject.database.dao.UserDAO;
import com.congdanh.androidbaseproject.database.entity.Address;
import com.congdanh.androidbaseproject.database.entity.User;

/**
 * Created by congdanh on 2/25/2018.
 */
@Database(entities = {User.class, Address.class}, version = 1)
public abstract class UserAddressDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();

    public abstract AddressDAO addressDAO();
}
