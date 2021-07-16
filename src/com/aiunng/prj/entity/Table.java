package com.aiunng.prj.entity;

import java.util.List;

/**
 * 表信息
 *
 * @author：wangXinYu
 * @date：2021/7/16 2:06 下午
 */
public class Table {
  /**
   * 表名
   */
  private String name;
  /**
   * 表描述
   */
  private String comment;
  /**
   * 表字段信息
   */
  private List<TableField> tableFields;
  /**
   * 表索引信息
   */
  private List<TableKey> tableKeys;

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

  public List<TableField> getTableFields() {
    return tableFields;
  }

  public void setTableFields(List<TableField> tableFields) {
    this.tableFields = tableFields;
  }

  public List<TableKey> getTableKeys() {
    return tableKeys;
  }

  public void setTableKeys(List<TableKey> tableKeys) {
    this.tableKeys = tableKeys;
  }
}
