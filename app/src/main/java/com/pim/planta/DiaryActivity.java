package com.pim.planta;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pim.planta.db.DatabaseExecutor;
import com.pim.planta.db.PlantRepository;
import com.pim.planta.models.CalendarDraw;
import com.pim.planta.models.DiaryEntry;
import com.pim.planta.models.User;
import com.pim.planta.models.UserLogged;
import com.pim.planta.models.YearAdapter;
import com.pim.planta.models.YearSelectorButton;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DiaryActivity extends NotificationActivity  {

    private CalendarDraw calendarDraw;
    private PlantRepository plantRepo;
    private List<DiaryEntry> diaryEntries;
    private YearSelectorButton yearSelectorButton;
    private ImageButton previousMonthButton, nextMonthButton;
    private TextView dateTextView;
    private ConstraintSet initialConstraintSet;
    private boolean isExpanded = false;
    private int selectedEmotion = -1;
    private EditText highlightInput, annotationInput;
    private List<ImageView> emotionImages;
    private DiaryEntry currentDiaryEntry;
    private long dateInMillis;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        setUpBottom();

        calendarDraw = findViewById(R.id.calendar_draw);
        calendarDraw.setVisibility(View.VISIBLE);

        dateTextView = findViewById(R.id.dateTextView);
        calendarDraw.highlightDay(LocalDate.now());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        dateTextView.setText(formattedDate);

        previousMonthButton = findViewById(R.id.previousMonthButton);
        previousMonthButton.setOnClickListener(v -> calendarDraw.prevMonth());
        nextMonthButton = findViewById(R.id.nextMonthButton);
        nextMonthButton.setOnClickListener(v -> calendarDraw.nextMonth());

        ConstraintLayout bottomSection = findViewById(R.id.bottomSection);
        View greenArrow = findViewById(R.id.greenArrow);

        initialConstraintSet = new ConstraintSet();
        initialConstraintSet.clone(bottomSection);

        highlightInput = findViewById(R.id.highlightInput);
        annotationInput = findViewById(R.id.annotationInput);

        emotionImages = Arrays.asList(
                findViewById(R.id.excitedImage),
                findViewById(R.id.happyImage),
                findViewById(R.id.neutralImage),
                findViewById(R.id.sadImage),
                findViewById(R.id.verySadImage)
        );
        for (ImageView image : emotionImages) {
            image.setAlpha(0.5f); // Dim all emotion images
        }

        LocalDate currentDay = LocalDate.now();
        dateInMillis = Date.from(currentDay.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();

        yearSelectorButton = findViewById(R.id.year_selector_button);
        yearSelectorButton.setCalendarDraw(calendarDraw);
        yearSelectorButton.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                RecyclerView yearRecyclerView = yearSelectorButton.yearRecyclerView;
                YearAdapter yearAdapter = new YearAdapter(yearSelectorButton.getCurrentYear(), yearSelectorButton.getMinimumYear());
                yearRecyclerView.setAdapter(yearAdapter);
                yearRecyclerView.setLayoutManager(new LinearLayoutManager(DiaryActivity.this, LinearLayoutManager.HORIZONTAL, false));

                yearRecyclerView.setVisibility(View.VISIBLE);

                yearSelectorButton.removeOnLayoutChangeListener(this);
            }
        });

        plantRepo = PlantRepository.getInstance(this);

        loadEmotions();

        new LoadDiaryEntryTask().execute(dateInMillis);

        calendarDraw.setOnTouchListener((view, motionEvent) -> {
            LocalDate currentDayClicked = null;
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                // Posiciones de los botones de cambio de mes
                float centerX1 = calendarDraw.getHeight() / 5;
                float centerX2 = 4 * calendarDraw.getWidth() / 5;
                float centerY = 60;
                float radius = 50;

                // Comprobación de toque dentro de las hitboxes
                /*if (Math.pow(x - centerX1, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                    Log.d("onTouch", "HA TOCADO FLECHA IZQUIERDA");
                    calendarDraw.prevMonth();
                    return true;
                } else if (Math.pow(x - centerX2, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                    Log.d("onTouch", "HA TOCADO FLECHA DERECHA");
                    calendarDraw.nextMonth();
                    return true;
                }*/

                // Detectar si se tocó un día específico
                int dayClicked = calendarDraw.getDayFromCoordinates(motionEvent.getX(), motionEvent.getY());
                if (dayClicked != -1) {
                    if (dayClicked > LocalDate.now().getDayOfMonth() && calendarDraw.getCurrentMonth() == LocalDate.now().getMonthValue() && calendarDraw.getCurrentYear() == LocalDate.now().getYear())
                        return true;
                    // Obtener la fecha en milisegundos para el día tocado
                    currentDayClicked = LocalDate.of(calendarDraw.getCurrentYear(), calendarDraw.getCurrentMonth(), dayClicked);
                    calendarDraw.highlightDay(currentDayClicked);
                    // Format and set date in TextView
                    SimpleDateFormat touchDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                    String currentFormattedDate = touchDateFormat.format(Date.from(currentDayClicked.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    dateTextView.setText(currentFormattedDate);

                    // Llamar al métod para mostrar el diálogo de emociones, pasando el `dateInMillis` y el listener
                    /*calendarDraw.showEmotionDialog(dateInMillis, new OnDiaryEntryListener() {
                        @Override
                        public void onDiaryEntryCreated(DiaryEntry entry) {
                            saveDiaryEntry(entry);
                            calendarDraw.invalidate();
                        }
                    });*/
                    dateInMillis = Date.from(currentDayClicked.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
                    new LoadDiaryEntryTask().execute(dateInMillis);
                } else {
                    dateInMillis = Date.from(currentDay.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
                    new LoadDiaryEntryTask().execute(dateInMillis);
                }
            }
            return true;
        });

        Button saveButton = findViewById(R.id.buttonSaveEntry);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentDiaryEntry != null) {
                    // Update currentDiaryEntry with the latest data from UI elements
                    currentDiaryEntry.setHighlight(highlightInput.getText().toString());
                    currentDiaryEntry.setAnnotation(annotationInput.getText().toString());
                    currentDiaryEntry.setEmotion(selectedEmotion);

                    new SaveDiaryEntryTask().execute(currentDiaryEntry);
                } else {
                    LocalDate day = LocalDate.of(calendarDraw.getCurrentYear(), calendarDraw.getCurrentMonth(), calendarDraw.getCurrentDay().getDayOfMonth());
                    DiaryEntry entry = new DiaryEntry(
                            highlightInput.getText().toString(),
                            annotationInput.getText().toString(),
                            selectedEmotion,
                            UserLogged.getInstance().getCurrentUser().getId(),
                            Date.from(day.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()
                    );
                    new SaveDiaryEntryTask().execute(entry);
                }
            }
        });

        greenArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExpanded = !isExpanded; // Toggle expansion state
                initialConstraintSet.applyTo(bottomSection);
                // Define target translationY based on expansion state
                float targetTranslationY = isExpanded ? -(bottomSection.getTop()) : 0;
                if (!isExpanded)
                    greenArrow.setBackground(getResources().getDrawable(R.drawable.ic_arrow_up));
                else greenArrow.setBackground(getResources().getDrawable(R.drawable.ic_arrow_down));
                // Animate the transition
                bottomSection.animate()
                        .translationY(targetTranslationY)
                        .setDuration(300)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                // Change arrow direction and update expansion state
                                boolean isExpanded = bottomSection.getTranslationY() != 0;
                                if (!isExpanded) { // If bottomSection is collapsed
                                    initialConstraintSet.applyTo(bottomSection);
                                }
                            }
                        })
                        .start();
            }
        });
        for (final ImageView image : emotionImages) {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Highlight the selected emotion
                    image.setAlpha(1.0f);
                    // Dim other emotion images
                    for (ImageView otherImage : emotionImages) {
                        if (otherImage != image) {
                            otherImage.setAlpha(0.5f);
                        }
                    }
                    selectedEmotion = emotionImages.indexOf(image);
                }
            });
        }
    }

    private void updateDate(long finalDateInMillis) {
        dateInMillis = finalDateInMillis;
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
    private void animateButton(View view) {
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1.0f)
        );
        animator.setDuration(150); // Duración de la animación
        animator.start();
    }
    public void setUpBottom(){
        ImageButton imageButtonLupa = findViewById(R.id.imageButtonLupa);
        imageButtonLupa.setEnabled(false); // Deshabilita el boton
        imageButtonLupa.setImageAlpha(128); // Oscurece el boton
        ImageButton imageButtonMaceta = findViewById(R.id.imageButtonMaceta);
        ImageButton imageButtonPlantadex = findViewById(R.id.imageButtonPlantadex);
        ImageButton imageButtonUsuario = findViewById(R.id.imageButtonUsuario);

        imageButtonPlantadex.setOnClickListener(v -> {
            animateButton(v);
            Intent intent = new Intent(DiaryActivity.this, PlantListActivity.class);
            startActivity(intent);
        });
        imageButtonMaceta.setOnClickListener(view -> {
            animateButton(view);
            Intent intent = new Intent(DiaryActivity.this, JardinActivity.class);
            startActivity(intent);
        });
        imageButtonUsuario.setOnClickListener(v -> {
            animateButton(v);
            Intent intent = new Intent(DiaryActivity.this, PerfilActivity.class);
            startActivity(intent);
        });
    }
    private void loadDiaryEntryUI(DiaryEntry entry) {
        LocalDate selectedDate = Instant.ofEpochMilli(dateInMillis).atZone(ZoneId.systemDefault()).toLocalDate();
        if (entry != null && (entry.getHighlight() != null || entry.getAnnotation() != null || entry.getEmotion() != -1)) {
            selectedEmotion = entry.getEmotion();
            // Update emotion images based on selectedEmotion
            for (int i = 0; i < emotionImages.size(); i++) {
                ImageView image = emotionImages.get(i);
                if (i == selectedEmotion) {
                    image.setAlpha(1.0f);
                } else {
                    image.setAlpha(0.5f);
                }
            }
            highlightInput.setText(entry.getHighlight());
            annotationInput.setText(entry.getAnnotation());
        }
        else if (entry == null && !selectedDate.isAfter(LocalDate.now())){
            // Reset UI elements if no entry is found
            resetEntry();
        }
    }

    private void resetEntry() {
        highlightInput.setText("");
        annotationInput.setText("");
        selectedEmotion = -1;
        for (ImageView image : emotionImages) {
            image.setAlpha(0.5f);
        }
    }

    private class LoadDiaryEntryTask extends AsyncTask<Long, Void, DiaryEntry> {
        @Override
        protected DiaryEntry doInBackground(Long... params) {
            long dateInMillis = params[0];
            LocalDate selectedDate = Instant.ofEpochMilli(dateInMillis).atZone(ZoneId.systemDefault()).toLocalDate();
            if (selectedDate.isAfter(LocalDate.now())) {
                // Date is in the future, return null to prevent loading
                return null;
            }
            return plantRepo.getPlantaDAO().getEntradaByUserIdAndDate(UserLogged.getInstance().getCurrentUser().getId(), dateInMillis);
        }

        @Override
        protected void onPostExecute(DiaryEntry entry) {
            currentDiaryEntry = entry; // Store the loaded entry
            loadDiaryEntryUI(entry);
            loadEmotions(); // Reload entries
            calendarDraw.invalidate(); // Redraw calendar
        }
    }
    private class SaveDiaryEntryTask extends AsyncTask<DiaryEntry, Void, Void> {
        @Override
        protected Void doInBackground(DiaryEntry... entries) {
            DiaryEntry entry = entries[0];
            // Check if an entry already exists for the same user and date
            DiaryEntry existingEntry = plantRepo.getPlantaDAO().getEntradaByUserIdAndDate(entry.getUserId(), entry.getDate());

            if (existingEntry != null) {
                // Update existing entry
                entry.setId(existingEntry.getId()); // Set the ID of the existing entry
                plantRepo.getPlantaDAO().update(entry);
            } else {
                // Insert new entry
                plantRepo.getPlantaDAO().insert(entry);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            // Update diary entries and redraw calendar
            loadEmotions(); // Reload entries
            calendarDraw.invalidate(); // Redraw calendar
        }
    }
}
