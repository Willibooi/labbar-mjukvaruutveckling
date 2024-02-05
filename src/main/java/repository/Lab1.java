/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package repository;

import domain.Person;
import domain.Room;
import domain.Site;
import java.util.ArrayList;
import java.util.List;
import service.PersonService;
import service.RoomService;
import service.SiteService;

/**
 *
 * @author GTSA - Infinity
 */
public class Lab1 {

    public static void main(String[] args) {
        
        // DAO-objekt
        PersonService pd = new PersonService();
        RoomService rd = new RoomService();
        SiteService sd = new SiteService();
        
        // Domän-objekt
        Person person = new Person("William", 2003);
        Room room = new Room(99516, 40);
        Site site = new Site("Högskolan i Gävle", 40000);
        
        // Hämtar och uppdaterar den första siten
        Site oldSite = sd.getAll().getFirst();
        oldSite.setName("Högskolan i Gävle");   // New data
        oldSite.setArea(50000);                 // New data
        sd.update(oldSite);
        
        // Hämtar all data från tabellerna i databasen
        List<Person> personList = pd.getAll();
        List<Room> roomList = rd.getAll();
        List<Site> siteList = sd.getAll();

        
        System.out.println(personList.toString());
        System.out.println(roomList.toString());
        System.out.println(siteList.toString());
        System.out.println(rd.get(12));
               
    }

}
