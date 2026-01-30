package org.csystem.util.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;
import java.util.Random;

public class ReflectionUtilGetMethodAsStringTest {
    private static int counter;

    @ParameterizedTest
    @ValueSource(
            strings = {
                    "public int nextInt(int, int)",
                    "public static double sqrt(double)",
                    "public abstract void run()",
                    "public void nextBytes(byte[])",
                    "public java.util.stream.IntStream ints(long)",
                    "public java.lang.reflect.Field getField(java.lang.String) throws java.lang.NoSuchFieldException, java.lang.SecurityException"
            }
    )
    void test(String str) throws Exception
    {
        var clsRandom = Random.class;
        var clsMath = Math.class;
        var clsRunnable = Runnable.class;
        var clsClass = Class.class;

        var methods = new Method[]{
            clsRandom.getMethod("nextInt", int.class, int.class),
            clsMath.getMethod("sqrt", double.class),
            clsRunnable.getMethod("run"),
            clsRandom.getMethod("nextBytes", byte[].class),
            clsRandom.getMethod("ints", long.class),
            clsClass.getMethod("getField", String.class),
        };

        Assertions.assertEquals(str, ReflectionUtil.getMethodPrototypesAsString(methods[counter++]));
    }
}
