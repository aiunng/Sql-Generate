package com.aiunng.prj.enumerate;

import com.aiunng.prj.util.StringUtil;

/**
 * 修改表结构-修改类型
 *
 * @author：wangXinYu
 * @date：2021/7/19 10:32 上午
 */
public enum AlterTypeEnum {
  /**
   * 新增字段、索引
   */
  ADD("ADD", "新增"),
  /**
   * 删除现有字段、索引
   */
  DROP("DROP", "删除"),
  /**
   * 修改现有字段、索引
   */
  MODIFY("MODIFY", "修改"),

  ;

  private String code;
  private String desc;

  AlterTypeEnum(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  public static AlterTypeEnum getByAlterTypeCode(String code) {
    for (AlterTypeEnum value : AlterTypeEnum.values()) {
      if (StringUtil.equals(code, value.getCode())) {
        return value;
      }
    }
    return null;
  }
}
