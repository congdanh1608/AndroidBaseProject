package com.danhtran.androidbaseproject.database.repository;

import com.danhtran.androidbaseproject.database.dao.AddressDAO;
import com.danhtran.androidbaseproject.database.dao.UserDAO;
import com.danhtran.androidbaseproject.database.entity.Address;
import com.danhtran.androidbaseproject.database.entity.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by SilverWolf on 2/25/2018.
 */

public class UserAddrAddrRepository implements UserAddrRepositoryInterface {
    private UserDAO userDAO;
    private AddressDAO addressDAO;

    public UserAddrAddrRepository(UserDAO userDAO, AddressDAO addressDAO) {
        this.userDAO = userDAO;
        this.addressDAO = addressDAO;
    }

    public List<User> getUsers_() {
        return userDAO.getUsers_();
    }

    public void insertUser_(User user) {
        userDAO.insertUser(user);
    }

    public Single<User> getUserById_(String userId) {
        return userDAO.getUserById_(userId);
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

        //Example for using executor
        /*executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.insertUser(user);
            }
        });*/

        userDAO.insertUser(user);
    }

    @Override
    public void deleteUserById(final String userId) {
        userDAO.deleteUserById(userId);
    }

    @Override
    public void deleteAllUsers() {
        userDAO.deleteAllUsers();
    }

    @Override
    public Single<Address> getAddresses() {
        return addressDAO.getAddresses();
    }

    @Override
    public Maybe<Address> getAddressById(String id) {
        return addressDAO.getAddressById(id);
    }

    @Override
    public void insertAddress(Address... addresses) {
        addressDAO.insertAddress(addresses);
    }

    @Override
    public void updateAddress(Address... addresses) {
        addressDAO.updateAddress(addresses);
    }

    @Override
    public void deleteAddressById(String id) {
        addressDAO.deleteAddressById(id);
    }

    @Override
    public void deleteAddress(Address... addresses) {
        addressDAO.deleteAddress(addresses);
    }

    @Override
    public void deleteAllAddress() {
        addressDAO.deleteAllAddress();
    }

    @Override
    public Maybe<List<Address>> getAddressForUser(int userId) {
        return addressDAO.getAddressForUser(userId);
    }
}
