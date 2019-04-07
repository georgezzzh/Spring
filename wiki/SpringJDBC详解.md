## 利用JDBCtemplate进行操作CRUD

* 可以显式的指定每个占位符所对应的字段数据类型

  ```java
  String sql="insert into t_forum(forum_name,forum_desc) values(?,?)";
  Object[]params=new Object[]{forum.getForumName(),forum.getForumDesc()};
  jdbcTemplate.update(sql,params,new int[]{Types.VARCHAR,Types.VARCHAR});
  ```

* 使用回调接口执行绑定操作

  不详细介绍了，太过繁复，不如直接放一个参数数组方便。

### 返回插入数据库之后的自增主键值

*书中的建议是通过获得一个UUID进行管理，我也觉得通过自增的主键太2了。*

```java
public void insertUser(){
    final  String sql="insert into user_info(name) values(?)";
    KeyHolder keyHolder=new GeneratedKeyHolder();
    //用回调函数来做，只有这一个API可以获得自增主键
    jdbcTemplate.update(new PreparedStatementCreator() {
        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
            //需要在这里说明要添加返回主键
            PreparedStatement ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,"george");
            return ps;
        }
    },keyHolder);
    int id=keyHolder.getKey().intValue();
    System.out.println("primary key is: "+id);
}
```

*针对复合主键和多行数据的主键会有`Map<String,Object>getKeys(), List<Map<String,Object>>getKeyList();`*方法来调用。

### 批量更新数据

```java
//批量更新数据
public void insertUserBatch(){
    String sql="insert into user_info(name) values(?)";
    List list=new ArrayList();
    list.add(new Object[]{"tim"});
    list.add(new Object[]{"cook"});
    //个人更喜欢用这个List<Object[]>作为参数的方法，要比写接口类要方便一些
    //看起来也不臃肿
    jdbcTemplate.batchUpdate(sql,list);
}
```

### 使用RowCallbackHander处理结果集，相当于用queryForList()

```java
//批量处理select读取出来的结果集
public void getUsers(){
    final String sql="select * from user_info";
    //List myList=jdbcTemplate.queryForList(sql);
    final List myList=new ArrayList();
    //不用写while(rs.next())来自行判断了，不过这个也没有方便多少
    //主要是用于设置一些List<Object>比较方便，一般用queryForList(sql)更方便了
    jdbcTemplate.query(sql, new RowCallbackHandler() {
        public void processRow(ResultSet resultSet) throws SQLException {
            User user=new User();
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getShort("age"));
            myList.add(user);
        }
    });
    System.out.println(myList);
}
```

### 用一张表借助Spring来构建自增主键

```
//自增表的创建
create table t_post_id(sequence_id int);
insert into t_post_id values(0);
```

*context.xml*配置

```xml
<!--利用表配置主键值-->
<bean id="incre" class="org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer">
    <property name="incrementerName" value="t_post_id"/><!--自增表的表名-->
    <property name="columnName" value="sequence_id"/><!--自增表的列名-->
    <property name="cacheSize" value="10"/><!--ID每次缓冲的数目-->
    <property name="dataSource" ref="datasource"/><!--配置数据源-->
</bean>
```

*java中使用*

```java
    @Autowired
    //注入主键值产生器
    private DataFieldMaxValueIncrementer incre;
    public void useAuto_incrementkey(){
    //调用nextIntValue()返回一个主键值
        System.out.println(incre.nextIntValue());
    }
```

### 行集SqlRowSet

查询并返回结果集后，数据库连接就已经断开了，并且结果集的数据已经保存道SqlRowSet中。SqlRowSet会一次性装载所有的匹配数据，而不像ResultSet一样，分批次返回一批数据。