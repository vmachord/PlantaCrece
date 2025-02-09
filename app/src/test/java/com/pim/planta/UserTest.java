package com.pim.planta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.pim.planta.models.User;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserTest {

    @Test
    public void testConstructorAndGetters() {
        User user = new User("testuser", "test@example.com", "password");
        assertNotNull(user.getCreationDate());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetters() {
        User user = new User("testuser", "test@example.com", "password");
        user.setUsername("newuser");
        user.setEmail("new@example.com");
        user.setPassword("newpassword");
        Date newDate = new Date(1672531200000L);
        user.setCreationDate(newDate);
        user.setId(1);

        assertEquals("newuser", user.getUsername());
        assertEquals("new@example.com", user.getEmail());
        assertEquals("newpassword", user.getPassword());
        assertEquals(1672531200000L, user.getCreationDate());
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetFormattedCreationDate() throws ParseException {
        User user = new User("testuser", "test@example.com", "password");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date = dateFormat.parse("01/01/2024");
        user.setCreationDate(date);
        assertEquals("1st of January, 2024", user.getFormattedCreationDate());

        date = dateFormat.parse("02/01/2024");
        user.setCreationDate(date);
        assertEquals("2nd of January, 2024", user.getFormattedCreationDate());

        date = dateFormat.parse("03/01/2024");
        user.setCreationDate(date);
        assertEquals("3rd of January, 2024", user.getFormattedCreationDate());

        date = dateFormat.parse("04/01/2024");
        user.setCreationDate(date);
        assertEquals("4th of January, 2024", user.getFormattedCreationDate());

        date = dateFormat.parse("11/01/2024");
        user.setCreationDate(date);
        assertEquals("11th of January, 2024", user.getFormattedCreationDate());

        date = dateFormat.parse("21/01/2024");
        user.setCreationDate(date);
        assertEquals("21st of January, 2024", user.getFormattedCreationDate());

        date = dateFormat.parse("22/01/2024");
        user.setCreationDate(date);
        assertEquals("22nd of January, 2024", user.getFormattedCreationDate());

        date = dateFormat.parse("23/01/2024");
        user.setCreationDate(date);
        assertEquals("23rd of January, 2024", user.getFormattedCreationDate());

        date = dateFormat.parse("24/01/2024");
        user.setCreationDate(date);
        assertEquals("24th of January, 2024", user.getFormattedCreationDate());
    }
}