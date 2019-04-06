package com.sonydafa.service.impl;

import com.sonydafa.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class TransferTest {
    @Autowired
    private IAccountService accountService;
    @Test
    public void test01(){
        accountService.transfer("tom","jack",1000);
    }
}
