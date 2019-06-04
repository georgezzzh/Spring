package me.gacl.mapping;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisHelloTest {
    public static void main(String[]args){
        String resource= "conf.xml";
        InputStream is=MybatisHelloTest.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(is);
        SqlSession session=sessionFactory.openSession();
        String statement="me.gacl.mapping.userMapper.getUser";
        User user=session.selectOne(statement,1);
        System.out.println(user);
    }
}
