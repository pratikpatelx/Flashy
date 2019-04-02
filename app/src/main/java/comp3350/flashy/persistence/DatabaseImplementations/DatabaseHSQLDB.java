package comp3350.flashy.persistence.DatabaseImplementations;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.persistence.DatabaseImplementation;

public class DatabaseHSQLDB implements DatabaseImplementation {

    private final String dbPath;

    public DatabaseHSQLDB(String dbPath) {

        this.dbPath = dbPath;
        System.out.println(dbPath);
        //createTables();
    }

    private Connection connection() throws SQLException {
        System.out.println("Connection called");
        Connection result;
        try {
            result = DriverManager.getConnection(
                    "jdbc:hsqldb:file:" + dbPath + ";shutdown=true",
                    "SA",
                    "");
        }finally{
            System.out.println("Failed to connnect");
        }

        return result;
    }


    @Override
    public void inputDeck(String username, String identifier, Deck inputDeck) {
        try (final Connection connection = connection()) {
            ArrayList<Flashcard> flashcardList = new ArrayList<>(inputDeck.getFlashcards());

            deleteDeck(username, identifier);

            for (int i = 0; i < flashcardList.size(); i++) {
                Flashcard flashcard = flashcardList.get(i);

                System.out.println(flashcardList.size() + "inputDeck:" + identifier + " " + flashcard.getCardName() + " " + flashcard.getQuestion() + " " + flashcard.getAnswer() + " ");

                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO DECK (DECKNAME, CARDNAME, CARDQUESTION, CARDANSWER) " +
                                "VALUES (?, ?, ?, ?);");
                statement.setString(1, identifier);
                statement.setString(2, flashcard.getCardName());
                statement.setString(3, flashcard.getQuestion());
                statement.setString(4, flashcard.getAnswer());
                statement.executeUpdate();

                // Update the decklist Table
                statement = connection.prepareStatement(
                        "INSERT INTO DECKLIST (USERNAME, DECKNAME) VALUES (?,?)");
                statement.setString(1, username);
                statement.setString(2, identifier);
                statement.executeUpdate();

                System.out.println("inputDeck DONE");
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("inputDeck Failed");
            e.printStackTrace(System.out);
        }
    }

    @Override
    public Deck getDeck(String username, String identifier) {
        System.out.println("getDeck started");

        Deck result = null;

        try (final Connection connection = connection()) {

            /*
            If a deck with the given name exists, get it from the database
             */
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT cardName, cardQuestion, cardAnswer FROM DECK join DECKLIST ON DECK.DECKNAME=DECKLIST.DECKNAME " +
                            "WHERE DECKLIST.USERNAME=?;");
            statement.setString(1, username);
            ResultSet resultSet =  statement.executeQuery();

            System.out.println("Got deck");

            result = new Deck(identifier);
            while (resultSet.next()) {
                String cardName = resultSet.getString("CARDNAME");
                String cardQuestion = resultSet.getString("CARDQUESTION");
                String cardAnswer = resultSet.getString("CARDANSWER");
                result.addCard(new Flashcard(cardName, cardQuestion, cardAnswer));
            }

            System.out.println("getDeck DONE");

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("getDeck Failed");
            e.printStackTrace(System.out);
        }

        return result;
    }

    @Override
    public void deleteDeck(String username, String identifier){
        System.out.println("deleteDeck started");
        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM DECKLIST WHERE USERNAME=? AND DECKNAME=?;");
            statement.setString(1, username);
            statement.setString(2, identifier);
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "DELETE FROM DECK WHERE DECKNAME=?;");
            statement.setString(1, identifier);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException  e) {
            System.out.println("Delete Failed");
        }
    }

    @Override
    public Collection getDeckCollection(String username) {
        System.out.println("getDeckCollection started");

        Collection result = null;
        ArrayList<Deck> deckList = new ArrayList();

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT DISTINCT * FROM DECKLIST WHERE USERNAME=?;");
            statement.setString(1, username);

            ResultSet resultSet =  statement.executeQuery();
            Deck deck = null;
            while (resultSet.next()) {
                deck = new Deck(resultSet.getString("DECKNAME"));
                deckList.add(deck);
            }

            statement = connection.prepareStatement("SELECT * FROM DECK;");
            resultSet =  statement.executeQuery();

            while (resultSet.next()) {
                String deckName = resultSet.getString("DECKNAME");
                String cardName = resultSet.getString("CARDNAME");
                String cardQuestion = resultSet.getString("CARDQUESTION");
                String cardAnswer = resultSet.getString("CARDANSWER");
                Flashcard flashcard = new Flashcard(cardName, cardQuestion, cardAnswer);

                for (int i = 0; i < deckList.size(); i++) {
                    Deck tempDeck = deckList.get(i);
                    System.out.println(deckList.size());
                    if (deckName.equals(tempDeck.getName())) {
                        tempDeck.addCard(flashcard);
                        deckList.remove(i);
                        deckList.add(tempDeck);
                        System.out.println("deckName: " + deckName + " CardName: " + cardName);
                    }
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("getDeckCollection Failed");
            e.printStackTrace();
        }

        result = deckList;

        return result;
    }

    @Override
    public void inputUser(String username, String password) {
        System.out.println("inputUser started");

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO USERACCOUNTS (USERNAME, PASSWORD) VALUES (?, ?);");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            System.out.println("inputUser Failed");
            e.printStackTrace();
        }
    }

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
        } catch (SQLException e) {
            System.out.println("getUserPassword Failed");
            e.printStackTrace();
        }
        return result;
    }

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
        } catch (SQLException e){
            System.out.println("removeUser Failed");
            e.printStackTrace();
        }
    }

    @Override
    public Collection<String> getUserCollection() {
        System.out.println("removeUser started");

        ArrayList<String> usernames = new ArrayList<>();

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT USERNAME FROM USERACCOUNTS");
            ResultSet results = statement.executeQuery();

            while(results.next()){
                usernames.add(results.getString("USERNAME"));
            }

            results.close();
            statement.close();

        } catch (SQLException e){
            System.out.println("get all users failed Failed");
            e.printStackTrace();
        }

        return usernames;

    }

    private void createTables() {
        System.out.println("createTables started");

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "create table if not exists userlist (" +
                            "USERNAME varChar(60) not null unique" +
                            "PASSWORD varChar(60))not null;");
            statement.execute();

            System.out.println("userlist Created");

            statement = connection.prepareStatement(
                    "create table if not exists DECKLIST (" +
                            "USERNAME varChar(60)" +
                            "deckName varChar(60));");
            statement.execute();

            System.out.println("DECKLIST Created");

            statement = connection.prepareStatement(
                    "create table if not exists deck ("
                            + "deckName varChar(60), "
                            + "cardName varChar(60), "
                            + "cardQuestion varChar(60), "
                            + "cardAnswer varChar(255));");
            statement.execute();

            System.out.println("deck Created");
            statement.close();

        } catch (SQLException e) {
            System.out.println("Failed to create tables");
        }
    }
}
