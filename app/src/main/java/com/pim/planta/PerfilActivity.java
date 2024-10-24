package com.pim.planta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import android.graphics.Color;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity{

    private ImageView profileImageView;
    private TextView userNameTextView;
    private BarChart barChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeNameAndProfile();


    }
    private void initializeGraph(){
        // Configurar el gráfico de barras
        barChart = findViewById(R.id.bar_chart);

        // Crear entradas para el gráfico de barras
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, new float[]{2f, 3f, 1f, 4f, 5f}));  // Cada valor representa un color en la barra

        BarDataSet barDataSet = new BarDataSet(barEntries, "Progreso Diario");
        barDataSet.setColors(new int[]{Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.MAGENTA}); // Colores de la barra

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
        userNameTextView.setText("Usuario Ejemplo");
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

