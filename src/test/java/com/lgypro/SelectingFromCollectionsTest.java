package com.lgypro;

import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SelectingFromCollectionsTest {

    private List<Department> departmentList;

    @Before
    public void setup() {
        departmentList = List.of(
                new Department(10, "Accounting", "New York"),
                new Department(20, "Research", "Dallas"),
                new Department(30, "Sales", "Chicago"),
                new Department(40, "Operations", "Boston")
        );
    }

    @Test
    public void testSelection() throws OgnlException {
        @SuppressWarnings("unchecked")
        List<Department> result = (List<Department>) Ognl.getValue("#this.{? #this.id % 20 == 0}", departmentList);
        result.forEach(System.out::println);
    }


    @Test
    public void testFirstMatch() throws OgnlException {
        @SuppressWarnings("unchecked")
        List<Department> result = (List<Department>) Ognl.getValue("#this.{^ #this.name.length() > 5}", departmentList);
        System.out.println(result.getClass().getName());
        System.out.println(result);
    }


    @Test
    public void testLastMatch() throws OgnlException {
        @SuppressWarnings("unchecked")
        List<Department> result = (List<Department>) Ognl.getValue("#this.{$ #this.name.length() > 5}", departmentList);
        System.out.println(result.getClass().getName());
        System.out.println(result);
    }
}
