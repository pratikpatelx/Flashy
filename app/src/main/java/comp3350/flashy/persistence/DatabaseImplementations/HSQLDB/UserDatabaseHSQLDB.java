package comp3350.flashy.persistence.DatabaseImplementations.HSQLDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.persistence.Interfaces.UserDatabaseImplementation;

public class UserDatabaseHSQLDB implements UserDatabaseImplementation {

    private final String dbPath;

    /**
     * Constructor
     *
     * @param dbPath
     */
    public UserDatabaseHSQLDB(String dbPath) {
        this.dbPath = dbPath;
    }

    /**
     * get a connection to the DB
     *
     * @return
     * @throws SQLException
     */
    private Connection connection() throws SQLException {

        Connection result;

            result = DriverManager.getConnection(
                    "jdbc:hsqldb:file:" + dbPath + ";shutdown=true",
                    "SA",
                    "");


        return result;
    }

    /**
     * add a user to the DB
     *
     * @param username
     * @param password
     */
    @Override
    public void inputUser(String username, String password) {


        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO USERACCOUNTS (USERNAME, PASSWORD) values (?, ?);");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            System.out.println("inputUser Failed");
            e.printStackTrace();
        }
    }

    /**
     * get a users password
     *
     * @param username
     * @return
     */
    @Override
    public String getUserPassword(String username) {


        String result = null;
        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT PASSWORD FROM USERACCOUNTS WHERE USERNAME=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("PASSWORD");
            }
            statement.close();

        } catch (SQLException e) {
            System.out.println("getUserPassword Failed");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * remove a user from the DB
     *
     * @param username
     */
    @Override
    public void removeUser(String username) {


        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM USERACCOUNTS WHERE USERNAME=?;");
            statement.setString(1, username);
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "DELETE FROM DECKLIST WHERE USERNAME=?;");
            statement.setString(1, username);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            System.out.println("removeUser Failed");
            e.printStackTrace();
        }
    }

    /**
     * get a collection of the users in the DB
     *
     * @return
     */
    @Override
    public Collection<String> getUserCollection() {


        ArrayList<String> usernames = new ArrayList<>();

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT USERNAME FROM USERACCOUNTS");
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                usernames.add(results.getString("USERNAME"));
            }

            results.close();
            statement.close();


        } catch (SQLException e) {
            System.out.println("getUserCollection Failed");
            e.printStackTrace();
        }

        return usernames;

    }
}
