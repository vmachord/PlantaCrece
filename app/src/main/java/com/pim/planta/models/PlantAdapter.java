package com.pim.planta.models;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pim.planta.R;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    private List<Plant> plantList;
    private OnItemClickListener onItemClickListener;
    private Typeface aventaFont;

    public PlantAdapter(List<Plant> plantList, Typeface aventaFont) {
        this.plantList = plantList;
        this.aventaFont = aventaFont;
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_item, parent, false);
        return new PlantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        Plant plant = plantList.get(position);
        holder.plantNameTextView.setText(plant.getName());
        holder.plantImageView.setImageResource(plant.getImageResourceId());
        holder.plantDescriptionTextView.setText(plant.getDescription());


        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(plant); // Llamar a la función de la actividad
            }
        });
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public class PlantViewHolder extends RecyclerView.ViewHolder {
        public TextView plantNameTextView;
        public ImageView plantImageView;
        public TextView plantDescriptionTextView;

        public PlantViewHolder(View itemView) {
            super(itemView);
            plantNameTextView = itemView.findViewById(R.id.plant_name_textview);
            plantImageView = itemView.findViewById(R.id.plant_imageview);
            plantDescriptionTextView = itemView.findViewById(R.id.plant_description_textview);
            plantNameTextView.setTypeface(aventaFont);
            plantDescriptionTextView.setTypeface(aventaFont);
        }
    }
    // Interface para el manejo de clic
    public interface OnItemClickListener {
        void onItemClick(Plant plant);
    }

    // Método para configurar el listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
