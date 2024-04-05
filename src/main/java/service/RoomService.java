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
        this(new RoomDao());
    }
    public RoomService(RoomDao roomDao) {
        dao = roomDao;
    }
    
    public void save(Room p) {
        if(p == null) {
            throw new NullPointerException("Cant save null");
        }
        dao.save(p);
    }
    
    public Room get(int i) {
        return dao.get(i);
    }
    
    public List<Room> getAll() {
        return dao.getAll();
    }
    
    public void update(Room p) {
        if(p == null) {
            throw new NullPointerException("Cant update null");
        }
        dao.update(p);
    }
    
    public void delete(Room p) {
        if(p == null) {
            throw new NullPointerException("Cant delete null");
        }
        dao.delete(p);
    }
}
