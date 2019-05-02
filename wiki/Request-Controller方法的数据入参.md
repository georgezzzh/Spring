## 控制器方法的数据入参

* <a href="#ModelAndView">ModelAndView</a>
* <a href="#ModelAttribute">@ModelAttribute</a>
* <a href="#ModelMap">ModelMap</a>
* <a href="#Map">Map</a>

<h3  id="ModelAndView">1. ModelAndView，使用ModelAndView同时转发视图和模型</h3>

   ```java
      @RequestMapping(value = "/create",method = RequestMethod.POST)
   //需要注意的时，这里的RequestMapping中的值和setViewName中的值应该时相同的，否则会出错
   //将表单值映射到User对象中，User类应该实现get和set方法
       public ModelAndView createUser(User user){
           ModelAndView modelAndView=new ModelAndView();
           modelAndView.addObject("user",user);
           modelAndView.setViewName("user/create");
           return modelAndView;
       }
   ```

   thymeleaf中取出数据

   ```html
   <p th:text="${user.userName}"></p>
   <p th:text="${user.password}"></p>
   <p th:text="${user.realName}"></p>
   ```

<h3 id="ModelAttribute"> 2. @ModelAttribute</h3>

   ```java
   @RequestMapping(value = "/user/create",method = RequestMethod.POST)
   //设置模型的键为"u"
   public String handle(@ModelAttribute("u") User user){
       user.setPassword("123456");
       return "user/create";
   }

   ```
html模板
```html
    <p th:text="${u.userName}"></p>
    <p th:text="${u.password}"></p>
    <p th:text="${u.realName}"></p>
```

<h3 id="ModelMap">3. ModelMap入参</h3>

```java
@RequestMapping(value = "/user/create",method = RequestMethod.POST)
public String handle(ModelMap modelMap){
    //增加ModelMap的属性
    modelMap.addAttribute("character","modelmap");
    return "user/create";
}
```

html模板

```html
<!--直接取character即可-->
<p th:text="${character}"></p>
```

<h3 id="Map"> 4. Map入参</h3>

```java
@RequestMapping(value="/user/map")
public String handle2(Map map){
    map.put("map_k","map_value");
    return "user/create";
}
```
模板取值
```html
<p th:text="${map_k}"></p>
```