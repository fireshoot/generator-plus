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
 * @time 2020/3/18  11:18
 */
public class GetSortedResultByConditionListXmlElementGenerator
        extends AbstractXmlElementGenerator {

    public GetSortedResultByConditionListXmlElementGenerator(
            Context context,
            Document document,
            IntrospectedTable introspectedTable) {

        super.setContext(context);
        super.setIntrospectedTable(introspectedTable);
        addElements(document.getRootElement());
    }

    /**
     * <select id="getSortedResultByConditionList" resultMap="BaseResultMap">
     *     select
     *     <include refid="Base_Column_List"/>
     *     from course_info
     * */
    @Override
    public void addElements(XmlElement parentElement) {
        // 定义select 标签
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "getSortedResultByConditionList"));
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
     *       <when test="sorter !=null">
     *         order by ${sorter.field} ${sorter.sortType}
     *       </when>
     *     </choose>
     * */
    private XmlElement getChoose() {
        XmlElement result = new XmlElement("choose");
        XmlElement when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "sorter !=null"));
        when.addElement(new TextElement(" order by ${sorter.field} ${sorter.sortType} "));
        result.addElement(when);
        return result;
    }
}
