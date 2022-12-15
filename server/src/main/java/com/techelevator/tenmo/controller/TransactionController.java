package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.transactionCheck.transactionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransactionController {

    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private UserDao userDao;
    private transactionCheck transactionCheck;

    //method handler for transaction
    @RequestMapping(path = "/tenmo", method = RequestMethod.POST )
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionDao.create(transaction);

    }

    // get all users for transactions
    @RequestMapping(path ="/tenmo", method =RequestMethod.GET)
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        List<User> allUsers = userDao.findAll();
        for(User user : allUsers){
           users.add(user.getUsername());
        }
        return  users;
    }
    //update balance
    @RequestMapping(path ="/tenmo", method = RequestMethod.PUT)
    public void updateBalance(@RequestBody Transaction transaction){
        transactionCheck.performTransaction(transaction);
        updateBalance(transaction);
    }




}
