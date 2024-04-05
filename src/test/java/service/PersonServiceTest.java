/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package service;

import domain.Person;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import repository.PersonDao;

/**
 *
 * @author GTSA - Infinity
 */
public class PersonServiceTest {
    
    public PersonServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of save method, of class PersonService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        PersonDao personDaoMock = mock(PersonDao.class);
        Person person = new Person(1, "William Lindahl", 2003);
        when(personDaoMock.save(person)).thenReturn(true);
        
        PersonService instance = new PersonService(personDaoMock);
        
        // Throws exception if person is null
        Exception e = assertThrows(NullPointerException.class,() -> instance.save(null));
        assertEquals("Cant save null", e.getMessage());
        
        // Does not throw exception if person is not null
        assertDoesNotThrow(() -> instance.save(person));
        
        verify(personDaoMock, times(1)).save(person);
    }

    /**
     * Test of get method, of class PersonService.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        PersonDao personDaoMock = mock(PersonDao.class);
        when(personDaoMock.get(1)).thenReturn(new Person(1, "William Lindahl", 2003));
        
        PersonService instance = new PersonService(personDaoMock);
        Person expResult = new Person(1, "William Lindahl", 2003);
        Person result = instance.get(1);
        assertEquals(expResult, result);
        
        
        verify(personDaoMock, times(1)).get(1);
    }

    /**
     * Test of getAll method, of class PersonService.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        PersonDao personDaoMock = mock(PersonDao.class);
        when(personDaoMock.getAll()).thenReturn(List.of(new Person(1, "William Lindahl", 2003), new Person(2, "per persson", 1994)));
        
        PersonService instance = new PersonService(personDaoMock);
        List<Person> expResult = List.of(new Person(1, "William Lindahl", 2003), new Person(2, "per persson", 1994));
        List<Person> result = instance.getAll();
        assertEquals(expResult, result);
        
        
        verify(personDaoMock, times(1)).getAll();
    }

    /**
     * Test of update method, of class PersonService.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        PersonDao personDaoMock = mock(PersonDao.class);
        Person person = new Person(1, "William Lindahl", 2003);
        //when(personDaoMock.update(person)).thenReturn(true);
        
        PersonService instance = new PersonService(personDaoMock);
        
        // Throws exception if person is null
        Exception e = assertThrows(NullPointerException.class,() -> instance.update(null));
        assertEquals("Cant update null", e.getMessage());
        
        // Does not throw exception if person is not null
        assertDoesNotThrow(() -> instance.update(person));
        
        verify(personDaoMock, times(1)).update(person);
    }

    /**
     * Test of delete method, of class PersonService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        PersonDao personDaoMock = mock(PersonDao.class);
        Person person = new Person(1, "William Lindahl", 2003);
        //when(personDaoMock.delete(person)).thenReturn(true);
        
        PersonService instance = new PersonService(personDaoMock);
        
        // Throws exception if person is null
        assertThrows(NullPointerException.class,() -> instance.delete(null));
        
        // Does not throw exception if person is not null
        assertDoesNotThrow(() -> instance.delete(person));
        
        verify(personDaoMock, times(1)).delete(person);
    }
    
}
