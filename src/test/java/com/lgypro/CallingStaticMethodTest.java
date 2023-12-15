package com.lgypro;

import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Test;

public class CallingStaticMethodTest {
    @Test
    public void testCallStaticMethod() throws OgnlException {
        String userHome = (String) Ognl.getValue("@java.lang.System@getProperty('user.dir')", null);
        System.out.println(userHome);

        Thread currentThread = (Thread) Ognl.getValue("@java.lang.Thread@currentThread()", null);
        System.out.println(currentThread.getName());
    }

    @Test
    public void testGetStaticField() throws OgnlException {
        String minValue = (String) Ognl.getValue("@java.lang.String@valueOf(@java.lang.Integer@MIN_VALUE)", null);
        System.out.println(minValue);
    }
}
