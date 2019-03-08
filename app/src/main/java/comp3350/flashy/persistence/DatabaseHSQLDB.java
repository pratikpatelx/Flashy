package comp3350.flashy.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.hsqldb.Server;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

public class DatabaseHSQLDB implements DatabaseImplementation {
    private static Connection connection;

    public DatabaseHSQLDB() {
        Server hsqlServer = new Server();
        hsqlServer.setDatabaseName(0, "FlashyDB");
        hsqlServer.setDatabasePath(0, "file:FlashyDB");

//        hsqlServer.setLogWriter(null);
        hsqlServer.setSilent(true);

        // Start the database!
        hsqlServer.start();

        System.out.println("Created DB Server");

        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:FlashyDB", "SA", "");
//            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/FlashyDB", "SA", "");
            System.out.println(connection.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
            System.out.println("Class Not Found..");
            System.exit(0);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Connection failed..");
            System.exit(0);
        }
        System.out.println("Connection Successful!");
        createTables();
    }

    @Override
    public void inputDeck(String identifier, Deck inputDeck) {
        try {
            ArrayList<Flashcard> cardList = inputDeck.getCards();
            for (int i = 0; i < cardList.size(); i++) {
                Flashcard card = cardList.get(i);

                System.out.println("Starting to insert Deck");

                PreparedStatement statement = connection.prepareStatement("select * from DeckList;");
                ResultSet resultSet =  statement.executeQuery();
                if (resultSet.next()) {
                    statement = connection.prepareStatement(
                            "delete from DeckList where deckName=?;");
                    statement.setString(1, identifier);
                    statement.executeUpdate();
                }

                /*
                Update the Deck Table
                 */
                statement = connection.prepareStatement(
                        "insert into Deck (deckName, cardName, cardQuestion, cardAnswer) values (?, ?, ?, ?);");
                statement.setString(1, identifier);
                statement.setString(2, card.getCardName());
                statement.setString(3, card.getQuestion());
                statement.setString(4, card.getAnswer());
                statement.executeUpdate();

                System.out.println("Inserted Deck");

                /*
                Update the DeckList Table
                 */
                statement = connection.prepareStatement(
                        "insert into DeckList values (?);");
                statement.setString(1, identifier);
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
    public Deck getDeck(String identifier) {
        Deck result = null;

        try {
            /*
            If a deck with the given name exists, get it from the database
             */
            PreparedStatement statement = connection.prepareStatement(
                    "select cardName, cardQuestion, cardAnswer from Deck where Deck.deckName=?;");
            statement.setString(1, identifier);
            ResultSet resultSet =  statement.executeQuery();

            System.out.println("Got Deck");

            while (resultSet.next()) {
                result = new Deck(identifier);
                String cardName = resultSet.getString("cardName");
                String cardQuestion = resultSet.getString("cardQuestion");
                String cardAnswer = resultSet.getString("cardAnswer");
                result.addCard(new Flashcard(cardName, cardQuestion, cardAnswer));
            }

            System.out.println("Parsed Deck Object");

            /*
            Delete the Deck from the list
            */
//            statement = connection.prepareStatement(
//                    "delete from DeckList where deckName=?;");
//            statement.setString(1, identifier);
//            statement.executeUpdate();

            System.out.println("Deleted from decklist");

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
    public Collection getDeckCollection() {
        Collection result = new ArrayList();
        ArrayList<Deck> deckList = new ArrayList();

        try {
            PreparedStatement statement = connection.prepareStatement("select * from DeckList;");
            ResultSet resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                Deck deck = new Deck(resultSet.getString("deckName"));
                System.out.println("deckName: " + resultSet.getString("deckName"));
                deckList.add(deck);
            }

            statement = connection.prepareStatement("select * from Deck;");
            resultSet =  statement.executeQuery();

            while (resultSet.next()) {
                String deckName = resultSet.getString("deckName");
                String cardName = resultSet.getString("cardName");
                String cardQuestion = resultSet.getString("cardQuestion");
                String cardAnswer = resultSet.getString("cardAnswer");
                Flashcard card = new Flashcard(cardName, cardQuestion, cardAnswer);
                for (int i = 0; i < deckList.size(); i++) {
                    Deck tempDeck = deckList.get(i);
                    System.out.println(deckList.size());
                    if (deckName.equals(tempDeck.getName())) {
                        tempDeck.addCard(card);
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

    private void createTables() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "create table if not exists DeckList ("
                            + "deckName varChar(60));");
            statement.execute();

            System.out.println("DeckList Created");

            statement = connection.prepareStatement(
                    "create table if not exists Deck ("
                            + "deckName varChar(60), "
                            + "cardName varChar(60), "
                            + "cardQuestion varChar(60), "
                            + "cardAnswer varChar(60));");
            statement.execute();

            System.out.println("Deck Created");

        } catch (SQLException e) {
            System.out.println("Failed to create tables");
        }
    }
}
