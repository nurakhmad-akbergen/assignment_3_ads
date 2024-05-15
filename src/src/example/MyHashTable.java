package src.example;

import java.util.Iterator;

public class MyHashTable<K, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    public MyHashTable(int capacity) {
        this.M = capacity;
        chainArray = new HashNode[M];
        size = 0;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if (chainArray[index] == null) {
            chainArray[index] = newNode;
        } else {
            HashNode<K, V> currentNode = chainArray[index];
            while (currentNode.next != null) {
                if (currentNode.key.equals(key)) {
                    currentNode.value = value;
                    return;
                }
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> currentNode = chainArray[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> prev = null;
        HashNode<K, V> currentNode = chainArray[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = currentNode.next;
                } else {
                    prev.next = currentNode.next;
                }
                size--;
                return currentNode.value;
            }
            prev = currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> currentNode = chainArray[i];
            while (currentNode != null) {
                if (currentNode.value.equals(value)) {
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> currentNode = chainArray[i];
            while (currentNode != null) {
                if (currentNode.value.equals(value)) {
                    return currentNode.key;
                }
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    public Iterator<K> keySet() {
        return new Iterator<K>() {
            private int currentIndex = 0;
            private HashNode<K, V> currentNode = null;

            @Override
            public boolean hasNext() {
                if (currentNode != null && currentNode.next != null) {
                    return true;
                }
                for (int i = currentIndex; i < M; i++) {
                    if (chainArray[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (currentNode != null && currentNode.next != null) {
                    currentNode = currentNode.next;
                    return currentNode.key;
                }
                for (int i = currentIndex; i < M; i++) {
                    if (chainArray[i] != null) {
                        currentIndex = i;
                        currentNode = chainArray[i];
                        return currentNode.key;
                    }
                }
                return null;
            }
        };
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % M);
    }

}






