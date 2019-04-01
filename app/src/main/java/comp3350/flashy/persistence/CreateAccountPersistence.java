package comp3350.flashy.persistence;

import java.util.List;

import comp3350.flashy.domain.CreateAccount;
import comp3350.flashy.persistence.hsqldb.PersistenceException;

public interface CreateAccountPersistence {
    List<CreateAccount> getAccountSequential();

    List<CreateAccount> getAccountRandom(CreateAccount currentCreateAccount);

    CreateAccount insertAccount (CreateAccount currentCreateAccount);

    List<CreateAccount>getAccountInformation();

    boolean getAccountInformation(String userName, String password);
}
