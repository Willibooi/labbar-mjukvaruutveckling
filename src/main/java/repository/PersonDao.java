package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import domain.Person;
/**
 * DAO for the persistent handling of a Person object. It manages all
 * CRUD operations and conversion between the object world student and
 * the relational version student (DB version).
 * Due to the use of a DbConnectionManager the DAO doesen't need to 
 * use, or even know, about any of lower level connections to the Database.
 * It 'speaks' in Objects with the object world (Domain model)and in 
 * relational sql strings, tables, columns and result sets with the database.
 * @author awi
 *
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
                //****This is just for checking the 'save' is a sucess. Count rows before save... ***
                resultSet = dbConManagerSingleton.excecuteQuery("SELECT COUNT(id) FROM labpersons");
                resultSet.next();
                rowCount = resultSet.getInt(1);
                //System.out.println(rowCount); // Debug print

                //*******This is the main 'save' operation ***************************
                preparedStatement = dbConManagerSingleton.prepareStatement(
                                                                                  "INSERT INTO labpersons (name, birth_year) " +
                                                                                  "VALUES (?, ?)");
                preparedStatement.setString(1, t.getName());
                preparedStatement.setInt(2, t.getBirthYear());
                preparedStatement.executeUpdate();
                // ********************************************************************

                // **** Check nbr of rows after 'save'. Compare with previous row count *****
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
	/**
	 * This method uses a temporary Student set with the desired changed values.
	 * It must have a 'id' that corresponds to a existing record in the database.
	 * @param t - an instance of a Student with new values on attributes but 
	 * an 'id' identical to an existing student in the DB
	 */
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
