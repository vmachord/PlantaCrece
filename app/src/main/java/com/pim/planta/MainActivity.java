package com.pim.planta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pim.planta.db.DatabaseHelper;
import com.pim.planta.db.SQLite;

import com.pim.planta.db.DatabasePlantoo;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.Plant;

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


        try {
            // Inicializar Room para manejar la base de datos
            plantaRepo = new PlantRepository(this);

            // Crear la base de datos y agregar un usuario en SQLite
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            dbHelper.getWritableDatabase();  // Crea la base de datos si no existe

            // Agregar una planta usando Room (hilo de fondo)
            new Thread(() -> {
                Plant planta = new Plant("Cactus", 100, "100", "100", "Planta de prueba");
                try {
                    plantaRepo.getPlantaDAO().insert(planta);
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Planta agregada", Toast.LENGTH_SHORT).show());
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error al agregar planta", Toast.LENGTH_SHORT).show());
                }
            }).start();

        } catch (Exception e) {
            // Manejo de errores
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

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