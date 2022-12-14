package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {


    @Autowired // no need constructor because of autowired
    private AccountDAO accountDao;
    @Autowired
    private UserDao userDao;


    //all method handlers
    //As an authenticated user of the system, I need to be able to see my Account Balance.

    @RequestMapping(path= "/tenmo/account", method= RequestMethod.GET)
    public Account getAccountBalance(int userId){

        //who is the user?
        User user = new User();
        userId = user.getId();
        Account account = accountDao.getAccountBalance(userId);
        return account;
        //accountid sensitive info.
        //add throw new responseStatusException, NOT FOUND, if-else statement
    }
}
