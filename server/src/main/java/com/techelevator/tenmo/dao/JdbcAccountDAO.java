package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class JdbcAccountDAO implements AccountDAO{

    JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate =jdbcTemplate;
    }

    @Override
    public Account getAccountByUserId(int userId) {
        Account account = new Account();
        String sql = "SELECT * FROM account WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if(result.next()){
             account =mapRowToAccount(result);
        }
        return account;

    }

    @Override
    public Account getAccountByAccountId(int accountId) {
        Account account = new Account(); // rather than new Account use it!!
        String sql = "SELECT * FROM account WHERE account_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);
        if(result.next()){
            account =mapRowToAccount(result);
        }
        return account;
    }


    //bigdecimal balance
    @Override
    public BigDecimal getBalance(int accountId) {
        //Account account = new Account();
        BigDecimal balance = BigDecimal.valueOf(0);
        String sql = "SELECT balance from account WHERE account_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql,accountId);
        if(result.next()){
            balance = result.getBigDecimal("balance");
        }
        return balance;
    }


    @Override
    public void addBalance(BigDecimal amount, int accountId) {
        String sql = "UPDATE account SET balance = balance + ? WHERE account_id = ?;";

        jdbcTemplate.update(sql, amount, accountId);
    }

    @Override
      public void substractBalance(BigDecimal amount, int accountId){
      String sql = "UPDATE account SET balance = balance - ? WHERE account_id = ?;";

        jdbcTemplate.update(sql, amount, accountId);
}

    @Override
    public List<Account> getAllAccounts() {
        return null;
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
