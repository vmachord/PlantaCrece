package com.pim.planta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.Plant;
import com.pim.planta.models.User;

public class MainActivity extends AppCompatActivity {

    private PlantRepository plantaRepo;

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


        // Poblar la base de datos en caso de que no lo este
        DatabaseExecutor.execute(() -> {
            if (plantaRepo.getPlantaDAO().getAllUsuarios().isEmpty()){
                User user_prueba = new User ("elCigala", "admin@gmail.com", "1234");
                plantaRepo.getPlantaDAO().insert(user_prueba);
            }

            if (plantaRepo.getPlantaDAO().getAllPlantas().isEmpty()){
                Plant nuevaPlanta1 = new Plant("Rosa","image_rosa", R.drawable.image_rosa, 100, 100, "Perfecta para regalo entre enamorados");
                Plant nuevaPlanta2 = new Plant("Margarita","image_margarita", R.drawable.image_margarita,100, 100, "Simple y bonita, como tu <3");
                Plant nuevaPlanta3 = new Plant("Girasol","image_girasol", R.drawable.image_girasol, 100, 100, "Persiguiendo la estrella más grande");
                Plant nuevaPlanta4 = new Plant("Tulipan","image_tulipan", R.drawable.image_tulipan, 100, 100, "De diversos y vivos colores");
                Plant nuevaPlanta5 = new Plant("Diente de León","image_diente_de_leon", R.drawable.image_diente_de_leon, 100, 100, "Una metomorfosis unica");
                plantaRepo.getPlantaDAO().insert(nuevaPlanta1);
                plantaRepo.getPlantaDAO().insert(nuevaPlanta2);
                plantaRepo.getPlantaDAO().insert(nuevaPlanta3);
                plantaRepo.getPlantaDAO().insert(nuevaPlanta4);
                plantaRepo.getPlantaDAO().insert(nuevaPlanta5);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}