package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDAO implements AccountDAO{

    JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate){
    }

    @Override
    public Account getAccountBalance(int userId) {
        Account account = new Account();
        String sql = "SELECT * FROM account WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if(result.next()){
             account =mapRowToAccount(result);
        }
        return account;

    }

    @Override
    public boolean updateBalanceByIncrease(Account account) {
        BigDecimal balance = account.getBalance();
        String sql = "UPDATE account SET balance = balance + ?" +
                " WHERE account_id IN (SELECT to_user_account FROM transaction);";


        boolean result = false;
        int linesReturned =jdbcTemplate.update(sql, account.getAccountId(), account.getUserId(), account.getBalance());
        if(linesReturned == 1){
            result = true;

        }
        return result;

    }


    //we need to add map row to account to get all records, account object.

    private Account mapRowToAccount(SqlRowSet rowSet) {
           Account account = new Account();
           account.setAccountId(rowSet.getInt("account_id"));
           account.setUserId(rowSet.getInt("user_id"));
           account.setBalance(rowSet.getBigDecimal("balance"));
           return account;
    }


}
