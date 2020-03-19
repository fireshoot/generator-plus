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
 * @time 2020/3/17  22:14
 */
public class GetByConditionsMapXmlElementGenerator extends AbstractXmlElementGenerator {

    public GetByConditionsMapXmlElementGenerator(Context context, Document document,
                                                 IntrospectedTable introspectedTable) {
        super.setContext(context);
        super.setIntrospectedTable(introspectedTable);
        addElements(document.getRootElement());
    }

    /**
     *  增加 ：
     *
     *  select
     *     <include refid="Base_Column_List"/>
     *     from course_info
     *     <where>
     *       <choose>
     *         <when test="conditions!= null">
     *           <foreach close="" collection="conditions" index="key" item="value" open="" separator="AND">
     *             ${key}=#{value}
     *           </foreach>
     *         </when>
     *       </choose>
     *     </where>
     * */
    @Override
    public void addElements(XmlElement parentElement) {

        parentElement.addElement(new TextElement("<!--以下部分是DynamicSqlPlugin 插件生成的 --> \n\n\n\n"));

        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id", "getByConditions"));
        answer.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        answer.addAttribute(new Attribute("parameterType", "map"));
        answer.addElement(new TextElement("select "));
        // 添加 <include refid="Base_Column_List"/>
        answer.addElement(getBaseColumnListElement());
        answer.addElement(new TextElement("from " +
                introspectedTable.getFullyQualifiedTableNameAtRuntime()));
        XmlElement where = getConditionElement();

        answer.addElement(where);

        parentElement.addElement(answer);
    }

    private XmlElement getConditionElement() {
        // 添加where 标签
        XmlElement where = new XmlElement("where");
        XmlElement choose = new XmlElement("choose");
        XmlElement when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "conditions!= null"));
        XmlElement foreach = new XmlElement("foreach");
        foreach.addAttribute(new Attribute("collection", "conditions"));
        foreach.addAttribute(new Attribute("index", "key"));
        foreach.addAttribute(new Attribute("item", "value"));
        foreach.addAttribute(new Attribute("close", ""));
        foreach.addAttribute(new Attribute("open", ""));
        foreach.addAttribute(new Attribute("separator", "AND"));
        foreach.addElement(new TextElement(" ${key}=#{value}"));
        when.addElement(foreach);
        choose.addElement(when);
        where.addElement(choose);
        return where;
    }

}
