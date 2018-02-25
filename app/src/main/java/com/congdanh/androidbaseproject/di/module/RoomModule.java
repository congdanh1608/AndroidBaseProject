package com.congdanh.androidbaseproject.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.congdanh.androidbaseproject.database.dao.UserDAO;
import com.congdanh.androidbaseproject.database.db.UserDatabase;
import com.congdanh.androidbaseproject.database.repository.UserRepository;
import com.congdanh.androidbaseproject.di.scope.RoomScope;
import com.congdanh.androidbaseproject.enums.StaticString;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by congd on 2/25/2018.
 */
@Module
public class RoomModule {

    @Provides
    @RoomScope
    public UserRepository getUserRepository(UserDAO userDAO, Executor executor) {
        return new UserRepository(userDAO, executor);
    }

    @RoomScope
    @Provides
    public UserDAO getUserDAO(UserDatabase userDatabase) {
        return userDatabase.userDAO();
    }

    @RoomScope
    @Provides
    public UserDatabase getUserDatabase(Application application) {
        return Room.databaseBuilder(
                application.getApplicationContext(),
                UserDatabase.class,
                StaticString.DATABASE_NAME.toString())
                .build();
    }

    @RoomScope
    @Provides
    public Executor getExecutor() {
        return Executors.newFixedThreadPool(2);
    }

    @RoomScope
    @Provides
    public CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

}
