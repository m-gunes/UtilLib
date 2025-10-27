package org.csystem.util.numeric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class IsPrimeEdgeTest {
    @ParameterizedTest
    @ValueSource(longs = {-1,1,0})
    void testNotPrimes(long val)
    {
        assertFalse(NumberUtil.isPrime(val));
    }

//    @Test
//    void givenValue_whenNotPrime_thenFalse()
//    {
//        long input = -1;
//        assertFalse(NumberUtil.isPrime(input));
//    }

//    @Test
//    void givenValue_whenOne_thenFalse()
//    {
//        long input = 1;
//        assertFalse(NumberUtil.isPrime(input));
//    }

    @Test
    void givenValue_whenUniqueEvenPrime_thenTrue()
    {
        long input = 2;
        assertTrue(NumberUtil.isPrime(input));
    }

    @Test
    void givenValue_whenEven_thenFalse()
    {
        long input = 24;
        assertFalse(NumberUtil.isPrime(input));
    }

}
