package com.yangxin.framwork.generator;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author yangxin
 * @类描述
 * @time 2020/3/18  20:38
 */
public  class CommonXmlElement {

    /**
     *
     *  <where>
     *       <choose>
     *         <when test="conditions != null and conditions.size() > 0">
     *           <foreach item="item" collection="conditions"
     *                    open="" separator="AND" close="">
     *           </foreach>
     *         </when>
     *       </choose>
     *     </where>
     * */
    public static XmlElement getWhere(){
        XmlElement where = new XmlElement("where");
        XmlElement choose = new XmlElement("choose");
        XmlElement when = new XmlElement("when");
        when.addAttribute(new Attribute("test", "conditions != null and conditions.size() > 0"));
        XmlElement foreach = new XmlElement("foreach");
        foreach.addAttribute(new Attribute("collection", "conditions"));
        foreach.addAttribute(new Attribute("index", "key"));
        foreach.addAttribute(new Attribute("item", "value"));
        foreach.addAttribute(new Attribute("close", ""));
        foreach.addAttribute(new Attribute("open", ""));
        foreach.addAttribute(new Attribute("separator", "AND"));

        XmlElement forEachChoose = new XmlElement("choose");

        XmlElement locateCondition = getLocateCondition();
        XmlElement inCondition = getInCondition();
        XmlElement betweenCondition = getBetweenCondition();
        XmlElement otherCondition = getOtherCondition();

        forEachChoose.addElement(locateCondition);
        forEachChoose.addElement(inCondition);
        forEachChoose.addElement(betweenCondition);
        forEachChoose.addElement(otherCondition);

        foreach.addElement(forEachChoose);
        when.addElement(foreach);

        choose.addElement(when);
        where.addElement(choose);

        return where;
    }

    /**
     * <when test="item.opt == 'locate'">
     *                 locate(#{item.value}, ${item.key}) > 0
     *               </when>
     * */
    private static XmlElement getLocateCondition() {
        XmlElement locateWhen = new XmlElement("when");
        locateWhen.addAttribute(new Attribute("test", "item.opt == 'locate'"));
        locateWhen.addElement(new TextElement("locate(#{item.value}, ${item.key}) > 0"));
        return locateWhen;
    }

    /**
     *
     * <when test="item.opt == 'in'">
     *                 ${item.key} in
     *      <foreach item="itemIn" collection="item.value" open="(" separator="," close=")">
     *                   #{itemIn}
     *      </foreach>
     *    </when>
     *
     * */
    private static XmlElement getInCondition() {
        XmlElement inWhen = new XmlElement("when");
        inWhen.addAttribute(new Attribute("test", "item.opt == 'in'"));
        inWhen.addElement(new TextElement(" ${item.key} in "));

        XmlElement inWhenForeach = new XmlElement("foreach");
        inWhenForeach.addAttribute(new Attribute("collection", "item.value"));
        inWhenForeach.addAttribute(new Attribute("item", "itemIn"));
        inWhenForeach.addAttribute(new Attribute("close", ")"));
        inWhenForeach.addAttribute(new Attribute("open", "("));
        inWhenForeach.addAttribute(new Attribute("separator", ","));
        inWhenForeach.addElement(new TextElement(" #{itemIn}"));
        inWhen.addElement(inWhenForeach);
        return inWhen;
    }

    /**
     * <when test="item.opt == 'between'">
     *                 ${item.key} between
     *    <foreach collection="item.value" index="key" item="value" separator="AND" close=""
     *               open="">
     *                   #{value}
     *                 </foreach>
     *               </when>
     *
     * */
    private static XmlElement getBetweenCondition(){
        XmlElement between = new XmlElement("when");
        between.addAttribute(new Attribute("test", "item.opt == 'between'"));
        between.addElement(new TextElement("  ${item.key} between "));

        XmlElement forEach = new XmlElement("foreach");
        forEach.addAttribute(new Attribute("index", "key"));
        forEach.addAttribute(new Attribute("collection", "item.value"));
        forEach.addAttribute(new Attribute("item", "value"));
        forEach.addAttribute(new Attribute("close", ""));
        forEach.addAttribute(new Attribute("open", ""));
        forEach.addAttribute(new Attribute("separator", "AND"));
        forEach.addElement(new TextElement(" #{value}"));
        between.addElement(forEach);
        return between;
    }

    /**
     * <otherwise>
     *                 ${item.key} ${item.opt} #{item.value}
     *               </otherwise>
     * */
    private static XmlElement getOtherCondition() {
        XmlElement result = new XmlElement("otherwise");
        result.addElement(new TextElement(" ${item.key} ${item.opt} #{item.value}"));
        return result;
    }
}
