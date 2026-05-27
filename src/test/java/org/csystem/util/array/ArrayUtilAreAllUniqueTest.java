package org.csystem.util.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ArrayUtilAreAllUniqueTest {

    @Test
    void givenValues_whenArray_thenReturnTrue()
    {
        Integer [] list = {1,2,3,4,5};
//        List<Integer> list = List.of(1,2,3);
        Assertions.assertTrue(ArrayUtil.areAllUnique(list));
    }

    @Test
    void givenValues_whenArray_thenReturnFalse()
    {
        Integer [] list = {3,2,3,2,5};
        Assertions.assertFalse(ArrayUtil.areAllUnique(list));
    }
}
