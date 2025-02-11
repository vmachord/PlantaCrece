package com.pim.planta.models;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pim.planta.R;
import com.pim.planta.db.DAO;
import com.pim.planta.db.DatabaseExecutor;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    private List<Plant> plantList;
    private OnItemClickListener onItemClickListener;
    private Typeface aventaFont;
    private DAO dao;
    private User user;
    private Context context;

    public PlantAdapter(List<Plant> plantList, Typeface aventaFont, DAO dao, User user, Context context) {
        this.plantList = plantList;
        this.aventaFont = aventaFont;
        this.dao = dao;
        this.user = user;
        this.context = context;
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
        if (plant.getNickname() != null && !plant.getNickname().isEmpty())
            holder.plantNameTextView.setText(plant.getNickname() + " XP: " + plant.getXp() + "/" + plant.getXpMax());
        else
            holder.plantNameTextView.setText(plant.getName());
        holder.plantImageView.setImageResource(plant.getImageResourceId());
        holder.plantNameTextView.setTypeface(aventaFont);
        holder.plantDescriptionTextView.setTypeface(aventaFont);
        holder.plantGrowthCountTextView.setTypeface(aventaFont);
        holder.plantNameTextView.setTextColor(ContextCompat.getColor(context,R.color.dark_green));
        holder.plantDescriptionTextView.setTextColor(ContextCompat.getColor(context,R.color.dark_green));
        holder.plantGrowthCountTextView.setTextColor(ContextCompat.getColor(context,R.color.dark_green));
        holder.plantDescriptionTextView.setText(plant.getDescription());
        if (user != null) {
            DatabaseExecutor.executeAndWait(() -> {
                try {
                    int growthCount = Math.max(0, dao.getGrowCount(user.getId(), dao.getPlantaByName(plant.getName()).getId()));
                    holder.plantGrowthCountTextView.setText("Growth count: " + growthCount);
                } catch (Exception e) {
                    Log.e("PlantAdapter", "Error getting growth count", e);
                }
            });
        } else {
            Log.e("PlantAdapter", "User is null");
        }
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
        public TextView plantGrowthCountTextView;

        public PlantViewHolder(View itemView) {
            super(itemView);
            plantNameTextView = itemView.findViewById(R.id.plant_name_textview);
            plantImageView = itemView.findViewById(R.id.plant_imageview);
            plantDescriptionTextView = itemView.findViewById(R.id.plant_description_textview);
            plantGrowthCountTextView = itemView.findViewById(R.id.plant_growth_count);
            plantNameTextView.setTypeface(aventaFont);
            plantDescriptionTextView.setTypeface(aventaFont);
            plantGrowthCountTextView.setTypeface(aventaFont);
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
