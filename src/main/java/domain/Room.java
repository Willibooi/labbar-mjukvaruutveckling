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
public class Room {
    
    int id;
    int roomNum;
    int numOfSeats;
    
    public Room(int roomNum, int numOfSeats) {
        this.roomNum = roomNum;
        this.numOfSeats = numOfSeats;
    }
    
    public Room(int id, int roomNum, int numOfSeats) {
        this.id = id;
        this.roomNum = roomNum;
        this.numOfSeats = numOfSeats;
    }
    
    public int getId() {
        return id;
    }
    
    public int getRoomNum() {
        return roomNum;
    }
    
    public void setRoomNum(int roomNum) {
        if(roomNum > 0)
            this.roomNum = roomNum;
        else
            throw new IllegalArgumentException();
    }
    
    public int getNumOfSeats() {
        return numOfSeats;
    }
    
    public void setNumOfSeats(int numOfSeats) {
        if(numOfSeats > 0)
            this.numOfSeats = numOfSeats;
        else 
            throw new IllegalArgumentException();
    }
    
    @Override
    public String toString() {
        return Integer.toString(roomNum);
    }
    
    @Override
    public boolean equals(Object p) {
        return p instanceof Room && hashCode() == p.hashCode();
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + this.roomNum;
        hash = 29 * hash + this.numOfSeats;
        return hash;
    }
    
}
