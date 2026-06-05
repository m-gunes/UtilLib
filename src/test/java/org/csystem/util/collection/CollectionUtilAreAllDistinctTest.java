package org.csystem.util.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Sınıf Çalışması: Parametresi ile aldığı generic türden bir Collection'ın tüm elemanlarının farklı olup olmadığını test eden areAllUnique isimli metodu UtilLib içerisindeki ArrayUtil sınıfı içerisinde yazınız.
 */
public class CollectionUtilAreAllDistinctTest {

    @Test
    void givenValue_whenCollection_thenReturnTrue()
    {
        var list = new ArrayList<String>();
        list.add("ankara");
        list.add("istanbul");
        list.add("izmir");
        Assertions.assertTrue(CollectionUtil.areAllDistinct(list));
    }

    @Test
    void givenValue_whenCollection_thenReturnFalse()
    {
        var list = new ArrayList<String>();
        list.add("ankara");
        list.add("istanbul");
        list.add("izmir");
        list.add("istanbul");
        Assertions.assertFalse(CollectionUtil.areAllDistinct(list));
    }
}
