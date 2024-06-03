/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.Year;

/**
 *
 * @author 22wili03
 */
public class Person {
    
    int id;
    String name;
    int birth_year;
    
    public Person(String name, int birth_year) {
        setName(name);
        setBirthYear(birth_year);
    }
    
    public Person(int id, String name, int birth_year) {
        this(name, birth_year);
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if(name.equals(""))
            throw new IllegalArgumentException("Name cannot be empty");
        if(name.length() > 50) 
            this.name = name.substring(0, 50);
        else
            this.name = name;
    }
    
    public int getBirthYear() {
        return birth_year;
    }
    
    public void setBirthYear(int birth_year) {
        if(birth_year < 1900 || birth_year > Year.now().getValue())
            throw new IllegalArgumentException();
        this.birth_year = birth_year;
    }
    
    @Override
    public String toString() {
        return name + ", " + birth_year;
    }
    
}
