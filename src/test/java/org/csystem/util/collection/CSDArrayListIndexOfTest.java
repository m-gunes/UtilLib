package org.csystem.util.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CSDArrayListIndexOfTest {
    @Test
    void givenValues_whenListAndReference_thenFound()
    {
        var list = new CSDArrayList<String>();
        var s = "2";
        var index = 1;
        list.add("1");
        list.add("2");
        list.add("3");
        Assertions.assertEquals(index, list.indexOf(s));
    }

    @Test
    void givenValues_whenListAndReference_thenNotFound()
    {
        var list = new CSDArrayList<String>();
        var s = "4";
        var index = -1;
        list.add("1");
        list.add("2");
        list.add("3");
        Assertions.assertEquals(index, list.indexOf(s));
    }
}
