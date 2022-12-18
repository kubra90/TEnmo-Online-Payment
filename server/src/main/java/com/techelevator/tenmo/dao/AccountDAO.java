package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    //all CRUD methods!!!

    Account getAccountByUserId(int userId);

   Account getAccountByAccountId(int accountId);

   BigDecimal getBalance(int accountId);

   List<Account> getAllAccounts();

    void addBalance(BigDecimal amount, int accountId);
    public void substractBalance(BigDecimal amount, int accountId);


}
