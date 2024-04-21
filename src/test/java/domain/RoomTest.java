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
public class RoomTest {
    
    Room instance;
    
    public RoomTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        instance = new Room(1, 12, 32);
    }
    
    @AfterEach
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of getId method, of class Room.
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
     * Test of getRoomNum method, of class Room.
     */
    @Test
    public void testGetRoomNum() {
        System.out.println("getRoomNum");
        assertEquals(12, instance.getRoomNum());
    }
    
    /**
     * Test of getArea method, of class Site.
     */
    @Test
    public void testGetRoomNumOnNullInstance() {
        System.out.println("getRoomNum");
        instance = null;
        assertThrows(NullPointerException.class, () -> instance.getRoomNum());
    }

    /**
     * Test of setRoomNum method, of class Room.
     */
    @Test
    public void testSetRoomNum() {
        System.out.println("setRoomNum");
        instance.setRoomNum(43);
        assertEquals(43, instance.getRoomNum());
    }
    
     /**
     * Test of setRoomNum method, of class Room.
     */
    @Test
    public void testSetRoomNumToZero() {
        System.out.println("setRoomNum");
        assertThrows(IllegalArgumentException.class, () -> instance.setRoomNum(0));
    }
    
    /**
     * Test of setRoomNum method, of class Room.
     */
    @Test
    public void testSetRoomNumToNegativeValue() {
        System.out.println("setRoomNum");
        assertThrows(IllegalArgumentException.class, () -> instance.setRoomNum(-20));
    }

    /**
     * Test of getNumOfSeats method, of class Room.
     */
    @Test
    public void testGetNumOfSeats() {
        System.out.println("getNumOfSeats");
        assertEquals(32, instance.getNumOfSeats());

    }

    /**
     * Test of setNumOfSeats method, of class Room.
     */
    @Test
    public void testSetNumOfSeats() {
        System.out.println("setNumOfSeats");
        instance.setNumOfSeats(23);
        assertEquals(23, instance.getNumOfSeats());
    }
    
    /**
     * Test of setRoomNum method, of class Room.
     */
    @Test
    public void testSetNumOfSeatsToZero() {
        System.out.println("setNumOfSeats");
        assertThrows(IllegalArgumentException.class, () -> instance.setNumOfSeats(0));
    }
    
    /**
     * Test of setRoomNum method, of class Room.
     */
    @Test
    public void testSetNumOfSeatsToNegativeValue() {
        System.out.println("setNumOfSeats");
        assertThrows(IllegalArgumentException.class, () -> instance.setNumOfSeats(-20));
    }

    /**
     * Test of toString method, of class Room.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals("12", instance.toString());
    }
    
}
