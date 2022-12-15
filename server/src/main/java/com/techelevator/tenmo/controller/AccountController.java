package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {


    @Autowired // no need constructor because of autowired
    private AccountDAO accountDao;
    @Autowired
    private UserDao userDao;


    //all method handlers
    //As an authenticated user of the system, I need to be able to see my Account Balance.

    @RequestMapping(path = "/tenmo/balance", method = RequestMethod.GET)
    public Account getAccountBalance(Principal principal) {
        Account account;
        User user = userDao.findByUsername(principal.getName());
        int userId = user.getId();
        account = accountDao.getAccountBalance(userId);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found!");
        }
        return account;
    }
}
