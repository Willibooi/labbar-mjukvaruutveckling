/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package service;

import domain.Room;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import repository.RoomDao;

/**
 *
 * @author GTSA - Infinity
 */
public class RoomServiceTest {
    
    public RoomServiceTest() {
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
     * Test of save method, of class RoomService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        RoomDao roomDaoMock = mock(RoomDao.class);
        Room room = new Room(1, 12, 30);
        when(roomDaoMock.save(room)).thenReturn(true);
        
        RoomService instance = new RoomService(roomDaoMock);
        
        // Throws exception if room is null
        Exception e = assertThrows(NullPointerException.class,() -> instance.save(null));
        assertEquals("Cant save null", e.getMessage());
        
        // Does not throw exception if room is not null
        assertDoesNotThrow(() -> instance.save(room));
        
        verify(roomDaoMock, times(1)).save(room);
    }

    /**
     * Test of get method, of class RoomService.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        RoomDao roomDaoMock = mock(RoomDao.class);
        when(roomDaoMock.get(1)).thenReturn(new Room(1, 12, 30));
        
        RoomService instance = new RoomService(roomDaoMock);
        Room expResult = new Room(1, 12, 30);
        Room result = instance.get(1);
        assertTrue(expResult.equals(result));
        
        verify(roomDaoMock, times(1)).get(1);
    }

    /**
     * Test of getAll method, of class RoomService.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        RoomDao roomDaoMock = mock(RoomDao.class);
        when(roomDaoMock.getAll()).thenReturn(List.of(new Room(1, 12, 30), new Room(2, 13, 40)));
        
        RoomService instance = new RoomService(roomDaoMock);
        List<Room> expResult = List.of(new Room(1, 12, 30), new Room(2, 13, 40));
        List<Room> result = instance.getAll();
        assertTrue(expResult.equals(result));
        
        verify(roomDaoMock, times(1)).getAll();
    }

    /**
     * Test of update method, of class RoomService.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        RoomDao roomDaoMock = mock(RoomDao.class);
        Room room = new Room(1, 12, 30);
        
        RoomService instance = new RoomService(roomDaoMock);
        
        // Throws exception if room is null
        Exception e = assertThrows(NullPointerException.class,() -> instance.update(null));
        assertEquals("Cant update null", e.getMessage());
        
        // Does not throw exception if room is not null
        assertDoesNotThrow(() -> instance.update(room));
        
        verify(roomDaoMock, times(1)).update(room);
    }

    /**
     * Test of delete method, of class RoomService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        RoomDao roomDaoMock = mock(RoomDao.class);
        Room room = new Room(1, 12, 30); 
        
        RoomService instance = new RoomService(roomDaoMock);
        
        // Throws exception if site is null
        assertThrows(NullPointerException.class,() -> instance.delete(null));
        
        // Does not throw exception if room is not null
        assertDoesNotThrow(() -> instance.delete(room));
        
        verify(roomDaoMock, times(1)).delete(room);
    }
    
}
