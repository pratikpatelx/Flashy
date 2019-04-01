package comp3350.flashy.business;

import java.util.Collections;
import java.util.List;

import comp3350.flashy.application.Services;
import comp3350.flashy.domain.CreateAccount;
import comp3350.flashy.persistence.CreateAccountPersistence;
import comp3350.flashy.persistence.hsqldb.PersistenceException;

public class AccessCreateAccounts {
    private CreateAccountPersistence accountPersistence;
    private List<CreateAccount> accounts;
    private CreateAccount account;
    private int currentAccount;

    public AccessCreateAccounts() {
        accountPersistence = Services.getCreateAccountPersistence();
    }

    public AccessCreateAccounts(CreateAccountPersistence accountPersistence) {
        this.accountPersistence = accountPersistence;
    }

    public List<CreateAccount> getAccounts(){
        accounts = accountPersistence.getAccountInformation();
        return Collections.unmodifiableList(accounts);

    }

    public CreateAccount insertAccountInformation(CreateAccount createAccountInformation) throws PersistenceException {

        if (createAccountInformation.validateAccountInfo()) {
            return accountPersistence.insertAccount(createAccountInformation);
        }

        return null;
    }


    public boolean getAccountInformation(String userName, String password) throws PersistenceException {
        return accountPersistence.getAccountInformation(userName, password);
    }

    public void deleteAccount(CreateAccount account) {
        accountPersistence.deleteAccount(account);
    }
}
