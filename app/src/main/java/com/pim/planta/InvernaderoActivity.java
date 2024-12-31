package com.pim.planta;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pim.planta.db.DAO;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.Plant;
import com.pim.planta.models.UserLogged;
import com.pim.planta.models.UserPlantRelation;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InvernaderoActivity extends AppCompatActivity {
    private ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invernadero);
        imageView2 = findViewById(R.id.plantoo);

        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
        String selectedPlant = sharedPreferences.getString("selectedPlant", null);

        PlantRepository repository = PlantRepository.getInstance(this);
        DAO dao = repository.getPlantaDAO();
        //setText(selectedPlant, dao);

        ImageButton imageButtonOjo = findViewById(R.id.imageButtonOjo);
        imageButtonOjo.setOnClickListener(v -> {
            Intent intent = new Intent(InvernaderoActivity.this, JardinActivity.class);
            startActivity(intent); // Volver a pantalla anterior
        });

       // Button Mas1 = findViewById(R.id.buttonMas1);
      /*  Mas1.setOnClickListener(v -> {
            incrementGrowCountInBackground(UserLogged.getInstance().getCurrentUser().getId(), selectedPlant, dao);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setText(selectedPlant, dao);
        });

        Button Menos1 = findViewById(R.id.buttonMenos1);
        Menos1.setOnClickListener(v -> {
            decrementGrowCountInBackground(UserLogged.getInstance().getCurrentUser().getId(), selectedPlant, dao);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setText(selectedPlant, dao);
        });    */
    }
    /*private void setText(String selectedPlant, DAO dao){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            int userId = UserLogged.getInstance().getCurrentUser().getId(); // Get the logged-in user's ID
            List<UserPlantRelation> relations = dao.getUserPlantRelations(userId);
            StringBuilder sb = new StringBuilder();
            for (UserPlantRelation relation : relations) {
                // Get the plant name from the Plant table using the plantId
                Plant plant = dao.getPlantaById(relation.plantId);
                sb.append(plant.getName()).append(": ").append(relation.growCount).append(" times\n");
            }
            runOnUiThread(() -> {
                TextView growStatsTextView = findViewById(R.id.growStats);
                growStatsTextView.setText(sb.toString());
                // Actualizar la imagen con base en el nombre de la planta
                if (selectedPlant != null) {
                    updateImage(selectedPlant);
                }
            });
        });
    }*/
    private static void incrementGrowCountInBackground(final int userId, final String plantName, final DAO dao) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Plant plant = dao.getPlantaByName(plantName);
                if (plant != null) {
                    dao.incrementGrowCount(userId, plant.getId());
                }
                return null;
            }
        }.execute();
    }

    private static void decrementGrowCountInBackground(final int userId, final String plantName, final DAO dao) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Plant plant = dao.getPlantaByName(plantName);
                if (plant != null) {
                    dao.decrementGrowCount(userId, plant.getId());
                }
                return null;
            }
        }.execute();
    }
    private void updateImage(String plantName) {
        // Reemplazar los espacios por guiones bajos y eliminar los acentos
        String sanitizedPlantName = plantName.replace(" ", "_").toLowerCase();
        sanitizedPlantName = removeAccents(sanitizedPlantName); // Eliminar caracteres acentuados

        // Crear el nombre del recurso de la imagen usando el nombre de la planta
        String imageName = "image_" + sanitizedPlantName + JardinActivity.currentImageIndex;;
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());

        if (resID != 0) {
            imageView2.setImageResource(resID);
        }else{
            String imageName2 = "image_" + JardinActivity.currentImageIndex; // Accede a la variable estática para la imagen
            int resID2 = getResources().getIdentifier(imageName2, "drawable", getPackageName());
            imageView2.setImageResource(resID2);
        }
    }

    private String removeAccents(String str) {
        return str.replaceAll("[áàäâ]", "a")
                .replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i")
                .replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u")
                .replaceAll("[ç]", "c");
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
        String selectedPlant = sharedPreferences.getString("selectedPlant", null);
        if (selectedPlant != null) {
            updateImage(selectedPlant);
        }
    }
}