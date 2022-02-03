package sr283106.foundation.fundstransfer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sr283106.foundation.fundstransfer.dao.AccountDAO;
import sr283106.foundation.fundstransfer.dao.TransactionDAO;
import sr283106.foundation.fundstransfer.domain.Account;
import sr283106.foundation.fundstransfer.domain.Transaction;
import sr283106.foundation.fundstransfer.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TransactionService transactionService;

    @MockBean
    AccountDAO accountDAO;

    @MockBean
    TransactionDAO transactionDAO;

    @Test
    void fundsTransferForInvalidFromAccount() throws Exception {

        Transaction transaction = new Transaction(1l, 123l, 124l, new BigDecimal(123), "test", LocalDate.now());
        mockMvc.perform(MockMvcRequestBuilders.post("/transaction/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(transaction)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("The account id with number : 123is not found")));
        ;
    }

    @Test
    void fundsTransferForInvalidToAccount() throws Exception {
        Account account = new Account(124l, new BigDecimal(300.00), "Test", "Test", "Test", "Test", "Test", "Test");
        Transaction transaction = new Transaction(1l, 123l, 124l, new BigDecimal(123), "test", LocalDate.now());
        Mockito.when(accountDAO.findById(123l)).thenReturn(Optional.of(account));

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(transaction)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("The account id with number : 124is not found")));
        ;
    }

    @Test
    void fundsTransferForInsufficientBalance() throws Exception {
        Account account = new Account(124l, new BigDecimal(0.00), "Test", "Test", "Test", "Test", "Test", "Test");
        Account account1 = new Account(123l, new BigDecimal(0.00), "Test", "Test", "Test", "Test", "Test", "Test");
        Transaction transaction = new Transaction(1l, 123l, 124l, new BigDecimal(123), "test", LocalDate.now());
        Mockito.when(accountDAO.findById(123l)).thenReturn(Optional.of(account1));
        Mockito.when(accountDAO.findById(124l)).thenReturn(Optional.of(account));
        mockMvc.perform(MockMvcRequestBuilders.post("/transaction/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(transaction)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Insufficient balance to transfer the funds. Please contact the Bank")));
        ;
    }

    @Test
    void fundsTransfer() throws Exception {
        Account account = new Account(124l, new BigDecimal(300.00), "Test", "Test", "Test", "Test", "Test", "Test");
        Account account1 = new Account(123l, new BigDecimal(300.00), "Test", "Test", "Test", "Test", "Test", "Test");
        Transaction transaction = new Transaction(1l, 123l, 124l, new BigDecimal(123), "test", LocalDate.now());
        Mockito.when(accountDAO.findById(123l)).thenReturn(Optional.of(account1));
        Mockito.when(accountDAO.findById(124l)).thenReturn(Optional.of(account));
        mockMvc.perform(MockMvcRequestBuilders.post("/transaction/").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Transaction Successful")));
        ;
    }
}