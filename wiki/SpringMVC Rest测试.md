## SpringMVC Rest测试

1. 测试Rest方法

```java
@Test
public void restTest(){
    RestTemplate restTemplate=new RestTemplate();
    MultiValueMap<String,String>form=new LinkedMultiValueMap<>();
    form.add("birthday","1999-03-11");
    form.add("salary","123,123.00");
    form.add("userName","swiden");
    form.add("password","123456");
    String url2="http://localhost:8080//mvc//user//format";
    String response=restTemplate.postForObject(url2,form,String.class);
    System.out.println(response);
}
```


