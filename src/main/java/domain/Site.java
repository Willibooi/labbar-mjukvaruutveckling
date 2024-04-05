/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Objects;

/**
 *
 * @author GTSA - Infinity
 */
public class Site {
    
    int id;
    String name;
    int squareMeter;
    
    public Site(String name, int squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }
    
    public Site(int id, String name, int squareMeter) {
        this.id = id;
        this.name = name;
        this.squareMeter = squareMeter;
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
    
    public int getArea() {
        return squareMeter;
    }
    
    public void setArea(int squareMeter) {
        if(squareMeter > 0)
            this.squareMeter = squareMeter;
        else 
            throw new IllegalArgumentException();
        
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object p) {
        return p instanceof Site && hashCode() == p.hashCode();
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + this.squareMeter;
        return hash;
    }
}
