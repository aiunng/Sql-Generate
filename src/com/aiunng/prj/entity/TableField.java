package com.aiunng.prj.entity;

import com.aiunng.prj.enumerate.FieldTypeEnum;
import com.aiunng.prj.util.StringUtil;

/**
 * 字段信息
 *
 * @author：wangXinYu
 * @date：2021/7/16 2:05 下午
 */
public class TableField {
  /**
   * 字段名
   */
  private String name;
  /**
   * 字段描述
   */
  private String comment;
  /**
   * 字段类型
   */
  private FieldTypeEnum fieldType;
  /**
   * 字段长度
   */
  private String length;
  /**
   * 字段是否自增
   */
  private Boolean autoIncrement;
  /**
   * 字段是否不可为空
   */
  private Boolean notNull;
  /**
   * 字段默认值
   */
  private String defaultValue;
  /**
   * 字段更新值（常用于更新时间）
   */
  private String onUpdate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public FieldTypeEnum getFieldType() {
    return fieldType;
  }

  public void setFieldType(FieldTypeEnum fieldType) {
    this.fieldType = fieldType;
  }

  public String getLength() {
    return length;
  }

  public void setLength(String length) {
    this.length = length;
  }

  public Boolean getAutoIncrement() {
    return autoIncrement;
  }

  public void setAutoIncrement(Boolean autoIncrement) {
    this.autoIncrement = autoIncrement;
  }

  public Boolean getNotNull() {
    return notNull;
  }

  public void setNotNull(Boolean notNull) {
    this.notNull = notNull;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public String getOnUpdate() {
    return onUpdate;
  }

  public void setOnUpdate(String onUpdate) {
    this.onUpdate = onUpdate;
  }

  public String getFieldText() {
    StringBuilder result = new StringBuilder("\t");
    String lenth = "(" + this.length + ") ";
    result.append("`").append(this.name).append("` ").
        append(this.fieldType.getDesc()).
        append(StringUtil.isBlank(this.length) ? "" : lenth).append(" ").
        append(this.notNull ? "NOT NULL" : " ").append(" ").
        append(StringUtil.isBlank(this.defaultValue) ? "" : "DEFAULT " + this.defaultValue).append(" ").
        append(StringUtil.isBlank(this.onUpdate) ? "" : "ON UPDATE " + this.onUpdate).append(" ").
        append(this.autoIncrement ? "AUTO_INCREMENT" : " ").append(" ").
        append(StringUtil.isBlank(this.comment) ? "" : "COMMENT '" + this.comment).append("', \n");
    return result.toString();
  }
}
