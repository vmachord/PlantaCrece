package com.pim.planta;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;


public class PerfilActivity extends AppCompatActivity{

    private ImageView profileImageView;
    private TextView userNameTextView;
    private BarChart barChart;
    private List<BarDataSet> dataSets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeNameAndProfile();
        initializeGraph();
        setupBottom();

    }

    public void setupBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);
        imageButtonUsuario.setEnabled(false); // Deshabilita el boton
        imageButtonUsuario.setImageAlpha(128); // Oscurece el boton

        imageButtonLupa.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, DiarioActivity.class);
            startActivity(intent);
        });
        imageButtonPlantadex.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, PlantListActivity.class);
            startActivity(intent);
        });
        imageButtonMaceta.setOnClickListener(view -> {
            Intent intent = new Intent(PerfilActivity.this, JardinActivity.class);
            startActivity(intent);
        });
    }
    private void initializeGraph(){
        // Configurar el gráfico de barras
        barChart = findViewById(R.id.bar_chart);

        // Crear entradas para el gráfico de barras
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        // Añadir barras para diferentes días con valores en distintas posiciones
        barEntries.add(new BarEntry(0, new float[]{2f, 3f, 1f, 4f, 5f}));
        barEntries.add(new BarEntry(1, new float[]{3f, 1f, 2f, 7f, 1f}));
        barEntries.add(new BarEntry(2, new float[]{10f, 3f, 1f, 4f, 5f}));
        barEntries.add(new BarEntry(3, new float[]{2f, 3f, 1f, 4f, 5f}));

        // Añadir los colores al gráfico de barras
        BarDataSet barDataSet = addColorOnGraph(barEntries);

        // Configurar el BarData y agregarlo al gráfico
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        // Opcional: ajustes visuales
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        barChart.getAxisRight().setEnabled(false);  // Deshabilitar eje derecho
        barChart.getAxisLeft().setAxisMinimum(0f);  // Asegurar que el eje comience en 0
        barChart.getDescription().setEnabled(false);  // Deshabilitar descripción
        barChart.invalidate();  // Refrescar gráfico
    }

    private void initializeNameAndProfile(){
        profileImageView = findViewById(R.id.profile_image);
        userNameTextView = findViewById(R.id.user_name);

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarImagenDePerfil();
            }
        });
        userNameTextView.setText("Kun Aguero");
    }

    private  BarDataSet addColorOnGraph(ArrayList<BarEntry> barEntries){
        BarDataSet barDataSet = new BarDataSet(barEntries, "Progreso Diario");
        barDataSet.setColors(new int[]{Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.MAGENTA}); // Colores de la barra
        return barDataSet;
    }

    // Método para cambiar la imagen de perfil
    private void cambiarImagenDePerfil() {
        Toast.makeText(this, "Cambiar imagen de perfil", Toast.LENGTH_SHORT).show();

        // Aquí puedes abrir una actividad para seleccionar una imagen o usar un selector de imágenes
        // Por ejemplo, puedes abrir la galería con una Intent:
        // Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // startActivityForResult(intent, 100);

        profileImageView.setImageResource(R.drawable.default_profile);
    }

    // Si usas un Intent para seleccionar la imagen, puedes sobrescribir onActivityResult aquí.
}

