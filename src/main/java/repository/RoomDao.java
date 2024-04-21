/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import domain.Room;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public boolean save(Room t) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rowCount = 0;
        boolean saveSucess = false;
        try {

            resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(id) FROM labrooms");
            resultSet.next();
            rowCount = resultSet.getInt(1);

            preparedStatement = dbConManagerSingleton.prepareStatement(
                                                                              "INSERT INTO labrooms (roomNum, numOfSeats) " +
                                                                              "VALUES (?, ?)");
            preparedStatement.setInt(1, t.getRoomNum());
            preparedStatement.setInt(2, t.getNumOfSeats());
            preparedStatement.executeUpdate();

            resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(id) FROM labrooms");
            resultSet.next();
            int newRowCount = resultSet.getInt(1);
            if( newRowCount == (rowCount + 1)) // Check if table is one more row after 'save'
                    saveSucess = true;
            System.out.format("Previous row count: %d    Current row count: %d", rowCount, newRowCount);
        }
        catch ( SQLException e) {
            e.printStackTrace();
        }
        return saveSucess;
    }

    @Override
    public void update(Room t) {
        PreparedStatement preparedStatement = null;

        try {
            
            preparedStatement = dbConManagerSingleton
                            .prepareStatement("UPDATE labrooms SET roomNum=?, numOfSeats=? WHERE id=?;");
            preparedStatement.setInt(1, t.getRoomNum());
            preparedStatement.setInt(2, t.getNumOfSeats());
            preparedStatement.setInt(3, t.getId());
            
            boolean affectedRows = preparedStatement.execute();
            if( !affectedRows) {
                    throw new SQLException("No update was performed on labrooms with 'id' " + t.getId());
            }

            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Room t) {
        PreparedStatement preparedStatement = null;
        try {
            
            preparedStatement = dbConManagerSingleton
                            .prepareStatement("DELETE FROM labrooms WHERE id = " + t.getId());
            boolean affectedRows = preparedStatement.execute();
            if( !affectedRows) {
                    throw new SQLException("No update was performed on labrooms with 'id' " + t.getId());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
