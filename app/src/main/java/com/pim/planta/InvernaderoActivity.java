package com.pim.planta;

import android.content.Intent;
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
        updateImage();

        ImageButton imageButtonOjo = findViewById(R.id.imageButtonOjo);
        imageButtonOjo.setOnClickListener(v -> {
            Intent intent = new Intent(InvernaderoActivity.this, JardinActivity.class);
            startActivity(intent); // Volver a pantalla anterior
        });
    }
    private void updateImage() {
        String imageName = "image_" + JardinActivity.currentImageIndex; // Accede a la variable estática para la imagen
        int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
        imageView2.setImageResource(resID);
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateImage(); // Actualiza la imagen al volver a la actividad
    }
}