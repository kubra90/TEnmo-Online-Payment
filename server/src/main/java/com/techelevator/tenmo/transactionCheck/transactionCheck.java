package com.techelevator.tenmo.transactionCheck;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


public class TransactionCheck {

    //check valid transaction
    //increase receiver
    //decrease sender
    //update transactiontable

    private Account account;
    private Transaction transaction;
    private AccountDAO accountDAO;


    //I can't send more TE Bucks than I have in my account.
    //I can't send a zero or negative amount.
    public boolean checkTransaction(Transaction transaction) {

       // int senderId= transaction.getFromAccount();
        if (transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) == 1 && (account.getBalance()).compareTo(transaction.getTransactionAmount()) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
