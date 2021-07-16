package com.aiunng.prj.enumerate;

/**
 * 索引类型
 *
 * @author：wangXinYu
 * @date：2021/7/16 2:45 下午
 */
public enum KeyTypeEnum {
  /**
   * 索引类型
   */
  INDEX("INDEX", "普通索引"),
  UNIQUE("UNIQUE INDEX", "唯一索引"),
  PRIMARY("PRIMARY KEY", "主键索引"),
  FULLTEXT("FULLTEXT", "全文索引"),

  ;

  private String code;
  private String desc;

  KeyTypeEnum(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

}
