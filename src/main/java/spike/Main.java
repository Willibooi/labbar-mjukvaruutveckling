/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package spike;

import domain.Person;
import domain.Room;
import domain.Site;
import repository.PersonDao;
import repository.RoomDao;
import repository.SiteDao;

/**
 *
 * @author 22wili03
 */
public class Main {

    public static void main(String[] args) {
        // SETUP
        /*-----------------------------------------------------------------*/
        
        // DAO-objekt
        PersonDao personDao = new PersonDao();
        RoomDao roomDao = new RoomDao();
        SiteDao siteDao = new SiteDao();
        
        // Tar bort allt från databasen så att den inte fylls upp
        personDao.getAll().stream().forEach(p -> personDao.delete(p));
        roomDao.getAll().stream().forEach(r -> roomDao.delete(r));
        siteDao.getAll().stream().forEach(s -> siteDao.delete(s));
        
        // TESTER FÖR PersonDao
        /*-----------------------------------------------------------------*/
        
        // Sparar 3 personer till databasen
        Person person = personDao.save(new Person("William", 2004)); // Returnerar den sparade personen med givet id i databasen
        personDao.save(new Person("peter", 2006));
        personDao.save(new Person("håkan", 2001));
        // Hämtar alla personer och printar
        System.out.println(personDao.getAll());
        // Uppdaterar första personens namn, William -> Lars
        Person firstPerson = personDao.get(person.getId());
        firstPerson.setName("Lars");
        personDao.update(firstPerson);
        firstPerson = personDao.update(firstPerson);
        System.out.println("Uppdaterade Williams namn till " + firstPerson.getName());
        System.out.println(personDao.getAll());
        // Tar bort första personen (Lars)
        System.out.println("Tog bort personen: " + personDao.delete(firstPerson).toString());
        System.out.println(personDao.getAll());
        
        // TESTER FÖR RoomDao
        /*-----------------------------------------------------------------*/
        
        //Sparar 3 rum till databasen
        Room room = roomDao.save(new Room(12, 30)); // Returnerar det sparade rummet med givet id i databasen
        System.out.println("ID: " + room.getId());
        roomDao.save(new Room(13, 30));
        roomDao.save(new Room(14, 30));
        // Hämtar alla rum och printar
        System.out.println(roomDao.getAll());
        // Uppdaterar första rummets antal sittplatser, 12 -> 15
        Room firstRoom = roomDao.get(room.getId());
        firstRoom.setRoomNum(15);
        firstRoom = roomDao.update(firstRoom);
        System.out.println("Uppdaterade rum 12 till rum " + firstRoom.getRoomNum());
        System.out.println(roomDao.getAll());
        // Tar bort första rummet (15)
        System.out.println("Tog bort rum " + roomDao.delete(firstRoom).toString());
        System.out.println(roomDao.getAll());
        
        // TESTER FÖR SiteDao
        /*-----------------------------------------------------------------*/
        
        //Sparar 3 platser till databasen
        Site site = siteDao.save(new Site("Högskolan i Gävle", 40000)); // Returnerar den sparade platsen med givet id i databasen
        siteDao.save(new Site("Bessemerskolan", 10000));
        siteDao.save(new Site("Vallhovsskolan", 5000));
        // Hämtar alla platser och printar
        System.out.println(siteDao.getAll());
        // Hämtar och uppdaterar den första platsens area, 40000 -> 50000 kvadratmeter
        Site firstSite = siteDao.get(site.getId());
        System.out.println("Högskolan i Gävles area är " + firstSite.getArea());
        firstSite.setArea(50000);
        firstSite = siteDao.update(firstSite);
        System.out.println("Uppdaterade arean av " + firstSite.getName() + " till " +  firstSite.getArea());
        System.out.println("Högskolan i Gävles area är nu " + firstSite.getArea());
        // Tar bort första platsen (Högskolan i Gävle)
        System.out.println("Tog bort " + siteDao.delete(firstSite).toString());
        System.out.println(siteDao.getAll());
        
        
               
    }

}
