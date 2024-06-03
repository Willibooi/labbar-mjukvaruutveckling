package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import domain.Person;
import java.sql.Statement;
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
	public Person save(Person t) {
            try {
                PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement(
                        "INSERT INTO labpersons (name, birth_year) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                preparedStatement.setString(1, t.getName());
                preparedStatement.setInt(2, t.getBirthYear());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating room failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        t = new Person(generatedKeys.getInt(1), t.getName(), t.getBirthYear());
                    } else {
                        throw new SQLException("Creating room failed, no ID obtained.");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception appropriately in your context
            }

            return t;
	}
	
        
	@Override
	public Person update(Person t) {
            try {
                PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement("UPDATE labpersons SET name=?, birth_year=? WHERE id=?;");
                preparedStatement.setString(1, t.getName());
                preparedStatement.setInt(2, t.getBirthYear());
                preparedStatement.setInt(3, t.getId());
                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0) { 
                    throw new SQLException("No update was performed on labpersons with 'id' " + t.getId());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return t;
	}

	@Override
	public Person delete(Person t) {

            try {
                PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement("DELETE FROM labpersons WHERE id = " + t.getId());
                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0) {
                    throw new SQLException("No update was performed on labpersons with 'id' " + t.getId());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return t;
	}
}
