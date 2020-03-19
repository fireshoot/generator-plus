package com.yangxin.framwork.bean;

public class Sort {

    /**
     * 可以field1,field2,field3,
     */
    private String field;
    /**
     * desc ,asc
     */
    private String sortType;

    public Sort(String field, String sortType) {
        this.field = field;
        this.sortType = sortType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
