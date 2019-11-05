package com.lesson02.task01;

import java.util.Objects;

public class MyEntity<K, V> {

    private Object key;
    private Object value;


    public MyEntity(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
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
