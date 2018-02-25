package com.congdanh.androidbaseproject.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.congdanh.androidbaseproject.database.entity.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by congd on 2/25/2018.
 */
@Dao
public interface UserDAO {
    @Query("SELECT * FROM User")
    List<User> getUsers_();

    @Query("SELECT * FROM User")
    Single<List<User>> getUsers();

    @Query("SELECT * FROM User WHERE id = :userId")
    Maybe<User> getUserById(String userId);

    @Query("SELECT * FROM User LIMIT 1")
    Single<User> getOneUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("DELETE FROM User WHERE id = :userId")
    void deleteUserById(String userId);

    @Query("DELETE FROM User")
    void deleteAllUsers();
}
