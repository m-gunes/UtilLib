package org.csystem.util.array;

import static  org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ArrayUtilMergeTest {

    @Test
    void givenValues_whenTwoArrays_thenReturnsMergeArray()
    {
        int[] a = {1,2,3,4};
        int[] b = {5,6,7,8,9};
        int[] expect = {1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expect, ArrayUtil.merge(a, b));
    }

    @Test
    void givenValues_whenArrayAndVarargs_thenReturnMergeArray()
    {
        int[] a = {1,2,3,4};
        int[] expect = {1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expect, ArrayUtil.merge(a, 5,6,7,8,9));
    }
}
