/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domain.Person;
import java.util.List;
import repository.PersonDao;

/**
 *
 * @author GTSA - Infinity
 */
public class PersonService {
    
    PersonDao dao = new PersonDao();
    
    public PersonService() {
        this(new PersonDao());
    }
    public PersonService(PersonDao personDao) {
        dao = personDao;
    }
    
    public void save(Person p) {
        if(p == null) {
            throw new NullPointerException("Cant save null");
        }
        dao.save(p);
    }
    
    public Person get(int i) {
        return dao.get(i);
    }
    
    public List<Person> getAll() {
        return dao.getAll();
    }
    
    public void update(Person p) {
        if(p == null) {
            throw new NullPointerException("Cant update null");
        }
        dao.update(p);
    }
    
    public void delete(Person p) {
        if(p == null) {
            throw new NullPointerException("Cant delete null");
        }
        dao.delete(p);
    }
}
