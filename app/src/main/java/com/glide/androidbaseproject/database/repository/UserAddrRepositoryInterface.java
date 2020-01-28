package com.glide.androidbaseproject.database.repository;

import com.glide.androidbaseproject.database.entity.Address;
import com.glide.androidbaseproject.database.entity.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by danhtran on 2/25/2018.
 */

public interface UserAddrRepositoryInterface {
    Single<List<User>> getUsers();

    Maybe<User> getUserById(String userId);

    Single<User> getOneUser();

    void insertUser(User user);

    void deleteUserById(String userId);

    void deleteAllUsers();

    Single<Address> getAddresses();

    Maybe<Address> getAddressById(String id);

    void insertAddress(Address... addresses);

    void updateAddress(Address... addresses);

    void deleteAddressById(String id);

    void deleteAddress(Address... addresses);

    void deleteAllAddress();

    Maybe<List<Address>> getAddressForUser(int userId);
}
