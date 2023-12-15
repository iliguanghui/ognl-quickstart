package com.lgypro;

import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class CollectionConstructionTest {
    @Test
    public void testList() throws ParseException, OgnlException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("1993-06-13");
        Employee employee = new Employee(7369, "SMITH", "CLERK", 7902,
                date, 800.0, 0.0, 20);
        employee.setDepartment(new Department(20, "Research", "Dallas"));
        boolean flag = (boolean) Ognl.getValue("""
                getDepartment().location in {null, "New York", "Boston"}
                """, employee);
        System.out.println(flag);
    }

    @Test
    public void testArray() throws ParseException, OgnlException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("1993-06-13");
        Employee employee = new Employee(7369, "SMITH", "CLERK", 7902,
                date, 800.0, 0.0, 20);
        employee.setDepartment(new Department(20, "Research", "Dallas"));
        Object[] department = (Object[]) Ognl.getValue("""
                getDepartment().(#array = new java.lang.Object[3], #array[0] = #this.id, #array[1] = #this.name, #array[2] = #this.location, #array)
                """, employee);
        System.out.println(Arrays.toString(department));
    }

    @Test
    public void testMap() throws ParseException, OgnlException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("1993-06-13");
        Employee employee = new Employee(7369, "SMITH", "CLERK", 7902,
                date, 800.0, 0.0, 20);
        employee.setDepartment(new Department(20, "Research", "Dallas"));
        Object department = Ognl.getValue("""
                (#@java.util.LinkedHashMap@{"id": department.id, "name": department.name, "location": department.location}).toString()
                """, employee);
        System.out.println(department);
    }
}
