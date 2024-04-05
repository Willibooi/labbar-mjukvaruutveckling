/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.Year;
import java.util.Objects;

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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        if(name.equals(""))
            throw new IllegalArgumentException();
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
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
        public boolean equals(Object p) {
        return p instanceof Person && hashCode() == p.hashCode();
    }
    @Override
        public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + this.birth_year;
        return hash;
    }

    
}
