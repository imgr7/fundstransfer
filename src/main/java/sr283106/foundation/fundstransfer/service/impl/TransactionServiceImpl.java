package sr283106.foundation.fundstransfer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sr283106.foundation.fundstransfer.dao.AccountDAO;
import sr283106.foundation.fundstransfer.dao.TransactionDAO;
import sr283106.foundation.fundstransfer.domain.Account;
import sr283106.foundation.fundstransfer.domain.Transaction;
import sr283106.foundation.fundstransfer.domain.TransactionMessage;
import sr283106.foundation.fundstransfer.exception.AccountNotFoundException;
import sr283106.foundation.fundstransfer.exception.InsufficientBalanceException;
import sr283106.foundation.fundstransfer.service.TransactionService;

import java.time.LocalDate;
import java.util.Arrays;
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    TransactionDAO transactionDAO;

    @Override
    public TransactionMessage fundsTransfer(Transaction transaction) {
        transaction.setTransactionDate(LocalDate.now());
        validateAccountsForTransfer(transaction);
        transactionDAO.save(transaction);
        return generateTransactionResponse();
    }

    private TransactionMessage generateTransactionResponse() {
        TransactionMessage transactionMessage = new TransactionMessage();
        transactionMessage.setMessage("Transaction Successful");
        transactionMessage.setTimeStamp(LocalDate.now());
        return transactionMessage;
    }

    private void validateAccountsForTransfer(Transaction transaction) {
        Account fromAccount = accountDAO.findById(transaction.getFromAccount()).orElseThrow(
                () -> new AccountNotFoundException("The account id with number : " + transaction.getFromAccount() + "is not found"));
        Account toAccount = accountDAO.findById(transaction.getToAccount()).orElseThrow(
                () -> new AccountNotFoundException("The account id with number : " + transaction.getToAccount() + "is not found"));

        checkBalanceForTransfer(fromAccount, toAccount, transaction);

    }

    private void checkBalanceForTransfer(Account fromAccount, Account toAccount, Transaction transaction) {
        if (fromAccount.getBalanceAmount().compareTo(transaction.getTransferAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to transfer the funds. Please contact the Bank");
        }
        fromAccount.setBalanceAmount(fromAccount.getBalanceAmount().subtract(transaction.getTransferAmount()));
        toAccount.setBalanceAmount(toAccount.getBalanceAmount().add(transaction.getTransferAmount()));
        accountDAO.saveAll(Arrays.asList(fromAccount, toAccount));
    }

}
