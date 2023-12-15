package com.lgypro;

import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VariableReferencesTest {
    @Test
    public void testVariableReference() throws ParseException, OgnlException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("1993-06-13");
        Employee employee = new Employee(7369, "SMITH", "CLERK", 7902,
                date, 800.0, 0.0, 20);
        employee.setDepartment(new Department(20, "Research", "Dallas"));

        Object value = Ognl.getValue("getDepartment().(#this.getId() > 20 ? 2 * #this.getId() : 3 * #this.getId())", employee);
        System.out.println(value);
    }
}
