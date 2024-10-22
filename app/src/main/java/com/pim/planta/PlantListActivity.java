package com.pim.planta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.pim.planta.models.*;

public class PlantListActivity extends AppCompatActivity {

    private RecyclerView plantListRecyclerView;
    private PlantAdapter plantAdapter;
    private List<Plant> plantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantlist);

        plantListRecyclerView = findViewById(R.id.plant_list_recyclerview);
        plantListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        plantList = new ArrayList<>();
        // Agrega las plantas a la lista
        plantList.add(new Plant("Rosa", R.drawable.rosa, null, null, "Perfecta para regalo entre enamorados"));
        plantList.add(new Plant("Margarita", R.drawable.margarita, null, null, "Simple y bonita"));
        plantList.add(new Plant("Girasol", R.drawable.girasol, null, null, "Persiguiendo la estrella mas grande"));
        plantList.add(new Plant("Tulipan", R.drawable.tulipan, null, null, "De diversos y vivos colores"));
        plantList.add(new Plant("Diente de león", R.drawable.diente_de_leon, null, null, "Una metamorfosis unica"));

        plantAdapter = new PlantAdapter(plantList);
        plantListRecyclerView.setAdapter(plantAdapter);
    }
}
