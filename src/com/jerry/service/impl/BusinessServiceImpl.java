package com.jerry.service.impl;

import com.jerry.dao.CustomerDao;
import com.jerry.dao.impl.CustomerDaoImpl;
import com.jerry.domain.Customer;
import com.jerry.exception.IdIsNullException;
import com.jerry.service.BusinessService;
import com.jerry.util.IdGenertor;

import java.util.List;

public class BusinessServiceImpl implements BusinessService {
    private CustomerDao dao = new CustomerDaoImpl();

    public List<Customer> findAllCustomers() {
        return dao.findAll();
    }

    public void saveCustomer(Customer c) {
        if (c == null)
            throw new IllegalArgumentException("参数不全");
        c.setId(IdGenertor.genGUID());
        dao.save(c);
    }

    public Customer findCustomerById(String customerId) {
        return dao.findById(customerId);
    }

    public void updateCustomer(Customer c) throws IdIsNullException {
        if (c == null)
            throw new IllegalArgumentException("参数不全");
        if (c.getId() == null || c.getId().trim().equals("")) {
            throw new IdIsNullException("更新的客户信息，id不能为空");
        }
        dao.update(c);
    }

    public void deleteCustomerById(String customerId) {
        if (customerId == null || customerId.trim().equals("")) {
            throw new IllegalArgumentException("客户的id不能为空");
        }
        dao.delete(customerId);
    }
}
