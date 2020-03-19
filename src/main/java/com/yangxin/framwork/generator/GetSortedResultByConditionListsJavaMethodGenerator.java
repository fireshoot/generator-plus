package com.yangxin.framwork.generator;

import com.yangxin.framwork.bean.Condition;
import com.yangxin.framwork.bean.Sort;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.Context;

/**
 * @author yangxin
 * @类描述
 * @time 2020/3/18  11:18
 */
public class GetSortedResultByConditionListsJavaMethodGenerator extends DynamicAbstractJavaGenerator {

    public GetSortedResultByConditionListsJavaMethodGenerator(Context context, Interface interfaze,
                                                             IntrospectedTable introspectedTable) {
        super.setContext(context);
        super.setIntrospectedTable(introspectedTable);
        super.addInterfaceElements(interfaze);
    }


    /**
     *
     *List<T> getSortedResultByConditionLists(
     * @Param("conditions") List<Condition> conditions,
     *                                             @Param("sorter") List<Sort> sort);
     * */
    @Override
    public Method getMethod() {
        // 创建方法对象
        Method method = new Method();
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        method.setName("getSortedResultByConditionLists");
        // import参数类型对象
        FullyQualifiedJavaType firstParameters = new FullyQualifiedJavaType(List.class.getSimpleName());
        importedTypes.add(firstParameters);
        firstParameters.addTypeArgument(new FullyQualifiedJavaType(Condition.class.getSimpleName()));
        method.addParameter(new Parameter(firstParameters, "set", "@Param(\"set\")"));

        FullyQualifiedJavaType secondParameter = new FullyQualifiedJavaType(List.class.getSimpleName());
        secondParameter.addTypeArgument(new FullyQualifiedJavaType(Sort.class.getSimpleName()));

        method.addParameter(new Parameter(secondParameter,
                "sorter", "@Param(\"sorter\")"));
        importedTypes.add(new FullyQualifiedJavaType(Param.class.getName()));
        importedTypes.add(new FullyQualifiedJavaType(Sort.class.getName()));
        return method;
    }
}
