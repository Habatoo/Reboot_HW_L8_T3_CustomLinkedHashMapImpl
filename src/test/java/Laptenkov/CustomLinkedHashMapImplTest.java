package Laptenkov;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования public методов класса {@link CustomLinkedHashMapImpl<Object>}.
 *
 * @author habatoo
 */
class CustomLinkedHashMapImplTest {

    CustomLinkedHashMap<Integer, String> customEmptyLinkedHashMap;
    CustomLinkedHashMap<Integer, String> customNotEmptyLinkedHashMap;

    /**
     * Инициализация экземпляров тестируемого класса {@link CustomLinkedHashMapImpl <Object>}.
     */
    @BeforeEach
    void setUp() {
        customEmptyLinkedHashMap = new CustomLinkedHashMapImpl<Integer, String>();

        customNotEmptyLinkedHashMap = new CustomLinkedHashMapImpl<Integer, String>();
        customNotEmptyLinkedHashMap.put(1, "first");
        customNotEmptyLinkedHashMap.put(2, "second");
        customNotEmptyLinkedHashMap.put(3, "third");
        customNotEmptyLinkedHashMap.put(4, "fourth");
        customNotEmptyLinkedHashMap.put(99, "last");
    }

    /**
     * Очистка экземпляров тестируемого класса {@link CustomLinkedHashMapImpl<Object>}..
     */
    @AfterEach
    void tearDown() {
        customEmptyLinkedHashMap = null;
        customNotEmptyLinkedHashMap = null;
    }

    /**
     * Проверяет создание пустого экземпляра {@link CustomLinkedHashMapImpl}.
     * Сценарий, при котором конструктор отрабатывает пустую коллекцию,
     * проверяет размер коллекции
     * равный 0 и отображение коллекции.
     */
    @Test
    public void customHashSetImpl_Test() {
        customEmptyLinkedHashMap = new CustomLinkedHashMapImpl<Integer, String>();
        Assertions.assertEquals(0, customEmptyLinkedHashMap.size());
        Assertions.assertEquals(
                "[ ]", customEmptyLinkedHashMap.toString());
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#size_Test()}
     * проверяет метод {@link CustomLinkedHashMapImpl#size()}.
     * Проверяет размер не пустого экземпляра {@link CustomLinkedHashMapImpl}.
     * Сценарий, при котором пустой экземпляр возвращает величину
     * объекта равную 0, не пустой экземпляр возвращает 5.
     */
    @Test
    void size_Test() {
        Assertions.assertEquals(0, customEmptyLinkedHashMap.size());
        Assertions.assertEquals(5, customNotEmptyLinkedHashMap.size());
    }

    /**
     * Метод  {@link CustomLinkedHashMapImplTest#isEmpty_Test()}
     * проверяет метод {@link CustomLinkedHashMapImpl#isEmpty()}.
     * Проверяет на пустоту экземпляр объекта {@link CustomLinkedHashMapImpl}.
     * Сценарий, при котором пустой экземпляр возвращает <code>true</code>,
     * не пустой экземпляр возвращает <code>false</code>.
     */
    @Test
    void isEmpty_Test() {
        Assertions.assertEquals(true, customEmptyLinkedHashMap.isEmpty());
        Assertions.assertEquals(false, customNotEmptyLinkedHashMap.isEmpty());
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#getSuccess_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#get(Object)}.
     * Сценарий, при котором проверяется наличие существующего ключа и
     * возвращает значение V соответсвующее этому ключу.
     */
    @Test
    void getSuccess_Test() {
        Assertions.assertEquals("second", customNotEmptyLinkedHashMap.get(2));
        Assertions.assertEquals("last", customNotEmptyLinkedHashMap.get(99));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#getFail_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#get(Object)}.
     * Сценарий, при котором проверяется наличие не существующего ключа и
     * возвращает null.
     */
    @Test
    void getFail_Test() {
        Assertions.assertEquals(null, customEmptyLinkedHashMap.get(999));
        Assertions.assertEquals(null, customNotEmptyLinkedHashMap.get(999));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#putSuccess_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#put(Object, Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * сушествующего объекта и возвращает его старое значение.
     */
    @Test
    void putSuccess_Test() {
        Assertions.assertEquals(5, customNotEmptyLinkedHashMap.size());
        Assertions.assertEquals("first", customNotEmptyLinkedHashMap.put(1,"notFirst"));
        Assertions.assertEquals(5, customNotEmptyLinkedHashMap.size());
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#putNull_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#put(Object, Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т и возвращает <code>null</code>.
     */
    @Test
    void putNull_Test() {
        Assertions.assertEquals(0, customEmptyLinkedHashMap.size());
        Assertions.assertEquals(null, customEmptyLinkedHashMap.put(15,"first"));
        Assertions.assertEquals(null, customEmptyLinkedHashMap.put(99, "null"));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#putNull_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#remove(Object)}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого существующего объекта по ключу К и возвращает старое значение V.
     */
    @Test
    void removeSuccess_Test() {
        Assertions.assertEquals("first", customNotEmptyLinkedHashMap.remove(1));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#putNull_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#remove(Object)}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого не существующего объекта по ключу К и возвращает null.
     */
    @Test
    void removeFail_Test() {
        Assertions.assertEquals(null, customNotEmptyLinkedHashMap.remove(999));
        Assertions.assertEquals(null, customNotEmptyLinkedHashMap.remove(999));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#containsKeySuccess_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#containsKey(Object)}.
     * Сценарий, при котором проверяется наличие существующего ключа и
     * возвращает <code>true</code>.
     */
    @Test
    void containsKeySuccess_Test() {
        Assertions.assertEquals(true, customNotEmptyLinkedHashMap.containsKey(1));
        Assertions.assertEquals(true, customNotEmptyLinkedHashMap.containsKey(2));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#containsKeyFail_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#containsKey(Object)}.
     * Сценарий, при котором проверяется наличие не существующего ключа и
     * возвращает <code></code>.
     */
    @Test
    void containsKeyFail_Test() {
        Assertions.assertEquals(false, customEmptyLinkedHashMap.containsKey(999));
        Assertions.assertEquals(false, customNotEmptyLinkedHashMap.containsKey(999));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#containsValueSuccess_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#containsValue(Object)}.
     * Сценарий, при котором проверяется наличие существующего значения и
     * возвращает <code>true</code>.
     */
    @Test
    void containsValueSuccess_Test() {
        Assertions.assertEquals(true, customNotEmptyLinkedHashMap.containsValue("first"));
        Assertions.assertEquals(true, customNotEmptyLinkedHashMap.containsValue("last"));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#containsValueFail_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#containsValue(Object)}.
     * Сценарий, при котором проверяется наличие не существующего значения и
     * возвращает <code></code>.
     */
    @Test
    void containsValueFail_Test() {
        Assertions.assertEquals(false, customEmptyLinkedHashMap.containsValue("none"));
        Assertions.assertEquals(false, customNotEmptyLinkedHashMap.containsValue("none"));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#keys_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#keys()}.
     * Сценарий, при котором проверяется отображение массива всех ключей.
     */
    @Test
    void keys_Test() {
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 99), Arrays.asList(customNotEmptyLinkedHashMap.keys()));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#containsValueFail_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashMapImpl#containsValue(Object)}.
     * Сценарий, при котором проверяется отображение массива всех значений.
     */
    @Test
    void values_Test() {
        Assert.assertEquals(Arrays.asList(
                "first", "second", "third", "fourth", "last"), Arrays.asList(customNotEmptyLinkedHashMap.values()));
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#testToStringEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomLinkedHashMapImpl}
     * методом {@link CustomLinkedHashMapImpl#toString()}.
     * Сценарий, при котором пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringEmpty_Test() {
        Assertions.assertEquals("[ ]", customEmptyLinkedHashMap.toString());
    }

    /**
     * Метод {@link CustomLinkedHashMapImplTest#testToStringNotEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomLinkedHashMapImpl}
     * методом {@link CustomLinkedHashMapImpl#toString()}.
     * Сценарий, при котором не пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringNotEmpty_Test() {
        Assertions.assertEquals(
                "[ { key=1;value=first} { key=2;value=second} { key=3;value=third} { key=4;value=fourth} { key=99;value=last} ]",
                customNotEmptyLinkedHashMap.toString());
    }

}