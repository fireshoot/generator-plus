#### Generator-plus 

​	generator-plus 是基于mybatis-generator-core的基础上，自定义的一个插件。这个插件生成的逆向工程接口，涵盖所有单表查询的操作，按照某个字段查询、查询后排序、in查询、between 查询等，并且在Service中简单几句就省去了写大量的sql语句。



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

jar目录下的压缩文件

下载后，将压缩文件加压放到：C:\Users\用户\.m2\repository\com 目录下

**配置**

其他配置和mybatis generator 差不多 ，参考：http://mybatis.org/generator/quickstart.html

<font color="red">其中变化如下</font>

​	在mybatis generator 的基础上添加 plugin的插件 `type="com.yangxin.framwork.plugin.DynamicSqlPlugin" `和 javaModelGenerator的父类`value="com.yangxin.framwork.bean.BaseModel"` 即可。

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

表 course_info生成如下：

CourseInfoMapper:

```java
import com.yangxin.framwork.bean.Sort;
import com.yangxin.greatecoder.dao.model.CourseInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CourseInfoMapper extends BaseMapper<CourseInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    CourseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);

    // 以下是 DynamicSqlPlugin 插件动态生成的部分
    List<CourseInfo> getByConditions(@Param("conditions") Map<String, Object> conditions);

    List<CourseInfo> getByConditionList(@Param("conditions") List<Condition> conditions);

    List<CourseInfo> getByIn(@Param("set") Set<Object> set, @Param("field") String field);

    List<CourseInfo> getSortedResultByConditionList(@Param("set") List<Condition> set, @Param("sorter") Sort sorter);

    List<CourseInfo> getSortedResultByConditionLists(@Param("set") List<Condition> set, @Param("sorter") List<Sort> sorter);
}
```



在项目中怎么去使用我们自动生成mapper接口

```java
// 查询学生的id,并且通过创建时间排序。

List<Condition> conditions = new ArrayList<>();
conditions.add(new Condition("id", id));

List<CourseInfo> infos = courseInfoMapper.getSortedResultByConditionList(conditions, new Sort("created_at", "desc"));

```

<font color ='red'> 如果要查询这个表的其他字段，new 一个Conditions就可以了，也可以将多个条件传进去完成查询。</font>

```java
example:

List<Condition> conditionList = new ArrayList<>();
        if (id != null) {
            conditionList.add(new Condition("coupon.id", id));
        }
        if (internalName != null) {
            conditionList.add(new Condition("coupon.internal_name", CONDITION_LOCATE, internalName));
        }
        if (StringUtils.isNotEmpty(couponName)) {
            conditionList.add(new Condition("coupon.coupon_name", CONDITION_LOCATE, couponName));
        }
        if (status != null) {
            if(CouponReleaseStatus.NO.getStatus() == status){
                conditionList.add(new Condition("status", "is", "null"));
            }else {
                conditionList.add(new Condition("status", "is", "not null"));
            }
        }
        if (startTime != null && endTime != null) {
            LocalDateTime startLocalDateTime = DateUtils.dateToLocalDateTime(startTime);
            LocalDateTime endLocalDateTime= DateUtils.dateToLocalDateTime(endTime);
            LocalDateTime start = LocalDateTime.of(startLocalDateTime.getYear(), startLocalDateTime.getMonthValue(), startLocalDateTime.getDayOfMonth(), startLocalDateTime.getHour(), startLocalDateTime.getMinute(),
                    0, 0);
            LocalDateTime end = LocalDateTime.of(endLocalDateTime.getYear(), endLocalDateTime.getMonthValue(), endLocalDateTime.getDayOfMonth(), endLocalDateTime.getHour(), endLocalDateTime.getMinute(),
                    59, 999999999);
            Map<String, String> map = new HashMap<>(2);
            map.put("start", VehicleConstant.sdfSSS.format(start));
            map.put("end", VehicleConstant.sdfSSS.format(end));
            conditionList.add(new Condition("created_at", Constants.CONDITION_BETWEEN, map));
        }
        List<CourseInfo> infos = courseInfoMapper.getByConditionList(conditionList);
        
        
        
        
```

