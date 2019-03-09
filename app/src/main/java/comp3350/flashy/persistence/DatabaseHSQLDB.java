package comp3350.flashy.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import org.hsqldb.Server;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

public class DatabaseHSQLDB implements DatabaseImplementation {
    private static Connection connection;

    public DatabaseHSQLDB() {
//        Server hsqlServer = new Server();
//        hsqlServer.setDatabaseName(0, "FlashyDB");
//        hsqlServer.setDatabasePath(0, "file:FlashyDB");
//
//        hsqlServer.setSilent(true);
//
//        // Start the database!
//        hsqlServer.start();

        System.out.println("Created DB Server");

        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:file:FlashyDB", "SA", "");
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

            deleteDeck(identifier);

            for (int i = 0; i < cardList.size(); i++) {
                Flashcard card = cardList.get(i);

                System.out.println(cardList.size() + "inputDeck:" + identifier + " " + card.getCardName() + " " + card.getQuestion() + " " + card.getAnswer() + " ");

                /*
                Update the Deck Table
                 */
                PreparedStatement statement = connection.prepareStatement(
                        "insert into Deck (deckName, cardName, cardQuestion, cardAnswer) values (?, ?, ?, ?);");
                statement.setString(1, identifier);
                statement.setString(2, card.getCardName());
                statement.setString(3, card.getQuestion());
                statement.setString(4, card.getAnswer());
                statement.executeUpdate();

                /*
                Update the DeckList Table
                 */
                statement = connection.prepareStatement(
                        "insert into DeckList (deckName) values (?)");// if (select count (deckName) from DeckList where deckName=?) <= 1;");
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
    public void deleteDeck(String identifier){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from DeckList where deckName=?;");
            statement.setString(1, identifier);
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
    public Collection getDeckCollection() {
        Collection result = null;
        ArrayList<Deck> deckList = new ArrayList();

        try {
            PreparedStatement statement = connection.prepareStatement("select distinct * from DeckList;");
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
                    "create table if not exists DeckList (" +
                            "deckName varChar(60));");
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
