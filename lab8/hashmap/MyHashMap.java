package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Howard
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private int initialSize, size;
    private double maxLoad;
    private Collection<Node>[] table;

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    class KeyIterator implements Iterator<K> {
        int index;
        Iterator<Node> it;

        public KeyIterator() {
            index = 0;
            it = (Iterator<Node>) table[index].iterator();
            refresh();
        }

        private void refresh() {
            while (it == null || !it.hasNext()) {
                if (index == initialSize) {
                    break;
                }
                it = (Iterator<Node>) table[index].iterator();
                index++;
            }
        }

        public boolean hasNext() {
            refresh();
            return it != null && it.hasNext();
        }

        public K next() {
            if (!hasNext()) {
                return null;
            }
            return it.next().key;
        }
    }

    class MyHashMapIterator implements Iterator<Node>{
        int index;
        Iterator<Node> it;

        public MyHashMapIterator() {
            index = 0;
            it = (Iterator<Node>) table[index].iterator();
            refresh();
        }

        private void refresh() {
            while (it == null || !it.hasNext()) {
                if (index == initialSize) {
                    break;
                }
                it = (Iterator<Node>) table[index].iterator();
                index++;
            }
        }

        public boolean hasNext() {
            refresh();
            return it != null && it.hasNext();
        }

        public Node next() {
            if (!hasNext()) {
                return null;
            }
            return it.next();
        }
    }

    public void clear() {
        table = createTable(initialSize);
        size = 0;
    }

    public boolean containsKey(K key) {
        Iterator<K> it = this.iterator();
        while (it.hasNext()) {
            K item = it.next();
            if (item.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        Iterator<Node> it = new MyHashMapIterator();
        while (it.hasNext()) {
            Node t = it.next();
            if (t.key.equals(key)) {
                return t.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    private void resize() {
        Collection<Node>[] temp = createTable(initialSize*2);
        Iterator<Node> it = new MyHashMapIterator();
        while (it.hasNext()) {
            Node n = it.next();
            int c = n.key.hashCode();
            while (c<0) {
                c += initialSize*2;
            }
            c %= initialSize*2;
            temp[c].add(n);
        }
        table = temp;
        initialSize*=2;
    }

    public void put(K key, V value) {
        if (!containsKey(key)) {
            size++;
        }
        if ((double) size / initialSize > maxLoad) {
            resize();
        }
        Node n = createNode(key, value);
        int c = key.hashCode();
        while (c<0) {
            c += initialSize;
        }
        c %= initialSize;
        if (containsKey(key)) {
            Node temp = null;
            Iterator<Node> it = new MyHashMapIterator();
            while (it.hasNext()) {
                Node t = it.next();
                if (t.key.equals(key)) {
                    temp = t;
                }
            }
            table[c].remove(temp);
        }
        table[c].add(n);
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> s = new HashSet<K>();
        Iterator<K> it = this.iterator();
        while (it.hasNext()) {
            s.add(it.next());
        }
        return s;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        initialSize = 16;
        maxLoad = 0.75;
        size = 0;
        table = createTable(initialSize);
    }

    public MyHashMap(int initialSize) {
        super();
        this.initialSize = initialSize;
        table = createTable(initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        super();
        this.initialSize = initialSize;
        this.maxLoad = maxLoad;
        table = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i=0; i<tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }



}
