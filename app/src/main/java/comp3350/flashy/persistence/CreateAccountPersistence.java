package comp3350.flashy.persistence;

import comp3350.flashy.domain.CreateAccount;
import comp3350.flashy.persistence.hsqldb.PersistenceException;

public interface CreateAccountPersistence {
    CreateAccount insertAccount (CreateAccount currentCreateAccount) throws PersistenceException, PersistenceException;

    CreateAccount getAccountInformation() throws PersistenceException;

    boolean getAccountInformation(String userName, String password) throws PersistenceException;
}
