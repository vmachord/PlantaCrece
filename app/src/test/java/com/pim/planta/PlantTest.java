package com.pim.planta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.pim.planta.models.Plant;

import org.junit.Test;

public class PlantTest {

    @Test
    public void testConstructorAndGetters() {
        Plant plant = new Plant("Test Plant", "basePath", 1, 100, 200, "description", "scientificName");
        assertEquals("Test Plant", plant.getName());
        assertEquals("basePath", plant.getBasePath());
        assertEquals(1, plant.getImageResourceId());
        assertEquals(100, plant.getXp());
        assertEquals(200, plant.getXpMax());
        assertEquals("description", plant.getDescription());
        assertEquals("scientificName", plant.getScientificName());
        assertNull(plant.getNickname());
    }

    @Test
    public void testSetters() {
        Plant plant = new Plant("Test Plant", "basePath", 1, 100, 200, "description", "scientificName");
        plant.setName("Updated Plant");
        plant.setBasePath("newBasePath");
        plant.setImageResourceId(2);
        plant.setXp(150);
        plant.setXpMax(250);
        plant.setDescription("new description");
        plant.setScientificName("new scientific name");
        plant.setNickname("nickname");
        plant.setId(1);

        assertEquals("Updated Plant", plant.getName());
        assertEquals("newBasePath", plant.getBasePath());
        assertEquals(2, plant.getImageResourceId());
        assertEquals(150, plant.getXp());
        assertEquals(250, plant.getXpMax());
        assertEquals("new description", plant.getDescription());
        assertEquals("new scientific name", plant.getScientificName());
        assertEquals("nickname", plant.getNickname());
        assertEquals(1, plant.getId());
    }

    @Test
    public void testAddXp() {
        Plant plant = new Plant("Test Plant", "basePath", 1, 100, 200, "description", "scientificName");
        plant.addXp(50);
        assertEquals(150, plant.getXp());

        plant.addXp(100);
        assertEquals(250, plant.getXp());
    }
}