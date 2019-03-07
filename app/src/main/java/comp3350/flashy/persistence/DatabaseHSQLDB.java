package comp3350.flashy.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

import comp3350.flashy.DomainLogic.Deck;
import comp3350.flashy.DomainLogic.Flashcard;

public class DatabaseHSQLDB implements DatabaseImplementation {
    private static Connection connection;

    public DatabaseHSQLDB() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        createTables();
    }

    private void createTables() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "create table if not exists Deck ("
                            + "deckName varChar(180), "
                            + "Primary Key (deckName)");
            statement.execute();

            statement = connection.prepareStatement(
                    "create table if not exists Deck ("
                            + "deckName varChar(180), "
                            + "cardName varChar(180), "
                            + "cardQuestion varChar(180), "
                            + "cardAnswer varChar(180), "
                            + "Primary Key (cardName), "
                            + "Foreign Key (deckName) References DeckList (deckName));");
            statement.execute();
            
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void inputDeck(String identifier, Deck inputDeck) {
        try {
            ArrayList cardList = inputDeck.getCards();
            Flashcard card = null;
            for (int i = 0; i < cardList.size(); i++) {
                card = (Flashcard) cardList.get(i);
                /*
                Update the Deck Table
                 */
                PreparedStatement statement = connection.prepareStatement(
                        "insert into Deck values (?, ?, ?, ?);");
                statement.setString(1, identifier);
                statement.setString(2, card.getCardName());
                statement.setString(3, card.getQuestion());
                statement.setString(4, card.getAnswer());
                ResultSet resultSet =  statement.executeQuery();

                /*
                Update the DeckList Table
                 */
                statement = connection.prepareStatement(
                        "insert into DeckList values (?);");
                statement.setString(1, identifier);
                resultSet =  statement.executeQuery();

                resultSet.close();
                statement.close();
            }
        }catch (SQLException e) {
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
                    "select cardName, cardQuestion, cardAnswer from Decks join DeckList " +
                            " where Decks.deckName=DeckList.deckName " +
                            " and DeckList.deckName=?;");
            statement.setString(1, identifier);
            ResultSet resultSet =  statement.executeQuery();

            int counter = 0;
            while (resultSet.next()) {
                result = new Deck(identifier);
                String cardName = resultSet.getString("cardName");
                String cardQuestion = resultSet.getString("cardQuestion");
                String cardAnswer = resultSet.getString("cardAnswer");
                result.addCard(new Flashcard(cardName, cardQuestion, cardAnswer));

                if (counter == 0) {
                    /*
                    Delete the Deck from the list
                     */
                    statement = connection.prepareStatement(
                            "delete from DeckList where deckName=?;");
                    statement.setString(1, identifier);
                    statement.executeUpdate();
                    counter = 1;
                }
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return result;
    }

    @Override
    public Collection getDeckCollection() {
        Collection result = null;
        ArrayList temp = new ArrayList();

        PreparedStatement statement = null;
        try {
            /*
            Get the list of decks
             */
            statement = connection.prepareStatement(
                    "select deckName, cardName, cardQuestion, cardAnswer from Decks join DeckList" +
                    " where Decks.deckName=DeckList.deckName;");
            ResultSet resultSet =  statement.executeQuery();

            Deck tempDeck = null;
            while (resultSet.next()) {
                tempDeck = new Deck(resultSet.getString("deckName"));
                String cardName = resultSet.getString("cardName");
                String cardQuestion = resultSet.getString("cardQuestion");
                String cardAnswer = resultSet.getString("cardAnswer");
                tempDeck.addCard(new Flashcard(cardName, cardQuestion, cardAnswer));
                temp.add(tempDeck);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result.add(temp);
        return result;
    }
}
