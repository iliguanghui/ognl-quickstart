package com.lgypro;

import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Test;

import java.util.List;

public class ProjectingAcrossCollectionsTest {
    @Test
    public void testProjection() throws OgnlException {
        List<Department> departmentList = List.of(
                new Department(10, "Accounting", "New York"),
                new Department(20, "Research", "Dallas"),
                new Department(30, "Sales", "Chicago"),
                new Department(40, "Operations", "Boston")
        );
        @SuppressWarnings("unchecked")
        List<String> nameList = (List<String>) Ognl.getValue("""
                #this.{name}
                           """, departmentList);
        System.out.println(nameList);
    }
}