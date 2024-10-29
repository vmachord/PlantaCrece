package com.pim.planta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DiarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);  // Asegúrate de que este sea el nombre correcto del layout XML
        setupBottom();
        // Configurar el CalendarView
        CalendarView calendarView = findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Muestra la fecha seleccionada en un Toast
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(DiarioActivity.this, "Fecha seleccionada: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);

        imageButtonPlantadex.setOnClickListener(v -> {
            Intent intent = new Intent(DiarioActivity.this, PlantListActivity.class);
            startActivity(intent);
        });
        imageButtonMaceta.setOnClickListener(view -> {
            Intent intent = new Intent(DiarioActivity.this, JardinActivity.class);
            startActivity(intent);
        });
        imageButtonUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(DiarioActivity.this, PerfilActivity.class);
            startActivity(intent);
        });
    }
}
