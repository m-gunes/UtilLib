package org.csystem.util.numeric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NumberUtilCopyTest {

    @Test
    void givenTwoLists_whenIntegerAndNumberInstantiate_thenCopy()
    {
        List<Integer> src = new ArrayList<>();
        src.add(10);
        src.add(20);
        src.add(30);
        List<Number> dest = new ArrayList<>();
        NumberUtil.copy(src, dest);
        Assertions.assertEquals(src.size(), dest.size());

        ArrayList<Number> expected = new ArrayList<>();
        expected.add(10);
        expected.add(20);
        expected.add(30);
        Assertions.assertEquals(expected, dest);
    }

    @Test
    void givenTwoLists_whenIntegerAndObjectInstantiate_thenCopy()
    {
        List<Integer> src = new ArrayList<>();
        src.add(10);
        src.add(20);
        src.add(30);
        List<Object> dest = new ArrayList<>();
        NumberUtil.copy(src, dest);
        Assertions.assertEquals(src.size(), dest.size());

        ArrayList<Object> expected = new ArrayList<>();
        expected.add(10);
        expected.add(20);
        expected.add(30);
        Assertions.assertEquals(expected, dest);
    }
}
