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

    @FunctionalInterface
    public interface IFieldCallback {
        void doWith(Field field) throws Exception;
    }

    @FunctionalInterface
    public interface IFieldFilter {
        boolean matches(Field field) throws Exception;
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
        var clsExceptions = method.getExceptionTypes();
        var strExceptions = clsExceptions.length != 0 ? " throws " + joinParams(clsExceptions) : "";

        sb.append(Modifier.toString(modifiers))
                .append(' ')
                .append(method.getReturnType().getTypeName())
                .append(' ')
                .append(method.getName())
                .append('(')
                .append(params)
                .append(')')
                .append(strExceptions);

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

    public static void doWithMethods(Class<?> cls, IMethodCallback methodCallback, IMethodFilter methodFilter)
    {
        try {
            for (var method: cls.getMethods())
                if (methodFilter.matches(method))
                    methodCallback.doWith(method);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doWithMethods(Class<?> cls, IMethodCallback methodCallback)
    {
        try {
            for (var method: cls.getMethods())
                    methodCallback.doWith(method);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doWithDeclaredMethods(Class<?> cls, IMethodCallback methodCallback, IMethodFilter methodFilter)
    {
        try {
            for (var method: cls.getDeclaredMethods())
                if (methodFilter.matches(method))
                    methodCallback.doWith(method);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doWithDeclaredMethods(Class<?> cls, IMethodCallback methodCallback)
    {
        try {
            for (var method: cls.getDeclaredMethods())
                methodCallback.doWith(method);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doWithFields(Class<?> cls, IFieldCallback fieldCallback, IFieldFilter fieldFilter)
    {
        try {
            for (var field : cls.getFields())
                if (fieldFilter.matches(field))
                    fieldCallback.doWith(field);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doWithFields(Class<?> cls, IFieldCallback fieldCallback)
    {
        try {
            for (var field : cls.getFields())
                fieldCallback.doWith(field);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static Method findDeclaredMethod(Class<?> cls, String name, Class<?>...paramTypes)
    {
        Method method = null;
        try {
            method = cls.getDeclaredMethod(name, paramTypes);
        } catch (NoSuchMethodException ignore) {
        }
        return method;
    }

    private static Method findDeclaredMethod(Class<?> cls, String name)
    {
        Method method = null;
        try {
            method = cls.getDeclaredMethod(name);
        } catch (NoSuchMethodException ignore) {
        }
        return method;
    }

    public static Method findMethod(Class<?> cls, String name)
    {

        Method method;
        try {
            method = cls.getMethod(name);
        } catch (NoSuchMethodException ignore) {
            method = findDeclaredMethod(cls, name);
        }
        return method;
    }

    public static Method findMethod(Class<?> cls, String name, Class<?>...paramTypes)
    {
        Method method;
        try {
            method = cls.getMethod(name, paramTypes);
        } catch (NoSuchMethodException ignore) {
            method = findDeclaredMethod(cls, name, paramTypes);
        }
        return method;
    }

    public static Field findDeclaredField(Class<?> cls, String name)
    {
        Field field = null;
        try {
            field = cls.getDeclaredField(name);
        } catch (NoSuchFieldException ignore) {
        }
        return field;
    }

    public static Field findField(Class<?> cls, String name)
    {
        Field field;
        try {
            field = cls.getField(name);
        } catch (NoSuchFieldException ignore) {
            field = findDeclaredField(cls, name);
        }
        return field;
    }

}
