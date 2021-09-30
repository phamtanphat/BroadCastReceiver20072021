package com.example.broadcastreceiver20072021;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(context, "Connect Wifi", Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(context, "Connect Mobile", Toast.LENGTH_SHORT).show();
            }
            NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            int downSpeed = nc.getLinkDownstreamBandwidthKbps();
            int upSpeed = nc.getLinkUpstreamBandwidthKbps();

            Pair<Integer,Integer> pair = new Pair<>(upSpeed,downSpeed);
            EventBus.getDefault().post(pair);


        } else {
            Toast.makeText(context, "Disconnect", Toast.LENGTH_SHORT).show();
        }
    }
}
