package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;

public interface TransactionDao {

    Transaction create(Transaction transaction); //@valid

    //Transaction update(Transaction transaction, int )

    Transaction getTransaction(int transactionId);
}
