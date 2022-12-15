package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.transactionCheck.transactionCheck;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;


@Component
public class JdbcTransactionDao implements TransactionDao{

    JdbcTemplate jdbcTemplate;

    private AccountDAO accountDAO;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Transaction create(Transaction transaction) {
        Transaction transaction1 = new Transaction();
        String sql = "INSERT INTO transaction(from_user_account, to_user_account," +
                " transaction_amount) VALUES (?, ?, ?) RETURNING transaction_id;";

        Integer transaction_id = jdbcTemplate.queryForObject(sql, Integer.class, transaction.getFromAccount(), transaction.getToUserAccount(),
                transaction.getTransactionAmount());
        //check transaction from the account to to_account to be different, not the same.
         if(getTransaction(transaction_id).getFromAccount() != getTransaction(transaction_id).getToUserAccount()){
            return getTransaction(transaction_id);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");
        }

    }

    @Override
    public void update(Transaction updatedTransaction) {

        String sql = "UPDATE account SET balance = ?"+
                " WHERE account_id IN (Select to_user_account from transaction);";

        jdbcTemplate.update(sql,updatedTransaction.getToUserAccount(), updatedTransaction.getFromAccount(), updatedTransaction.getTransactionAmount());
    }

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


    //update transaction
    //@Override
    //public Transaction updateReceiverBalance(Transaction transaction) {

        //add receiver account balance increase by the amount of transaction.
        //with this query, receiver's account...
        //call performTransaction here



    //}



    //@Override
    //public List<Transaction> getAllTransaction(int transactionId) {
      //  return null;
    //}

    private Transaction mapRowToTransaction(SqlRowSet rowSet) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rowSet.getInt("transaction_id"));
        transaction.setFromAccount(rowSet.getInt("from_user_account"));
        transaction.setToUserAccount(rowSet.getInt("to_user_account"));
        transaction.setTransactionAmount(rowSet.getBigDecimal("transaction_amount"));
        return transaction;
}


}
