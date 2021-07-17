package com.example.projetfinetude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.GeofencingClient;


public class HomePage2 extends AppCompatActivity {

    private PositionsAdapter positionsAdapter;
    private GeofencingClient geofencingClient;
    private PositionsViewModel positionsViewModel ;
    private Geofencing mGeofencing;

    /*public void geoFencing() {
        mGeofencing = new Geofencing(this);

        positionsViewModel.getAll().observe(this, positionsList -> {
            *//*for(Tiers tiers : tiersList) {
                tiers.setPosition(viewModel.getPositionById((int)tiers.getPosition_id()));
            }*//*
            //  long id = Long.valueOf(positionsViewModel.getById(positions.getId()));
            //  for(Positions positions : positionsList) {
            // Transformations.map(positionsViewModel.getById(positions.getId()));
            //     positions.setId( positionsViewModel.getById(positions.getId()));

            // }

            mGeofencing.updateGeofencesList(positionsList);
            mGeofencing.registerAllGeofences();
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);
       // geoFencing();
    }



    public void goTocree_tache(View view) {
        Intent i1 = new Intent(this, AjouterTache.class );
        startActivity(i1);
    }

    public void goTotache_liste(View view) {
        Intent i2 = new Intent(this, TacheListe.class );
        startActivity(i2);
    }

    public void goTocree_Position(View view) {
        Intent i3 = new Intent(this, CurrentLocation.class );
        startActivity(i3);

    }


    public void goToposition_list(View view) {
        Intent i4 = new Intent(this, PositionListe.class );
        startActivity(i4);

        i4.putExtra("name","cas2");

    }
}