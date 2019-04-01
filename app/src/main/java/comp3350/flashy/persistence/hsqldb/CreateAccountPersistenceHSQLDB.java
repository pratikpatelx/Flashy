package comp3350.flashy.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import comp3350.flashy.domain.CreateAccount;
import comp3350.flashy.persistence.CreateAccountPersistence;

public class CreateAccountPersistenceHSQLDB implements CreateAccountPersistence {
    private final String dbPath;

    public CreateAccountPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private CreateAccount fromResultSet(final ResultSet rs) throws SQLException{
            final String username = rs.getString("USERNAME");
            final String password = rs.getString("PASSWORD");
            return new CreateAccount(username, password);
    }


    @Override
    public List<CreateAccount> getAccountSequential() {
        return null;
    }

    @Override
    public List<CreateAccount> getAccountRandom(CreateAccount currentCreateAccount) {
        return null;
    }

    @Override
    public CreateAccount insertAccount(CreateAccount currentCreateAccount)  {
        System.out.println("INSERT DB TEST");
        try (final Connection c = connection()) {
            final PreparedStatement st =c.prepareStatement("INSERT INTO useraccounts VALUES(?, ?)");
            st.setString(1, currentCreateAccount.getUsername());
            st.setString(2, currentCreateAccount.getPassword());


            st.executeUpdate();
            st.close();
            return currentCreateAccount;

        } catch (final SQLException e) {
             throw new PersistenceException(e);
        }
    }

    @Override
    public List<CreateAccount> getAccountInformation() {
        final List<CreateAccount> accountInfos = new ArrayList<>();

        try(final Connection c  = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM USERACCOUNTS");

            while (rs.next()){
                final CreateAccount accountInfo =fromResultSet(rs);
                accountInfos.add(accountInfo);
            }
            rs.close();
            st.close();

            return accountInfos;

        }catch (final SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public boolean getAccountInformation(String userName, String password) {
            final List<CreateAccount> accountInfos = new ArrayList<>();
            boolean result = false;
            try(final Connection c  = connection()) {
                final Statement st = c.createStatement();
                final ResultSet rs = st.executeQuery("SELECT * FROM USERACCOUNTS");

                while (rs.next()){
                    final CreateAccount accountInfo = fromResultSet(rs);
                    accountInfos.add(accountInfo);
                }
                rs.close();
                st.close();

                for (CreateAccount accObj : accountInfos){
                    if (accObj.getUsername().equalsIgnoreCase(userName) && accObj.getPassword().equalsIgnoreCase(password)){
                        result = true;
                        return  result;
                    }
                }

            }catch (final SQLException e){
                throw new PersistenceException(e);
            }
        return result;
    }

    @Override
    public void deleteAccount(CreateAccount currentAccount) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM useraccounts WHERE USERNAME = ?");
            sc.setString(1, currentAccount.getUsername());
            sc.executeUpdate();
            final PreparedStatement st = c.prepareStatement("DELETE FROM useraccounts WHERE USERNAME = ?");
            st.setString(1, currentAccount.getUsername());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
