package com.yangxin.framwork.generator;

import java.util.Set;
import java.util.TreeSet;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

/**
 * @author yangxin
 * @类描述
 * @time 2020/3/18  10:58
 */
public abstract class DynamicAbstractJavaGenerator extends AbstractJavaMapperMethodGenerator {

    protected Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();

    @Override
    public void addInterfaceElements(Interface interfaze) {

        Method method = getMethod();


        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze,
                introspectedTable)) {

            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    public abstract Method getMethod();


}
