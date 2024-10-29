package com.pim.planta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pim.planta.models.Plant;
import com.pim.planta.models.PlantAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlantListActivity extends AppCompatActivity {

    private RecyclerView plantListRecyclerView;
    private PlantAdapter plantAdapter;
    private List<Plant> plantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantlist);

        ImageButton lupaButton = findViewById(R.id.imageButtonLupa);
        ImageButton macetaButton = findViewById(R.id.imageButtonMaceta);
        ImageButton plantadexButton = findViewById(R.id.imageButtonPlantadex);
        plantadexButton.setEnabled(false); //Boton plantadex deshabilitado al estar en plantadex ya
        plantadexButton.setImageAlpha(128);
        ImageButton usuarioButton = findViewById(R.id.imageButtonUsuario);
        lupaButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlantListActivity.this, DiarioActivity.class);
            startActivity(intent);
        });
        macetaButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlantListActivity.this, JardinActivity.class);
            startActivity(intent);
        });
        plantadexButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlantListActivity.this, PlantListActivity.class);
            startActivity(intent);
        });
        usuarioButton.setOnClickListener(v -> {
            Intent intent = new Intent(PlantListActivity.this, PerfilActivity.class);
            startActivity(intent);
        });//Listeners para los botones de la barra de navegacion inferior

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
