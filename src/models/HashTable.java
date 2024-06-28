package models;

import java.util.LinkedList;

public class HashTable<K, V> {

    private LinkedList<HashNode<K, V>>[] bucketArray;
    private int numBuckets = 11;
    private int size;

    public HashTable() {
        bucketArray = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            bucketArray[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int squaredHashCode(K key) {
        int hashCode = key.hashCode();
        int squaredHashCode = hashCode * hashCode;
        return (squaredHashCode & Integer.MAX_VALUE) % numBuckets;
    }

    private int multipliedHashCode(K key) {
        int hashCode = key.hashCode() * 31;
        return (hashCode & Integer.MAX_VALUE) % numBuckets;
    }

    public void put(K key, V value, boolean useSquaredHashCode) {
        int bucketIndex = useSquaredHashCode ? squaredHashCode(key) : multipliedHashCode(key);
        for (HashNode<K, V> node : bucketArray[bucketIndex]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        bucketArray[bucketIndex].add(new HashNode<>(key, value));
        size++;
    }

    public int size() {
        return size;
    }
}