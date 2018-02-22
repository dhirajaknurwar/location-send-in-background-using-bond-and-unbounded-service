package com.master.locationsendsinbackground;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class LocationUpdateBroadCastReceiver extends BroadcastReceiver {
    public static final String ACTION_LOCATION_UPDATE = "actionLocationUpdate";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getExtras() != null) {
            if ("ACTION_LOCATION_UPDATE".equalsIgnoreCase(intent.getAction())) {
                Log.d("LOCATION", String.valueOf(intent.getExtras().getParcelable("Location")));

            }
        }

    }

}
