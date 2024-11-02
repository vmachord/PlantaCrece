package com.pim.planta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DiarioActivity extends AppCompatActivity {

    private int selectedColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBottom();

        setContentView(R.layout.activity_diary);

        EditText inputText = findViewById(R.id.input_text);
        CalendarView calendarView = findViewById(R.id.calendar_view);
        EditText inputAnnotation = findViewById(R.id.input_annotation);
        LinearLayout colorPicker = findViewById(R.id.color_picker);

        // Al hacer clic en el cuadro de texto inicial, mostrar el calendario, selector de colores y barra de anotación
        inputText.setOnClickListener(v -> {
            calendarView.setVisibility(View.VISIBLE);
            inputAnnotation.setVisibility(View.VISIBLE);
            colorPicker.setVisibility(View.VISIBLE);
        });

        // Capturar la fecha seleccionada en el calendario
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(this, "Fecha seleccionada: " + selectedDate, Toast.LENGTH_SHORT).show();
        });



        // Selector de color
        findViewById(R.id.color1).setOnClickListener(v -> selectedColor = 0xFFFF0000);
        findViewById(R.id.color2).setOnClickListener(v -> selectedColor = 0xFF00FF00);
        findViewById(R.id.color3).setOnClickListener(v -> selectedColor = 0xFF0000FF);
        findViewById(R.id.color4).setOnClickListener(v -> selectedColor = 0xFFFFFF00);
        findViewById(R.id.color5).setOnClickListener(v -> selectedColor = 0xFFFF00FF);

    }


    public void setupBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        imageButtonLupa.setEnabled(false); // Deshabilita el boton
        imageButtonLupa.setImageAlpha(128); // Oscurece el boton
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

    public void addEmotion(){

    }
}
