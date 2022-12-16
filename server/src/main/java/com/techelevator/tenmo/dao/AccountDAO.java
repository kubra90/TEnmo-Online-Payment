package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {

    //all CRUD methods!!!

    Account getAccountBalance(int userId);

    //update balance acc. to the transaction! AccountId is receiver's id!
    //public boolean updateBalanceByIncrease(Account account);

   Account getAccountBalanceByAccountId(int accountId);

   BigDecimal getBalance(int accountId);

   List<Account> getAllAccounts();

    void addBalance(BigDecimal amount, int accountId);
    public void substractBalance(BigDecimal amount, int accountId);



    //method having transaction amount with account id

}
