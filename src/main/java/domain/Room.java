/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author 22wili03
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
    
}
