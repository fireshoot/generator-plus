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
 * @time 2020/3/18  11:19
 */
public class GetSortedResultByConditionListsXmlElementGenerator
        extends AbstractXmlElementGenerator {

    public GetSortedResultByConditionListsXmlElementGenerator(
            Context context,
            Document document,
            IntrospectedTable introspectedTable) {

        super.setContext(context);
        super.setIntrospectedTable(introspectedTable);
        addElements(document.getRootElement());
    }

    /**
     *  <select id="getSortedResultByConditionLists" resultMap="BaseResultMap">
     *     select
     *     <include refid="Base_Column_List"/>
     *     from course_info
     * */
    @Override
    public void addElements(XmlElement parentElement) {
        // 定义select 标签
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "getSortedResultByConditionLists"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        // 开始select
        select.addElement(new TextElement("select "));
        // 添加 <include refid="Base_Column_List"/>
        select.addElement(getBaseColumnListElement());
        select.addElement(new TextElement("from " +
                introspectedTable.getFullyQualifiedTableNameAtRuntime()));
        XmlElement where = CommonXmlElement.getWhere();
        XmlElement choose = getChoose();
        select.addElement(where);
        select.addElement(choose);
        parentElement.addElement(select);
    }

    /**
     * <choose>
     *       <when test="sorter !=null and sorter.size() > 0">
     *         order by
     *         <foreach item="item" collection="sorter"
     *                  open="" separator="," close="">
     *           ${item.field} ${item.sortType}
     *         </foreach>
     *       </when>
     *     </choose>
     * */
    private XmlElement getChoose() {
        XmlElement result = new XmlElement("choose");
        XmlElement when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "sorter !=null and sorter.size() > 0"));
        when.addElement(new TextElement(" order by "));
        XmlElement foreach = new XmlElement("foreach");
        foreach.addAttribute(new Attribute("collection", "sorter"));
        foreach.addAttribute(new Attribute("item", "item"));
        foreach.addAttribute(new Attribute("close", ""));
        foreach.addAttribute(new Attribute("open", ""));
        foreach.addAttribute(new Attribute("separator", ","));
        foreach.addElement(new TextElement("${item.field} ${item.sortType}"));
        when.addElement(foreach);
        result.addElement(when);
        return result;
    }
}
