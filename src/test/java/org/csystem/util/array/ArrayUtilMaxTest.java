package org.csystem.util.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayUtilMaxTest {

    @Test
    void givenValues_whenArray_thenReturnsMax()
    {
        int [] a = {3, -4, 9, 7, 2, 8, 3};
        int expected = 9;
        Assertions.assertEquals(expected, ArrayUtil.max(a));
    }

    @Test
    void givenValues_whenVarargs_thenReturnsMax()
    {
        int expected = 9;
        Assertions.assertEquals(expected, ArrayUtil.max(3, -4, 9, 7, 2, 8, 3));
    }
}
