package com.lesson02.task01;

import java.util.*;

/**
 * Project to create a hashMap implementation variant.
 *
 */

public class MyHashMap<K, V> implements Map<K, V> {
    private int globalCounter = 0;
    private int length = 16;
    private int depth = 16;
    private int size = 0;
    private static double LOAD = 0.75d;
    private MyEntity[][] mapa;
    transient Set<K> keySet;

    public MyHashMap() {
        mapa = new MyEntity[length][depth];
    }

    public V put(K key, V value) {
        if (resizable()) {
            extendInlength();
        }

        MyEntity[] bucket = mapa[getBucketNumber((K) key)];
        if (fullnessOfBucket(bucket) == depth) {
            bucket = (MyEntity<K, V>[]) extendInDepth(bucket);
        }
        bucket = addElement(bucket, (K) key, (V) value);
        mapa[getBucketNumber((K) key)] = bucket;
        globalCounter = 0;
        for (MyEntity[] m :
                mapa) {


            if (fullnessOfBucket(m) != 0) {
                globalCounter++;
            }
        }
        return null;
    }

    private boolean resizable() {
        return globalCounter > mapa.length * LOAD;
    }

    private MyEntity<K, V>[] addElement(MyEntity<K, V>[] bucket, K key, V value) {
        int size = fullnessOfBucket(bucket);
        if (size + 1 == depth) {
            bucket = (MyEntity<K, V>[]) extendInDepth(bucket);
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

    private Object[] extendInDepth(MyEntity<K, V>[] busket) {
        busket = Arrays.copyOf(busket, busket.length * 2);
        depth = busket.length;
        return busket;
    }

    private int fullnessOfBucket(MyEntity<K, V>[] bucket) {
        int i = 0;
        for (MyEntity<K, V> obj : bucket) {
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

    public V get(Object key) {
        MyEntity[] bucket = mapa[getBucketNumber((K) key)];
        for (MyEntity<K, V> f :
                bucket) {
            if (f != null && f.getKey().equals(key)) {
                return f.getValue();
            }
        }
        return null;
    }

    public V remove(Object key) {

        MyEntity[] bucket = mapa[getBucketNumber((K) key)];
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != null && bucket[i].getKey().equals(key)) {
                bucket[i] = null;
                return null;
            }
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (resizable()) {
                extendInlength();
            }

            MyEntity[] bucket = mapa[getBucketNumber((K) pair.getKey())];
            if (fullnessOfBucket(bucket) == depth) {
                bucket = (MyEntity<K, V>[]) extendInDepth(bucket);
            }
            bucket = addElement(bucket, (K) pair.getKey(), (V) pair.getValue());
            mapa[getBucketNumber((K) pair.getKey())] = bucket;
            globalCounter = 0;
            for (MyEntity[] m :
                    mapa) {


                if (fullnessOfBucket(m) != 0) {
                    globalCounter++;
                }
            }
        }
    }

    public boolean containsKey(Object key) {
        return this.get(key) != null;
    }

    public boolean containsValue(Object value) {
        for (MyEntity<K, V>[] myEntities : mapa) {
            for (MyEntity<K, V> myEntity : myEntities) {
                if (myEntity != null && myEntity.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return globalCounter == 0;
    }

    public void clear() {
        mapa = new MyEntity[length][depth];
        ;
        globalCounter = 0;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        HashMap<K, V> map = new HashMap<K, V>();
        for (MyEntity<K, V>[] myEntities : mapa) {
            for (MyEntity<K, V> myEntity : myEntities) {
                if (myEntity != null) {
                    map.put(myEntity.getKey(), myEntity.getValue());
                }
            }
        }
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        HashMap<K, V> map = new HashMap<K, V>();
        for (MyEntity<K, V>[] myEntities : mapa) {
            for (MyEntity<K, V> myEntity : myEntities) {
                if (myEntity != null) {
                    map.put(myEntity.getKey(), myEntity.getValue());
                }
            }
        }
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        HashMap<K, V> map = new HashMap<K, V>();
        for (MyEntity<K, V>[] myEntities : mapa) {
            for (MyEntity<K, V> myEntity : myEntities) {
                if (myEntity != null) {
                    map.put(myEntity.getKey(), myEntity.getValue());
                }
            }
        }
        return map.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++){
                if(mapa[i][j]!=null) {
                    sb.append(mapa[i][j].getKey()).append(" : ").append(mapa[i][j].getValue()).append("\n");
                }
            }
        }
        return sb.toString();
    }

}
