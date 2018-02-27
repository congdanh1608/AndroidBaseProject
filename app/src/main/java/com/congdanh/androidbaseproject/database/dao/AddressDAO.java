package com.congdanh.androidbaseproject.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.congdanh.androidbaseproject.database.entity.Address;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;


/**
 * Created by congdanh on 2/26/2018.
 */
@Dao
public interface AddressDAO {
    @Query("SELECT * FROM Address")
    Single<Address> getAddresses();

    @Query("SELECT * FROM Address WHERE id = :id")
    Maybe<Address> getAddressById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAddress(Address... addresses);

    @Update
    void updateAddress(Address... addresses);

    @Query("DELETE FROM Address WHERE id = :id")
    void deleteAddressById(String id);

    @Delete
    void deleteAddress(Address... addresses);

    @Query("DELETE FROM Address")
    void deleteAllAddress();

    @Query("SELECT * FROM Address WHERE userid = :userId")
    Maybe<List<Address>> getAddressForUser(int userId);
}
