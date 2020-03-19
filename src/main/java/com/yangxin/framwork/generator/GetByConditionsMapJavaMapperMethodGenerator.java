package com.yangxin.framwork.generator;

import java.util.List;
import java.util.Map;
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
 * @time 2020/3/17  22:14
 */
public class GetByConditionsMapJavaMapperMethodGenerator extends DynamicAbstractJavaGenerator {

    public GetByConditionsMapJavaMapperMethodGenerator(Context context, Interface interfaze,
                                                       IntrospectedTable introspectedTable) {
        super.setContext(context);
        super.setIntrospectedTable(introspectedTable);
        super.addInterfaceElements(interfaze);
    }

    @Override
    public Method getMethod(){
        // 创建方法对象
        Method method = new Method();
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        // 设置该方法为public
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(List.class.getName());
        returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        // 方法对象设置返回类型对象
        method.setReturnType(returnType);
        method.setName("getByConditions");
        // import参数类型对象
        FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(Map.class.getSimpleName());
        importedTypes.add(daoSuperType);
        daoSuperType.addTypeArgument(new FullyQualifiedJavaType(String.class.getSimpleName()));
        daoSuperType.addTypeArgument(new FullyQualifiedJavaType(Object.class.getSimpleName()));
        method.addParameter(new Parameter(daoSuperType, "conditions", "@Param(\"conditions\")"));
        importedTypes.add(new FullyQualifiedJavaType(Param.class.getName()));

        method.addJavaDocLine("// 以下是 DynamicSqlPlugin 插件动态生成的部分");

        return method;
    }

}
