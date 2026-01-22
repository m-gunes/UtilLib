package org.csystem.util.numeric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigInteger;

public class NumberUtilFactorialBigIntegerResource {
    private static final String RESOURCE_FILE = "/numbers.csv";

    @Disabled("It can take too much time in some computers")
    @ParameterizedTest
    @CsvFileSource(resources = RESOURCE_FILE, numLinesToSkip = 1)
    void test(long input, boolean expected)
    {
        Assertions.assertEquals(expected, NumberUtil.isPrime(BigInteger.valueOf(input)));
    }
}
