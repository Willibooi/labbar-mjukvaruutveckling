/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package domain;

import java.time.Year;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 22wili03
 */
public class PersonTest {
    
    Person instance;
    
    public PersonTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        instance = new Person(1, "William Lindahl", 2003);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of getId method, of class Person.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        assertEquals(1, instance.getId());
    }

    /**
     * Test of getName method, of class Person.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "William Lindahl";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Person.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        assertEquals("William Lindahl", instance.getName());
        instance.setName("Omar Rajoub");
        assertEquals("Omar Rajoub", instance.getName());
    }
    
    /**
     * Test of setName method, of class Person.
     */
    @Test
    public void testSetNameWithEmptyString() {
        System.out.println("setName");
        String name = "";
        assertThrows(IllegalArgumentException.class, () -> instance.setName(name));
    }
    
    /**
     * Test of setName method, of class Person.
     */
    @Test
    public void testSetNameToStringWithLengthOver50() {
        System.out.println("setName");
        String name = "thisNameIsAVeryLongNameThatIsTooLongSoItHasToBeShortenedDownTo50Characters";
        instance.setName(name);
        assertEquals(50, instance.getName().length());
        assertEquals("thisNameIsAVeryLongNameThatIsTooLongSoItHasToBeSho", instance.getName());
        System.out.println(instance.getName());
    }

    /**
     * Test of getBirthYear method, of class Person.
     */
    @Test
    public void testGetBirthYear() {
        System.out.println("getBirthYear");
        int expResult = 2003;
        int result = instance.getBirthYear();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setBirthYear method, of class Person.
     */
    @Test
    public void testSetBirthYear() {
        System.out.println("setBirthYear");
        instance.setBirthYear(2010);
        assertEquals(2010, instance.getBirthYear());
    }

    /**
     * Test of setBirthYear method, of class Person.
     */
    @Test
    public void testSetBirthYearBefore1900() {
        System.out.println("setBirthYear");
        int birth_year = 1899;
        //Person instance = new Person("William Lindahl", 2003);
        assertThrows(IllegalArgumentException.class, () -> instance.setBirthYear(birth_year));
    }
    
    /**
     * Test of setBirthYear method, of class Person.
     */
    @Test
    public void testSetBirthYearInTheFuture() {
        System.out.println("setBirthYear");
        int birth_year = Year.now().getValue()+1;
        //Person instance = new Person("William Lindahl", 2003);
        assertThrows(IllegalArgumentException.class, () -> instance.setBirthYear(birth_year));
    }
    
    /**
     * Test of toString method, of class Person.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        //assertEquals("William Lindahl, 2003", instance.toString());
    }
    
}
