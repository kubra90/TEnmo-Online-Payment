package com.techelevator.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.techelevator.tenmo.dao.JdbcTransactionDao;
import com.techelevator.tenmo.model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

import java.math.BigDecimal;
import java.util.List;

public class JdbcTransactionDaoTests extends BaseDaoTests{

    private static final Transaction TRANSACTION_1 = new Transaction(3001, 2001, 2002, BigDecimal.valueOf(500), "APPROVED");
    private static final Transaction TRANSACTION_2 = new Transaction(3002, 2002, 2003, BigDecimal.valueOf(300), "APPROVED");
    private static final Transaction TRANSACTION_3 = new Transaction(3003, 2003, 2004, BigDecimal.valueOf(1000), "APPROVED");
    private static final Transaction TRANSACTION_4 = new Transaction(3004, 2003, 2001, BigDecimal.valueOf(300), "APPROVED");
    private static final Transaction TRANSACTION_5 = new Transaction(3005, 2001, 2004, BigDecimal.valueOf(800), "APPROVED");

    private JdbcTransactionDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTransactionDao(jdbcTemplate);

    }

    @Test
    public void createNewTransaction(){
        Transaction transaction = new Transaction();
        transaction.setFromAccount(2001);
        transaction.setToUserAccount(2002);
        transaction.setTransactionAmount(BigDecimal.valueOf(500));
        transaction.setStatus("APPROVED");

        Transaction createdTransaction = sut.create(transaction);
        Assert.assertEquals(transaction.getFromAccount(), createdTransaction.getFromAccount());
        Assert.assertEquals(transaction.getToUserAccount(), createdTransaction.getToUserAccount());
        Assert.assertTrue(transaction.getTransactionAmount().compareTo(createdTransaction.getTransactionAmount()) == 0);
        Assert.assertEquals(transaction.getStatus(), createdTransaction.getStatus());

    }

    @Test
    public void getTransactionById(){
        Transaction transaction = sut.getTransaction(3001);
        Transaction transaction1 = sut.getTransaction(3002);

        assertTransactionMatch(TRANSACTION_2, transaction1);
        assertTransactionMatch(TRANSACTION_1, transaction);
    }

    @Test
    public void getTransactionReturnedNullWithoutValidId() {
        Transaction transaction = sut.getTransaction(3006);
        Transaction transaction1 = sut.getTransaction(3015);

        Assert.assertNull(transaction);
        Assert.assertNull(transaction1);
    }

    @Test
    public void getTransactionByUsername(){
        List<Transaction> transactions = sut.getTransactionsByUserName("bob");
        int transactionsNumber = transactions.size();

        List<Transaction> transactions1 = sut.getTransactionsByUserName("user");
        int transactionsNumber1 = transactions1.size();

        Assert.assertEquals(3, transactionsNumber);
        Assert.assertEquals(2, transactionsNumber1);
    }

    @Test
    public void getTransactionByUsernameWithoutValidName(){
        List<Transaction> transactions = sut.getTransactionsByUserName("Bla");
        int transactionsNumber = transactions.size();

        Assert.assertEquals(0, transactionsNumber);
    }


    public void assertTransactionMatch(Transaction expected, Transaction actual){
        Assert.assertEquals(expected.getTransactionId(), actual.getTransactionId());
        Assert.assertEquals(expected.getFromAccount(), actual.getFromAccount());
        Assert.assertEquals(expected.getToUserAccount(), actual.getToUserAccount());
        Assert.assertTrue(expected.getTransactionAmount().compareTo(actual.getTransactionAmount()) == 0);
        Assert.assertEquals(expected.getStatus(), actual.getStatus());
    }

}
