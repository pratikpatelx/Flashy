package comp3350.flashy.persistence.DatabaseImplementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.persistence.DatabaseImplementation;

public class DatabaseHSQLDB implements DatabaseImplementation {

    private final String dbPath;

    /**
     * Constructor
     *
     * @param dbPath
     */
    public DatabaseHSQLDB(String dbPath) {
        this.dbPath = dbPath;
        System.out.println(dbPath);
    }

    /**
     * get a connection to the DB
     *
     * @return
     * @throws SQLException
     */
    private Connection connection() throws SQLException {
        System.out.println("Connection called");
        Connection result;
        try {
            result = DriverManager.getConnection(
                    "jdbc:hsqldb:file:" + dbPath + ";shutdown=true",
                    "SA",
                    "");
        } finally {
            System.out.println("failed to connect");
        }

        return result;
    }

    /**
     * put a deck into the DB, overwrite if a duplicate exists
     *
     * @param username
     * @param identifier
     * @param inputDeck
     */
    @Override
    public void inputDeck(String username, String identifier, Deck inputDeck) {
        System.out.println("Started inputDeck");
        try (final Connection connection = connection()) {
            ArrayList<Flashcard> flashcardList = new ArrayList<Flashcard>(inputDeck.getFlashcards());

            deleteDeck(username, identifier);
            PreparedStatement statement = null;

            for (int i = 0; i < flashcardList.size(); i++) {
                Flashcard flashcard = flashcardList.get(i);

                statement = connection.prepareStatement(
                        "INSERT INTO DECK (deckName, cardName, cardQuestion, cardAnswer)" +
                                " values (?, ?, ?, ?);");
                statement.setString(1, identifier);
                statement.setString(2, flashcard.getCardName());
                statement.setString(3, flashcard.getQuestion());
                statement.setString(4, flashcard.getAnswer());
                statement.executeUpdate();

            }

            // Update the decklist Table

            statement = connection.prepareStatement(
                    "INSERT INTO DECKLIST (username, deckName) values (?,?)");
            statement.setString(1, username);
            statement.setString(2, identifier);
            statement.executeUpdate();

            statement.close();

            System.out.println("inputDeck DONE");
        } catch (SQLException e) {
            System.out.println("inputDeck Failed");
            e.printStackTrace(System.out);
        }
    }

    /**
     * get a deck from the DB
     *
     * @param username
     * @param identifier
     * @return
     */
    @Override
    public Deck getDeck(String username, String identifier) {
        System.out.println("getDeck started");

        Deck result = null;

        try (final Connection connection = connection()) {

            /*
            If a deck with the given name exists, get it from the database
             */
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT cardName, cardQuestion, cardAnswer FROM DECK JOIN DECKLIST ON DECK.deckName=DECKLIST.deckName " +
                            "WHERE DECKLIST.username=? " +
                            "AND DECKLIST.deckName=? ;");
            statement.setString(1, username);
            statement.setString(2, identifier);
            ResultSet resultSet = statement.executeQuery();

            result = new Deck(identifier);
            while (resultSet.next()) {
                String cardName = resultSet.getString("cardName");
                String cardQuestion = resultSet.getString("cardQuestion");
                String cardAnswer = resultSet.getString("cardAnswer");
                result.addCard(new Flashcard(cardName, cardQuestion, cardAnswer));
                System.out.println(new Flashcard(cardName, cardQuestion, cardAnswer));
            }

            resultSet.close();
            statement.close();

            System.out.println("getDeck DONE");
        } catch (SQLException e) {
            System.out.println("getDeck Failed");
            e.printStackTrace(System.out);
        }

        return result;
    }

    /**
     * delete the specified deck from the DB if it exists
     *
     * @param username
     * @param identifier
     */
    @Override
    public void deleteDeck(String username, String identifier) {
        System.out.println("deleteDeck started");

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM DECKLIST WHERE username=? AND deckName=?;");
            statement.setString(1, username);
            statement.setString(2, identifier);
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "DELETE FROM DECK WHERE deckName=?;");
            statement.setString(1, identifier);
            statement.executeUpdate();
            statement.close();

            System.out.println("deleteDeck DONE");
        } catch (SQLException e) {
            System.out.println("deleteDeck Failed");
        }
    }

    /**
     * get a collection of all the decks currently in the DB
     *
     * @param username
     * @return
     */
    @Override
    public Collection getDeckCollection(String username) {
        System.out.println("getDeckCollection started");

        Collection result = null;
        ArrayList<Deck> deckList = new ArrayList();

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT DISTINCT * FROM DECKLIST WHERE USERNAME=?;");
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            Deck deck = null;
            while (resultSet.next()) {
                deck = new Deck(resultSet.getString("deckName"));
                deckList.add(deck);
            }

            statement = connection.prepareStatement("SELECT * FROM DECK;");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String deckName = resultSet.getString("deckName");
                String cardName = resultSet.getString("cardName");
                String cardQuestion = resultSet.getString("cardQuestion");
                String cardAnswer = resultSet.getString("cardAnswer");
                Flashcard flashcard = new Flashcard(cardName, cardQuestion, cardAnswer);

                for (int i = 0; i < deckList.size(); i++) {
                    Deck tempDeck = deckList.get(i);
                    if (deckName.equals(tempDeck.getName())) {
                        tempDeck.addCard(flashcard);
                        deckList.remove(i);
                        deckList.add(tempDeck);
                    }
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("getDeckCollection DONE");
        } catch (SQLException e) {
            System.out.println("getDeckCollection Failed");
            e.printStackTrace();
        }

        result = deckList;

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
        System.out.println("inputUser started");

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO USERACCOUNTS (USERNAME, PASSWORD) values (?, ?);");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();

            statement.close();

            System.out.println("inputUser DONE");
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
        System.out.println("getUserPassword started");

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

            System.out.println("getUserPassword DONE");
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
        System.out.println("removeUser started");

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

            System.out.println("removeUser DONE");
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
        System.out.println("removeUser started");

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

            System.out.println("getUserCollection DONE");
        } catch (SQLException e) {
            System.out.println("getUserCollection Failed");
            e.printStackTrace();
        }

        return usernames;

    }
}
