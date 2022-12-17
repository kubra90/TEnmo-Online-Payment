package com.techelevator.dao;


import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.net.UnknownServiceException;
import java.util.List;

public class JdbcUserDaoTests extends BaseDaoTests{

    private static final User USER_1 = new User(1001, "bob", "123", "USER");
    private static final User USER_2 = new User(1002, "user", "123", "USER");
    private static final User USER_3 = new User(1003, "nick", "12345", "USER");
    private static final User USER_4 = new User(1004, "daniel", "123456", "USER");

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
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
        Assert.assertEquals(1001, actualId);
    }

}
