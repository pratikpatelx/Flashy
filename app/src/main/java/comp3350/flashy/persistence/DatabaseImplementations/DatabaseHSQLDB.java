package comp3350.flashy.persistence.DatabaseImplementations;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.domain.Flashcard;
import comp3350.flashy.domain.Deck;
import comp3350.flashy.persistence.DatabaseImplementation;

public class DatabaseHSQLDB implements DatabaseImplementation {

    private final String dbPath;

    public DatabaseHSQLDB(String givenDBPath) {
        dbPath = givenDBPath;
        System.out.println("Connection Successful!");
    }

    private Connection connection() throws SQLException {
        System.out.println("Connection called");
        return DriverManager.getConnection(
                "jdbc:hsqldb:file:" + dbPath + ";shutdown=true",
                "SA",
                "");
    }

    @Override
    public void inputDeck(String username, String identifier, Deck inputDeck) {
        System.out.println("inputDeck started");

        try (final Connection connection = connection()) {
            ArrayList<Flashcard> flashcardList = new ArrayList<>(inputDeck.getFlashcards());

            deleteDeck(username, identifier);

            for (int i = 0; i < flashcardList.size(); i++) {
                Flashcard flashcard = flashcardList.get(i);

                System.out.println(flashcardList.size() + "inputDeck:" + identifier + " " + flashcard.getCardName() + " " + flashcard.getQuestion() + " " + flashcard.getAnswer() + " ");

                /*
                Update the Deck Table
                 */
                PreparedStatement statement = connection.prepareStatement(
                        "insert into Deck (deckName, cardName, cardQuestion, cardAnswer)" +
                                " values (?, ?, ?, ?);");
                statement.setString(1, identifier);
                statement.setString(2, flashcard.getCardName());
                statement.setString(3, flashcard.getQuestion());
                statement.setString(4, flashcard.getAnswer());
                statement.executeUpdate();

                /*
                Update the DeckList Table
                 */
                statement = connection.prepareStatement(
                        "insert into DeckList (username, deckName) values (?,?)");
                statement.setString(1, username);
                statement.setString(2, identifier);
                statement.executeUpdate();

                System.out.println("inputDeck DONE");

                statement.close();
            }
        }catch (SQLException e) {
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
                    "select cardName, cardQuestion, cardAnswer from Deck join DeckList " +
                            "where DeckList.username=? " +
                            "and DeckList.deckName=? " +
                            "and Deck.deckName=DeckList.deckName;");
            statement.setString(1, username);
            statement.setString(1, identifier);
            ResultSet resultSet =  statement.executeQuery();

            System.out.println("Got Deck");

            result = new Deck(identifier);
            while (resultSet.next()) {
                String cardName = resultSet.getString("cardName");
                String cardQuestion = resultSet.getString("cardQuestion");
                String cardAnswer = resultSet.getString("cardAnswer");
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
                    "delete from DeckList where username=? and deckName=?;");
            statement.setString(1, username);
            statement.setString(2, identifier);
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "delete from Deck where deckName=?;");
            statement.setString(1, identifier);
            statement.executeUpdate();
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
                    "select distinct * from DeckList where username=?;");
            statement.setString(1, username);

            ResultSet resultSet =  statement.executeQuery();
            Deck deck = null;
            while (resultSet.next()) {
                deck = new Deck(resultSet.getString("deckName"));
                deckList.add(deck);
            }

            statement = connection.prepareStatement("select * from Deck;");
            resultSet =  statement.executeQuery();

            while (resultSet.next()) {
                String deckName = resultSet.getString("deckName");
                String cardName = resultSet.getString("cardName");
                String cardQuestion = resultSet.getString("cardQuestion");
                String cardAnswer = resultSet.getString("cardAnswer");
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
                    "insert into UserList (username, password) values (?, ?);");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
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
                    "select password from UserList where username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("password");
            }
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
                    "delete from UserList where username=?;");
            statement.setString(1, username);
            statement.executeUpdate();

            statement = connection.prepareStatement(
                    "delete from DeckList where username=?;");
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println("removeUser Failed");
            e.printStackTrace();
        }
    }

    @Override
    public Collection<String> getUserCollection(){
        System.out.println("getUserCollection started");
        Collection userList = new ArrayList<String>();

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from UserList;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(resultSet.getString("username"));
            }
        } catch (SQLException e){
            System.out.println("getUserCollection Failed");
            e.printStackTrace();
        }

        return userList;
    }

    private void createTables() {
        System.out.println("createTables started");

        try (final Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "create table if not exists UserList (" +
                            "username varChar(60) not null unique" +
                            "password varChar(60))not null;");
            statement.execute();

            System.out.println("UserList Created");

            statement = connection.prepareStatement(
                    "create table if not exists DeckList (" +
                            "username varChar(60)" +
                            "deckName varChar(60));");
            statement.execute();

            System.out.println("DeckList Created");

            statement = connection.prepareStatement(
                    "create table if not exists Deck ("
                            + "deckName varChar(60), "
                            + "cardName varChar(60), "
                            + "cardQuestion varChar(60), "
                            + "cardAnswer varChar(255));");
            statement.execute();

            System.out.println("Deck Created");

        } catch (SQLException e) {
            System.out.println("Failed to create tables");
        }
    }
}
