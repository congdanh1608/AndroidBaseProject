package com.danhtran.androidbaseproject.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.danhtran.androidbaseproject.database.dao.AddressDAO;
import com.danhtran.androidbaseproject.database.dao.UserDAO;
import com.danhtran.androidbaseproject.database.db.UserAddressDatabase;
import com.danhtran.androidbaseproject.database.repository.UserAddrAddrRepository;
import com.danhtran.androidbaseproject.di.scope.RoomScope;
import com.danhtran.androidbaseproject.extras.enums.StringConstants;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by SilverWolf on 2/25/2018.
 */
@Module
public class RoomModule {

    @Provides
    @RoomScope
    //Example to use executor
//    public UserAddrAddrRepository getUserRepository(UserDAO userDAO, Executor executor) {
    //Example to use DAO with RxJava
    public UserAddrAddrRepository getUserRepository(UserDAO userDAO, AddressDAO addressDAO) {
        return new UserAddrAddrRepository(userDAO, addressDAO);
    }

    @RoomScope
    @Provides
    public UserDAO getUserDAO(UserAddressDatabase userDatabase) {
        return userDatabase.userDAO();
    }

    @RoomScope
    @Provides
    public AddressDAO getAddressDAO(UserAddressDatabase userDatabase) {
        return userDatabase.addressDAO();
    }

    @RoomScope
    @Provides
    public UserAddressDatabase getUserDatabase(Context context) {
        return Room.databaseBuilder(
                context,
                UserAddressDatabase.class,
                StringConstants.DATABASE_NAME.toString())
                .build();
    }

    /*@RoomScope
    @Provides
    public Executor getExecutor() {
        return Executors.newFixedThreadPool(2);
    }*/

    @RoomScope
    @Provides
    public CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

}
