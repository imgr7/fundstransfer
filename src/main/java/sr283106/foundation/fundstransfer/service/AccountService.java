package sr283106.foundation.fundstransfer.service;

import org.springframework.stereotype.Service;
import sr283106.foundation.fundstransfer.domain.Account;

import java.util.List;


public interface AccountService {

    Account createAccount(Account account,boolean isBankUser);

    List<Account> fetchAllAccounts();

    Account fetchAccountsById(Long accountId);

    Account updateAccount(Account account,boolean isBankUser);

    void deleteAccount(Long accountId,boolean isBankUser);

}
