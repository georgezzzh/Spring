## SpringMVC使用ThymeLeaf模板配置

```xml
<bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver"
p:templateEngine-ref="templateEngine"/>
<bean id="templateEngine" class="org.thymeleaf.spring5.SpringTemplateEngine"
p:templateResolver-ref="templateResolver"/>
<bean id="templateResolver" class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver"
p:prefix="/WEB-INF/templates/"
p:suffix=".html"
p:templateMode="HTML5"/>
```

这里要注意的时第三个bean配置的时候是，SpringResourceTemplateResolver,不是*Spring in Action*中的ServletContextTemplateResolver，这个是个抽象类，不能实例化。   

感慨一下，Spring In Action中错误太多了。

