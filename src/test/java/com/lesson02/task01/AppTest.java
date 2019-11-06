package com.lesson02.task01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private HashMap map;
    private MyHashMap mapa;
    private Random random;

    @Before
    public void setup() {
        map = new HashMap();
        mapa = new MyHashMap();
        random = new Random();
    }


    @Test
    public void put1000Elements() {

        for (int i = 0; i < 1000; i++) {
            map.put(i, random.nextInt());
            mapa.put(i, random.nextInt());
        }

        Assert.assertEquals(mapa.size(), map.size());
    }

    @Test
    public void putAllElements(){
        for (int i = 0; i < 100; i++) {
            map.put(i, random.nextInt());
            mapa.putAll(map);
            Assert.assertEquals(mapa.size(), map.size());
        }
    }

    @Test
    public void removeByKey() {
        for (int i = 0; i < 100; i++) {
            map.put(i, random.nextInt());
            mapa.put(i, random.nextInt());
        }
        map.remove(1);
        map.remove(17);
        map.remove(74);
        mapa.remove(1);
        mapa.remove(17);
        mapa.remove(74);

        assertEquals(mapa.size(), map.size());
    }

    @Test
    public void getFromMap() {
        final int data = random.nextInt();
        map.put(data, data);
        mapa.put(data,data);
        assertEquals(data, map.get(data));
        assertEquals(data, mapa.get(data));
        assertEquals(map.get(data), mapa.get(data));
    }

    @Test
    public void containsKeyFunction() {
        final int data = random.nextInt();
        map.put(data, data);
        mapa.put(data,data);
        assertTrue(map.containsKey(data));
        assertFalse(map.containsKey(3));
        assertEquals(1, map.size());
        assertTrue(mapa.containsKey(data));
        assertFalse(mapa.containsKey(3));
        assertEquals(1, mapa.size());
    }

    @Test
    public void containsValueFunction() {
        final int data = random.nextInt();
        map.put(data, data);
        mapa.put(data, data);
        assertTrue(map.containsValue(data));
        assertFalse(map.containsValue(5));
        assertEquals(1, map.size());
        assertTrue(mapa.containsValue(data));
        assertFalse(mapa.containsValue(5));
        assertEquals(1, mapa.size());
    }

    @Test
    public void isEmptyTrue() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertEquals(0, mapa.size());
        assertTrue(mapa.isEmpty());
    }

    @Test
    public void isEmptyFalse() {
        final int data = random.nextInt();
        map.put(data, data);
        mapa.put(data, data);
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertEquals(1, mapa.size());
        assertFalse(mapa.isEmpty());

    }

    @Test
    public void clearMap() {
        for (int i = 0; i < 1000; i++) {
            map.put(i, random.nextInt());
            mapa.put(i, random.nextInt());
        }
        assertEquals(1000, map.size());
        map.clear();
        assertEquals(0, map.size());
        assertEquals(1000, mapa.size());
        mapa.clear();
        assertEquals(0, mapa.size());
    }
}

