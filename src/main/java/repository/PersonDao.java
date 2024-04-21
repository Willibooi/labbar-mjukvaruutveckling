package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import domain.Person;

/**
 *
 * @author 22wili03
 */
public class PersonDao implements Dao<Person> {

	DbConnectionManager dbConManagerSingleton = null;
	
	public PersonDao() {
            dbConManagerSingleton = DbConnectionManager.getInstance();
	}
	
	@Override
	
	public Person get(int id) throws NoSuchElementException {
            Person person = null;
            try{
                ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year FROM labpersons WHERE id=" + id);
                if( !resultSet.next())
                    throw new NoSuchElementException("The person with id " + id + " doesen't exist in database");
                else
                    person = new Person(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                dbConManagerSingleton.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return person;
	}

	@Override
	public List<Person> getAll() {	
            ArrayList<Person> list = new ArrayList<>();
            try {
                ResultSet resultSet = dbConManagerSingleton.excecuteQuery("SELECT id, name, birth_year FROM labpersons");
                while (resultSet.next()) {
                        list.add(new Person(resultSet.getInt(1), resultSet.getString(2).trim(),resultSet.getInt(3)));
                }
                dbConManagerSingleton.close();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return list;
	}

	@Override
	public boolean save(Person t) {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            int rowCount = 0;
            boolean saveSucess = false;
            try {
                
                resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(id) FROM labpersons");
                resultSet.next();
                rowCount = resultSet.getInt(1);
                
                
                preparedStatement = dbConManagerSingleton.prepareStatement(
                                                                                  "INSERT INTO labpersons (name, birth_year) " +
                                                                                  "VALUES (?, ?)");
                preparedStatement.setString(1, t.getName());
                preparedStatement.setInt(2, t.getBirthYear());
                preparedStatement.executeUpdate();
                
                
                resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(id) FROM labpersons");
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
	public void update(Person t) {
            try {
                PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement("UPDATE labpersons SET name=?, birth_year=? WHERE id=?;");
                preparedStatement.setString(1, t.getName());
                preparedStatement.setInt(2, t.getBirthYear());
                preparedStatement.setInt(3, t.getId());
                boolean affectedRows = preparedStatement.execute();
                if( !affectedRows) { 
                    throw new SQLException("No update was performed on labpersons with 'id' " + t.getId());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	}

	@Override
	public void delete(Person t) {

            try {
                PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement("DELETE FROM labpersons WHERE id = " + t.getId());
                boolean affectedRows = preparedStatement.execute();
                if( !affectedRows) {
                    throw new SQLException("No update was performed on labpersons with 'id' " + t.getId());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
	}
}
