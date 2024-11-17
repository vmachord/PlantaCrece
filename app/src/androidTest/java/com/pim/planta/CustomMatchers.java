package com.pim.planta;

import android.view.View;

import androidx.test.espresso.matcher.BoundedMatcher;

import com.pim.planta.models.CalendarDraw;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomMatchers {

    public static Matcher<View> hasDayColor(final int day, final int expectedColor) {
        return new DayColorMatcher(day, expectedColor);
    }

    // Clase interna para el Matcher
    public static class DayColorMatcher extends BoundedMatcher<View, CalendarDraw> {
        private final int day;
        private final int expectedColor;

        public DayColorMatcher(int day, int expectedColor) {
            super(CalendarDraw.class);  // Especifica que el Matcher es para CalendarDraw
            this.day = day;
            this.expectedColor = expectedColor;
        }

        @Override
        protected boolean matchesSafely(CalendarDraw calendarDraw) {
            // Obtener el año y mes actuales desde CalendarDraw
            int currentYear = calendarDraw.getCurrentYear();
            int currentMonth = calendarDraw.getCurrentMonth();

            // Crear la clave para acceder a dayBackgroundColors (año-mes-día)
            String key = currentYear + "-" + currentMonth + "-" + day;

            // Obtener el color del día desde el HashMap
            Integer actualColor = calendarDraw.getDayBackgroundColors().get(key);

            // Verificar si el color actual es igual al esperado
            return actualColor != null && actualColor == expectedColor;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("Day color should be: " + Integer.toHexString(expectedColor));
        }
    }
}
