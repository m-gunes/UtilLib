package org.csystem.util.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ReflectionUtilGetMethodAsStringTest {

    // Todo: bu testi ValueSource annotation ile yap
    @Test
    void test() throws Exception
    {
        var clsRandom = Random.class;
        var clsMath = Math.class;
        var clsRunnable = Runnable.class;

        var method1 = clsRandom.getMethod("nextInt", int.class, int.class);
        var method2 = clsMath.getMethod("sqrt", double.class);
        var method3 = clsRunnable.getMethod("run");
        var method4 = clsRandom.getMethod("nextBytes", byte[].class);
        var method5 = clsRandom.getMethod("ints", long.class);

        var methodStr1Str = ReflectionUtil.getMethodPrototypesAsString(method1);
        var methodStr2Str = ReflectionUtil.getMethodPrototypesAsString(method2);
        var methodStr3Str = ReflectionUtil.getMethodPrototypesAsString(method3);
        var methodStr4Str = ReflectionUtil.getMethodPrototypesAsString(method4);
        var methodStr5Str = ReflectionUtil.getMethodPrototypesAsString(method5);

        Assertions.assertEquals("public int nextInt(int, int)", methodStr1Str);
        Assertions.assertEquals("public static double sqrt(double)", methodStr2Str);
        Assertions.assertEquals("public abstract void run()", methodStr3Str);
        Assertions.assertEquals("public void nextBytes(byte[])", methodStr4Str);
        Assertions.assertEquals("public java.util.stream.IntStream ints(long)", methodStr5Str);
    }
}
