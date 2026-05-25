package org.csystem.util.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilAreAllUniqueTest {

    @Test
    void givenValue_whenString_thenReturnTrue()
    {
        String str = "ali";
        Assertions.assertTrue(StringUtil.areAllUnique(str));
    }

    @Test
    void givenValue_whenString_thenReturnFalse()
    {

        String str = "halil";
        Assertions.assertFalse(StringUtil.areAllUnique(str));
    }
}
