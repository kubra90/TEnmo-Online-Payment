package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDAO {

    //all CRUD methods!!!

    Account getAccountBalance(int userId);

    //update balance acc. to the transaction! AccountId is receiver's id!
    //public boolean updateBalanceByIncrease(Account account);


    //method having transaction amount with account id

}
