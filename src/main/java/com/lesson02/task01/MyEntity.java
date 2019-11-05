package com.lesson02.task01;

import java.util.Objects;

public class MyEntity<K, V> {

    private final K key;
    private V value;


    public MyEntity(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }


    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEntity myEntity = (MyEntity) o;
        return key.equals(myEntity.key) &&
                value.equals(myEntity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
