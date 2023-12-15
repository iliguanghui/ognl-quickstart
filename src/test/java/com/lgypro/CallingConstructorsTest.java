package com.lgypro;

import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Test;

public class CallingConstructorsTest {
    @Test
    public void testCallConstructor() throws OgnlException {
        Department department = (Department) Ognl.getValue("new com.lgypro.Department(50, 'Security', 'Jarkata')", null);
        System.out.println(department);
    }
}
