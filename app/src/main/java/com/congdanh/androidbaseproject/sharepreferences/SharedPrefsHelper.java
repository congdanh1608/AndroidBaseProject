package com.congdanh.androidbaseproject.sharepreferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by CongDanh on 19/05/2016.
 */
public class SharedPrefsHelper {
    private SharedPreferences sharedPreferences;

    public SharedPrefsHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void writeBoolean(String key, Boolean b) {
        sharedPreferences.edit().putBoolean(key, b).apply();
    }

    public Boolean readBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void writeString(String key, String string) {
        sharedPreferences.edit().putString(key, string).apply();
    }

    public void removeKey(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public String readString(String key) {
        return sharedPreferences.getString(key, null);
    }

    public void writeInt(String key, int i) {
        sharedPreferences.edit().putInt(key, i).apply();
    }

    public int readInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public void writeLong(String key, long i) {
        sharedPreferences.edit().putLong(key, i).apply();
    }

    public long readLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void clearKey(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public <T extends Serializable> void writeObject(String key, T b) {
        synchronized (this) {
            Gson gson = new Gson();
            String json = gson.toJson(b, b.getClass());
            writeString(key, json);
        }
    }

    public <T extends Serializable> T readObject(Class<T> aClass, String key) {
        synchronized (this) {
            Gson gson = new Gson();
            String json = readString(key);
            return gson.fromJson(json, aClass);
        }
    }
}
