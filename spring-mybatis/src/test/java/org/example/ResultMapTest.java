package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.resultMapMapper;
import org.example.pojo.DeptCamel;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ResultMapTest {
    @Test
    public void testResultMap() throws IOException {
        //准备工作
        String resources = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resources);
        System.out.println(resourceAsStream);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true);
        //测试ResultMap结果集映射
        resultMapMapper mapper = sqlSession.getMapper(resultMapMapper.class);
        List<DeptCamel> depts = mapper.selectAll();
        System.out.println(depts);
        sqlSession.close();
    }
}
