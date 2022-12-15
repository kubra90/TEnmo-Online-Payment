package com.techelevator.tenmo.model;

import javax.validation.Valid;
import java.math.BigDecimal;

public class Transaction {

    private int transactionId;

    @Valid
    private int fromAccount;

    @Valid
    private int toUserAccount;
    private BigDecimal transactionAmount;

    public Transaction(int transactionId, int fromAccount, int toUserAccount, BigDecimal transactionAmount) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toUserAccount = toUserAccount; //account_id
        this.transactionAmount = transactionAmount;
    }

    public Transaction(){

    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getToUserAccount() {
        return toUserAccount;
    }

    public void setToUserAccount(int toUserAccount) {
        this.toUserAccount = toUserAccount;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
