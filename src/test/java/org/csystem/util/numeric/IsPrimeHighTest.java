package org.csystem.util.numeric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsPrimeHighTest {

    @Disabled("It can take too much time in some computers")
    @ParameterizedTest()
    @ValueSource(longs = { 6584583408148485263L, 6245098347044246839L, 5697859706174583067L, 4498306523077899307L })
    void givenValues_whenHighPrime_thenTrue(long val)
    {
        Assertions.assertTrue(NumberUtil.isPrime(val));
    }

}
