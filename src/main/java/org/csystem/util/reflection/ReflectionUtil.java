package org.csystem.util.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtil {
    private ReflectionUtil()
    {
        throw new UnsupportedOperationException("Utility class");
    }

    private static String joinParams(Class<?>[] classes)
    {
        var sb = new StringBuilder();
        for (var cls : classes)
            sb.append(cls.getTypeName()).append(", ");

        return sb.substring(0, sb.length() - 2);
    }

    public static String getMethodPrototypesAsString(Method method)
    {
        var sb = new StringBuilder();
        var modifiers = method.getModifiers();
        var clsParameters = method.getParameterTypes();
        var params = clsParameters.length != 0 ? joinParams(clsParameters) : "";

        sb.append(Modifier.toString(modifiers))
                .append(' ')
                .append(method.getReturnType().getTypeName())
                .append(' ')
                .append(method.getName())
                .append('(')
                .append(params)
                .append(')');

        return sb.toString();
    }
}
