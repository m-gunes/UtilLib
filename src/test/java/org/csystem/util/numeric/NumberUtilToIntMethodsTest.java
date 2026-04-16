package org.csystem.util.numeric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberUtilToIntMethodsTest {

    @ParameterizedTest
    @ValueSource(strings={"10", "-100"})
    void givenValues_whenTexts_thenValidDecimal(String str)
    {
        Assertions.assertTrue(NumberUtil.toInt(str).isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"12ali", "4000000000"})
    void givenValues_whenTexts_thenInvalidDecimal(String str)
    {
        Assertions.assertTrue(NumberUtil.toInt(str).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings={"A", "FFFF"})
    void givenValues_whenTexts_thenValidHexadecimal(String str)
    {
        Assertions.assertTrue(NumberUtil.toInt(str, 16).isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"12ali", "F00ABBBBAA"})
    void givenValues_whenTexts_thenInvalidHexadecimal(String str)
    {
        Assertions.assertTrue(NumberUtil.toInt(str, 16).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings={"A", "FFFF"})
    void givenValues_whenTexts_thenValidHex(String str)
    {
        Assertions.assertTrue(NumberUtil.toIntHex(str).isPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"12ali", "F00ABBBBAA"})
    void givenValues_whenTexts_thenInvalidHex(String str)
    {
        Assertions.assertTrue(NumberUtil.toIntHex(str).isEmpty());
    }
}
