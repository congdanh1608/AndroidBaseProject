package com.glide.androidbaseproject.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by danhtran on 2/25/2018.
 */
@Scope
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RoomScope {
}
