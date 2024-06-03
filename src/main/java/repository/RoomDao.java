/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import domain.Room;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author 22wili03
 */
public class RoomDao implements Dao<Room> {
    
    
    DbConnectionManager dbConManagerSingleton = null;

    public RoomDao() {
            dbConManagerSingleton = DbConnectionManager.getInstance();
    }

    @Override
    public Room get(int id) {
        Room person = null;
        try{
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, roomNum, numOfSeats FROM labrooms WHERE id=" + id);
            if( !resultSet.next())
                    throw new NoSuchElementException("The room with id " + id + " doesen't exist in database");
            else
                    person = new Room(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
            dbConManagerSingleton.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    @Override
    public List<Room> getAll() {
        ArrayList<Room> list = new ArrayList<>();
		
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, roomNum, numOfSeats FROM labrooms");
            while (resultSet.next()) {
                    list.add(new Room(resultSet.getInt(1), 
                                                             resultSet.getInt(2),
                                                             resultSet.getInt(3))
                                    );
            }
            dbConManagerSingleton.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Room save(Room t) {
        try {
            
            PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement(
                    "INSERT INTO labrooms (roomNum, numOfSeats) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, t.getRoomNum());
            preparedStatement.setInt(2, t.getNumOfSeats());
            int affectedRows = preparedStatement.executeUpdate();

            
            if (affectedRows == 0) {
                throw new SQLException("Creating room failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        t = new Room(generatedKeys.getInt(1), t.getRoomNum(), t.getNumOfSeats());
                    } else {
                        throw new SQLException("Creating room failed, no ID obtained.");
                    }
                }
        }
        catch ( SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public Room update(Room t) {
        PreparedStatement preparedStatement = null;

        try {
            // *******This is the main 'save' operation ***************************
            preparedStatement = dbConManagerSingleton
                            .prepareStatement("UPDATE labrooms SET roomNum=?, numOfSeats=? WHERE id=?;");
            preparedStatement.setInt(1, t.getRoomNum());
            preparedStatement.setInt(2, t.getNumOfSeats());
            preparedStatement.setInt(3, t.getId());
            
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("No update was performed on labrooms with 'id' " + t.getId());
            }

            // ********************************************************************
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public Room delete(Room t) {
        PreparedStatement preparedStatement = null;
        try {
            // *******This is the main 'save' operation ***************************
            preparedStatement = dbConManagerSingleton
                            .prepareStatement("DELETE FROM labrooms WHERE id = " + t.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0) {
                    throw new SQLException("Nothing was deleted on labrooms with 'id' " + t.getId());
            }
            
            // ********************************************************************
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
    
}
