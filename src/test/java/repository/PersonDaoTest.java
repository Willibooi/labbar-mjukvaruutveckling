/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import domain.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
public class PersonDaoTest {
    
    DbConnectionManager dbConManager;
    ResultSet resultSet;
    PreparedStatement preparedStatment;
    
    public PersonDaoTest() {

    }
    
    @BeforeAll
    public static void setUpClass() {  
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        dbConManager = mock(DbConnectionManager.class);
        resultSet = mock(ResultSet.class);
        preparedStatment = mock(PreparedStatement.class);
    }
    
    @AfterEach
    public void tearDown() {
        dbConManager = null;
        resultSet = null;
        preparedStatment = null;
    }
    
    @Test
    public void testGet() throws SQLException { 
        
        when(dbConManager.excecuteQuery(anyString())).thenReturn(resultSet);
        
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1, 2);
        when(resultSet.getString(2)).thenReturn("William Lindahl", "Per Persson");
        when(resultSet.getInt(3)).thenReturn(2003, 1920);
        
        PersonDao personDao = new PersonDao(dbConManager);
        
        Person person = personDao.get(1);
        Person person2 = personDao.get(2);
        
        assertEquals(1, person.getId());
        assertEquals("William Lindahl", person.getName());
        assertEquals(2003, person.getBirthYear());
        
        assertEquals(2, person2.getId());
        assertEquals("Per Persson", person2.getName());
        assertEquals(1920, person2.getBirthYear());
    }
    
    @Test
    public void testGetAll() throws SQLException { 
        
        when(dbConManager.excecuteQuery(anyString())).thenReturn(resultSet);
        
        // Simulerar ett resultSet med tre personer 
        when(resultSet.next()).thenReturn(true, true, true, false);
        when(resultSet.getInt(1)).thenReturn(1, 2, 3);
        when(resultSet.getString(2)).thenReturn("William Lindahl", "Per Person", "Sven Svensson");
        when(resultSet.getInt(3)).thenReturn(2003, 1993, 1965);
        
        PersonDao personDao = new PersonDao(dbConManager);
        
        List<Person> personList = personDao.getAll();
        
        assertEquals(3, personList.size());
        
        // Assert each user's properties
        assertEquals(1, personList.get(0).getId());
        assertEquals("William Lindahl", personList.get(0).getName());
        assertEquals(2003, personList.get(0).getBirthYear());
        
        assertEquals(2, personList.get(1).getId());
        assertEquals("Per Person", personList.get(1).getName());
        assertEquals(1993, personList.get(1).getBirthYear());
        
        assertEquals(3, personList.get(2).getId());
        assertEquals("Sven Svensson", personList.get(2).getName());
        assertEquals(1965, personList.get(2).getBirthYear());
        
    }
    
    @Test
    public void testSave() throws SQLException { 
        
        PersonDao personDao = new PersonDao(dbConManager);
        Person person = new Person(1, "William Lindahl", 2003);
        
        // Behavior for dbConManager
        when(dbConManager.excecuteQuery(anyString())).thenReturn(resultSet);
        when(dbConManager.prepareStatement(anyString())).thenReturn(preparedStatment);
        
        // Behavior for resultSet
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(40, 40, 40, 41);
        
        // Behavior for preparedStatment
        when(preparedStatment.executeUpdate()).thenReturn(1);
        
        // saveSuccess should be true if number of rows has not increased by 1, 40 -> 40
        boolean saveSuccess = personDao.save(person);
        assertFalse(saveSuccess);
        
        // saveSuccess should be true if number of rows has increased by 1, 40 -> 41
        saveSuccess = personDao.save(person);
        assertTrue(saveSuccess);
        
    }
    
    @Test
    public void testUpdate() throws SQLException { 
        
        PersonDao personDao = new PersonDao(dbConManager);
        Person person = new Person(1, "William Lindahl", 2003);
        
        // Behavior for dbConManager
        when(dbConManager.prepareStatement(anyString())).thenReturn(preparedStatment);
        
        // Behavior for preparedStatment
        when(preparedStatment.execute()).thenReturn(true, false);
        
        // Successful update
        personDao.update(person);
        
        // Unsuccessful update
        personDao.update(person);
        
    }
    
    @Test
    public void testDelete() throws SQLException { 
        
        PersonDao personDao = new PersonDao(dbConManager);
        Person person = new Person(1, "William Lindahl", 2003);
        
        // Behavior for dbConManager
        when(dbConManager.prepareStatement(anyString())).thenReturn(preparedStatment);
        
        // Behavior for preparedStatment
        when(preparedStatment.execute()).thenReturn(true, false);
        
        // Successful delete
        personDao.delete(person);
        
        // Unsuccessful delete
        personDao.delete(person);
        
    }
    
    
    
}
