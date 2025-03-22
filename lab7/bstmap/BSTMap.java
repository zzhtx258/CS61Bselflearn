package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;

    private class Node {
        K key;
        V value;
        Node left, right;
        int size;
        public Node(K key, V value, Node left, Node right, int size){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size = size;
        }
    }

    Node root = new Node(null, null, null, null, 0);

    public void clear() {
        root = new Node(null, null, null, null, 0);
        size = 0;
    }

    private Node find(K key) {
        Node p = root;
        boolean f = true;
        while (p.key != key) {
            int cmp = p.key.compareTo(key);
            if (cmp > 0) {
                p = p.left;
            } else if (cmp < 0) {
                p = p.right;
            } else {
                break;
            }
            if (p == null) {
                f = false;
                break;
            }
        }
        if (!f) {
            return null;
        }
        return p;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if(size == 0) {
            return false;
        }
        return find(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        return find(key).value;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if(containsKey(key)) {
            return;
        }
        size++;
        Node p = root;
        Node t = new Node(key, value, null, null, 0);
        if (size == 1){
            root = t;
            return;
        }
        while (p.key != key) {
            if (p.key.compareTo(key) > 0) {
                if (p.left == null) {
                    p.left = t;
                    break;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = t;
                    break;
                }
                p = p.right;
            }
        }

    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
