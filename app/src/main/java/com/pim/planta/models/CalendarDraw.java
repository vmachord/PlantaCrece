package com.pim.planta.models;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CalendarDraw extends View {

    private Paint dayPaint, headerPaint, backGroundPaint, emotionPaint;
    private int currentMonth, currentYear;

    // Almacena el color de fondo para cada día
    private HashMap<Long, Integer> dayBackgroundColors = new HashMap<>();


    private List<DiaryEntry> diaryEntries;


    public CalendarDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        dayBackgroundColors = new HashMap<>();

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
        emotionPaint.setStyle(Paint.Style.FILL);

        LocalDate today = LocalDate.now();
        currentMonth = today.getMonthValue(); // Mes actual (1-12)
        currentYear = today.getYear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Fondo del calendario
        canvas.drawColor(Color.LTGRAY);

        // Dibuja los encabezados del calendario
        drawMonthHeader(canvas);

        // Dibuja los días con colores basados en las emociones
        drawDays(canvas);
    }
    //AÑADIR FLECHAS PARA MOVERTE ENTRE MESES
    private void drawMonthHeader(Canvas canvas) {
        // Dibuja el encabezado (mes y año)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.getDefault());
        String monthYear = LocalDate.of(currentYear, currentMonth, 1).format(formatter);

        canvas.drawText(monthYear, getWidth() / 2, 50, headerPaint);
    }

    private void drawDays(Canvas canvas) {
        int dayWidth = getWidth() / 7;
        int dayHeight = (getHeight() - 100) / 6;

        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        int startColumn = (startDayOfWeek == 7) ? 6 : startDayOfWeek - 1;

        List<DiaryEntry> monthEntries = getEntriesForCurrentMonth();

        // Recorre el mes entero para dibujarlo
        for (int day = 1; day <= daysInMonth; day++) {

            //Logica de los dias
            int column = (startColumn + day - 1) % 7; // Calcular la columna basándose en el día de la semana
            int row = (startColumn + day - 1) / 7; // Calcular la fila

            float x = column * dayWidth + dayWidth / 2; // Coordenada X (centro de la celda)
            float y = row * dayHeight + dayHeight / 2 + 100; // Coordenada Y (centro de la celda)

            emotionPaint.setColor(Color.WHITE);
            canvas.drawCircle(x, y, dayWidth / 3, emotionPaint);
            canvas.drawText(String.valueOf(day), x, y + 10, dayPaint);
        }

        //Dibuja las entradas del diario encima del calendario vacio
        for (DiaryEntry entry : monthEntries) {
            long dateInMillis = normalizeToStartOfDay(entry.getDate());
            int day = getDayOfMonthFromMillis(dateInMillis);

            //Logica de los dias
            int column = (day - 1) % 7;
            int row = (day - 1) / 7;
            float x = column * dayWidth + dayWidth / 2;
            float y = row * dayHeight + dayHeight / 2 + 100;

            // Dibujar el día con el color correspondiente a la emoción
            emotionPaint.setColor(getColorForEmotion(entry.emotionToString()));
            canvas.drawCircle(x, y, dayWidth / 3, emotionPaint);
            canvas.drawText(String.valueOf(day), x, y + 10, dayPaint);
        }
    }

    private List<DiaryEntry> getEntriesForCurrentMonth() {
        // Devuelve solo las entradas del mes y año actuales
        return diaryEntries.stream()
                .filter(entry -> isInCurrentMonth(entry.getDate()))
                .collect(Collectors.toList());
    }

    private boolean isInCurrentMonth(long dateInMillis) {
        // Como la fecha esta en long elimina los milisegundos normalizandolo
        long normalizedDate = normalizeToStartOfDay(dateInMillis);
        LocalDate date = Instant.ofEpochMilli(normalizedDate).atZone(ZoneId.systemDefault()).toLocalDate();
        return date.getMonthValue() == currentMonth && date.getYear() == currentYear;
    }

    private int getDayOfMonthFromMillis(long dateInMillis) {
        LocalDate date = Instant.ofEpochMilli(dateInMillis).atZone(ZoneId.systemDefault()).toLocalDate();
        return date.getDayOfMonth();
    }

    @SuppressLint("NewApi")
    private long normalizeToStartOfDay(long timestamp) {
        return LocalDate.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();

            // Posiciones cambio de mes
            float centerX1 = getWidth() / 5;
            float centerX2 = 4 * getWidth() / 5;
            float centerY = 60;
            float radius = 50;

            if (Math.pow(x - centerX1, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                prevMonth();
                return true;
            } else if (Math.pow(x - centerX2, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2)) {
                nextMonth();
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    public void showEmotionDialog(long dateInMillis, OnDiaryEntryListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Seleccione una emoción para el día " +
                new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(dateInMillis));

        final EditText input = new EditText(getContext());
        input.setHint("Escriba una anotación");
        builder.setView(input);

        // Definir las emociones disponibles
        String[] emotions = {"Feliz", "Triste", "Ansioso", "Contento", "Estresado"};
        final int[] selectedEmotionId = {-1}; // Variable para la emoción seleccionada

        builder.setSingleChoiceItems(emotions, -1, (dialog, which) -> selectedEmotionId[0] = which);

        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            // Verificar si se ha seleccionado una emoción
            if (selectedEmotionId[0] != -1) {
                String annotation = input.getText().toString().trim();
                if (!annotation.isEmpty()) {
                    User user = UserLogged.getInstance().getCurrentUser();
                    DiaryEntry entry = new DiaryEntry(annotation, selectedEmotionId[0], user.getId(), dateInMillis);

                    // Llamar al listener para devolver el DiaryEntry
                    listener.onDiaryEntryCreated(entry);
                } else {
                    // Si la anotación está vacía, mostrar un mensaje de advertencia
                    Toast.makeText(getContext(), "Por favor, ingrese una anotación antes de guardar.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Si no se ha seleccionado emoción
                Toast.makeText(getContext(), "Seleccione una emoción antes de guardar.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            // Si se cancela, no hacer nada
            dialog.dismiss();
        });

        builder.show();
    }


    private int getColorForEmotion(String emotion) {
        switch (emotion) {
            case "Feliz": return Color.YELLOW;
            case "Triste": return Color.BLUE;
            case "Ansioso": return Color.RED;
            case "Contento": return Color.GREEN;
            case "Estresado": return Color.MAGENTA;
            default: return Color.WHITE; // Color predeterminado si no hay emoción
        }
    }

    public int getDayFromCoordinates(float x, float y) {
        int calendarStartY = getHeight() / 5; // Supongamos que el calendario comienza en esta posición
        int dayWidth = getWidth() / 7; // Ancho de cada celda
        int dayHeight = (getHeight() - calendarStartY) / 6; // Altura de cada celda

        // Obtener el día de la semana en que empieza el mes (1 = Lunes, 7 = Domingo)
        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // Lunes=1, Domingo=7

        // Ajustamos el inicio de la semana para que el domingo esté en la columna 6
        int startColumn = (startDayOfWeek == 7) ? 6 : startDayOfWeek - 1; // El domingo debe caer en columna 6

        // Calcular la columna y la fila donde se encuentra el toque
        int col = (int) (x / dayWidth); // Columna que toca (0-6)
        int row = (int) ((y - calendarStartY) / dayHeight); // Fila que toca (0-5)

        // Validar que el toque está dentro de las celdas del calendario
        if (col >= 0 && col < 7 && row >= 0 && row < 6) {
            // El cálculo del día toma en cuenta el desplazamiento de la columna inicial
            int dayClicked = row * 7 + col + 1 - startColumn; // Ajustar según el día de inicio de la semana

            // Validar que el día clicado es válido (dentro del rango del mes)
            if (dayClicked > 0 && dayClicked <= firstDayOfMonth.lengthOfMonth()) {
                return dayClicked; // Devuelve el día del mes
            }
        }

        return -1; // Si no se clicó en un día válido
    }



    public void setDiaryEntries(List<DiaryEntry> entries) {
        this.diaryEntries = entries;
        invalidate(); // Redibuja el calendario con la nueva información
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

    public HashMap<Long, Integer> getDayBackgroundColors() {
        return dayBackgroundColors;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }
}
