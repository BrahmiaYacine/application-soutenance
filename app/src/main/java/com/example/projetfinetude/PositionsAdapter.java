package com.example.projetfinetude;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PositionsAdapter extends RecyclerView.Adapter<PositionsAdapter.PositionsViewHolder> {
    private List<Positions> positionsList;
    private Positions selectedPosition;

 //   private RecyclerViewClickListener listener;

//public PositionsAdapter(List<PositionListe>, RecyclerViewClickListener listener){
//    this.positionsList =
//}


    @NonNull
    @Override
    public PositionsViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.position_item, parent, false);
        return new PositionsViewHolder(v);

    }

    public Positions getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(Positions selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @Override
    public void onBindViewHolder(@NonNull  PositionsViewHolder holder, int position) {
        Positions positions = positionsList.get(position);
        holder.position_Title.setText( positions.getName());
        holder.latitude.setText(positions.getLatitude());
        holder.longitude.setText(positions.getLongitude());
        holder.position_Title.setOnClickListener(v -> {
             selectedPosition = positionsList.get(position);
        });

    }

    @Override
    public int getItemCount() { return positionsList == null ? 0 : positionsList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v , int position);
    }
    RecyclerViewClickListener clickListener;

    public RecyclerViewClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(RecyclerViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class PositionsViewHolder extends RecyclerView.ViewHolder  {
        TextView position_Title ;
        TextView latitude ;
        TextView longitude ;

        public PositionsViewHolder (View v){
            super(v);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v, getAdapterPosition());


                }
            });
            position_Title = v.findViewById(R.id.position_title);
            latitude = v.findViewById(R.id.latitude);
            longitude = v.findViewById(R.id.longitude);
        }
    }

public void setPositionsList(List<Positions> positionsList){
        this.positionsList = positionsList;

    }

}