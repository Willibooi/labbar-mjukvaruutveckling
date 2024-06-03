/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Site;
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
    public Site save(Site t) {
        try {
            PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement(
                    "INSERT INTO labsites (name, area) VALUES (?, ?)", 
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, t.getName());
            preparedStatement.setInt(2, t.getArea());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating site failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    t = new Site(generatedKeys.getInt(1), t.getName(), t.getArea());
                } else {
                    throw new SQLException("Creating site failed, no ID obtained.");
                }
            }
        }
        catch ( SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public Site update(Site t) {
        PreparedStatement preparedStatement = null;

        try {
            // *******This is the main 'save' operation ***************************
            preparedStatement = dbConManagerSingleton
                            .prepareStatement("UPDATE labsites SET name=?, area=? WHERE id=?;");
            preparedStatement.setString(1, t.getName());
            preparedStatement.setInt(2, t.getArea());
            preparedStatement.setInt(3, t.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("No update was performed on labsites with 'id' " + t.getId());
            }

            // ********************************************************************
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public Site delete(Site t) {
        PreparedStatement preparedStatement = null;
        try {
            // *******This is the main 'save' operation ***************************
            preparedStatement = dbConManagerSingleton
                            .prepareStatement("DELETE FROM labsites WHERE id = " + t.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0) {
                    throw new SQLException("No update was performed on labsites with 'id' " + t.getId());
            }
            // ********************************************************************
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }
}
