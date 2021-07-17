package com.example.projetfinetude;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentLocation extends AppCompatActivity {
    private PositionsViewModel positionsViewModel;
    private PositionsAdapter positionsAdapter;
    private Positions positions;
    private TextView title;
    private TextView longitude;
    private TextView latitude;

    //geofence inchallah
    private GeofencingClient geofencingClient;




    //Initialize variable
    Button btlocation;
    EditText editTextnomPosition;
    TextView textViewLatitude, textViewLongitude, textView3, textView4, textViewAdress;
    FusedLocationProviderClient fusedLocationProviderClient;
    Address address;

    int radius = 100;

    private Geofencing mGeofencing;

    public void geoFencing() {
        mGeofencing = new Geofencing(this);

        positionsViewModel.getAll().observe(this, positionsList -> {

            //tbanli mns79hch 1

            /*for(Tiers tiers : tiersList) {
                tiers.setPosition(viewModel.getPositionById((int)tiers.getPosition_id()));
            }*/
            //  long id = Long.valueOf(positionsViewModel.getById(positions.getId()));
            //  for(Positions positions : positionsList) {
            // Transformations.map(positionsViewModel.getById(positions.getId()));
            //     positions.setId( positionsViewModel.getById(positions.getId()));

            // }

            mGeofencing.updateGeofencesList(positionsList);
            mGeofencing.registerAllGeofences();
        });
    }

    public CurrentLocation() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_location_main);
        //nchallah yadkhol base de donneee
        //initViews();
       /* positionsViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(PositionsViewModel.class);
        positionsViewModel.getAll().observe((LifecycleOwner) this, positions -> {
            Collections.reverse(positions);
            positionsAdapter.setPositionsList(positions);
            positionsAdapter.notifyDataSetChanged();
            geoFencing();
*/


            // not sure if its in place
            //besmellah geofencing
           /* Geofence geofence1 = null ;
            for (Positions p : positions) {
                geofence1 = new Geofence.Builder()
                        .setRequestId(String.valueOf(0)) // Geofence ID
                        .setCircularRegion(Double.parseDouble(p.getLatitude()), Double.parseDouble(p.getLongitude()), radius) // defining fence region
                        .setExpirationDuration(50) // expiring date
                        // Transition types that it should look for
                        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                        .build();

            }
            //inchallah geofence
             geofencingClient = LocationServices.getGeofencingClient(this);

            //to monitor geofence1
            assert geofence1 != null;
            GeofencingRequest request = new GeofencingRequest.Builder()
                    // Notification to trigger when the Geofence is created
                    .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                    .addGeofence(geofence1) // add a Geofence
                    .build();
            // i need a broadcast receiver

/*
                private PendingIntent getGeofencePendingIntent() {
                    // Reuse the PendingIntent if we already have it.
                    if (geofencePendingIntent != null) {
                        return geofencePendingIntent;
                    }
                    Intent intent = new Intent(this, GeofenceBroadcastReceiver.class);
                    // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
                    // calling addGeofences() and removeGeofences().
                    geofencePendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.
                            FLAG_UPDATE_CURRENT);
                    return geofencePendingIntent;
                }
*/


            // end geofencing
        /*      });*/

            //Assign variable
            btlocation = findViewById(R.id.bt_location);
            textViewLatitude = findViewById(R.id.text_view_Latitude);
            textViewLongitude = findViewById(R.id.text_view_Longitude);
            textView3 = findViewById(R.id.text_view3);
            textView4 = findViewById(R.id.text_view4);
            textViewAdress = findViewById(R.id.text_view_adress);
            editTextnomPosition = findViewById(R.id.edit_text_nomposition);

            //Initialize fusedLocationProviderClient
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

            btlocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Check permission
                    if (ActivityCompat.checkSelfPermission(CurrentLocation.this
                            , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        // when permission granted
                        getLocation();
                    } else {
                        //When permission denied
                        ActivityCompat.requestPermissions(CurrentLocation.this
                                , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                    }
                }
            });


    }

        private void getLocation () {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {

                    //Initialize Location
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            //Initialize geoCoder
                            Geocoder geocoder = new Geocoder(CurrentLocation.this, Locale.getDefault());
                            // Initialze address list
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1
                            );
                            address = addresses.get(0);
                            //Set latitude on TextView
                            textViewLatitude.setText(Html.fromHtml("<font color ='#6200EE'><b>Latitude :</b><br></font>" + addresses.get(0).getLatitude()
                            ));
                            //Set longitude on TextView
                            textViewLongitude.setText(Html.fromHtml("<font color ='#6200EE'><b>Longitude :</b><br></font>" + addresses.get(0).getLongitude()
                            ));
                            //set Country name
                            textView3.setText(Html.fromHtml("<font color ='#6200EE'><b>Country name :</b><br></font>" + addresses.get(0).getCountryName()
                            ));
                            //set locality
                            textView4.setText(Html.fromHtml("<font color ='#6200EE'><b>Locality :</b><br></font>" + addresses.get(0).getLocality()
                            ));
                            //Set address
                            textViewAdress.setText(Html.fromHtml("<font color ='#6200EE'><b>Adress :</b><br></font>" + addresses.get(0).getAddressLine(0)
                            ));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }



        public void goToPosition_List (View view){
            Intent i2 = new Intent(this, PositionListe.class);
            startActivity(i2);
        }


       /* private void initViews () {
            RecyclerView recyclerView = findViewById(R.id.positionListe);
            title = findViewById(R.id.position_title);
            latitude = findViewById(R.id.latitude);
            longitude = findViewById(R.id.longitude);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            positionsAdapter = new PositionsAdapter();
            recyclerView.setAdapter(positionsAdapter);
        }*/


        public void enregistrer (View view){
            //PositionsViewModel viewModel;
            positionsViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(PositionsViewModel.class);
            positionsViewModel.insert(new Positions(editTextnomPosition.getText() + "", address.getLatitude() + "", address.getLongitude() + ""));
            //  String t = textViewAdress.getText().toString();
            // String c = textViewLatitude.getText().toString();
            //  String f = textViewLongitude.getText().toString();
            // if (!t.isEmpty()) {
            //     Positions position = new Positions(t, c, f);
            //     viewModel.insert(position);
            //      title.setText("");
            //     latitude.setText("");
        }


    }
