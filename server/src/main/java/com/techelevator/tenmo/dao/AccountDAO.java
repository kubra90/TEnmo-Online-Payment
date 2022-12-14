package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDAO {

    //all CRUD methods!!!

    Account getAccountBalance(int accountId);
}
