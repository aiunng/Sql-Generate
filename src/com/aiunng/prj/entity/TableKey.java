package com.aiunng.prj.entity;

import com.aiunng.prj.enumerate.KeyTypeEnum;
import com.aiunng.prj.util.StringUtil;
import java.util.Arrays;
import java.util.Objects;

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
    if (StringUtil.isBlank(this.name) && null == this.type && (null == this.fileds || StringUtil.isBlank(this.fileds[0]))) {
      return "";
    }
    StringBuilder result = new StringBuilder("    ");
    String name = "`" + this.name + "` ";
    result.append(this.type.getCode()).append(" ").
        append(StringUtil.isBlank(this.name) ? "" : name).append(" ");

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
