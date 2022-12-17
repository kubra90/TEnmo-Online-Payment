package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcTransactionDao implements TransactionDao{

    JdbcTemplate jdbcTemplate;


    @Autowired // the object, otherwise constructor needed
    private AccountDAO accountDAO;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Transaction create(Transaction transaction) {

        //Transaction transaction1 = new Transaction();
        String sql = "INSERT INTO transaction(from_user_account, to_user_account," +
                " transaction_amount, transaction_status) VALUES (?, ?, ?, ?) RETURNING transaction_id;";

        Integer transaction_id = jdbcTemplate.queryForObject(sql, Integer.class, transaction.getFromAccount(), transaction.getToUserAccount(),
                transaction.getTransactionAmount(), transaction.getStatus());


            return getTransaction(transaction_id);
        }
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");



    @Override
    public Transaction getTransaction(int transactionId) {

        Transaction transaction = null;
        String sql = "SELECT * from transaction WHERE transaction_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transactionId);
        if(result.next()){
            transaction = mapRowToTransaction(result);
        }
        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByUserName(String userName) {

        List<Transaction> transactions = new ArrayList<>();

        // get transaction of receiver
        String sql = " SELECT * from transaction "+
                " JOIN account ON account.account_id = transaction.to_user_account "+
                " JOIN tenmo_user as tu ON tu.user_id = account.user_id "+
                "WHERE tu.username = ?;";

        SqlRowSet result=jdbcTemplate.queryForRowSet(sql, userName);

        while(result.next()){
            Transaction transaction =mapRowToTransaction(result);
            transactions.add(transaction);
        }

        // get transaction of sender
        String sql2 = " SELECT * from transaction "+
                " JOIN account ON account.account_id = transaction.from_user_account "+
                " JOIN tenmo_user as tu ON tu.user_id = account.user_id "+
                "WHERE tu.username = ?;";

        SqlRowSet resultNew=jdbcTemplate.queryForRowSet(sql2, userName);

        while(resultNew.next()){
            Transaction transaction =mapRowToTransaction(resultNew);
            transactions.add(transaction);
        }
        return transactions;

    }





    private Transaction mapRowToTransaction(SqlRowSet rowSet) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rowSet.getInt("transaction_id"));
        transaction.setFromAccount(rowSet.getInt("from_user_account"));
        transaction.setToUserAccount(rowSet.getInt("to_user_account"));
        transaction.setTransactionAmount(rowSet.getBigDecimal("transaction_amount"));
        transaction.setStatus(rowSet.getString("transaction_status"));
        return transaction;
    }


}