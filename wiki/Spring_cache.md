## Spring cache

*实例参见[源码](<https://github.com/georgezhou314/Spring/tree/master/Spring_Cache>)*

1. 在需要缓存的方法上标注`@Cacheable`

    ```java
    @Service("userServiceBean")
    public class UserService {
        //标注缓存，Spring会先从users缓存中查询匹配的缓存对象，如果存在则直接返回
        //如果不存在缓存，则执行方法内的逻辑
        @Cacheable(cacheNames = "users")
        public User getUserById(String userid){
                return getFromDB(userid);
        }
        private User getFromDB(String userId){
            System.out.println("execute to query..."+userId);
            return new User();
        }
    }
    ```

2. 在xml中配置缓存

   ```xml
   <!--启动自动扫描注解-->    
   <context:component-scan base-package="com.sonydafa.cache"/>
   <!--开启基于注解的缓存驱动-->
   <cache:annotation-driven/>
   <!--缓存管理器-->
   <bean id="cacheManager" class="org.springframework.cache.support.SimpleCacheManager">
   <!--配置缓存-->
       <property name="caches">
       <set>
           <bean class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean">
               <property name="name" value="users"/>
           </bean>
       </set>
   </property>
   </bean>
   ```

   

### @Cacheable

@Cacheable是最主要的注解，它指定了被注解的返回值是可被缓存的，其工作原理是Spring首先在缓存中查找数据，如果没有则执行方法并缓存结果，然后返回数据，缓存名是必须要提供的。可以用引号，value和cacheName来定义名称。
* cacheNames | value 

  指定缓存名，必须定义至少一个

* key

  不显式指定，就用方法中所有参数来做key，如果指定就要按照SpEL来编写。

* condition

  缓存的条件，通过SpELl来指定条件，返回true或者false

* unless 

  排除某些不希望缓存的对象，与condition作用相反

  ```java
  public class UserService {
      //指定缓存，缓存名为users，缓存key为userid的hashCode()
      //当userid.hashCode()<1000的情况，开启缓存
      @Cacheable(value= "users",key = "#userid.hashCode()",condition = "#userid.hashCode()<1000")
      public User getUserById(String userid){
              return getFromDB(userid);
      }
      private User getFromDB(String userId){
          System.out.println("execute to query...hashcode is:"+userId.hashCode());
          return new User();
      }
  }
  ```

### @CachePut

@CachePut与@Cacheable功能效果几乎一致，它首先执行方法，然后将返回值放入缓存，当希望使用方法返回值来更新缓存时，便可以选用这种方法。

```jAVA
@CachePut(value = "users")
public User getUpdateUserById(String userid){
    System.out.println("Update Cache....");
    return getFromDB(userid);
}
```

*在同一个方法内不能同时使用@CachePut和@Cacheable注解*

### @CacheEvict

@CacheEvict注解是@Cacheable注解的反向操作，它负责从给定的缓存中移除一个值

* allEntries属性

  是布尔类型的，用来表示是否需要清除缓存中的所有元素，默认为false。当指定allEntries为true时，Spring Cache将忽略指定的key，清除缓存中的所有内容。

* beforeInvocation属性

  清除操作默认时hi对应方法执行成功之后触发的，即如果方法抛出异常而未能成功则不会触发清除操作。当指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素。

```java
@CacheEvict(value = "users")
public void removeUser(String userid){
    //
    System.out.println("remove cache:"+userid);
}
```

### 缓存管理器

CompositeCacheManager定义将多个缓存管理器定义组合一起

```xml
<bean id="cacheManager" class="org.springframework.cache.support.CompositeCacheManager">
<property name="cacheManager">
    <list>
        <!--定义Spring自带的SimpleCacheManager-->
        <bean class="rog.springframework.cache.support.SimpleCacheManager">
            <property name="caches">
            <set>
                <bean id="members" class="org.springframework.cacche.concurrent.ConcurrentMapCaccheFactoryBean"></bean>
            </set>
            </property>
        </bean>
        <!--定义其他的缓存器-->
        <bean class="com.hazelcast.spring.cache.HazecastCacheManager">
        	<constructor-arg ref="hazelcast"></constructor-arg>
        </bean>
    </list>
    </property>
</bean>
```

### 初始化缓存

首先访问缓存管理器，然后将数据手工加载到缓存中，这些缓存根据名称进行区分。

@PostConstruct在构造之后进行初始化缓存，这个注解在javaee-8.0 api中

```java
private Map<String,User> userMap=new HashMap<>();
{
    userMap.put("12345",new User("12345","george"));
    userMap.put("12346",new User("12346","flow"));
}
//注入缓存管理器
@Autowired
private CacheManager cacheManager;
//在构造之后进行初始化缓存
@PostConstruct
public void setUp(){
    Cache usersCache=cacheManager.getCache("users");
    for(String key:userMap.keySet()){
        usersCache.put(key,userMap.get(key));
    }
}
```

### 基于XML的Cache声明

详见*精通Spring4.x企业应用开发实战 P506*

