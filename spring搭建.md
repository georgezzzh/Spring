## 创建Spring项目
直接用IDEA创建一个Spring项目即可,依赖的Spring包回自动导入.   
## 配置spring.xml文件
该文件必须放在src目录下,否则context找不到;
### Cause of this Exception :
spring.xml is not found by Class Path Xml Application Context.

### Solution :
The spring.xml file must exist in src folder and not under the Project Root folder.
## 基于注解的装配Bean
### 自动扫描Bean
1. 首先在Bean类下声明@Component   
为组件设置不同的ID,语法是@Component("anotherName")
2. 在xxxConfig类下声明@Configuration和@ComponentScan   
按照配置类所在的包作为基础包来扫描组件.  
### 自动装配Bean

1. 按照类型注入@AutoWired
2. 按照名称注入  
@AutoWired
@Qualifer("名称")
3. 按照名称注入
@Resource("名称")  

## 基于Java代码装配Bean
1. 首先创建配置类,xxxConfig类,并在类前面写注解@Configuration
2. 声明简单的Bean   
要在JavaConfig中声明Bean,我们需要编写一个方法,这个方法会创建所需类型的实例,然后给这个方法添加@Bean的注解
    

        @Bean
        public CompactDisc SgtPeppers(){
            return new SgtPeppers();
        }
