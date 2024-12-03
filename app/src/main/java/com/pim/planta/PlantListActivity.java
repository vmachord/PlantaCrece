package com.pim.planta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pim.planta.db.DAO;
import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.Plant;
import com.pim.planta.models.PlantAdapter;

import java.util.List;

public class PlantListActivity extends AppCompatActivity {

    private RecyclerView plantListRecyclerView;
    private PlantAdapter plantAdapter;
    private List<Plant> plantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PlantRepository repository = PlantRepository.getInstance(this);
        DAO dao = repository.getPlantaDAO();

        DatabaseExecutor.execute(() -> {
            plantList = dao.getAllPlantas();
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantlist);
        setupBottom();

        // Referencia al TextView donde se mostrará la planta elegida
        final TextView plantaElegidaTextView = findViewById(R.id.textView3);
        final ImageView imageView6 = findViewById(R.id.imageView6);
        imageView6.setVisibility(View.INVISIBLE);

        plantListRecyclerView = findViewById(R.id.plant_list_recyclerview);
        plantListRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        plantAdapter = new PlantAdapter(plantList);
        plantListRecyclerView.setAdapter(plantAdapter);
        plantAdapter.setOnItemClickListener(new PlantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Plant plant) {
                plantaElegidaTextView.setText("Planta Elegida: " + plant.getName());
                imageView6.setVisibility(View.VISIBLE);

                SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selectedPlant", plant.getName());
                editor.apply();

            }
        });
    }

    public void setupBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        imageButtonPlantadex.setEnabled(false); // Deshabilita el boton
        imageButtonPlantadex.setImageAlpha(128); // Oscurece el boton
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);

        imageButtonLupa.setOnClickListener(v -> {
            Intent intent = new Intent(PlantListActivity.this, DiaryActivity.class);
            startActivity(intent);
        });
        imageButtonMaceta.setOnClickListener(view -> {
            Intent intent = new Intent(PlantListActivity.this, JardinActivity.class);
            startActivity(intent);
        });
        imageButtonUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(PlantListActivity.this, PerfilActivity.class);
            startActivity(intent);
        });
    }
}
