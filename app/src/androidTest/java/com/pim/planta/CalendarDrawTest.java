package com.pim.planta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.pim.planta.models.CalendarDraw;
import com.pim.planta.models.DiaryEntry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class CalendarDrawTest {

    private CalendarDraw calendarDraw;
    private Context context;

    @Before
    public void setup() {
        context = ApplicationProvider.getApplicationContext();
        calendarDraw = new CalendarDraw(context, null);
        // Set the size of the view
        calendarDraw.layout(0, 0, 1000, 1000);
    }

    @Test
    public void testGetDayFromCoordinates() {
        // Test a valid day
        int day = calendarDraw.getDayFromCoordinates(100, 200);
        assertTrue(day > 0);
        // Test an invalid day
        int invalidDay = calendarDraw.getDayFromCoordinates(10000, 10000);
        assertEquals(-1, invalidDay);
    }

    @Test
    public void testPrevMonth() {
        int initialMonth = calendarDraw.getCurrentMonth();
        calendarDraw.prevMonth();
        int newMonth = calendarDraw.getCurrentMonth();
        if (initialMonth == 1) {
            assertEquals(1, newMonth);
        } else {
            assertEquals(initialMonth - 1, newMonth);
        }
    }

    @Test
    public void testNextMonth() {
        int initialMonth = calendarDraw.getCurrentMonth();
        calendarDraw.nextMonth();
        int newMonth = calendarDraw.getCurrentMonth();
        if (initialMonth == 12) {
            assertEquals(12, newMonth);
        } else if (initialMonth == LocalDate.now().getMonthValue() && calendarDraw.getCurrentYear() == LocalDate.now().getYear()) {
            assertEquals(initialMonth, newMonth);
        } else {
            assertEquals(initialMonth + 1, newMonth);
        }
    }

    @Test
    public void testSetDiaryEntries() {
        List<DiaryEntry> entries = new ArrayList<>();
        entries.add(new DiaryEntry("highlight", "annotation", 1, 1, 1234567890));
        calendarDraw.setDiaryEntries(entries);
        // This test is conceptual, you would need to verify that the calendar is redrawn
        // and that the entry indicators are drawn correctly.
    }

    @Test
    public void testHighlightDay() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        calendarDraw.highlightDay(date);
        assertEquals(date, calendarDraw.getCurrentDay());
    }

    @Test
    public void testHasEntryForDay() {
        List<DiaryEntry> entries = new ArrayList<>();
        entries.add(new DiaryEntry("highlight", "annotation", 1, 1, 1234567890));
        calendarDraw.setDiaryEntries(entries);
        LocalDate date = Instant.ofEpochMilli(1234567890).atZone(ZoneId.systemDefault()).toLocalDate();
        assertTrue(calendarDraw.hasEntryForDay(date));
        LocalDate date2 = LocalDate.of(2024, 1, 1);
        assertFalse(calendarDraw.hasEntryForDay(date2));
    }

    @Test
    public void testNormalizeToStartOfDay() {
        long dateInMillis = 1234567890;
        long normalizedDate = calendarDraw.normalizeToStartOfDay(dateInMillis);
        assertEquals(0, normalizedDate % (24 * 60 * 60 * 1000));
    }
}