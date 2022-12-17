package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.transactionCheck.TransactionCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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

    @Autowired
    private final TransactionCheck check;

    public TransactionController(TransactionCheck check) {
        this.check = check;
    }


    //method handler for transaction
    //specific name!
    @RequestMapping(path = "/send", method = RequestMethod.POST)
    public Transaction createTransaction(@RequestBody Transaction transaction) {
       /*
        int accountId = transaction.getFromAccount();
        Account account = accountDAO.getAccountBalanceByAccountId(accountId);
        BigDecimal currentBalance = account.getBalance();
        if (transaction.getTransactionAmount().compareTo(BigDecimal.ZERO) == 1 &&
                (transaction.getFromAccount() != transaction.getToUserAccount()) &&
                (currentBalance.compareTo(transaction.getTransactionAmount()) >= 0)) {
*/
        if(check.checkTransaction(transaction)){
            accountDAO.addBalance(transaction.getTransactionAmount(), transaction.getToUserAccount());
            accountDAO.substractBalance(transaction.getTransactionAmount(), transaction.getFromAccount());
            Transaction transaction1 = transactionDao.create(transaction);
            return transaction1;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
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
        return  transactionDao.getTransactionsByUserName(principal.getName());
    }

    //7. question.. get specific  transaction with the id

    @RequestMapping(path = "/tenmo/transaction/{id}", method = RequestMethod.GET)
    public Transaction getTransactionById(@PathVariable @Valid int id) {
        Transaction transaction = transactionDao.getTransaction(id);
        if(transaction == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            return transaction;
        }

    }



}