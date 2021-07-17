package com.example.projetfinetude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.GeofencingClient;

import java.util.Collections;

public class PositionListe extends AppCompatActivity {

    private PositionsAdapter positionsAdapter;
    private PositionsViewModel positionsViewModel ;

    private TextView positiontitre;

    private Positions positions;
    private TextView title;
    private TextView longitude;
    private TextView latitude;
    private RecyclerView recyclerView;
    private TextView tache_lat;
    private TextView tache_long;
    private TextView tache_nom_pos;

    //geofence inchallah
    private GeofencingClient geofencingClient;


    private Geofencing mGeofencing;

    public void geoFencing() {
        mGeofencing = new Geofencing(this);

        positionsViewModel.getAll().observe(this, positionsList -> {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.position_liste);
        tache_lat = findViewById(R.id.tache_lat);
        tache_long = findViewById(R.id.tache_long);
        tache_nom_pos = findViewById(R.id.tache_nom_pos);


        initViews();
        positionsViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(PositionsViewModel.class);
        positionsViewModel.getAll().observe((LifecycleOwner) this, positions -> {
                    Collections.reverse(positions);
                    positionsAdapter.setPositionsList(positions);
                    positionsAdapter.notifyDataSetChanged();
                    positionsAdapter.setClickListener((v, position) -> {
                        Positions p = positions.get(position);

                        //tache_lat.setText(p.getLatitude());
                        //tache_long.setText(p.getLongitude());
                        Intent i5 = new Intent(this,AjouterTache.class);
                        i5.putExtra("position name", p.getName());
                        i5.putExtra("longitude", p.getLongitude());
                        i5.putExtra("latitude", p.getLatitude());
                        startActivityForResult(i5,1);




                    });

                     geoFencing();

            Intent i4 = getIntent();
            String cas = i4.getStringExtra("name");
            recyclerView = findViewById(R.id.positionListe);
            //RecyclerView recyclerView = findViewById(R.id.recycler);
           // recyclerView.addOnItemTouchListener(
                    /*new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                          //  Log.e("tag",positionsAdapter.getItemId(recyclerView.getChildCount())+"");


                        }

                        @Override public void onLongItemClick(View view, int position) {
                            // do whatever
                          //  Log.e("tag","nchallah");
                        }
                    })
            );
            if (cas=="cas1"){    }*/

                });



    }

    private void initViews() {
        RecyclerView recyclerView = findViewById(R.id.positionListe);
        title = findViewById(R.id.position_title);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        positionsAdapter = new PositionsAdapter();
        recyclerView.setAdapter(positionsAdapter);
    }

   /* public void LatLongToTache(View view) {
        positiontitre = findViewById(R.id.position_title);
        Log.e("Tag","tag");
        Log.e("TAF",""+positiontitre);

    }*/
}