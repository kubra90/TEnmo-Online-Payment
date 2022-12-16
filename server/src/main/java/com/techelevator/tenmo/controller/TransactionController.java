package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.transactionCheck.TransactionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransactionController {

    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private AccountDAO accountDAO;


    //method handler for transaction
    //specific name!
    @RequestMapping(path = "/send", method = RequestMethod.POST)
    public Transaction createTransaction(@RequestBody Transaction transaction) {

        accountDAO.addBalance(transaction.getTransactionAmount(), transaction.getToUserAccount());
        accountDAO.substractBalance(transaction.getTransactionAmount(), transaction.getFromAccount());
        Transaction transaction1 = transactionDao.create(transaction);
        return transaction1;
        }

    // get all users for transactions
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        List<User> allUsers = userDao.findAll();
        for (User user : allUsers) {
            users.add(user.getUsername());
        }
        return users;
    }

    @RequestMapping(path ="/tenmo/transactionsbyuser", method = RequestMethod.GET)
    public List<Transaction> getAllTransactionsByUser(Principal  principal) {
      return transactionDao.getTransactionsByUserId(principal.getName());
    }



    }

