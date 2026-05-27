package org.csystem.util.reflection;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public final class ReflectionUtil {
    private ReflectionUtil()
    {
        throw new UnsupportedOperationException("Can not instantiate utility class.");
    }

    private static final FilenameFilter FILENAME_FILTER = (f, name) -> name.endsWith(".jar");

    private static String getClassName(JarEntry jarEntry)
    {
        String className = jarEntry.getName().replace("/", ".");

        className = className.substring(0, className.lastIndexOf("."));

        return className;
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

    private static String join(Class<?>[] classes)
    {
        var sb = new StringBuilder();

        for (var cls : classes)
            sb.append(cls.getTypeName()).append(", ");

        return sb.substring(0, sb.length() - 2);
    }

    public static String getMethodPrototypeAsString(Method method)
    {
        var sb = new StringBuilder();
        var modifiers = method.getModifiers();
        var clsParameters = method.getParameterTypes();
        var strParams = clsParameters.length != 0 ? join(clsParameters) : "";
        var clsExceptions = method.getExceptionTypes();
        var strExceptions =  clsExceptions.length != 0 ? " throws " + join(clsExceptions) : "";

        sb.append(Modifier.toString(modifiers))
                .append(' ')
                .append(method.getReturnType().getTypeName())
                .append(' ')
                .append(method.getName()).append('(')
                .append(strParams)
                .append(')')
                .append(strExceptions);

        return sb.toString();
    }

    public static String getFieldDeclarationAsString(Field field)
    {
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
            for (var method : cls.getMethods())
                if (methodFilter.matches(method))
                    methodCallback.doWith(method);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doWithMethods(Class<?> cls, IMethodCallback methodCallback)
    {
        try {
            for (var method : cls.getMethods())
                methodCallback.doWith(method);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doWithDeclaredMethods(Class<?> cls, IMethodCallback methodCallback, IMethodFilter methodFilter)
    {
        try {
            for (var method : cls.getDeclaredMethods())
                if (methodFilter.matches(method))
                    methodCallback.doWith(method);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doWithDeclaredMethods(Class<?> cls, IMethodCallback methodCallback)
    {
        try {
            for (var method : cls.getDeclaredMethods())
                methodCallback.doWith(method);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doWithFields(Class<?> cls, IFieldCallback fieldCallback, IFieldFilter fieldFilter)
    {
        try {
            for (var field : cls.getFields())
                if (fieldFilter.matches(field))
                    fieldCallback.doWith(field);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void doWithFields(Class<?> cls, IFieldCallback fieldCallback)
    {
        try {
            for (var field : cls.getFields()) {
                fieldCallback.doWith(field);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //...

    public static Method findDeclaredMethod(Class<?> cls, String name)
    {
        Method method = null;

        try {
            method = cls.getDeclaredMethod(name);
        }
        catch (NoSuchMethodException ignore) {

        }

        return method;
    }

    public static Method findMethod(Class<?> cls, String name)
    {
        Method method;

        try {
            method = cls.getMethod(name);
        }
        catch (NoSuchMethodException ignore) {
            method = findDeclaredMethod(cls, name);
        }

        return method;
    }

    public static Method findDeclaredMethod(Class<?> cls, String name, Class<?>...paramTypes)
    {
        Method method = null;

        try {
            method = cls.getDeclaredMethod(name, paramTypes);
        }
        catch (NoSuchMethodException ignore) {

        }

        return method;
    }

    public static Method findMethod(Class<?> cls, String name, Class<?>...paramTypes)
    {
        Method method;

        try {
            method = cls.getMethod(name, paramTypes);
        }
        catch (NoSuchMethodException ignore) {
            method = findDeclaredMethod(cls, name, paramTypes);
        }

        return method;
    }

    public static Field findDeclaredField(Class<?> cls, String name)
    {
        Field field = null;

        try {
            field = cls.getDeclaredField(name);
        }
        catch (NoSuchFieldException ignore) {

        }

        return field;
    }

    public static Field findField(Class<?> cls, String name)
    {
        Field field;

        try {
            field = cls.getField(name);
        }
        catch (NoSuchFieldException ignore) {
            field = findDeclaredField(cls, name);
        }

        return field;
    }

    //...

    public static boolean isConcreteClass(Class<?> cls)
    {
        return !cls.isInterface()
                && !cls.isEnum()
                && !cls.isAnnotation()
                && !cls.isArray()
                && !cls.isPrimitive()
                && !cls.isRecord()
                && !Modifier.isAbstract(cls.getModifiers());
    }

    public static List<Class<?>> getImplementedClassesByJar(String filePath, String tStr)
            throws IOException, ClassNotFoundException
    {
        return getImplementedClassesByJar(filePath, Class.forName(tStr));
    }

    public static List<Class<?>> getImplementedClassesByJar(String filePath, Class<?> tCls)
            throws IOException, ClassNotFoundException
    {
        var urls = new URL[]{new File(filePath).toURI().toURL()};

        try (var jarFile = new JarFile(filePath);
             var urlClassLoader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader())) {
            var entries = jarFile.entries();
            var list = new ArrayList<Class<?>>();

            while (entries.hasMoreElements()) {
                var jarEntry = entries.nextElement();

                if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class"))
                    continue;

                var cls = urlClassLoader.loadClass(getClassName(jarEntry));

                if (isConcreteClass(cls) && tCls.isAssignableFrom(cls))
                    list.add(cls);
            }
            return list;
        }
    }

    public static List<Class<?>> getImplementedClassesByJars(String dirPath, String tStr)
            throws IOException, ClassNotFoundException
    {
        return getImplementedClassesByJars(dirPath, Class.forName(tStr));
    }

    public static List<Class<?>> getImplementedClassesByJars(String dirPath, Class<?> tCls)
            throws IOException, ClassNotFoundException
    {
        var jarFiles = new File(dirPath).listFiles(FILENAME_FILTER);
        var list = new ArrayList<Class<?>>();

        if (jarFiles == null)
            return list;

        for (var jarFile : jarFiles) {
            var classList = getImplementedClassesByJar(jarFile.getAbsolutePath(), tCls);

            if (!classList.isEmpty())
                list.addAll(classList);
        }

        return list;
    }

    public static List<Class<?>> getAnnotatedClassesByJar(String filePath, Class<? extends Annotation> tCls)
            throws IOException, ClassNotFoundException
    {
        var urls = new URL[]{new File(filePath).toURI().toURL()};

        try (var jarFile = new JarFile(filePath);
             var urlClassLoader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader())) {
            var entries = jarFile.entries();
            var list = new ArrayList<Class<?>>();

            while (entries.hasMoreElements()) {
                var jarEntry = entries.nextElement();

                if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class"))
                    continue;

                var cls = urlClassLoader.loadClass(getClassName(jarEntry));
                var annotations = cls.getDeclaredAnnotationsByType(tCls);

                if (isConcreteClass(cls) && annotations.length == 1)
                    list.add(cls);
            }

            return list;
        }
    }

    public static List<Class<?>> getAnnotatedClassesByJars(String dirPath, Class <? extends Annotation> tCls)
            throws IOException, ClassNotFoundException
    {
        var jarFiles = new File(dirPath).listFiles(FILENAME_FILTER);
        var list = new ArrayList<Class<?>>();

        if (jarFiles == null)
            return list;

        for (var jarFile : jarFiles) {
            var classList = getAnnotatedClassesByJar(jarFile.getAbsolutePath(), tCls);

            if (!classList.isEmpty())
                list.addAll(classList);
        }

        return list;
    }

    public static boolean areAllSameTyped(Parameter [] parameters, Class<?> cls)
    {
        for (var param : parameters)
            if (param.getParameterizedType() != cls)
                return false;

        return true;
    }
}