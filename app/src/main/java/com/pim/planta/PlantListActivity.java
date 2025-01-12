package com.pim.planta;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
                // Actualizar el texto y mostrar la imagen
                plantaElegidaTextView.setText("Planta Elegida: " + plant.getName());
                imageView6.setVisibility(View.VISIBLE);

                // Animación de desvanecimiento
                plantaElegidaTextView.setAlpha(0f);
                plantaElegidaTextView.animate().alpha(1f).setDuration(300).start();

                imageView6.setAlpha(0f);
                imageView6.animate().alpha(1f).setDuration(300).start();

                // Efecto de rebote en la imagen
                imageView6.animate()
                        .scaleX(1.2f).scaleY(1.2f)
                        .setDuration(200)
                        .withEndAction(() -> imageView6.animate()
                                .scaleX(1f).scaleY(1f)
                                .setDuration(200)
                                .start())
                        .start();

                // Guardar la planta seleccionada
                SharedPreferences sharedPreferences = getSharedPreferences("plant_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selectedPlant", plant.getName());
                editor.apply();

                Intent intent = new Intent(PlantListActivity.this, JardinActivity.class);
                startActivity(intent);
            }
        });
    }

    // Método para animar los botones de la barra inferior
    private void animateButton(View view) {
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1.0f)
        );
        animator.setDuration(150); // Duración de la animación
        animator.start();
    }

    public void setupBottom() {
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        imageButtonPlantadex.setEnabled(false); // Deshabilita el botón
        imageButtonPlantadex.setImageAlpha(128); // Oscurece el botón
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);

        imageButtonLupa.setOnClickListener(v -> {
            animateButton(v); // Añade la animación
            Intent intent = new Intent(PlantListActivity.this, DiaryActivity.class);
            startActivity(intent);
        });

        imageButtonMaceta.setOnClickListener(view -> {
            animateButton(view); // Añade la animación
            Intent intent = new Intent(PlantListActivity.this, JardinActivity.class);
            startActivity(intent);
        });

        imageButtonUsuario.setOnClickListener(v -> {
            animateButton(v); // Añade la animación
            Intent intent = new Intent(PlantListActivity.this, PerfilActivity.class);
            startActivity(intent);
        });
    }
}
