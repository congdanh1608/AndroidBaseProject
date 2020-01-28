package com.glide.androidbaseproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.glide.androidbaseproject.database.entity.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by danhtran on 2/25/2018.
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
