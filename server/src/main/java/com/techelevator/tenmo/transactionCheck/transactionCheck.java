package com.techelevator.tenmo.transactionCheck;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class TransactionCheck {

    //check valid transaction
    //increase receiver
    //decrease sender
    //update transactiontable

@Autowired
    private AccountDAO dao;
    //public TransactionCheck(AccountDAO dao) { this.dao = dao; }   ???? ask about that part????

    //I can't send more TE Bucks than I have in my account.
    //I can't send a zero or negative amount.
    public boolean checkTransaction(Transaction transaction) {

        int accountId = transaction.getFromAccount();
        Account account =dao.getAccountBalanceByAccountId(accountId);
        BigDecimal currentBalance = account.getBalance();
        if (transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) == 1 &&
                (transaction.getFromAccount() != transaction.getToUserAccount()) &&
                (currentBalance.compareTo(transaction.getTransactionAmount()) >= 0)){
            return true;
        }else{
            return false;
        }
    }
}
