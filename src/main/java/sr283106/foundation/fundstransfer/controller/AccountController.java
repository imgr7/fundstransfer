package sr283106.foundation.fundstransfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sr283106.foundation.fundstransfer.domain.Account;
import sr283106.foundation.fundstransfer.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts/")
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @GetMapping("{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        logger.info("Entering into getAccountById method");
        return ResponseEntity.ok(accountService.fetchAccountsById(accountId));
    }

    @PostMapping
    ResponseEntity<Account> createAccount(@RequestBody Account account, @RequestParam boolean isBankUser) {
        logger.info("Entering into createAccount method");
        return ResponseEntity.ok(accountService.createAccount(account, isBankUser));
    }

    @GetMapping("all")
    ResponseEntity<List<Account>> fetchAllAccounts() {
        logger.info("Entering into fetchAllAccounts method");
        return ResponseEntity.ok(accountService.fetchAllAccounts());
    }

    @PutMapping()
    ResponseEntity<Account> updateAccount(@RequestBody Account account, @RequestParam boolean isBankUser) {
        logger.info("Entering into updateAccount method");
        return ResponseEntity.ok(accountService.updateAccount(account, isBankUser));
    }

    @DeleteMapping("{accountId}")
    ResponseEntity<String> deleteAccount(@PathVariable Long accountId, @RequestBody boolean isBankUser) {
        logger.info("Entering into deleteAccount method");
        accountService.deleteAccount(accountId, isBankUser);
        logger.info("Exiting from deleteAccount method");
        return ResponseEntity.ok("Record Is successfully deleted");
    }
}
