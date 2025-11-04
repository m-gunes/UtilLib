package org.csystem.util.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ArrayUtilBigDecimalSumTest {

    @Test
    void givenValues_whenArray_thenReturnsSum()
    {
        BigDecimal[] a = {new BigDecimal("0.1"), new BigDecimal("0.2"), new BigDecimal("0.3")};
        var expected = new BigDecimal("0.6");
        Assertions.assertEquals(expected, ArrayUtil.sum(a));
    }
}
