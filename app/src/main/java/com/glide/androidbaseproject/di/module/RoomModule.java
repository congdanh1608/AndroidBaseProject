package com.glide.androidbaseproject.di.module;

import androidx.room.Room;
import android.content.Context;

import com.glide.androidbaseproject.database.dao.AddressDAO;
import com.glide.androidbaseproject.database.dao.UserDAO;
import com.glide.androidbaseproject.database.db.UserAddressDatabase;
import com.glide.androidbaseproject.database.repository.UserAddrAddrRepository;
import com.glide.androidbaseproject.di.scope.RoomScope;
import com.glide.androidbaseproject.extras.Constant;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by danhtran on 2/25/2018.
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
                Constant.DATABASE_NAME)
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
