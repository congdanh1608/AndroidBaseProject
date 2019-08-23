package com.danhtran.androidbaseproject.extras.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.danhtran.androidbaseproject.extras.enums.EventBusKey;
import com.danhtran.androidbaseproject.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by DanhTran on 7/23/2019.
 */
public class NetworkReceiver extends BroadcastReceiver {
    final static String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private Context appContext;
    private Context context;

    public NetworkReceiver(Context context) {
        this.appContext = context.getApplicationContext();
        this.context = context;
    }

    public void registerReceiver() {
        appContext.registerReceiver(this, new IntentFilter(CONNECTIVITY_ACTION));      //must register in this to maintain activity + context
    }

    public void unregisterReceiver() {
        appContext.unregisterReceiver(this);      //must register in this to maintain activity + context
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (!isInitialStickyBroadcast() && CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean isAvailable = NetworkUtils.isNetworkAvailable(context);
            if (isAvailable) {
                EventBus.getDefault().post(EventBusKey.NETWORK_IS_AVAILABLE.getValue());
            } else {

            }
        }
    }
}
