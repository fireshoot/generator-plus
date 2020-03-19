package com.yangxin.framwork.bean;


/**
* @Description:   SQL基础查询条件
 *
 *  样例
 *  new Condition("account","like","%" + account + "%")
 *  new Condition("account","!=","account")
 *
 * @Author:         yang xin
* @CreateDate:     2020/3/17 21:16
*/
public class Condition {

    private String key;
    private String opt;
    private Object value;

    public Condition(String key, String opt, Object value) {
        this.key = key;
        this.opt = opt;
        this.value = value;
    }

    public Condition(String key, Object value) {
        this(key, "=", value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
