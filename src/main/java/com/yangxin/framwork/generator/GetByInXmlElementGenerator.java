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
public class GetByInXmlElementGenerator extends AbstractXmlElementGenerator {

    public GetByInXmlElementGenerator(Context context, Document document,
                                                 IntrospectedTable introspectedTable) {
        super.setContext(context);
        super.setIntrospectedTable(introspectedTable);
        addElements(document.getRootElement());
    }

    /**
     * <select id="getByIn" resultMap="BaseResultMap">
     * */
    @Override
    public void addElements(XmlElement parentElement) {
        // 定义select 标签
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "getByIn"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        // 开始select
        select.addElement(new TextElement("select "));
        // 添加 <include refid="Base_Column_List"/>
        select.addElement(getBaseColumnListElement());
        select.addElement(new TextElement("from " +
                introspectedTable.getFullyQualifiedTableNameAtRuntime()));

        XmlElement where = getWhere();
        select.addElement(where);
        parentElement.addElement(select);
    }

    /**
     * <where>
     *       <choose>
     *         <when test="field != null">
     *           ${field} in
     *           <foreach item="item" index="index" collection="set" open="(" separator="," close=")">
     *             #{item}
     *           </foreach>
     *         </when>
     *       </choose>
     *     </where>
     * */
    private XmlElement getWhere(){
        XmlElement result = new XmlElement("where");
        XmlElement choose = new XmlElement("choose");
        XmlElement when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "field != null"));
        when.addElement(new TextElement("${field} in"));
        XmlElement foreach = new XmlElement("foreach");
        foreach.addAttribute(new Attribute("collection", "set"));
        foreach.addAttribute(new Attribute("index", "index"));
        foreach.addAttribute(new Attribute("item", "item"));
        foreach.addAttribute(new Attribute("close", ")"));
        foreach.addAttribute(new Attribute("open", "("));
        foreach.addAttribute(new Attribute("separator", ","));
        foreach.addElement(new TextElement(" #{item}"));

        when.addElement(foreach);
        choose.addElement(when);
        result.addElement(choose);
        return result;
    }
}
