package comp3350.flashy.business;

import comp3350.flashy.application.Services;
import comp3350.flashy.domain.CreateAccount;
import comp3350.flashy.persistence.CreateAccountPersistence;
import comp3350.flashy.persistence.hsqldb.PersistenceException;

public class AccessCreateAccounts {
    private CreateAccountPersistence accountPersistence;

    public AccessCreateAccounts() {
        accountPersistence = Services.getCreateAccountPersistence();
    }

    public AccessCreateAccounts(CreateAccountPersistence accountPersistence) {
        this.accountPersistence = accountPersistence;
    }

    public CreateAccount insertAccountInformation(CreateAccount createAccountInformation) throws PersistenceException {

        if (createAccountInformation.validateAccountInfo()) {
            return accountPersistence.insertAccount(createAccountInformation);
        }

        return null;
    }

    public CreateAccount getRandomAccountInformation() throws PersistenceException {
        return accountPersistence.getAccountInformation();
    }

    public boolean getAccountInformation(String userName, String password) throws PersistenceException {
        return accountPersistence.getAccountInformation(userName, password);
    }
}
