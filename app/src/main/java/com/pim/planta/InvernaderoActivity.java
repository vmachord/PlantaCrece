package com.pim.planta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InvernaderoActivity extends AppCompatActivity {
    private ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invernadero);
        imageView2 = findViewById(R.id.imageView2);

        SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
        String selectedPlant = sharedPreferences.getString("selectedPlant", null);

        // Actualizar la imagen con base en el nombre de la planta
        if (selectedPlant != null) {
            updateImage(selectedPlant);
        }

        ImageButton imageButtonOjo = findViewById(R.id.imageButtonOjo);
        imageButtonOjo.setOnClickListener(v -> {
            Intent intent = new Intent(InvernaderoActivity.this, JardinActivity.class);
            startActivity(intent); // Volver a pantalla anterior
        });
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