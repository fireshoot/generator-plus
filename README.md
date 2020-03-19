#### Generator-plus 

​	generator-plus 是基于mybatis-generator-core的基础上，自定义的一个插件。官方的使用方式：http://mybatis.org/generator/quickstart.html。



#### 怎么去使用

**引入jar包**

```xml
<!-- 引入这个就不用引入mybatis-generator的jar包了-->
<dependency>
            <groupId>com.yangxin.generatorplus</groupId>
            <artifactId>generator-plus</artifactId>
            <version>1.0.0</version>
 </dependency>
```

<font color ="red">因为我还没有发到Maven的中央仓库，所以会找不到。jar包下载地址：</font>







​	在mybatis generator 的基础上添加 plugin的插件 和 javaModelGenerator的父类配置即可。

```xml
 <plugin type="com.yangxin.framwork.plugin.DynamicSqlPlugin"/>

<!-- 设置Java类生成的位置 -->
<javaModelGenerator ....>
      ......
      <property name="rootClass" value="com.yangxin.framwork.bean.BaseModel"/>
</javaModelGenerator>

详细配置见附录。
```



#### 生成的效果：

```

```

