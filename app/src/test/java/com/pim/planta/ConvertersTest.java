package com.pim.planta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.pim.planta.models.Converters;

import org.junit.Test;

import java.util.Date;

public class ConvertersTest {

    @Test
    public void testToDate() {
        Long timestamp = 1672531200000L;
        Date result = Converters.toDate(timestamp);
        assertEquals(new Date(timestamp), result);
    }

    @Test
    public void testToDateNull() {
        Date result = Converters.toDate(null);
        assertNull(result);
    }

    @Test
    public void testToTimestamp() {
        Date date = new Date(1672531200000L);
        Long result = Converters.toTimestamp(date);
        assertEquals(Long.valueOf(1672531200000L), result);
    }

    @Test
    public void testToTimestampNull() {
        Long result = Converters.toTimestamp(null);
        assertNull(result);
    }
}