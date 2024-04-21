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
 * @author 22wili03
 */
public class PersonService {
    
    PersonDao dao = new PersonDao();
    
    public PersonService() {
        
    }
    
    public void save(Person p) {
        dao.save(p);
    }
    
    public Person get(int i) {
        return dao.get(i);
    }
    
    public List<Person> getAll() {
        return dao.getAll();
    }
    
    public void update(Person p) {
        dao.update(p);
    }
    
    public void delete(Person p) {
        dao.delete(p);
    }
}
