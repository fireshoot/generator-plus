package com.yangxin.framwork.plugin;

import com.yangxin.framwork.basemapper.BaseMapper;
import com.yangxin.framwork.generator.GetByConditionListXmlElementGenerator;
import com.yangxin.framwork.generator.GetByConditionsMapXmlElementGenerator;
import com.yangxin.framwork.generator.GetByConditionListJavaMethodGenerator;
import com.yangxin.framwork.generator.GetByConditionsMapJavaMapperMethodGenerator;
import com.yangxin.framwork.generator.GetByInJavaMethodGenerator;
import com.yangxin.framwork.generator.GetByInXmlElementGenerator;
import com.yangxin.framwork.generator.GetSortedResultByConditionListJavaMethodGenerator;
import com.yangxin.framwork.generator.GetSortedResultByConditionListXmlElementGenerator;
import com.yangxin.framwork.generator.GetSortedResultByConditionListsJavaMethodGenerator;
import com.yangxin.framwork.generator.GetSortedResultByConditionListsXmlElementGenerator;
import java.util.List;
import java.util.Set;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/**
 * @author yangxin
 * @类描述 : 自定义组件，让逆向工程的时候自动生成：
 * List<T> getByConditions(@Param("conditions") Map<String, Object> conditions);
 * <p>
 * List<T> getByConditionList(@Param("conditions") List<Condition> conditions);
 * <p>
 * List<T> getSortedResultByConditionList(@Param("conditions") List<Condition> conditions,
 * @Param("sorter") Sort sort);
 * List<T> getSortedResultByConditionLists(@Param("conditions") List<Condition> conditions,
 * @Param("sorter") List<Sort> sort);
 * <p>
 * List<T> getByIn(@Param("field") String field, @Param("set") Set<Object> set);
 * <p>
 * <p>
 * T selectByPrimaryKeyForUpdate(Integer id);                                   @Param("sorter") Sort sort);
 * @time 2020/3/17  22:06
 */
public class DynamicSqlPlugin extends PluginAdapter {


    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document,
                                           IntrospectedTable introspectedTable) {
        AbstractXmlElementGenerator elementGenerator1 =
                new GetByConditionsMapXmlElementGenerator(context, document, introspectedTable);
        AbstractXmlElementGenerator elementGenerator2 =
                new GetByConditionListXmlElementGenerator(context, document, introspectedTable);
        AbstractXmlElementGenerator elementGenerator3 =
                new GetByInXmlElementGenerator(context, document, introspectedTable);
        AbstractXmlElementGenerator elementGenerator4 =
                new GetSortedResultByConditionListsXmlElementGenerator(context, document, introspectedTable);
        AbstractXmlElementGenerator elementGenerator5 =
                new GetSortedResultByConditionListXmlElementGenerator(context, document, introspectedTable);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        AbstractJavaMapperMethodGenerator methodGenerator1 =
                new GetByConditionsMapJavaMapperMethodGenerator(context, interfaze, introspectedTable);
        AbstractJavaMapperMethodGenerator methodGenerator2 =
                new GetByConditionListJavaMethodGenerator(context, interfaze, introspectedTable);
        AbstractJavaMapperMethodGenerator methodGenerator3 =
                new GetByInJavaMethodGenerator(context, interfaze, introspectedTable);
        AbstractJavaMapperMethodGenerator methodGenerator4 =
                new GetSortedResultByConditionListJavaMethodGenerator(context, interfaze, introspectedTable);
        AbstractJavaMapperMethodGenerator methodGenerator5 =
                new GetSortedResultByConditionListsJavaMethodGenerator(context, interfaze, introspectedTable);


        FullyQualifiedJavaType baseMapper = new FullyQualifiedJavaType(BaseMapper.class.getSimpleName());
        baseMapper.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));

        interfaze.addSuperInterface(baseMapper);

        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

}
