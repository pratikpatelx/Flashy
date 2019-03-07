package comp3350.flashy.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comp3350.flashy.domain.Deck;
import comp3350.flashy.domain.Flashcard;

public class DatabaseHSQLDB implements DatabaseImplementation {
    static Connection connection;

    class MyDatabase {
        private Connection connection;
        MyDatabase db = new MyDatabase();

        public MyDatabase() {
            try {
                Class.forName("org.hsqldb.jdbcDriver");

                connection = DriverManager.getConnection("jdbc:hsqldb:mem:fileDB", "SA", "");

                createTables();
            } catch (ClassNotFoundException e) {
                e.printStackTrace(System.out);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    @Override
    public void inputDeck(String identifier, Deck inputDeck) {
        try {
            ArrayList cardList = inputDeck.getCards();
            Flashcard card = null;
            for (int i = 0; i < cardList.size(); i++) {
                card = (Flashcard) cardList.get(i);
                PreparedStatement statement = connection.prepareStatement(
                        "insert into Decks values (?, ?, ?, ?);");
                statement.setString(1, identifier);
                statement.setString(2, card.getCardName());
                statement.setString(3, card.getQuestion());
                statement.setString(4, card.getAnswer());

                ResultSet resultSet =  statement.executeQuery();

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
            PreparedStatement statement = connection.prepareStatement(
                    "select cardName, cardQuestion, cardAnswer from Decks where deckName=?;");
            statement.setString(1, identifier);

            result = new Deck(identifier);
            String cardName = null;
            String cardQuestion = null;
            String cardAnswer = null;

            ResultSet resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                cardName = resultSet.getString("cardName");
                cardQuestion = resultSet.getString("cardQuestion");
                cardAnswer = resultSet.getString("cardAnswer");
                result.addCard(new Flashcard(cardName, cardQuestion, cardAnswer));
            }

            statement = connection.prepareStatement( "delete from Decks where deckName=?;");
            statement.setString(1, identifier);
            statement.executeUpdate();

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return result;
    }

    @Override
    public ArrayList<String> getAllDeckNames() {
        return null;
    }

    private void createTables(){
        try {
            // This is a terrible database design, but it works for now
            String createDeck = "CREATE TABLE Decks ("
                    + "deckName varchar(60)"
                    + "cardName varchar(60), "
                    + "cardQuestion varchar(60), "
                    + "cardAnswer varchar(60), "
                    + "PRIMARY KEY (deckName)"
                    + "UNIQUE (cardName)"
                    + ");";

            connection.createStatement().executeUpdate(createDeck);
        }
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
