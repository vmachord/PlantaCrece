package com.pim.planta;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DiarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);  // Asegúrate de que este sea el nombre correcto del layout XML

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
}
