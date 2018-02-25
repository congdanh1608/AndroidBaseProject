package com.congdanh.androidbaseproject.database.repository;

import com.congdanh.androidbaseproject.database.entity.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by congd on 2/25/2018.
 */

public interface UserRepositoryInterface {
    Single<List<User>> getUsers();

    Maybe<User> getUserById(String userId);

    Single<User> getOneUser();

    void insertUser(User user);

    void deleteUserById(String userId);

    void deleteAllUsers();
}
