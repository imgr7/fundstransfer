package sr283106.foundation.fundstransfer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sr283106.foundation.fundstransfer.domain.Account;
import sr283106.foundation.fundstransfer.service.AccountService;

import java.math.BigDecimal;
import java.util.Arrays;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AccountService accountService;

    @Test
    void getAccountById() throws Exception {
        Account account = new Account(123l, new BigDecimal(300.00), "Test", "Test", "Test", "Test", "Test", "Test");
        Mockito.when(accountService.fetchAccountsById(123l)).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber", Matchers.is(123)));

    }

    @Test
    void createAccount() throws Exception {

        Account account = new Account(123l, new BigDecimal(300.00), "Test", "Test", "Test", "Test", "Test", "Test");
        Mockito.when(accountService.createAccount(account, true)).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/?isBankUser=true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(account)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber", Matchers.is(123)));
    }

    @Test
    void fetchAllAccounts() throws Exception {

        Account account = new Account(123l, new BigDecimal(300.00), "Test", "Test", "Test", "Test", "Test", "Test");
        Mockito.when(accountService.fetchAllAccounts()).thenReturn(Arrays.asList(account));
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].accountNumber", Matchers.is(123)));

    }

    @Test
    void updateAccount() throws Exception {

        Account account = new Account(123l, new BigDecimal(300.00), "Test", "Test", "Test", "Test", "Test", "Test");
        Mockito.when(accountService.updateAccount(account, true)).thenReturn(account);
        mockMvc.perform(MockMvcRequestBuilders.put("/accounts/?isBankUser=true")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(account)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber", Matchers.is(123)));

    }


    void deleteAccount() throws Exception {
        Mockito.doNothing().when(accountService).deleteAccount(123l, true);
        boolean isBankUser = true;
        mockMvc.perform(MockMvcRequestBuilders.delete("/accounts/123").content(objectMapper.writeValueAsBytes(true))).andExpect(MockMvcResultMatchers.status().isOk());


    }
}