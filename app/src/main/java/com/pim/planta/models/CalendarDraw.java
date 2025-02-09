package com.pim.planta.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.pim.planta.R;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class CalendarDraw extends View {

    private Paint dayPaint, headerPaint, backGroundPaint, underlinePaint, highlightedDayPaint, highlightedDayCircle;
    private int currentMonth, currentYear;
    private Typeface customFont, customFontBold;
    private LocalDate highlightedDay;

    private List<DiaryEntry> diaryEntries;

    public CalendarDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        currentYear = LocalDate.now().getYear();
    }

    public CalendarDraw(Context context) {
        super(context);
        init();
        currentYear = LocalDate.now().getYear();
    }

    private void init() {
        customFont = ResourcesCompat.getFont(getContext(), R.font.aventa);
        customFontBold = Typeface.create(customFont, Typeface.BOLD);

        dayPaint = new Paint();
        dayPaint.setColor(Color.parseColor("#073E24"));;
        dayPaint.setTextSize(46);
        dayPaint.setTextAlign(Paint.Align.CENTER);
        dayPaint.setTypeface(customFont);

        underlinePaint = new Paint();
        underlinePaint.setColor(Color.parseColor("#35D18F"));
        underlinePaint.setStrokeWidth(5f);

        headerPaint = new Paint();
        headerPaint.setColor(Color.parseColor("#073E24"));
        headerPaint.setTextSize(48);
        headerPaint.setTextAlign(Paint.Align.CENTER);
        headerPaint.setTypeface(customFontBold);

        highlightedDayPaint = new Paint();
        highlightedDayPaint.setColor(Color.WHITE);
        highlightedDayPaint.setTextSize(46);
        highlightedDayPaint.setTextAlign(Paint.Align.CENTER);
        highlightedDayPaint.setTypeface(customFont);

        highlightedDayCircle = new Paint();
        highlightedDayCircle.setColor(getResources().getColor(R.color.dark_green));
        highlightedDayCircle.setStyle(Paint.Style.FILL);

        backGroundPaint = new Paint();
        backGroundPaint.setColor(Color.WHITE);
        backGroundPaint.setStyle(Paint.Style.FILL);

        backGroundPaint = new Paint();
        backGroundPaint.setColor(Color.WHITE);
        backGroundPaint.setStyle(Paint.Style.FILL);

        //emotionPaint = new Paint();
        //emotionPaint.setStyle(Paint.Style.FILL);

        LocalDate today = LocalDate.now();
        currentMonth = today.getMonthValue(); // Mes actual (1-12)
        currentYear = today.getYear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cornerRadius = getWidth() * 0.05f;
        RectF calendarBounds = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(calendarBounds, cornerRadius, cornerRadius, backGroundPaint);
        canvas.clipRect(calendarBounds);
        drawMonthHeader(canvas);
        drawDays(canvas);
        //invalidate();
        //drawButtonChangePerspective(canvas);
    }
    private void drawMonthHeader(Canvas canvas) {
        // Dibuja el encabezado (solo mes)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        String monthName = LocalDate.of(currentYear, currentMonth, 1).format(formatter);

        // Convert month name to uppercase
        String monthNameUppercase = monthName.toUpperCase();

        // Add margin to the top
        canvas.drawText(monthNameUppercase, getWidth() / 2, 100, headerPaint);
    }



    private void drawDays(Canvas canvas) {
        int dayWidth = getWidth() / 7;
        int dayHeight = (getHeight() - 100) / 6;

        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        int startColumn = (startDayOfWeek == 7) ? 6 : startDayOfWeek - 1;


        // Recorre el mes entero para dibujarlo
        for (int day = 1; day <= daysInMonth; day++) {

            //Logica de los dias
            int column = (startColumn + day - 1) % 7; // Calcular la columna basándose en el día de la semana
            int row = (startColumn + day - 1) / 7; // Calcular la fila

            float x = column * dayWidth + dayWidth / 2; // Coordenada X (centro de la celda)
            float y = row * dayHeight + dayHeight / 2 + 100; // Coordenada Y (centro de la celda)

            //emotionPaint.setColor(Color.WHITE);
            //canvas.drawCircle(x, y, dayWidth / 3, emotionPaint);

            canvas.drawText(String.valueOf(day), x, y + 10, dayPaint);
            LocalDate currentDay = LocalDate.of(currentYear, currentMonth, day);
            if (hasEntryForDay(currentDay) && !currentDay.equals(highlightedDay)) {
                // Draw entry style (green line)
                float lineStartX = x - dayWidth / 4;
                float lineEndX = x + dayWidth / 4;
                float lineY = y + 20;
                canvas.drawLine(lineStartX, lineY, lineEndX, lineY, underlinePaint);
            }
            if (currentDay.equals(highlightedDay) && hasEntryForDay(currentDay)) {
                // Draw highlighted day style
                canvas.drawCircle(x, y, dayWidth / 3, highlightedDayCircle); // Draw circle
                canvas.drawText(String.valueOf(day), x, y + 10, highlightedDayPaint); // Draw day number
                float lineStartX = x - dayWidth / 4;
                float lineEndX = x + dayWidth / 4;
                float lineY = y + 20;
                canvas.drawLine(lineStartX, lineY, lineEndX, lineY, highlightedDayPaint);
            }
            if (currentDay.equals(highlightedDay) && !hasEntryForDay(currentDay)) {
                canvas.drawCircle(x, y, dayWidth / 3, highlightedDayCircle); // Draw circle
                canvas.drawText(String.valueOf(day), x, y + 10, highlightedDayPaint); // Draw day number
            }
        }
    }

    @SuppressLint("NewApi")
    public long normalizeToStartOfDay(long timestamp) {
        return LocalDate.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
    }

    public int getDayFromCoordinates(float x, float y) {
        int calendarStartY = getHeight() / 5;
        int dayWidth = getWidth() / 7;
        int dayHeight = (getHeight() - calendarStartY) / 6;

        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        int startDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // Lunes=1, Domingo=7

        int startColumn = (startDayOfWeek == 7) ? 6 : startDayOfWeek - 1; // El domingo debe caer en columna 6

        int col = (int) (x / dayWidth); // Columna que toca (0-6)
        int row = (int) ((y - calendarStartY) / dayHeight); // Fila que toca (0-5)

        if (col >= 0 && col < 7 && row >= 0 && row < 6) {
            int dayClicked = row * 7 + col + 1 - startColumn;

            if (dayClicked > 0 && dayClicked <= firstDayOfMonth.lengthOfMonth()) {
                return dayClicked;
            }
        }
        return -1;
    }



    public void setDiaryEntries(List<DiaryEntry> entries) {
        this.diaryEntries = entries;
        invalidate(); // Redibuja el calendario con la nueva información
    }

    public void prevMonth() {
        currentMonth--;
        if (currentMonth < 1) {
            currentMonth = 1;
        }
        invalidate();
    }

    public void nextMonth() {
        currentMonth++;
        if (currentMonth > 12)
            currentMonth = 12;
        if (currentMonth > LocalDate.now().getMonthValue() && currentYear == LocalDate.now().getYear())
            currentMonth = LocalDate.now().getMonthValue();
        invalidate();
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public LocalDate getCurrentDay() {return highlightedDay;}

    public void setCurrentMonth(int monthValue) {
        this.currentMonth = monthValue;
    }

    public void highlightDay(LocalDate date) {
        highlightedDay = date;
        invalidate(); // Redraw the calendar
    }

    public boolean hasEntryForDay(LocalDate day) {
        if (diaryEntries == null || diaryEntries.isEmpty()) {
            return false;
        }
        for (DiaryEntry entry : diaryEntries) {
            if (Instant.ofEpochMilli(entry.getDate()).atZone(ZoneId.systemDefault()).toLocalDate().equals(day)) {
                return true;
            }
        }
        return false;
    }
}
