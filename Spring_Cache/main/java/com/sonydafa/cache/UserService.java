package com.sonydafa.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service("userServiceBean")
public class UserService {
    private Map<String,User> userMap=new HashMap<>();
    {
        userMap.put("12345",new User("12345","george"));
        userMap.put("12346",new User("12346","flow"));
    }
    @Autowired
    private CacheManager cacheManager;
    @PostConstruct
    public void setUp(){
        Cache usersCache=cacheManager.getCache("users");
        for(String key:userMap.keySet()){
            usersCache.put(key,userMap.get(key));
        }
    }
    @Cacheable(value= "users")
    public User getUserById(String userid){
            return getFromDB(userid);
    }
    @CachePut(value = "users")
    public User getUpdateUserById(String userid){
        System.out.println("Update Cache....");
        return getFromDB(userid);
    }
    @CacheEvict(value = "users")
    public void removeUser(String userid){
        //
        System.out.println("remove cache:"+userid);
    }
    private User getFromDB(String userId){
        System.out.println("execute to query...hashcode is:");
        User user= new User();
        user.setUserId(userId);
        user.setAge((int)(Math.random()*100));
        return user;
    }

}