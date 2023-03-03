public class BSTMap<K, V> implements Map61B<K, V>{
    private int size;
    private class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
    }
}
