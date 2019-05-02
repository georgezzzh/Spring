## SpringMVC-RequestMapping


### @RequestMapping注解

RequestMapping, value参数支持Ant风格(?,*和**字符)

| 模式 | 匹配规则                 | 举例                 | 含义                                                |
| ---- | ------------------------ | -------------------- | --------------------------------------------------- |
| ?    | 匹配文件名中的任一个字符 | /user/*/createUser   | 匹配/user/aaa/createUser，/user/bbb/createUser等URL |
| *    | 匹配文件名中的任意字符   | /user/**/createUser, | 匹配/user/createUser, /user/aaa/bbb/createUser等URL |
| **   | 匹配多层路径             | /user/createUser??   | 匹配/user/createUseraa, /user/createUserbb等URL     |

3. RequestMapping支持占位符

   ```java
      @RequestMapping(value="/pathvariable/{id}",method = RequestMethod.POST)
   	//此处用@ResponseBody时指仅仅发回相应体，而不是视图
       public @ResponseBody String getid(@PathVariable int id){
           Map m=new HashMap();
           m.put("id",id);
           String json=new Gson().toJson(m);
           return json;
       }
   ```

## RequestMapping对应的方法的入参

### 1.@RequestParam入参
@RequestParam有三个参数,
* value， 参数名

* required，是否必须，默认为true，表示是请求中必须包含对应的参数，如果不包含则抛出异常

* defaultValue，默认参数值，在设置该参数时，自动将required设置为false

  ```java
  @RequestMapping(path="/handle")
  //其中name属性不是必须的
  public @ResponseBody String handle(@RequestParam int id,
                                         @RequestParam(required = false,defaultValue = "george")String name){
      Map map=new HashMap();
      map.put("id",id);
      map.put("name",name);
      return new Gson().toJson(map);
  }
  ```

### 2. @CookieValue绑定请求中的Cookie值

与@RequestParam类似

### 3. @RequestHeader绑定请求报文的属性值

与@RequestParam类似

### 4. 使用表单对象绑定请求参数值

```java
//将表单值映射到User对象中，User类应该实现get和set方法
    public ModelAndView createUser(User user){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("user/create");
        return modelAndView;
    }
```

### 5. 使用Servlet API对象入参

```java
public void handle(HttpServletRequest request,HttpServletResponse response);
```

如果处理方法自行使用HttpServletResponse返回响应，则处理方法的返回值设置为void即可。
