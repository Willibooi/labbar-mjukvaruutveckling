/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.Year;

/**
 *
 * @author GTSA - Infinity
 */
public class Person {
    
    int id;
    String name;
    int birth_year;
    
    public Person(String name, int birth_year) {
        this.name = name;
        this.birth_year = birth_year;
    }
    
    public Person(int id, String name, int birth_year) {
        this.id = id;
        this.name = name;
        this.birth_year = birth_year;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        if(id > 0)
            this.id = id;
        else
            throw new IllegalArgumentException();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if(name.length() <= 50) 
            this.name = name;
        else 
            this.name = name.substring(50);
    }
    
    public int getBirthYear() {
        return birth_year;
    }
    
    public void setBirthYear(int birth_year) {
        if(birth_year >= 1900 && birth_year < Year.now().getValue())
            this.birth_year = birth_year;
        else 
            throw new IllegalArgumentException();
        
    }
    
    public String toString() {
        return name;
    }
    
}
