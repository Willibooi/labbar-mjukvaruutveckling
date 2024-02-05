/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package repository;

import domain.Person;
import domain.Room;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GTSA - Infinity
 */
public class Lab1 {

    public static void main(String[] args) {
        
        PersonDao pd = new PersonDao();
        RoomDao rd = new RoomDao();
        
        Person person = new Person("William", 2003);
        Room room = new Room(99516, 40);

        List<Room> roomList = new ArrayList<Room>();
        List<Person> personList = new ArrayList<Person>();
        
        roomList = rd.getAll();
        personList = pd.getAll();
        
        System.out.println(personList.toString());
        System.out.println(roomList.toString());
        System.out.println(rd.get(12));
               
    }

}
