package org.csystem.util.reflection;

import org.csystem.util.numeric.NumberUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.util.Random;

public class ReflectionUtilGetFieldDeclarationAsStringTest {
    private static int counter;

    @ParameterizedTest
    @ValueSource(
            strings = {
                    "private static final java.lang.String ZERO_TR",
                    "private static final long multiplier"
            }
    )
    void test(String str)
    {
        try {
            var clsNumberUtil = NumberUtil.class;
            var clsRandom = Random.class;
            var fields = new Field[] {
                clsNumberUtil.getDeclaredField("ZERO_TR"),
                clsRandom.getDeclaredField("multiplier")
            };
            Assertions.assertEquals(str, ReflectionUtil.getFieldDeclarationAsString(fields[counter++]));

        } catch (NoSuchFieldException e) {
            System.out.printf("No such field exception %s", e);
        }
    }
}
