package org.csystem.util.collection;

import org.csystem.util.array.ArrayUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Sınıf Çalışması: Parametresi ile aldığı generic türden bir Collection'ın tüm elemanlarının farklı olup olmadığını test eden areAllUnique isimli metodu UtilLib içerisindeki ArrayUtil sınıfı içerisinde yazınız.
 */
public class CollectionUtilAreAllUniqueTest {

    @Test
    void givenValues_whenCollection_thenReturnTrue()
    {
        var list = new ArrayList<Integer>();
        list.add(5);
        list.add(10);
        list.add(15);
        Assertions.assertTrue(CollectionUtil.areAllUnique(list));
    }

    @Test
    void givenValues_whenCollection_thenReturnFalse()
    {
        var list = new ArrayList<Integer>();
        list.add(5);
        list.add(5);
        list.add(15);
        Assertions.assertFalse(CollectionUtil.areAllUnique(list));
    }
}
