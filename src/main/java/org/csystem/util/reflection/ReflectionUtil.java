package org.csystem.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtil {
    private ReflectionUtil()
    {
        throw new UnsupportedOperationException("Utility class");
    }

    @FunctionalInterface
    public interface IMethodCallback {
       void doWith(Method method) throws Exception;
    }

    @FunctionalInterface
    public interface IMethodFilter {
        boolean matches(Method method) throws Exception;
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

    public static String getFieldDeclarationAsString(Field field)
    {
//        doWithMethod(Random.class, m -> {}, m -> m.getName().equals("nextInt"));
        var sb = new StringBuilder();
        var modifiers = field.getModifiers();

        sb.append(Modifier.toString(modifiers))
                .append(' ')
                .append(field.getType().getTypeName())
                .append(' ')
                .append(field.getName());

        return sb.toString();
    }

    public static void doWithMethod(Class<?> cls, IMethodCallback methodCallback, IMethodFilter methodFilter)
    {
        try {
            for (var method: cls.getMethods())
                if (methodFilter.matches(method))
                    methodCallback.doWith(method);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
