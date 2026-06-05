package org.csystem.util.collection;

import org.csystem.util.string.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.random.RandomGenerator;

public final class CollectionUtil {
    private CollectionUtil()
    {
    }

    /**
     * Sınıf Çalışması: Parametresi ile aldığı generic türden bir Collection'ın tüm elemanlarının farklı olup olmadığını test eden areAllUnique isimli metodu UtilLib içerisindeki ArrayUtil sınıfı içerisinde yazınız.
     */

    public static <E> boolean areAllDistinct(Collection<? extends E> collection)
    {
        return new HashSet<E>(collection).size() == collection.size();
    }

    public static ArrayList<String> randomStringListTR(RandomGenerator randomGenerator, int count, int min, int bound)
    {
        // ArrayList<String> list = new ArrayList<>();
        var list = new ArrayList<String>();

        for (int i = 0; i < count; ++i)
            list.add(StringUtil.randomTextTR(randomGenerator, randomGenerator.nextInt(min, bound)));

        return list;
    }

    public static ArrayList<String> randomStringListTR(RandomGenerator randomGenerator, int min, int bound)
    {
        return randomStringListTR(randomGenerator, randomGenerator.nextInt(min, bound), min, bound);
    }

    //...
}
