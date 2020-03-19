package com.yangxin.framwork.generator;


import com.yangxin.framwork.bean.Condition;
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
 * @time 2020/3/18  11:16
 */
public class GetByConditionListJavaMethodGenerator extends DynamicAbstractJavaGenerator {


    public GetByConditionListJavaMethodGenerator(Context context, Interface interfaze,
                                                  IntrospectedTable introspectedTable) {
        super.setContext(context);
        super.setIntrospectedTable(introspectedTable);
        super.addInterfaceElements(interfaze);
    }


    /**
     *
     * List<T> getByConditionList(@Param("conditions") List<Condition> conditions);
     *
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
        method.setName("getByConditionList");
        // import参数类型对象
        FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(List.class.getSimpleName());
        importedTypes.add(daoSuperType);
        daoSuperType.addTypeArgument(new FullyQualifiedJavaType(Condition.class.getSimpleName()));
        method.addParameter(new Parameter(daoSuperType, "conditions", "@Param(\"conditions\")"));
        importedTypes.add(new FullyQualifiedJavaType(Param.class.getName()));

        return method;
    }
}
