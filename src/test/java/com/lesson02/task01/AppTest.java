package com.lesson02.task01;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    @Test
    public void putAndGetTest()
    {
        MyHashMap map = new MyHashMap();

        map.put("1423", "123");
        map.put("321", "567");
        map.put("35621", "123216546");
        map.put("31", "567");
        map.put("3921", "567");
        map.put("1125", "13");
        map.put("4561", "0000");
        map.put("1111", "1111");
        map.put("2222", "2222");
        map.put("3333", "3333");
        map.put("4444", "4444");
        map.put("5555", "5555");
        map.put("6666", "6666");
        map.put("7777", "7777");
        map.put("8888", "8888");
        map.put("9999", "9999");
        map.put("123456", "178");
        Object expected = map.get("123456");
        Object actual = "178";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sizeTest(){
        MyHashMap map = new MyHashMap();

        map.put("1423", "123");
        map.put("321", "567");
        map.put("35621", "123216546");
        map.put("31", "567");
        map.put("3921", "567");
        map.put("1125", "13");
        map.put("4561", "0000");
        map.put("1111", "1111");
        map.put("2222", "2222");
        map.put("3333", "3333");
        map.put("4444", "4444");
        map.put("5555", "5555");
        map.put("6666", "6666");
        map.put("7777", "7777");
        map.put("8888", "8888");
        map.put("9999", "9999");
        map.put("123456", "178");
        int expected = map.size();
        int actual = 17;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeTest(){
        MyHashMap map = new MyHashMap();

        map.put("1423", "123");
        map.put("321", "567");
        map.put("35621", "123216546");
        map.put("31", "567");
        map.put("3921", "567");
        map.put("1125", "13");
        map.put("4561", "0000");
        map.put("1111", "1111");
        map.put("2222", "2222");
        map.put("3333", "3333");
        map.put("4444", "4444");
        map.put("5555", "5555");
        map.put("6666", "6666");
        map.put("7777", "7777");
        map.put("8888", "8888");
        map.put("9999", "9999");
        map.put("123456", "178");

        map.remove("3333");

        Assert.assertNull(map.get("3333"));
    }

    @Test
    public void nonTest(){
        MyHashMap map = new MyHashMap();

        map.put("1423", "123");
        map.put("321", "567");
        map.put("35621", "123216546");
        map.put("31", "567");
        map.put("3921", "567");
        map.put("1125", "13");
        map.put("4561", "0000");
        map.put("1111", "1111");
        map.put("2222", "2222");
        map.put("3333", "3333");
        map.put("4444", "4444");
        map.put("5555", "5555");
        map.put("6666", "6666");
        map.put("7777", "7777");
        map.put("8888", "8888");
        map.put("9999", "9999");
        map.put("123456", "178");

        map.remove("3333");

        Assert.assertNotNull(map.get("3333"));
    }
}
