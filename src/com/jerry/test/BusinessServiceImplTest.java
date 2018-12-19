package com.jerry.test;

import com.jerry.domain.Customer;
import com.jerry.exception.IdIsNullException;
import com.jerry.service.BusinessService;
import com.jerry.service.impl.BusinessServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class BusinessServiceImplTest {
    private BusinessService s = new BusinessServiceImpl();

    @Test
    public void testSaveCustomer() {
        Customer c = new Customer();
        c.setName("zhoujielun1");
        c.setBirthday(new Date());
        c.setGender("male");
        c.setPhonenum("18617151926");
        c.setEmail("jay@sina.com");
        c.setHobby("吃饭,睡觉,学java");
        c.setType("VVIP");
        c.setDescription("歌手一个");
        s.saveCustomer(c);
    }

    @Test
    public void testFindAllCustomers() {
        List<Customer> cs = s.findAllCustomers();
        assertEquals(1, cs.size());
    }

    @Test(expected = com.jerry.exception.IdIsNullException.class)
    public void testUpdateCustomer() throws IdIsNullException {
        Customer c = new Customer();
        s.updateCustomer(c);
    }

    @Test
    public void testFindCustomerById() {
        Customer c = s.findCustomerById("FVID3358J5DV41KFU8LGJHKTD4ZU8PCC");
        System.out.println(c.getName());
        assertNotNull(c);
    }

    @Test
    public void testUpdateCustomer1() throws IdIsNullException {
        Customer c = s.findCustomerById("FVID3358J5DV41KFU8LGJHKTD4ZU8PCC");
        c.setPhonenum("110");
        s.updateCustomer(c);
    }

    @Test
    public void testDeleteCustomerById() {
        s.deleteCustomerById("OUNI8498FU6YKOO94KJ0NRIR0SIO8BSI");
    }
}
