package com.aiunng.prj.entity;

import com.aiunng.prj.enumerate.KeyTypeEnum;

/**
 * 表索引信息
 *
 * @author：wangXinYu
 * @date：2021/7/16 2:06 下午
 */
public class TableKey {
  /**
   * 索引名称
   */
  private String name;
  /**
   * 索引类型
   */
  private KeyTypeEnum type;
  /**
   * 索引字段
   */
  private String[] fileds;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public KeyTypeEnum getType() {
    return type;
  }

  public void setType(KeyTypeEnum type) {
    this.type = type;
  }

  public String[] getFileds() {
    return fileds;
  }

  public void setFileds(String[] fileds) {
    this.fileds = fileds;
  }

  public String getKeyText() {
    StringBuilder result = new StringBuilder("\t");
    String name = "`" + this.name + "` ";
    result.append(this.type.getCode()).append(" ").
        append(null == this.name ? "" : name).append(" ");

    result.append("(");
    for (String filed : fileds) {
      result.append("`").append(filed).append("`").append(",");
    }
    result.deleteCharAt(result.lastIndexOf(","));
    result.append(")");
    result.append(", \n");
    return result.toString();
  }
}
