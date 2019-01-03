package com.danhtran.androidbaseproject.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.danhtran.androidbaseproject.database.entity.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by SilverWolf on 2/25/2018.
 */
@Dao
public interface UserDAO {
    //example for thread pool or AsyncTask
    @Query("SELECT * FROM User")
    List<User> getUsers_();

    @Query("SELECT * FROM User")
    Single<List<User>> getUsers();

    @Query("SELECT * FROM User WHERE id = :userId")
    Single<User> getUserById_(String userId);

    @Query("SELECT * FROM User WHERE id = :userId")
    Maybe<User> getUserById(String userId);

    @Query("SELECT * FROM User LIMIT 1")
    Single<User> getOneUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    // Insert multiple items
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertUser(User... users);

    @Query("DELETE FROM User WHERE id = :userId")
    void deleteUserById(String userId);

    @Query("DELETE FROM User")
    void deleteAllUsers();

    @Delete
    void deleteUser(User... users);

    @Update
    void updateUser(User... users);
}
