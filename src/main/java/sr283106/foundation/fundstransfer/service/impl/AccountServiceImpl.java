package sr283106.foundation.fundstransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sr283106.foundation.fundstransfer.dao.AccountDAO;
import sr283106.foundation.fundstransfer.domain.Account;
import sr283106.foundation.fundstransfer.exception.AccountNotFoundException;
import sr283106.foundation.fundstransfer.exception.InsufficientPrivilageException;
import sr283106.foundation.fundstransfer.service.AccountService;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO accountDAO;

    @Override
    public Account createAccount(Account account, boolean isBankUser) {
        if (!isBankUser) {
            throw new InsufficientPrivilageException("You don't have sufficient privilege to Create Account. Please contact Bank !!!");
        }
        return accountDAO.save(account);
    }

    @Override
    public List<Account> fetchAllAccounts() {
        return accountDAO.findAll();
    }

    @Override
    public Account fetchAccountsById(Long accountId) {
        return accountDAO.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account with account number : " + accountId + " not found. Please check the account number entered"));
    }

    @Override
    public Account updateAccount(Account account, boolean isBankUser) {
        if (!isBankUser) {
            throw new InsufficientPrivilageException("You don't have sufficient privilege to Update Account. Please contact Bank !!!");
        }
        validateAccountNumber(account);
        return accountDAO.save(account);
    }

    private void validateAccountNumber(Account account) {
        accountDAO.findById(account.getAccountNumber()).orElseThrow(() -> new AccountNotFoundException("Please check the Account number entered, You cannot update the account number!!!"));
    }

    @Override
    public void deleteAccount(Long accountId,boolean isBankUser) {
        if (!isBankUser) {
            throw new InsufficientPrivilageException("You don't have sufficient privilege to Delete Account. Please contact Bank !!!");
        }
        accountDAO.delete(accountDAO.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account with account number : " + accountId + " not found. Please check the account number entered")));
    }
}
