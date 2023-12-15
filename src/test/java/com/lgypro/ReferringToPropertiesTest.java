package com.lgypro;

import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * OGNL treats different kinds of objects differently in its handling of property references.
 * Maps treat all property references as element lookups or storage, with the property name as the key.
 * Lists and arrays treat numeric properties similarly,
 * with the property name as the index, but string properties the same way ordinary objects do.
 * Ordinary objects (that is, all other kinds) only can handle string properties and do so
 * by using "get" and "set" methods (or "is" and "set"), if the object has them,
 * or a field with the given name otherwise.
 */
public class ReferringToPropertiesTest {

    @Test
    public void testGetPropertyFromMap() throws OgnlException {
        Map<String, String> map = Map.of("country", "China", "city", "xinyang");
        String country = (String) Ognl.getValue("country", map);
        System.out.println(country);
        String city = (String) Ognl.getValue("city", map);
        System.out.println(city);
    }

    @Test
    public void testGetPropertyFromList() throws OgnlException {
        List<String> cities = List.of("Beijing", "Shanghai", "Chongqing", "Tianjin");
        Object value = Ognl.getValue("[0]", cities);
        System.out.println(value);
        value = Ognl.getValue("[1]", cities);
        System.out.println(value);
    }

    @Test
    public void testGetPropertyFromArray() throws OgnlException {
        String[] cities = new String[]{
                "Intel",
                "AMD",
                "ARM"
        };
        Object length = Ognl.getValue("length", cities);
        System.out.println(length);
        Object value = Ognl.getValue("[2]", cities);
        System.out.println(value);
    }

    @Test
    public void testGetPropertyFromOrdinaryObject() throws OgnlException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("1993-06-13");
        Employee employee = new Employee(7369, "SMITH", "CLERK", 7902,
                date, 800.0, 0.0, 20);
        employee.setDepartment(new Department(20, "Research", "Dallas"));
        String location = (String) Ognl.getValue("department.location", employee);
        System.out.println(location);
        Object value = Ognl.getValue("""
                ["depart" + "ment"]["loca" + "tion"]
                """, employee);
        System.out.println(value);
    }
}
