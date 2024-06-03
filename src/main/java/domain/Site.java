/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author 22wili03
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
        this(name, squareMeter);
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
            throw new IllegalArgumentException();
        if(name.length() > 50) 
            this.name = name.substring(50);
        this.name = name;
    }
    
    public int getArea() {
        return squareMeter;
    }
    
    public void setArea(int squareMeter) {
        if(squareMeter <= 0)
            throw new IllegalArgumentException("Area cannot be equal or less than 0");
        this.squareMeter = squareMeter;
        
    }
    
    @Override
    public String toString() {
        return name;
    }
}
