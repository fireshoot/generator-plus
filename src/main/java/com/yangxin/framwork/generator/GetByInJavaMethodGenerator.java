package com.yangxin.framwork.generator;

import java.util.List;
import java.util.Set;
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
 * @time 2020/3/18  11:19
 */
public class GetByInJavaMethodGenerator extends DynamicAbstractJavaGenerator {

    public GetByInJavaMethodGenerator(Context context, Interface interfaze,
                                      IntrospectedTable introspectedTable) {
        super.setContext(context);
        super.setIntrospectedTable(introspectedTable);
        super.addInterfaceElements(interfaze);
    }

    /**
     * List<T> getByIn(@Param("field") String field, @Param("set") Set<Object> set);
     */
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
        method.setName("getByIn");
        // import参数类型对象
        FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(Set.class.getSimpleName());
        importedTypes.add(daoSuperType);
        daoSuperType.addTypeArgument(new FullyQualifiedJavaType(Object.class.getSimpleName()));
        method.addParameter(new Parameter(daoSuperType, "set", "@Param(\"set\")"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType(String.class.getSimpleName()),
                "field", "@Param(\"field\")"));
        importedTypes.add(new FullyQualifiedJavaType(Param.class.getName()));
        return method;
    }
}
