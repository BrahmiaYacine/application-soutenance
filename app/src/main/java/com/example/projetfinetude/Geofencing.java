package com.example.projetfinetude;


import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class Geofencing implements ResultCallback {

    // Constants
    public static final String TAG = Geofencing.class.getSimpleName();
    private static final float GEOFENCE_RADIUS = 500; // 100 meters
    private static final long GEOFENCE_TIMEOUT = 24 * 60 * 60 * 1000; // 24 hours

    private List<Geofence> mGeofenceList;
    private PendingIntent mGeofencePendingIntent;
    private GoogleApiClient mGoogleApiClient;
    private GeofencingClient geofencingClient;
    private Context mContext;
    private Taches tache ;

    public Geofencing(Context context) {
        mContext = context;
        geofencingClient = LocationServices.getGeofencingClient(context);
        mGeofencePendingIntent = null;
        mGeofenceList = new ArrayList<>();
    }

    /***
     * Registers the list of Geofences specified in mGeofenceList with Google Place Services
     * Uses {@code #mGoogleApiClient} to connect to Google Place Services
     * Uses {@link #getGeofencingRequest} to get the list of Geofences to be registered
     * Uses {@link #getGeofencePendingIntent} to get the pending intent to launch the IntentService
     * when the Geofence is triggered
     * Triggers {@link #onResult} when the geofences have been registered successfully
     */
    @SuppressLint("MissingPermission")
    public void registerAllGeofences() {
        // Check that the API client is connected and that the list has Geofences in it
        if (geofencingClient == null ||
                mGeofenceList == null || mGeofenceList.size() == 0) {
            return;
        }

        geofencingClient.addGeofences(getGeofencingRequest(), getGeofencePendingIntent())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e(TAG, "Geofences added");
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAGFAIL", "Geofences failed to  add");
                        // Failed to add geofences
                        // ...
                    }
                });

    }



    /***
     * Unregisters all the Geofences created by this app from Google Place Services
     * Uses {@code #mGoogleApiClient} to connect to Google Place Services
     * Uses {@link #getGeofencePendingIntent} to get the pending intent passed when
     * registering the Geofences in the first place
     * Triggers {@link #onResult} when the geofences have been unregistered successfully
     */


    public void unRegisterAllGeofences() {
        geofencingClient.removeGeofences(getGeofencePendingIntent()).addOnCompleteListener((OnCompleteListener<Void>) mContext);
    }




    /***
     * Updates the local ArrayList of Geofences using data from the passed in list
     * Uses the Place ID defined by the API as the Geofence object Id
     *
     * @param positionsList the PlaceBuffer result of the getPlaceById call
     */
    /*public void updateGeofencesList(List <Taches> tachesList){
        if (tachesList==null || tachesList.size()==0) return;
        for (Taches taches : tachesList){
            if (taches!=null){*/

    public void updateGeofencesList(List<Positions> positionsList) {
        mGeofenceList = new ArrayList<>();

        if (positionsList == null || positionsList.size() == 0) return;
        for (Positions positions : positionsList) {
            // Read the place information from the DB cursor
            if (positions != null) {
                String placeUID = positions.getName() + "";
                double placeLat = Double.parseDouble(positions.getLatitude());
                double placeLng = Double.parseDouble(positions.getLongitude());
                Log.e(TAG, "Geofence " + positions.getName() + " adding " + placeLat + ", " + placeLng);
                // Build a Geofence object
                Geofence geofence = new Geofence.Builder()
                        .setRequestId(placeUID)
                        .setExpirationDuration(GEOFENCE_TIMEOUT)
                        .setCircularRegion(placeLat, placeLng, GEOFENCE_RADIUS)
                        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                        .setLoiteringDelay(100)
                        .build();
                // Add it to the list
                mGeofenceList.add(geofence);
                Log.e(TAG, "Geofence " + positions.getName() + " added");
            }
        }
    }

    /***
     * Creates a GeofencingRequest object using the mGeofenceList ArrayList of Geofences
     * Used by {@code #registerGeofences}
     *
     * @return the GeofencingRequest object
     */
    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }

    /***
     * Creates a PendingIntent object using the GeofenceTransitionsIntentService class
     * Used by {@code #registerGeofences}
     *
     * @return the PendingIntent object
     */

    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.

        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(mContext, GeofenceBroadcastReceiver.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        mGeofencePendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
        return mGeofencePendingIntent;
    }


    @Override
    public void onResult(@NonNull Result result) {
        Log.e(TAG, String.format("Error adding/removing geofence : %s",
                result.getStatus().toString()));
    }

}
