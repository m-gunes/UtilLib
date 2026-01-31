package org.csystem.util.reflection;

import org.csystem.sample.B;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtilDoWithMethods {
    private int m_total;

    private void methodCallback(B b, Method method) throws InvocationTargetException, IllegalAccessException
    {
        m_total += (int)method.invoke(b, 10, 20);
    }

    private boolean methodFilterCallback(Method method)
    {
        var typeCls = method.getParameterTypes();
        return typeCls.length == 2 && typeCls[0] == int.class && typeCls[1] == int.class;
    }

    @Test
    void test()
    {
        var cls = B.class;
        var b = new B();
        ReflectionUtil.doWithMethods(cls, m -> methodCallback(b, m), this::methodFilterCallback);

        Assertions.assertEquals(230, m_total);
    }
}
