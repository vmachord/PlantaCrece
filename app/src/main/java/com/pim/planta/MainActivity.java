package com.pim.planta;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.Plant;
import com.pim.planta.db.DatabaseExecutor;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PlantRepository plantaRepo;  // Para usar Room

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonLogin = findViewById(R.id.buttonEmpezar);

        plantaRepo = new PlantRepository(this);

        // Inserción en un hilo secundario
        DatabaseExecutor.execute(() -> {
            Plant nuevaPlanta = new Plant("Cactus", 100, "100", "100", "Planta de prueba");
            plantaRepo.getPlantaDAO().insert(nuevaPlanta);

            // Recuperar datos en un hilo secundario
            List<Plant> plantas = plantaRepo.getPlantaDAO().getAllPlantas();
            for (Plant p : plantas) {
                Log.d("MainActivity", "Planta: " + p.getName() + ", ID: " + p.getId());
            }
        });

        // Escuchamos el evento de clic
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}