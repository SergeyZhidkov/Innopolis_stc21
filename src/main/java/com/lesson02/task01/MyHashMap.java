package com.lesson02.task01;

import java.util.Arrays;

public class MyHashMap<K,V> {
    private int globalCounter = 0;
    private int length = 16;
    private int depth = 16;
    private static double LOAD = 0.75d;
    private MyEntity<K,V> [][] mapa = new MyEntity[length][depth];


    public void put(K key, V value) {
        if (resizable()) {
            extendInlength();
        }

        MyEntity[] bucket = mapa[getBucketNumber(key)];
        if (fullnessOfBucket(bucket) == depth) {
            bucket = (MyEntity<K,V>[]) extendInDepth(bucket);
        }
        bucket = addElement(bucket, key, value);
        mapa[getBucketNumber(key)] = bucket;
        globalCounter = 0;
        for (MyEntity[] m :
                mapa) {
            if (fullnessOfBucket(m) != 0) {
                globalCounter++;
            }
        }
    }

    private boolean resizable() {
        return globalCounter > mapa.length * LOAD;
    }

    private MyEntity<K, V>[] addElement(MyEntity<K,V>[] bucket, K key, V value) {
        int size = fullnessOfBucket(bucket);
        if (size + 1 == depth) {
            bucket = (MyEntity<K,V>[]) extendInDepth(bucket);
        }
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == null) {
                bucket[i] = new MyEntity<>(key, value);
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

    private void extendInlength() {
        int index = mapa.length - 1;
        mapa = Arrays.copyOf(mapa, mapa.length * 2);
        for (int i = index; i < mapa.length; i++) {
            mapa[i] = new MyEntity[depth];
        }
    }

    private Object[] extendInDepth(MyEntity<K,V>[] busket) {
        busket = Arrays.copyOf(busket, busket.length * 2);
        depth = busket.length;
        return busket;
    }

    private int fullnessOfBucket(MyEntity<K,V>[] bucket) {
        int i = 0;
        for (MyEntity<K,V> obj : bucket) {
            if (obj != null) {
                i++;
            }
        }
        return i;
    }

    private int getBucketNumber(K key) {
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

    public V get(K key) {
        MyEntity[] bucket = mapa[getBucketNumber(key)];
        for (MyEntity<K,V> f :
                bucket) {
            if (f != null && f.getKey().equals(key)) {
                return  f.getValue();
            }
        }
        return null;
    }

    public boolean remove(K key) {

        MyEntity[] bucket = mapa[getBucketNumber(key)];
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != null && bucket[i].getKey().equals(key)) {
                bucket[i] = null;
                return true;
            }
        }

        return false;
    }

}
