import java.util.HashMap;

public interface Cache<K extends Comparable, V> {
    V get(K key);
    void set(K key, V value);
}