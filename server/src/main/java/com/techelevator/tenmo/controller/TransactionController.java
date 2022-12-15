package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransactionController {

    @Autowired
    private TransactionDao transactionDao;

    //method handler for transaction
    @RequestMapping(path = "/tenmo", method = RequestMethod.POST )
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionDao.create(transaction);

    }


}
