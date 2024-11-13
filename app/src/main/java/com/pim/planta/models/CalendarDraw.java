package com.pim.planta.models;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

public class CalendarDraw extends View {

    private Paint dayPaint, headerPaint, backGroundPaint, defaultPaint;
    private int currentMonth, currentYear;
    private Calendar calendar;
    private HashMap<Integer, String> dayEmotions; // Para almacenar emociones por día
    private HashMap<Integer, String> dayAnnotations; // Para almacenar anotaciones por día

    // Colores para emociones
    private final HashMap<String, Integer> emotionColors = new HashMap<String, Integer>() {{
        put("Feliz", Color.YELLOW);
        put("Triste", Color.BLUE);
        put("Ansioso", Color.RED);
        put("Contento", Color.GREEN);
        put("Estresado", Color.MAGENTA);
    }};

    public CalendarDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        dayEmotions = new HashMap<>();
        dayAnnotations = new HashMap<>();

        // Paint para días
        dayPaint = new Paint();
        dayPaint.setColor(Color.BLACK);
        dayPaint.setTextSize(30);
        dayPaint.setTextAlign(Paint.Align.CENTER);

        // Paint para encabezado
        headerPaint = new Paint();
        headerPaint.setColor(Color.BLUE);
        headerPaint.setTextSize(40);
        headerPaint.setTextAlign(Paint.Align.CENTER);

        // Paint para fondo
        backGroundPaint = new Paint();
        backGroundPaint.setColor(Color.WHITE);
        backGroundPaint.setStyle(Paint.Style.FILL);

        // Paint predeterminado para días sin emoción
        defaultPaint = new Paint();
        defaultPaint.setColor(Color.LTGRAY); // Fondo gris predeterminado
        defaultPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dibuja el encabezado del mes
        String monthYearText = String.format("%tB %d", calendar, currentYear);
        canvas.drawRect(0, 0, getWidth(), getHeight(), backGroundPaint);
        canvas.drawText(monthYearText, getWidth() / 2, 60, headerPaint);

        // Dibuja las flechas de cambio de mes
        canvas.drawText("<", getWidth() / 5, 60, headerPaint);
        canvas.drawText(">", 4 * getWidth() / 5, 60, headerPaint);

        // Dibuja los días
        calendar.set(Calendar.MONTH, currentMonth);
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int dayWidth = getWidth() / 7;
        int dayHeight = (getHeight() - 100) / 6;

        for (int day = 1; day <= daysInMonth; day++) {
            int column = (startDay + day - 1) % 7;
            int row = (startDay + day - 1) / 7;
            float x = column * dayWidth + dayWidth / 2;
            float y = row * dayHeight + dayHeight / 2 + 100;

            // Establecer el color de fondo según la emoción
            String emotion = dayEmotions.get(day);
            Paint paintToUse = defaultPaint; // Color predeterminado gris
            if (emotion != null && emotionColors.containsKey(emotion)) {
                paintToUse = new Paint();
                paintToUse.setColor(emotionColors.get(emotion));
                paintToUse.setStyle(Paint.Style.FILL);
            }

            // Dibujar fondo del día
            canvas.drawCircle(x, y, dayWidth / 3, paintToUse);

            // Mostrar el número del día
            canvas.drawText(String.valueOf(day), x, y + 10, dayPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();

            // Coordenadas y área de las flechas de cambio de mes
            float centerX1 = getWidth() / 5;
            float centerX2 = 4 * getWidth() / 5;
            float centerY = 60;
            float radius = 50;

            // Detectar el toque para cambiar de mes
            if (Math.pow(x - centerX1, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                prevMonth();
                return true;
            } else if (Math.pow(x - centerX2, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                nextMonth();
                return true;
            }

            // Detectar el toque en los días del calendario
            int calendarStartY = getHeight() / 5;
            int dayWidth = getWidth() / 7;
            int dayHeight = (getHeight() - calendarStartY) / 6;
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;

            int col = (int) (x / dayWidth);
            int row = (int) ((y - calendarStartY) / dayHeight);

            if (col >= 0 && col < 7 && row >= 0 && row < 6) {
                int dayClicked = row * 7 + col + 1 - startDay;

                if (dayClicked > 0 && dayClicked <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    showEmotionDialog(dayClicked);
                }
            }

            performClick();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void showEmotionDialog(int day) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Seleccione una emoción para el día " + day);

        // Caja de texto para anotación
        final EditText input = new EditText(getContext());
        builder.setView(input);

        // Opciones de emociones con colores
        String[] emotions = {"Feliz", "Triste", "Ansioso", "Contento", "Estresado"};
        SpannableString[] emotionOptions = new SpannableString[emotions.length];
        for (int i = 0; i < emotions.length; i++) {
            SpannableString option = new SpannableString(emotions[i]);
            option.setSpan(new ForegroundColorSpan(emotionColors.get(emotions[i])), 0, option.length(), 0);
            emotionOptions[i] = option;
        }

        builder.setItems(emotionOptions, (dialog, which) -> {
            String emotion = emotions[which];
            String annotation = input.getText().toString();

            // Almacena emoción y anotación
            dayEmotions.put(day, emotion);
            dayAnnotations.put(day, annotation);

            invalidate(); // Redibuja el calendario para mostrar cambios
            Toast.makeText(getContext(), "Emoción y anotación guardadas para el día " + day, Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    private void prevMonth() {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        calendar.set(Calendar.MONTH, currentMonth);
        calendar.set(Calendar.YEAR, currentYear);
        invalidate();
    }

    private void nextMonth() {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        calendar.set(Calendar.MONTH, currentMonth);
        calendar.set(Calendar.YEAR, currentYear);
        invalidate();
    }
}
