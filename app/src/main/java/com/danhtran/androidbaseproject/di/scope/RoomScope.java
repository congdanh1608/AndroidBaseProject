package com.danhtran.androidbaseproject.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by SilverWolf on 2/25/2018.
 */
@Scope
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RoomScope {
}
