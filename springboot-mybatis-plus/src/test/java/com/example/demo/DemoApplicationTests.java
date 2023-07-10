package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private UserDao userDao;
	@Test
	void testGetAll() {
		List<User> userList = userDao.selectList(null);
		System.out.println("userList");
		System.out.println(userList);
	}

}
