package com.techelevator.tenmo.transactionCheck;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;

public class transactionCheck {

    //check valid transaction
    //increase receiver
    //decrease sender
    //update transactiontable

    private Account account;
    //accountDao???

    //I can't send more TE Bucks than I have in my account.
    //I can't send a zero or negative amount.
    private boolean checkTransaction(Transaction transaction) {

        if(transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) == 1 && (account.getBalance()).compareTo(transaction.getTransactionAmount()) == 1 ){
            return true;
        }else{
            return false;
        }
    }

    private BigDecimal increaseBalance(Transaction transaction){
        //get Current balance
        BigDecimal currentBalance = account.getBalance();
        //add transaction amount to the current amount
        BigDecimal newBalance = currentBalance.add(transaction.getTransactionAmount());
        //return new balance.
        return newBalance;
    }

    private BigDecimal decreaseBalance(Transaction transaction){
        //get Current balance
        BigDecimal currentBalance = account.getBalance();
        //add transaction amount to the current amount
        BigDecimal newBalance = currentBalance.subtract(transaction.getTransactionAmount());
        //return new balance.
        return newBalance;
    }

    public boolean performTransaction(Transaction transaction){
        //checktransaction first
        if(checkTransaction(transaction)){
            increaseBalance(transaction);
            decreaseBalance(transaction);
            return true;
        }
        return false;
    }

}
