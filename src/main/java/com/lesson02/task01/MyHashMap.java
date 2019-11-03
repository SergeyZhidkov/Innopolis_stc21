package com.lesson02.task01;

import java.util.Arrays;

public class MyHashMap {
    private int globalCounter = 0;
    private int length = 16;
    private int depth = 16;
    private static double LOAD = 0.75d;
    private MyEntity[][] mapa = new MyEntity[length][depth];


    public MyEntity put(Object key, Object value) {
        if (resizable()) {
            length();
        }

        MyEntity[] bucket = mapa[getBucketNumber(key)];
        if (sizze(bucket) == depth) {
            bucket = (MyEntity[]) depth(bucket);
        }
        bucket = (MyEntity[]) addElement(bucket, key, value);
        mapa[getBucketNumber(key)] = bucket;
        globalCounter = 0;
        for (MyEntity[] m :
                mapa) {
            if (sizze(m) != 0) {
                globalCounter++;
            }
        }
        //globalCounter++;
        return null;
    }

    private boolean resizable() {
        return globalCounter > mapa.length * LOAD;
    }

    private Object[] addElement(MyEntity[] bucket, Object key, Object value) {
        int size = sizze(bucket);
        if (size + 1 == depth) {
            bucket = (MyEntity[]) depth(bucket);
        }
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == null) {
                bucket[i] = new MyEntity(key, value);
                break;
            } else if (bucket[i] != null) {
                if (key.equals(bucket[i].getKey())) {
                    bucket[i].setValue(value);
                    break;
                }
            }
        }
        return bucket;
    }

    private void length() {
        int index = mapa.length - 1;
        mapa = Arrays.copyOf(mapa, mapa.length * 2);
        for (int i = index; i < mapa.length; i++) {
            mapa[i] = new MyEntity[depth];
        }
    }

    private Object[] depth(MyEntity[] busket) {
        busket = Arrays.copyOf(busket, busket.length * 2);
        depth = busket.length;
        return busket;
    }

    private int sizze(MyEntity[] bucket) {
        int i = 0;
        for (MyEntity obj : bucket) {
            if (obj != null) {
                i++;
            }
        }
        return i;
    }

    private int getBucketNumber(Object key) {
        int bucketNumber = key.hashCode() % mapa.length;
        bucketNumber = bucketNumber > 0 ? bucketNumber : bucketNumber * -1;
        while (bucketNumber > mapa.length) {
            bucketNumber = key.hashCode() % mapa.length;
        }
        return bucketNumber;
    }


    public int size() {
        int size = 0;
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] != null) {
                    size++;
                }
            }
        }
        return size;
    }

    public Object get(Object key) {
        MyEntity[] bucket = mapa[getBucketNumber(key)];
        for (MyEntity f :
                bucket) {
            if (f != null && f.getKey().equals(key)) {
                return f.getValue();
            }
        }
        return null;
    }

    public boolean remove(Object key) {

        MyEntity[] bucket = mapa[getBucketNumber(key)];
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != null && bucket[i].getKey().equals(key)) {
                //f.setValue(null);
                //f.setKey(null);
                bucket[i] = null;
                return true;
            }
        }

        return false;
    }

}
