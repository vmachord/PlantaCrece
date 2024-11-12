package com.pim.planta.models;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;

public class CalendarDraw extends View {

    private Paint dayPaint, headerPaint, backGroundPaint, emotionPaint;
    private int currentMonth, currentYear;
    private Calendar calendar;
    private HashMap<Integer, String> dayEmotions; // Para almacenar emociones/anotaciones por día

    public CalendarDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);
        dayEmotions = new HashMap<>();

        dayPaint = new Paint();
        dayPaint.setColor(Color.BLACK);
        dayPaint.setTextSize(30);
        dayPaint.setTextAlign(Paint.Align.CENTER);

        headerPaint = new Paint();
        headerPaint.setColor(Color.BLUE);
        headerPaint.setTextSize(40);
        headerPaint.setTextAlign(Paint.Align.CENTER);

        backGroundPaint = new Paint();
        backGroundPaint.setColor(Color.WHITE);
        backGroundPaint.setStyle(Paint.Style.FILL);

        emotionPaint = new Paint();
        emotionPaint.setColor(Color.RED);
        emotionPaint.setStyle(Paint.Style.FILL);
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

            // Dibujar círculo de fondo para el día
            canvas.drawCircle(x, y, dayWidth / 3, emotionPaint);

            // Mostrar el número del día
            canvas.drawText(String.valueOf(day), x, y + 10, dayPaint);

            // Si hay una emoción asociada al día, cambiar el color
            if (dayEmotions.containsKey(day)) {
                dayPaint.setColor(Color.GREEN); // Cambia el color para días con emoción
                canvas.drawText(dayEmotions.get(day), x, y + 10, dayPaint); // Muestra la emoción
                dayPaint.setColor(Color.BLACK); // Resetea el color para el próximo día
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();

            // Detecta el clic en el calendario
            int dayWidth = getWidth() / 7;
            int dayHeight = (getHeight() - 100) / 6;
            int startY = 100;

            int column = (int) (x / dayWidth);
            int row = (int) ((y - startY) / dayHeight);
            int dayClicked = row * 7 + column + 1 - (calendar.get(Calendar.DAY_OF_WEEK) - 1);

            if (dayClicked > 0 && dayClicked <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                showEmotionDialog(dayClicked);
            }

            performClick();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void showEmotionDialog(int day) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Seleccione una emoción para el día " + day);

        // Caja de texto para la anotación
        final EditText input = new EditText(getContext());
        builder.setView(input);

        // Opciones de emociones (ejemplo)
        String[] emotions = {"Feliz", "Triste", "Ansioso", "Contento", "Estresado"};
        builder.setItems(emotions, (dialog, which) -> {
            String emotion = emotions[which];
            String annotation = input.getText().toString();

            // Almacena la emoción y la anotación
            dayEmotions.put(day, emotion + ": " + annotation);
            invalidate(); // Redibuja el calendario para mostrar la emoción
            Toast.makeText(getContext(), "Emoción añadida para el día " + day, Toast.LENGTH_SHORT).show();
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
