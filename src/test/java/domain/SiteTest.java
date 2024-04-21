/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author GTSA - Infinity
 */
public class SiteTest {
    
    Site instance;
    
    public SiteTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        instance = new Site(1, "Högskolan i Gävle", 10000);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of getId method, of class Site.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        assertEquals(1, instance.getId());
    }
    
    /**
     * Test of getArea method, of class Site.
     */
    @Test
    public void testGetIdOnNullInstance() {
        System.out.println("getId");
        instance = null;
        assertThrows(NullPointerException.class, () -> instance.getId());
    }

    /**
     * Test of getName method, of class Site.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals("Högskolan i Gävle", instance.getName());
    }
    
    /**
     * Test of getArea method, of class Site.
     */
    @Test
    public void testGetNameOnNullInstance() {
        System.out.println("getName");
        instance = null;
        assertThrows(NullPointerException.class, () -> instance.getName());
    }

    /**
     * Test of setName method, of class Site.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        instance.setName("Högskolan i Borås");
        assertEquals("Högskolan i Borås", instance.getName());
    }
    
    /**
     * Test of setName method, of class Site.
     */
    @Test
    public void testSetNameToNull() {
        System.out.println("setName");
        instance.setName("Högskolan i Borås");
        assertEquals("Högskolan i Borås", instance.getName());
    }
    
    /**
     * Test of setName method, of class Site.
     */
    @Test
    public void testSetNameToEmptyString() {
        System.out.println("setName");
        assertThrows(IllegalArgumentException.class, () -> instance.setName(""));
    }

    /**
     * Test of getArea method, of class Site.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        assertEquals(10000, instance.getArea());
    }
    
    /**
     * Test of getArea method, of class Site.
     */
    @Test
    public void testGetAreaOnNullInstance() {
        System.out.println("getArea");
        instance = null;
        assertThrows(NullPointerException.class, () -> instance.getArea());
    }

    /**
     * Test of setArea method, of class Site.
     */
    @Test
    public void testSetArea() {
        System.out.println("setArea");
        instance.setArea(20000);
        assertEquals(20000, instance.getArea());
    }
    
    /**
     * Test of setArea method, of class Site.
     */
    @Test
    public void testSetAreaToZero() {
        System.out.println("setArea");
        assertThrows(IllegalArgumentException.class, () -> instance.setArea(0));
    }
    
    /**
     * Test of setArea method, of class Site.
     */
    @Test
    public void testSetAreaToNegativeValue() {
        System.out.println("setArea");
        assertThrows(IllegalArgumentException.class, () -> instance.setArea(-10));
    }

    /**
     * Test of toString method, of class Site.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals("Högskolan i Gävle", instance.toString());
    }
    
}
