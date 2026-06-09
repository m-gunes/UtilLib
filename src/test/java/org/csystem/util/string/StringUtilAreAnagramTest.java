package org.csystem.util.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilAreAnagramTest {

    @Test
    void givenValues_whenStrings_thenAnagram()
    {
        String s1 = "para";
        String s2 = "arap";
        Assertions.assertTrue(StringUtil.areAnagram(s1, s2));
    }

    @Test
    void givenValues_whenStrings_thenNotAnagram()
    {
        String s1 = "para";
        String s2 = "mara";
        Assertions.assertFalse(StringUtil.areAnagram(s1, s2));

    }

    @Test
    void givenValues_whenStringsDifferentSizes_thenNotAnagram()
    {
        String s1 = "brat";
        String s2 = "bartu";

        Assertions.assertFalse(StringUtil.areAnagram(s1, s2));
    }
}

