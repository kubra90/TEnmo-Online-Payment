package com.techelevator.dao;


import com.techelevator.tenmo.dao.JdbcAccountDAO;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.net.UnknownServiceException;
import java.util.List;

public class JdbcUserDaoTests extends BaseDaoTests{

    private static final User USER_1 = new User(1001, "bob", "123", "USER");
    private static final User USER_2 = new User(1002, "user", "123", "USER");
    private static final User USER_3 = new User(1003, "nick", "12345", "USER");
    private static final User USER_4 = new User(1004, "daniel", "123456", "USER");

    private JdbcUserDao sut;
    private JdbcAccountDAO sut2;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
        sut2 = new JdbcAccountDAO(jdbcTemplate);
    }

    @Test
    public void createNewUser() {
        boolean userCreated = sut.create("TEST_USER","test_password");
        Assert.assertTrue(userCreated);
        User user = sut.findByUsername("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getUsername());
    }

    @Test
    public void findByUserName(){
        boolean userCreated = sut.create("nicolas", "test_password");
        Assert.assertTrue(userCreated);
        User user = sut.findByUsername("nicolas");
        Assert.assertEquals("nicolas", user.getUsername());

    }



    @Test
    public void findAllUsersBySize(){
        List<User> allUsers = sut.findAll();
        Assert.assertEquals(4, allUsers.size());

    }
    @Test
    public void find_id_by_username(){
        int actualId = sut.findIdByUsername("bob");
        Assert.assertEquals(USER_1.getId(), actualId);
    }

    @Test
    public void create_new_user_with_the_balance(){
        boolean Usercreated  =sut.create("nate", "password");
        Assert.assertTrue(Usercreated);
        User user = sut.findByUsername("nate");
        Account account =sut2.getAccountByUserId(user.getId());
        BigDecimal balance  =account.getBalance();
        Assert.assertTrue(BigDecimal.valueOf(1000).compareTo(balance) == 0);

    }

}
