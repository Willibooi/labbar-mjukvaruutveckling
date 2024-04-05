/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package service;

import domain.Site;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import repository.SiteDao;

/**
 *
 * @author GTSA - Infinity
 */
public class SiteServiceTest {
    
    public SiteServiceTest() {
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
     * Test of save method, of class SiteService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        SiteDao siteDaoMock = mock(SiteDao.class);
        Site site = new Site(1, "HIG", 30);
        when(siteDaoMock.save(site)).thenReturn(true);
        
        SiteService instance = new SiteService(siteDaoMock);
        
        // Throws exception if site is null
        Exception e = assertThrows(NullPointerException.class,() -> instance.save(null));
        assertEquals("Cant save null", e.getMessage());
        
        // Does not throw exception if site is not null
        assertDoesNotThrow(() -> instance.save(site));
        
        verify(siteDaoMock, times(1)).save(site);
    }

    /**
     * Test of get method, of class SiteService.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        SiteDao siteDaoMock = mock(SiteDao.class);
        when(siteDaoMock.get(1)).thenReturn(new Site(1, "HIG", 30));
        
        SiteService instance = new SiteService(siteDaoMock);
        Site expResult = new Site(1, "HIG", 30);
        Site result = instance.get(1);
        assertTrue(expResult.equals(result));
        
        verify(siteDaoMock, times(1)).get(1);
    }

    /**
     * Test of getAll method, of class SiteService.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        SiteDao siteDaoMock = mock(SiteDao.class);
        when(siteDaoMock.getAll()).thenReturn(List.of(new Site(1, "HIG", 30), new Site(2, "HIG", 40)));
        
        SiteService instance = new SiteService(siteDaoMock);
        List<Site> expResult = List.of(new Site(1, "HIG", 30), new Site(2, "HIG", 40));
        List<Site> result = instance.getAll();
        assertTrue(expResult.equals(result));
        
        verify(siteDaoMock, times(1)).getAll();
    }

    /**
     * Test of update method, of class SiteService.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        SiteDao siteDaoMock = mock(SiteDao.class);
        Site site = new Site(1, "HIG", 30);
        //when(siteDaoMock.update(site)).thenReturn(true);
        
        SiteService instance = new SiteService(siteDaoMock);
        
        // Throws exception if site is null
        Exception e = assertThrows(NullPointerException.class,() -> instance.update(null));
        assertEquals("Cant update null", e.getMessage());
        
        // Does not throw exception if site is not null
        assertDoesNotThrow(() -> instance.update(site));
        
        verify(siteDaoMock, times(1)).update(site);
    }

    /**
     * Test of delete method, of class SiteService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        SiteDao siteDaoMock = mock(SiteDao.class);
        Site site = new Site(1, "HIG", 30);
        //when(siteDaoMock.delete(site)).thenReturn(true);
        
        SiteService instance = new SiteService(siteDaoMock);
        
        // Throws exception if site is null
        assertThrows(NullPointerException.class,() -> instance.delete(null));
        
        // Does not throw exception if site is not null
        assertDoesNotThrow(() -> instance.delete(site));
        
        verify(siteDaoMock, times(1)).delete(site);
    }
    
}
