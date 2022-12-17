package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDAO;
import com.techelevator.tenmo.dao.JdbcTransactionDao;
import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcAccountDaoTests extends BaseDaoTests{

    private static final Account ACCOUNT_1 = new Account(2001, 1001, BigDecimal.valueOf(1000));
    private static final Account ACCOUNT_2 = new Account(2002, 1002, BigDecimal.valueOf(1500));
    private static final Account ACCOUNT_3 = new Account(2003, 1003, BigDecimal.valueOf(1400));
    private static final Account ACCOUNT_4 = new Account(2004, 1004, BigDecimal.valueOf(600));

    private JdbcAccountDAO sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccountDAO(jdbcTemplate);
    }

    @Test
    public void getAccountBalanceByUserId(){
        Account account1 = sut.getAccountBalance(1001);
        Account account2 = sut.getAccountBalance(1002);

        Assert.assertTrue(ACCOUNT_1.getBalance().compareTo(account1.getBalance()) == 0);
        Assert.assertTrue(ACCOUNT_2.getBalance().compareTo(account2.getBalance()) == 0);
    }

    @Test
    public void getAccountBalanceWithoutValidUserId(){
        Account account1 = sut.getAccountBalance(1006);

        Assert.assertNull(account1.getBalance());
    }

    @Test
    public void getAccountBalanceByAccountId(){
        Account account1 = sut.getAccountBalanceByAccountId(2003);
        Account account2 = sut.getAccountBalanceByAccountId(2004);

        Assert.assertTrue(ACCOUNT_3.getBalance().compareTo(account1.getBalance()) == 0);
        Assert.assertTrue(ACCOUNT_4.getBalance().compareTo(account2.getBalance()) == 0);
    }

    @Test
    public void getAccountBalanceByWithoutValidAccountId(){
        Account account1 = sut.getAccountBalanceByAccountId(2006);

        Assert.assertNull(account1.getBalance());
    }

    @Test
    public void addBalanceWithCorrectAmountAndId(){
        //Account account1 = new Account();
        //account1.setBalance(BigDecimal.valueOf(600));
        //account1.setAccountId(2004);

        sut.addBalance(BigDecimal.valueOf(1000), 2004);
        BigDecimal amount = sut.getBalance(2004);

        Assert.assertTrue(BigDecimal.valueOf(1600).compareTo(amount) == 0);
    }

    public void assertAccountMatch(Account expected, Account actual){
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertTrue(expected.getBalance().compareTo(actual.getBalance()) == 0);
    }
}
