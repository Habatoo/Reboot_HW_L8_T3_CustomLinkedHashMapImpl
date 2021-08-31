package Laptenkov;

import java.util.Objects;

/**
 * Класс {@link CustomLinkedHashMapImpl<K, V>} отображение на основе хеш-таблицы.
 * Класс {@link CustomLinkedHashMapImpl<K, V>} реализует интерфейс {@link CustomLinkedHashMap<K, V>}<K, V>.
 * Класс {@link CustomLinkedHashMapImpl<K, V>} может хранить объекты любого типа.
 * Методы toString & keys должны возвращать элементы в том порядке,
 * в котором элементы были добавлены в отображение.
 *
 * @param <K>
 * @param <V>
 */
public class CustomLinkedHashMapImpl<K, V> implements CustomLinkedHashMap{

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> after;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int DEFAULT_CAPACITY = 16;
    private Node<K, V> table[] = new Node[DEFAULT_CAPACITY];
    private int size;
    private Node<K, V> head, tail;
    private int capacity;

    /**
     * Конструктор пустого объекта {@link CustomLinkedHashMapImpl <K, V>}.
     */
    CustomLinkedHashMapImpl() {
        this.capacity = DEFAULT_CAPACITY;
    }

    /**
     * Конструктор пустого объекта {@link CustomLinkedHashMapImpl<K, V>}.
     */
    CustomLinkedHashMapImpl(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl#size()} возвращает размер связанного списка
     * объекта {@link CustomLinkedHashMapImpl}.
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl#isEmpty()} возвращает булево значение
     * при проверке объекта {@link CustomLinkedHashMapImpl} на пустоту.
     *
     * @return <code>true</code> если размер не нулевой,
     * <code>false</code> если размер нулевой.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl#get(Object)} возвращает V - значение
     * по переданному ключу K
     *
     * @param key ключ К.
     * @return V - значение если ключу К соотвествует значение,
     * null если такого ключа нет.
     */
    @Override
    public Object get(Object key) {

        if (null == key && table[0] != null) {
            return null;
        }

        Node<K, V> tmp = table[getBucketId((K) key)];
        while (tmp != null) {
            if (getItemHash(tmp.key) == getItemHash((K) key) && Objects.equals(tmp.key, key)) {
                return tmp.value;
            }
            tmp = tmp.next;
        }

        return null;
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl#put(Object, Object)} устанавливает V - значение
     * по переданному ключу K, если значение уже есьб заменяет его.
     *
     * @param key ключ.
     * @param value значение
     * @return замененное значение или null.
     */
    @Override
    public Object put(Object key, Object value) {

        int bucketId = getBucketId((K) key);
        int keyHash = getItemHash((K) key);

        Node<K, V> tmp = table[bucketId];
        Node<K, V> prev = null;

        while (tmp != null) {
            if (getItemHash(tmp.key) == keyHash && Objects.equals(tmp.key, key)) {
                V oldValue = tmp.value;
                tmp.value = (V) value;

                return oldValue;
            }

            prev = tmp;
            tmp = tmp.next;
        }

        Node<K, V> insertedNode = new Node<>((K)key, (V) value);

        if (prev == null) {
            table[bucketId] = insertedNode;
        } else {
            prev.next = insertedNode;
        }

        if (tail == null) {
            head = tail = insertedNode;
        } else {
            tail.after = insertedNode;
            tail = insertedNode;
        }
        size++;

        return null;
    }

    private int getItemHash(K item) {
        if (item == null) {
            return 0;
        }
        return item.hashCode();
    }

    private int getBucketId(K key) {

        if (key == null) {
            return 0;
        }
        return 1 + key.hashCode() % (table.length - 1);
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl#remove(Object)} удаляет объект по переданному
     * значению ключа К при наличия ключа K объекта в {@link CustomLinkedHashMapImpl} или
     * возвращает null.
     *
     * @param key передаваемый ключ.
     * @return удаленное значение или null если объект не существует.
     */
    @Override
    public Object remove(Object key) {

        if (size == 0) {
            return null;
        }

        if (null == key && table[0] != null) {
            table[0] = null;
            return null;
        }

        int bucketId = getBucketId((K) key);
        int keyHash = getItemHash((K) key);
        boolean flag = false;

        Node<K, V> tmp = head;
        Node<K, V> tmpPrev = null;

        while (tmp != null) {
            if (getItemHash(tmp.key) == keyHash && Objects.equals(tmp.key, key)) {
                flag = true;
                break;
            }
            tmpPrev = tmp;
            tmp = tmp.after;
        }

        if (!flag) {
            return null;
        }

        if (tmpPrev == null) {
            table[bucketId] = null;
        } else {
            tmpPrev.next = tmp.next;
        }

        Node<K, V> result = tmp;
        if (head == tmp && tail == tmp) {
            head = tail = null;
        } else if (head == tmp) {
            head = tmp.after;
        } else if (tail == tmp) {
            tail = tmpPrev;
        } else {
            tmpPrev.after = tmp.after;
        }

        size--;

        return result.value;
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl#containsKey(Object)} возвращает булево значение
     * при проверке наличия ключа K объекта в {@link CustomLinkedHashMapImpl}.
     *
     * @param key ключ типа К для проверки.
     * @return возвращает <code>true</code> если ключ присутствует,
     * возвращает <code>false</code> если ключ отсутствует.
     */
    @Override
    public boolean containsKey(Object key) {
        if (null == key && table[0] != null) {
            return true;
        }

        return get(key) != null ? true : false;
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl#containsValue(Object)} возвращает булево значение
     * при проверке наличия значения V объекта в {@link CustomLinkedHashMapImpl}.
     *
     * @param value - значение V для проверки.
     * @return возвращает <code>true</code> если значение присутствует,
     * возвращает <code>false</code> если значение отсутствует.
     */
    @Override
    public boolean containsValue(Object value) {
        if (null == value && table[0] != null) {
            return true;
        }

        for (int i = 0; i < table.length; ++i) {

            Node<K, V> tmp = table[i];
            while (tmp != null) {
                if (Objects.equals(tmp.value, value)) {
                    return true;
                }
                tmp = tmp.next;
            }
        }

        return false;
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl#keys()} возвращает массив всех ключей К
     * объекта {@link CustomLinkedHashMapImpl}.
     *
     * @return массив ключей Object[]
     */
    @Override
    public Object[] keys() {
        Object[] objects = new Object[size];
        int j = 0;

        Node<K, V> tmp = head;
        while (tmp != null) {
            objects[j++] = tmp.key;
            tmp = tmp.after;
        }

        return objects;
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl#keys()} возвращает массив всех значений К
     * объекта {@link CustomLinkedHashMapImpl}.
     *
     * @return массив значений Object[]
     */
    @Override
    public Object[] values() {
        Object[] objects = new Object[size];
        int j = 0;

        Node<K, V> tmp = head;
        while (tmp != null) {
            objects[j++] = tmp.value;
            tmp = tmp.after;
        }

        return objects;
    }

    /**
     * Метод {@link CustomLinkedHashMapImpl<K, V>#toString()}
     * возвращает строковое представление дерева {@link CustomLinkedHashMapImpl<K, V>}
     *
     * @return объект типа String в формате '[ element1 element2 ... elementN ]
     * или [ ] если дерево пустое.
     */
    @Override
    public String toString() {

        StringBuilder cb = new StringBuilder();

        cb.append("[ ");

        Node<K, V> tmp = head;
        while (tmp != null) {
            cb.append("{ key=" + tmp.key + ";value=" + tmp.value + "} ");
            tmp = tmp.after;
        }
        cb.append("]");
        return cb.toString();
    }
}