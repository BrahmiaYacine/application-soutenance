package com.example.projetfinetude;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.Geofence;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    private final static String TAG = "Geofence BroadcastReceiver";
    String geofenceTransitionString;

    public void onReceive(Context context, Intent intent) {
        Log.e("Receive", "Broadcast");
        GeofenceTransitionsJobIntentService.enqueueWork(context, intent);
        getTransitionString(1);
    }


    private String getTransitionString(int transitionType) {
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return "Entering";
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return "Exiting";
            default:
                return "UKNOWN";
        }
    }


}
