package sr283106.foundation.fundstransfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sr283106.foundation.fundstransfer.domain.Transaction;
import sr283106.foundation.fundstransfer.domain.TransactionMessage;
import sr283106.foundation.fundstransfer.service.TransactionService;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService transactionService;

    @PostMapping
    ResponseEntity<TransactionMessage> fundsTransfer(@RequestBody Transaction transaction) {
        logger.info("Entering into fundsTransfer method");
        return ResponseEntity.ok(transactionService.fundsTransfer(transaction));
    }

}
