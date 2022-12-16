package com.techelevator.tenmo.model;

import javax.validation.Valid;
import java.math.BigDecimal;

public class Transaction {

    @Valid
    private int transactionId;

    @Valid
    private int fromAccount;

    @Valid
    private int toUserAccount;
    private BigDecimal transactionAmount;


    private String status = "APPROVED";

    public Transaction(int transactionId, int fromAccount, int toUserAccount, BigDecimal transactionAmount, String status) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toUserAccount = toUserAccount;
        this.transactionAmount = transactionAmount;
        this.status = status;
    }

    public Transaction(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
