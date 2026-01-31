package org.csystem.sample;

import java.io.IOException;

public class B extends A {
    public int foo(int a, int b) throws IOException, NoSuchFieldException
    {
        return a + b;
    }

    public int bar(int a, int b) throws IOException, NoSuchFieldException
    {
        return a * b;
    }

    private int foo(int a, double b) throws IOException, NoSuchFieldException
    {
        return 0;
    }
}

