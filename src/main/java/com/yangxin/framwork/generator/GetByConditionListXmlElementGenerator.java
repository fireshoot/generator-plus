package com.yangxin.framwork.generator;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.config.Context;

/**
 * @author yangxin
 * @类描述
 * @time 2020/3/18  11:16
 */
public class GetByConditionListXmlElementGenerator extends AbstractXmlElementGenerator {

    public GetByConditionListXmlElementGenerator(Context context, Document document,
                                                 IntrospectedTable introspectedTable) {
        super.setContext(context);
        super.setIntrospectedTable(introspectedTable);
        addElements(document.getRootElement());
    }

    @Override
    public void addElements(XmlElement parentElement) {
        /**
         * <select id="getByConditionList" resultMap="BaseResultMap" parameterType="list">
         *     select
         *     <include refid="Base_Column_List"/>
         *     from course_info
         * */
        // 定义select 标签
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "getByConditionList"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        select.addAttribute(new Attribute("parameterType", "list"));
        // 开始select
        select.addElement(new TextElement("select "));
        // 添加 <include refid="Base_Column_List"/>
        select.addElement(getBaseColumnListElement());
        select.addElement(new TextElement("from " +
                introspectedTable.getFullyQualifiedTableNameAtRuntime()));
        XmlElement where = CommonXmlElement.getWhere();
        select.addElement(where);
        parentElement.addElement(select);
    }



}
