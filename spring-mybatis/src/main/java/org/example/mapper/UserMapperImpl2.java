package org.example.mapper;

import org.apache.ibatis.session.SqlSession;
import org.example.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> selectUser() {
        SqlSession sqlSession = getSqlSession();
        List<User> users = sqlSession.getMapper(UserMapper.class).selectUser();
        return users;
    }
}
