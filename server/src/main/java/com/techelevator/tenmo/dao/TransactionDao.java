package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {

    Transaction create(Transaction transaction); //@valid

    void update(Transaction updatedTransaction);

    Transaction getTransaction(int transactionId);

}
