package com.congdanh.androidbaseproject.database.repository;

import com.congdanh.androidbaseproject.database.dao.UserDAO;
import com.congdanh.androidbaseproject.database.entity.User;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by congd on 2/25/2018.
 */

public class UserRepository implements UserRepositoryInterface {
    private UserDAO userDAO;
    private Executor executor;

    public UserRepository(UserDAO userDAO, Executor executor) {
        this.userDAO = userDAO;
        this.executor = executor;
    }

    public List<User> getUsers_() {
        return userDAO.getUsers_();
    }


    public void insertUser_(User user) {
        userDAO.insertUser(user);
    }


    @Override
    public Single<List<User>> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public Maybe<User> getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public Single<User> getOneUser() {
        return userDAO.getOneUser();
    }

    @Override
    public void insertUser(final User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.insertUser(user);
            }
        });
    }

    @Override
    public void deleteUserById(final String userId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.deleteUserById(userId);
            }
        });
    }

    @Override
    public void deleteAllUsers() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.deleteAllUsers();
            }
        });
    }
}
