package com.pim.planta.models;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Calendar;

public class CalendarDraw extends View {

    private Paint dayPaint, headerPaint, backGroundPaint, Emotion_1_Paint, Emotion_2_Paint, Emotion_3_Paint, Emotion_4_Paint, Emotion_5_Paint;
    private int currentMonth, currentYear;
    private Calendar calendar;


    public CalendarDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        calendar = Calendar.getInstance();
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        // Inicializar los Paints
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

        Emotion_1_Paint = new Paint();
        Emotion_1_Paint.setColor(Color.RED);
        Emotion_1_Paint.setStyle(Paint.Style.FILL);

        Emotion_2_Paint = new Paint();
        Emotion_3_Paint = new Paint();
        Emotion_4_Paint = new Paint();
        Emotion_5_Paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dibuja el encabezado del mes
        String monthYearText = String.format("%tB %d", calendar, currentYear);
        canvas.drawRect(0,0,getWidth(),getHeight(),backGroundPaint);
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
        int dayHeight = getHeight() / 6;

        for (int day = 1; day <= daysInMonth; day++) {
            int column = (startDay + day - 1) % 7;
            int row = (startDay + day - 1) / 7;
            float x = column * dayWidth + dayWidth / 2;
            float y = row * dayHeight + dayHeight / 2 + 100;
            canvas.drawCircle(x, y, (dayWidth/2), Emotion_1_Paint);
            canvas.drawText(String.valueOf(day), x, y, dayPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();

            // Centros de los botones
            float centerX1 = getWidth() / 5;
            float centerX2 = 4 * getWidth() / 5;
            float centerY = 60;

            // Define el radio de área alrededor del botón donde se detecta el toque
            float radius = 50; // Puedes ajustar este valor

            // Detectar el toque para cambiar de mes
            if (Math.pow(x - centerX1, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                prevMonth();
                System.out.println("hoal");
            } else if (Math.pow(x - centerX2, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                nextMonth();
            } else {
                // Dimensiones de la cuadrícula del calendario
                int calendarStartX = 0;
                int calendarStartY = getHeight() / 5; // Ajustar si es necesario
                int dayWidth = getWidth() / 7;
                int dayHeight = (getHeight() - calendarStartY) / 6;

                // Calcular la fila y columna seleccionada
                int col = (int) ((x - calendarStartX) / dayWidth);
                int row = (int) ((y - calendarStartY) / dayHeight);

                // Obtener el número de días en el mes actual
                Calendar calendar = Calendar.getInstance();
                int currentYear = calendar.get(Calendar.YEAR);
                int currentMonth = calendar.get(Calendar.MONTH);
                calendar.set(currentYear, currentMonth, 1);
                int daysInCurrentMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                // Ignorar clics fuera del área de los días
                if (col >= 0 && col < 7 && row >= 0 && row < 6) {
                    int dayClicked = row * 7 + col + 1; // Día relativo en la cuadrícula

                    // Validar que esté dentro del mes actual
                    if (dayClicked > 0 && dayClicked <= daysInCurrentMonth) {
                        System.out.println("Día seleccionado: " + dayClicked);
                    }
                }
            }
            performClick();
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        // Llama a la superclase para manejar el clic
        super.performClick();
        // Aquí puedes añadir lógica adicional si es necesario
        return true;
    }


    private void prevMonth() {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        invalidate();
    }

    private void nextMonth() {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        invalidate();
    }
}