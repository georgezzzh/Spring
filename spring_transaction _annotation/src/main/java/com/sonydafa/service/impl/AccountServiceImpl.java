package com.sonydafa.service.impl;

import com.sonydafa.dao.impl.AccountDaoImpl;
import com.sonydafa.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountDaoImpl accountDao;
    @Transactional
    public void transfer(String outName, String inName, double money) {
        accountDao.out(outName,money);
        int i=1/0;
        accountDao.in(inName,money);
    }
    @Transactional(readOnly = true)
    public int find(){
        return 0;
    }
}
