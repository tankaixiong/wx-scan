package tk.vo;

/**
 * @Author: tank
 * @Email: kaixiong.tan@qq.com
 * @Date: 2017/12/27
 * @Version: 1.0
 * @Description:
 */
public class KeyValue<K, V> {
    private K key;
    private V value;

    public KeyValue() {
    }

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
