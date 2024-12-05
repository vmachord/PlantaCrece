package com.pim.planta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.Calendar;
import com.pim.planta.models.CalendarDraw;
import com.pim.planta.models.DiaryEntry;
import com.pim.planta.models.Plant;
import com.pim.planta.models.User;
import com.pim.planta.models.UserLogged;
import com.pim.planta.models.OnDiaryEntryListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class DiaryActivity extends AppCompatActivity  {

    private int selectedColor = 0;

    private CalendarDraw calendarDraw;

    private PlantRepository plantRepo;

    private List<DiaryEntry> diaryEntries;

    private Date date;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        setUpBottom();

        calendarDraw = findViewById(R.id.calendar_draw);
        calendarDraw.setVisibility(View.VISIBLE);

        plantRepo = PlantRepository.getInstance(this);

        loadEmotions();

        calendarDraw.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                // Posiciones de los botones de cambio de mes
                float centerX1 = calendarDraw.getHeight() / 5;
                float centerX2 = 4 * calendarDraw.getWidth() / 5;
                float centerY = 60;
                float radius = 50;

                // Comprobación de toque dentro de las hitboxes
                if (Math.pow(x - centerX1, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                    Log.d("onTouch", "HA TOCADO FLECHA IZQUIERDA");
                    calendarDraw.prevMonth();
                    return true;
                } else if (Math.pow(x - centerX2, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                    Log.d("onTouch", "HA TOCADO FLECHA DERECHA");
                    calendarDraw.nextMonth();
                    return true;
                }

                // Detectar si se tocó un día específico
                int dayClicked = calendarDraw.getDayFromCoordinates(motionEvent.getX(), motionEvent.getY());
                if (dayClicked != -1) {
                    // Obtener la fecha en milisegundos para el día tocado
                    LocalDate currentDay = LocalDate.of(calendarDraw.getCurrentYear(), calendarDraw.getCurrentMonth(), dayClicked);
                    long dateInMillis = currentDay.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

                    // Llamar al métod para mostrar el diálogo de emociones, pasando el `dateInMillis` y el listener
                    calendarDraw.showEmotionDialog(dateInMillis, new OnDiaryEntryListener() {
                        @Override
                        public void onDiaryEntryCreated(DiaryEntry entry) {
                            saveDiaryEntry(entry);
                            calendarDraw.invalidate();
                        }
                    });
                }
            }
                return true;
        });
    }

    private void saveDiaryEntry(DiaryEntry entry) {
        DatabaseExecutor.execute(() -> {
            plantRepo.getPlantaDAO().insert(entry);
        });
        Toast.makeText(this, "Entrada de diario guardada", Toast.LENGTH_SHORT).show();
    }

    private void loadEmotions(){
        User user = UserLogged.getInstance().getCurrentUser();
        DatabaseExecutor.execute(() -> {
            diaryEntries = plantRepo.getPlantaDAO().getEntradasByUserId(user.getId());
            if (!diaryEntries.isEmpty()){
                calendarDraw.setDiaryEntries(diaryEntries);
            }
        });
    }

    public void setUpBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        imageButtonLupa.setEnabled(false); // Deshabilita el boton
        imageButtonLupa.setImageAlpha(128); // Oscurece el boton
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);

        imageButtonPlantadex.setOnClickListener(v -> {
            Intent intent = new Intent(DiaryActivity.this, PlantListActivity.class);
            startActivity(intent);
        });
        imageButtonMaceta.setOnClickListener(view -> {
            Intent intent = new Intent(DiaryActivity.this, JardinActivity.class);
            startActivity(intent);
        });
        imageButtonUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(DiaryActivity.this, PerfilActivity.class);
            startActivity(intent);
        });
    }
}
