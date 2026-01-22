package org.csystem.util.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ArrayUtilRandomArrayExceptionTest {

    private static void assertThrowsExecutableCallback()
    {
        ArrayUtil.randomArray(new Random(), 10, 6, 5);
    }

    private static void assertDoesNotThrowsExecutableCallback()
    {
        ArrayUtil.randomArray(new Random(), 10, 4, 5);
    }

    @Test
    void givenValues_whenOriginGreaterThanBound_thenThrowIllegalArgumentException()
    {
        Assertions.assertThrows(IllegalArgumentException.class, ArrayUtilRandomArrayExceptionTest::assertThrowsExecutableCallback);
    }

    @Test
    void givenValues_whenOriginGreaterThanBound_thenDoesNotThrowIllegalArgumentException()
    {
        Assertions.assertDoesNotThrow(ArrayUtilRandomArrayExceptionTest::assertDoesNotThrowsExecutableCallback);
    }
}
