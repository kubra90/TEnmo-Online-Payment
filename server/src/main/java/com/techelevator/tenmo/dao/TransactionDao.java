package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {

    Transaction create(Transaction transaction); //@valid

    Transaction getTransaction(int transactionId);

    List<Transaction>  getTransactionsByUserName(String name);

}
