package org.csystem.util.reflection;

import org.csystem.util.numeric.NumberUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ReflectionUtilGetFieldDeclarationAsStringTest {

    @Test
    void test()
    {
        try {
            var clsNumberUtil = NumberUtil.class;
            var clsRandom = Random.class;

            var field1 = clsNumberUtil.getDeclaredField("ZERO_TR");
            var field2 = clsRandom.getDeclaredField("multiplier");

            var field1String = ReflectionUtil.getFieldDeclarationAsString(field1);
            var field2String = ReflectionUtil.getFieldDeclarationAsString(field2);

            Assertions.assertEquals("private static final java.lang.String ZERO_TR", field1String);
            Assertions.assertEquals("private static final long multiplier", field2String);

        } catch (NoSuchFieldException e) {
            System.out.printf("No such field exception %s", e);
        }
    }
}
