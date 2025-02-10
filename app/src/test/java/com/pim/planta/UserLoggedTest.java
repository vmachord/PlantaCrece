package com.pim.planta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import com.pim.planta.models.User;
import com.pim.planta.models.UserLogged;

import org.junit.Before;
import org.junit.Test;

public class UserLoggedTest {

    @Before
    public void setup() {
        // Reset the instance before each test
        UserLogged.getInstance().setCurrentUser(null);
    }

    @Test
    public void testSingleton() {
        UserLogged instance1 = UserLogged.getInstance();
        UserLogged instance2 = UserLogged.getInstance();
        assertNotNull(instance1);
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetCurrentUserNull() {
        UserLogged userLogged = UserLogged.getInstance();
        assertNull(userLogged.getCurrentUser());
    }

    @Test
    public void testSetAndGetCurrentUser() {
        UserLogged userLogged = UserLogged.getInstance();
        User user = new User("testuser", "test@example.com", "password");
        userLogged.setCurrentUser(user);
        assertEquals(user, userLogged.getCurrentUser());
    }
}