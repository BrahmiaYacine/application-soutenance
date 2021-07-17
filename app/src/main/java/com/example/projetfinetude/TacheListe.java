package com.example.projetfinetude;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

public class TacheListe extends AppCompatActivity {
    private TachesViewModel viewModel;
    private EditText title;
    private EditText content;
    private TachesAdapter tachesAdapter;
    private Positions positions ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tache_liste);

        initViews();
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(TachesViewModel.class);
        viewModel.getAll().observe((LifecycleOwner) this, taches -> {
            Collections.reverse(taches);
            tachesAdapter.setTachesList(taches);
            tachesAdapter.notifyDataSetChanged();
        });
    }
    private void initViews(){
        RecyclerView recyclerView = findViewById(R.id.tachesliste);
        title = findViewById(R.id.tache_title);
        content = findViewById(R.id.description);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tachesAdapter = new TachesAdapter();
        recyclerView.setAdapter(tachesAdapter);
    }

}