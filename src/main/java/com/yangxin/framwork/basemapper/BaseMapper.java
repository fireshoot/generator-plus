package com.yangxin.framwork.basemapper;


import com.yangxin.framwork.bean.BaseModel;
import com.yangxin.framwork.bean.Condition;
import com.yangxin.framwork.bean.Sort;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
* @Description:  基本数据库操作统一mapper
* @Author:         yang xin
* @CreateDate:     2020/3/17 21:27
*/
public interface BaseMapper<T extends BaseModel> {

    /**
     * 方法实现说明 : 逆向工程生成文件
     * @return 0
     * @param id
     * @author      yangxin
     * @date        2020/3/17 21:19
    */
    int deleteByPrimaryKey(Integer id);

    /**
     * 方法实现说明 : 逆向工程生成文件
     * @return 0
     * @param record
     * @author      yangxin
     * @date        2020/3/17 21:19
     */
    int insert(T record);

    /**
     * 方法实现说明 : 逆向工程生成文件
     * @return 0
     * @param record
     * @author      yangxin
     * @date        2020/3/17 21:19
     */
    int insertSelective(T record);

    /**
     * 方法实现说明 : 逆向工程生成文件
     * @return 0
     * @param id
     * @author      yangxin
     * @date        2020/3/17 21:19
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 方法实现说明 : 逆向工程生成文件
     * @return 0
     * @param record
     * @author      yangxin
     * @date        2020/3/17 21:19
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 方法实现说明 : 逆向工程生成文件
     * @return 0
     * @param record
     * @Description: d
     * @author      yangxin
     * @date        2020/3/17 21:19
     */
    int updateByPrimaryKey(T record);

    /**
     * 方法实现说明
     * @param conditions  map 结构的查询条件、
     * @return 返回一个对应 单表结构的pojo List
     * @author      yangxin
     * @date        2020/3/17 21:21
    */
    List<T> getByConditions(@Param("conditions") Map<String, Object> conditions);

    /**
     * 方法实现说明
     * @param conditions  List 结构的查询条件、
     * @return 返回一个对应 单表结构的pojo List
     * @author      yangxin
     * @date        2020/3/17 21:21
     */
    List<T> getByConditionList(@Param("conditions") List<Condition> conditions);

    /**
     * 方法实现说明
     * @param conditions  List 结构的查询条件、
     * @param sort  查询后根据这个字段排序(单个字段排序)
     * @return 返回一个对应 单表结构的pojo List
     * @author      yangxin
     * @date        2020/3/17 21:21
     */
    List<T> getSortedResultByConditionList(@Param("conditions") List<Condition> conditions,
                                           @Param("sorter") Sort sort);

    /**
     * 方法实现说明
     * @param conditions  查询条件 List类型
     * @param sort 排序条件 List类型，可以根据多个字段排序，优先级按照先后顺序
     * @used
     *
     * <choose>
     *       <when test="sorter !=null and sorter.size() > 0">
     *         order by
     *         <foreach item="item" collection="sorter"
     *                  open="" separator="," close="">
     *           ${item.field} ${item.sortType}
     *         </foreach>
     *       </when>
     * </choose>
     * @return List
     * @author      yangxin
     * @date        2020/3/17 21:23
    */
    List<T> getSortedResultByConditionLists(@Param("conditions") List<Condition> conditions,
                                            @Param("sorter") List<Sort> sort);

    /**
     * 方法实现说明 ： 单独的IN操作
     * @param field  表字段名、
     * @param set  一个集合
     * @return 返回一个对应 单表结构的pojo List
     * @author      yangxin
     * @date        2020/3/17 21:21
     */
    List<T> getByIn(@Param("field") String field, @Param("set") Set<Object> set);


}
