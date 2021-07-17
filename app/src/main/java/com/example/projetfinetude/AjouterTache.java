package com.example.projetfinetude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class AjouterTache extends AppCompatActivity {
    private TachesViewModel tachesViewModel;
    private EditText title;
    private EditText content;
    private TachesAdapter tachesAdapter;
    private Positions positions ;
    private TextView tache_nom_pos;
    private TextView tache_long;
    private TextView tache_lat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_tache);

        //initViews();
      /*  viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(TachesViewModel.class);
        viewModel.getAll().observe((LifecycleOwner) this, taches -> {
            Collections.reverse(taches);
            tachesAdapter.setTachesList(taches);
            tachesAdapter.notifyDataSetChanged();
        });*/


//assign variable
        title = findViewById(R.id.tache_title);
        content = findViewById(R.id.description);
        tache_nom_pos = findViewById(R.id.tache_nom_pos);
        tache_long = findViewById(R.id.tache_long);
        tache_lat = findViewById(R.id.tache_lat);

        String rempli_tache_nom_pos = getIntent().getStringExtra("position name");
        String rempli_tache_long = getIntent().getStringExtra("longitude");
        String rempli_tache_lat = getIntent().getStringExtra("latitude");

        tache_nom_pos.setText(rempli_tache_nom_pos);
        tache_long.setText( rempli_tache_long);
        tache_lat.setText(rempli_tache_lat);



    }
   /* private void initViews(){
        RecyclerView recyclerView = findViewById(R.id.tachesliste);
        title = findViewById(R.id.tache_title);
        content = findViewById(R.id.description);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tachesAdapter = new TachesAdapter();
        recyclerView.setAdapter(tachesAdapter);
    }*/





    public void addTache(View view) {
        String t = title.getText().toString();
        String c = content.getText().toString();
        String np =  tache_nom_pos.getText().toString();
        String lng = tache_long.getText().toString();
        String lat = tache_lat.getText().toString();
        if (!t.isEmpty()) {
            Taches tache = new Taches(t, c, np, lng, lat);
//            tachesViewModel.insert(tache);


            tachesViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(TachesViewModel.class);
            tachesViewModel.insert(new Taches(title.getText().toString(), content.getText().toString(),np,
                    tache_long.getText().toString(), tache_lat.getText().toString()));

           // title.setText("");
            content.setText("");
        }
    }


    public void goToposition_list(View view) {
        Intent i1 = new Intent(this, PositionListe.class );
        startActivity(i1);

    }

    public void associer_position(View view) {
        Intent i4 = new Intent(this,PositionListe.class);
        i4.putExtra("name","cas1");
        startActivity(i4);
    }
}