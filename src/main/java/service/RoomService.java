/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;


import domain.Room;
import java.util.List;
import repository.RoomDao;


/**
 *
 * @author GTSA - Infinity
 */
public class RoomService {
    RoomDao dao = new RoomDao();
    
    public RoomService() {
        
    }
    
    public void save(Room p) {
        dao.save(p);
    }
    
    public Room get(int i) {
        return dao.get(i);
    }
    
    public List<Room> getAll() {
        return dao.getAll();
    }
    
    public void update(Room p) {
        dao.update(p);
    }
    
    public void delete(Room p) {
        dao.delete(p);
    }
}
