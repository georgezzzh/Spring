## Spring MVC响应Excel表格视图

1. maven依赖

   ```xml
   <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
   <dependency>
     <groupId>org.apache.poi</groupId>
     <artifactId>poi</artifactId>
     <version>3.17</version>
   </dependency>
   <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
   <dependency>
     <groupId>org.apache.poi</groupId>
     <artifactId>poi-ooxml</artifactId>
     <version>3.17</version>
   </dependency>
   ```

2. dispatcher-servlet.xml 配置

   ```xml
      <!--自定义的视图解析器-->
       <bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
           <!--order表示优先级，数字越小，有衔接越高-->
           <property name="order" value="10"/>
       </bean>
   		<!--自定义的视图解析器-->
       <bean id="ExcelViewer" class="com.sonydafa.mvc.ExcelView"/>
   ```

3. 视图类

   ```java
   //创建2007之后的xlsx文件需要实现AbstractXlsxView类
   //创建1997-2003的xls文件，实现AbstratcXlsView类
   public class ExcelView extends AbstractXlsxView{
       @Override
       protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse)throws Exception {
           httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                   "attachment;filename="+new String("userList.xlsx".getBytes(),"utf-8"));
           httpServletResponse.setContentType("application/octet-stream;charset=UTF-8");
           httpServletResponse.setCharacterEncoding("utf-8");
           @SuppressWarnings("unchecked")
           List<User>resultList=(List<User>) map.get("userList");
           //创建sheet
           Sheet sheet=workbook.createSheet("userList");
           //创建sheet的头部
           Row header=sheet.createRow(0);
           header.createCell(0).setCellValue("username");
           header.createCell(1).setCellValue("password");
           header.createCell(2).setCellValue("salary");
           int rowIndex=1;
           //创建sheet的主体
           for(User tmp:resultList){
               Row rowItem=sheet.createRow(rowIndex++);
               rowItem.createCell(0).setCellValue(tmp.getUserName());
               rowItem.createCell(1).setCellValue(tmp.getPassword());
               rowItem.createCell(2).setCellValue(tmp.getSalary());
           }
       }
   ```

4. Controller类

   ```java
   @RequestMapping("/user/excel")
   public String showExcel(ModelMap modelMap){
       User user1=new User();
       user1.setUserName("george");
       user1.setPassword("123456");
       user1.setSalary(100);
       List<User> list=new ArrayList<>();
       list.add(user1);
       modelMap.addAttribute("userList",list);
       //返回视图的id, Spring找不到对应的html后缀解析的thymeleaf视图，会找其他的视图
       return "ExcelViewer";
   }
   ```

   