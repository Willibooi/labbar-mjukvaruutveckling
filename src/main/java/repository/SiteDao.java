/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Site;
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
public class SiteDao implements Dao<Site> {
    
    DbConnectionManager dbConManagerSingleton = null;

    public SiteDao() {
            dbConManagerSingleton = DbConnectionManager.getInstance();
    }

    @Override
    public Site get(int id) {
        Site site = null;
        try{
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, area FROM labsites WHERE id=" + id);
            if( !resultSet.next())
                    throw new NoSuchElementException("The room with id " + id + " doesen't exist in database");
            else
                    site = new Site(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
            dbConManagerSingleton.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return site;
    }

    @Override
    public List<Site> getAll() {
        ArrayList<Site> list = new ArrayList<>();
		
        try {
            ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, area FROM labsites");
            while (resultSet.next()) {
                    list.add(new Site(resultSet.getInt(1), 
                                                             resultSet.getString(2),
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
    public boolean save(Site t) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rowCount = 0;
        boolean saveSucess = false;
        try {
            
            resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(id) FROM labsites");
            resultSet.next();
            rowCount = resultSet.getInt(1);

            preparedStatement = dbConManagerSingleton.prepareStatement(
                                                                              "INSERT INTO labsites (name, area) " +
                                                                              "VALUES (?, ?)");
            preparedStatement.setString(1, t.getName());
            preparedStatement.setInt(2, t.getArea());
            preparedStatement.executeUpdate();

            resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(id) FROM labsites");
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
    public void update(Site t) {
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = dbConManagerSingleton
                            .prepareStatement("UPDATE labsites SET name=?, area=? WHERE id=?;");
            preparedStatement.setString(1, t.getName());
            preparedStatement.setInt(2, t.getArea());
            preparedStatement.setInt(3, t.getId());

            boolean affectedRows = preparedStatement.execute();
            if( !affectedRows) {
                    throw new SQLException("No update was performed on labsites with 'id' " + t.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Site t) {
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = dbConManagerSingleton
                            .prepareStatement("DELETE FROM labsites WHERE id = " + t.getId());
            boolean affectedRows = preparedStatement.execute();
            if( !affectedRows) {
                    throw new SQLException("No update was performed on labsites with 'id' " + t.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
