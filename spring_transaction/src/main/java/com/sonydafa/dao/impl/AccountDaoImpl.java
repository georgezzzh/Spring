package com.sonydafa.dao.impl;

import com.sonydafa.dao.Iaccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements Iaccount {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void out(String outName, double money) {
        String sql="update t_account set money=money-? where name=?";
        jdbcTemplate.update(sql,money,outName);
    }

    public void in(String inName, double money) {
        String sql="update t_account set money=money+? where name=?";
        jdbcTemplate.update(sql,money,inName);
    }
}
