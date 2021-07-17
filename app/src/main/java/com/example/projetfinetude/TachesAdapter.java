package com.example.projetfinetude;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TachesAdapter extends RecyclerView.Adapter<TachesAdapter.TachesViewHolder> {
    private List<Taches> tacheList;

    @NonNull
    @Override
    public TachesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tache_item, parent, false);
        return new TachesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TachesViewHolder holder, int position) {
        Taches tache = tacheList.get(position);
        holder.title.setText(tache.getTitle());
        holder.description.setText(tache.getDescription());
    }

    @Override
    public int getItemCount() {
        return tacheList == null ? 0 : tacheList.size();
    }

    static class TachesViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        TachesViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tache_title);
            description = v.findViewById(R.id.description);
        }
    }

    public void setTachesList(List<Taches> tacheList) {
        this.tacheList = tacheList;
    }
}