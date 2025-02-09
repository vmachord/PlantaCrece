package com.pim.planta;

import static org.junit.Assert.assertEquals;

import com.pim.planta.models.UserPlantRelation;

import org.junit.Test;

public class UserPlantRelationTest {

    @Test
    public void testConstructorAndGetters() {
        UserPlantRelation relation = new UserPlantRelation(1, 2);
        assertEquals(1, relation.getUserId());
        assertEquals(2, relation.getPlantId());
        assertEquals(0, relation.getGrowCount());
    }

    @Test
    public void testSetters() {
        UserPlantRelation relation = new UserPlantRelation(1, 2);
        relation.setUserId(3);
        relation.setPlantId(4);
        relation.setGrowCount(5);

        assertEquals(3, relation.getUserId());
        assertEquals(4, relation.getPlantId());
        assertEquals(5, relation.getGrowCount());
    }
}