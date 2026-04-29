package org.csystem.util.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CSDArrayListIteratorTest {

    @Test
    void givenValues_hasNextAndNext_thenCorrectNumberOfElement()
    {
        var list = new CSDArrayList<String>();
        list.add("ankara");
        list.add("istanbul");
        list.add("izmir");

        var iter = list.iterator();
        int count=0;

        while (iter.hasNext()) {
            iter.next();
            ++count;
        }

        Assertions.assertEquals(3, count);
    }
}
