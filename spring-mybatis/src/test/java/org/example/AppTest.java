package org.example;

import static org.junit.Assert.assertTrue;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.resultMapMapper;
import org.example.mapper.DeptMapper;
import org.example.mapper.UserMapper;
import org.example.pojo.Dept;
import org.example.pojo.DeptCamel;
import org.example.pojo.User;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Unit test for simple App.
 */
@MapperScan("org.example.mapper")

public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        //准备工作
        String resources = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resources);
        System.out.println(resourceAsStream);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true);
        //
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectUser();
        for (User u : users)
            System.out.println(u);
        assertTrue(true);
    }

    @Test
    public void testSpringMybatis() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        List<User> users = userMapper.selectUser();
        System.out.println(users);
    }

    @Test
    public void testSpringMybatis2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
        UserMapper userMapper2 = context.getBean("userMapper2", UserMapper.class);
        List<User> users = userMapper2.selectUser();
        System.out.println(users);
    }

    @Test
    public void pureAnnotationMybatis() throws IOException {
        //准备工作
        String resources = "mybatis-config-annotation.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resources);
        System.out.println(resourceAsStream);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true);
        //获取连接，查询
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDept(2);
        System.out.println("dept" + dept);
    }

    @Test
    public void testSelectAll() throws IOException {
        //准备工作
        String resources = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resources);
        System.out.println(resourceAsStream);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true);
        //
        resultMapMapper mapper = sqlSession.getMapper(resultMapMapper.class);
        List<DeptCamel> depts = mapper.selectAll();
        System.out.println(depts);
        sqlSession.close();
    }


}