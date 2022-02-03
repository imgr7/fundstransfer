package sr283106.foundation.fundstransfer.service;

import org.springframework.stereotype.Service;
import sr283106.foundation.fundstransfer.domain.Transaction;
import sr283106.foundation.fundstransfer.domain.TransactionMessage;


public interface TransactionService {

    TransactionMessage fundsTransfer(Transaction transaction);

}
