package com.sonydafa.service.impl;

import com.sonydafa.dao.impl.AccountDaoImpl;
import com.sonydafa.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountDaoImpl accountDao;
    public void transfer(String outName, String inName, double money) {
        accountDao.out(outName,money);
        int i=1/0;
        accountDao.in(inName,money);
    }
}
