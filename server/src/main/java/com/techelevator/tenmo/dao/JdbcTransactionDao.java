package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JdbcTransactionDao implements TransactionDao{

    JdbcTemplate jdbcTemplate;

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

       return getTransaction(transaction_id);
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

private Transaction mapRowToTransaction(SqlRowSet rowSet) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rowSet.getInt("transaction_id"));
        transaction.setFromAccount(rowSet.getInt("from_user_account"));
        transaction.setToUserAccount(rowSet.getInt("to_user_account"));
        transaction.setTransactionAmount(rowSet.getBigDecimal("transaction_amount"));
        return transaction;
}


}
