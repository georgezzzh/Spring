package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.domain.query.UserQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private UserDao userDao;
	//查找所有
	@Test
	void testGetAll() {
		List<User> userList = userDao.selectList(null);
		System.out.println("userList");
		System.out.println(userList);

	}

	@Test
	//增加
	void testSave(){
		User user = new User();
		user.setName("george");
		user.setId(5L);
		user.setAge(18);
		user.setTel("1305090");
		user.setPassword("123456");
		userDao.insert(user);
	}
	//删除
	@Test
	void testDeleteData(){
		userDao.deleteById(1L);
	}
	//修改
	@Test
	void testUpdate(){
		User user = new User();
		user.setName("george");
		user.setId(5L);
		user.setAge(8);
		//只修改提供的字段，如果某个字段为null则不修改
		userDao.updateById(user);
	}
	//根据Id查找
	@Test
	void testGetById(){
		User user = userDao.selectById(2L);
		System.out.println(user);
	}
	//按照分页查，必须先配置一个分页拦截器，如本代码的MpConfig类
	@Test
	void testGetByPage(){
		//取第1页，取2条
		IPage<User> page = new Page<User>(1, 2);
		userDao.selectPage(page, null);
		System.out.println("当前页码值:"+page.getCurrent());
		System.out.println("每页显示数:"+page.getSize());
		System.out.println("一共有几页:"+page.getPages());
		System.out.println("一共有多少条数据:"+page.getTotal());
		System.out.println("数据:"+page.getRecords());
	}
	//按照条件查询
	@Test
	void testQueryByWrapper(){
		/* 方式一：直接用变量查询
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.lt("age", 5);
		List<User> users = userDao.selectList(wrapper);
		System.out.println(users);

		 */
		/* 方式二：使用lambda条件查询
		QueryWrapper<User> wrapper = new QueryWrapper<User>();
		wrapper.lambda().lt(User::getAge, 5);
		List<User> users = userDao.selectList(wrapper);
		System.out.println(users);

		 */
		//方式三：直接使用Lambda条件查询
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		//两个条件 and
		wrapper.lt(User::getAge, 10).gt(User::getAge, 3);
		List<User> users = userDao.selectList(wrapper);
		System.out.println(users);
		//两个条件 or, 既大于10或者小于3的条件
		wrapper.lt(User::getAge, 4).or().gt(User::getAge, 7);
		System.out.println(userDao.selectList(wrapper));
	}

	@Test
	void testConditionQuery(){
		UserQuery userQuery = new UserQuery();
		//userQuery.setAge(2);
		userQuery.setAgeUpper(5);
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		//lt(boolean, column, val) 如果第一个参数为true，则连接条件，否则不用
		wrapper.lt(null!=userQuery.getAge(), User::getAge,userQuery.getAge()).gt(User::getAge, userQuery.getAgeUpper());
		List<User> users = userDao.selectList(wrapper);
		System.out.println(users);
	}
	//查询投影，只查某几个字段
	@Test
	void testQueryProjection(){
		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		//select方法，只查询给出的几个字段 相当于 SELECT id name FROM USER;
		wrapper = wrapper.select(User::getId, User::getName);
		List<User> users = userDao.selectList(wrapper);
		System.out.println(users);

		//selectMaps，将结果保存成map
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper = queryWrapper.select("count(*) as count, age");
		//根据age分组
		queryWrapper = queryWrapper.groupBy("age");
		List<Map<String, Object>> maps = userDao.selectMaps(queryWrapper);
		//maps:[{count=2, age=4}, {count=1, age=6}, {count=1, age=8}]
		System.out.println("maps:"+maps);
	}

}
