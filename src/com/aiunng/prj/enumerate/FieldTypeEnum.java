package com.aiunng.prj.enumerate;

/**
 * 字段类型
 *
 * @author：wangXinYu
 * @date：2021/7/16 2:10 下午
 */
public enum FieldTypeEnum {
  /**
   * 数值类型
   */
  TINYINT(101, "TINYINT"),
  SMALLINT(102, "SMALLINT"),
  MEDIUMINT(103, "MEDIUMINT"),
  INT(104, "INT"),
  BIGINT(105, "BIGINT"),
  FLOAT(106, "FLOAT"),
  DECIMAL(107, "DECIMAL"),

  /**
   * 日期和时间类型
   */
  DATE(201, "DATE"),
  TIME(202, "TIME"),
  YEAR(203, "YEAR"),
  DATETIME(204, "DATETIME"),
  TIMESTAMP(205, "TIMESTAMP"),

  /**
   * 字符串类型
   */
  CHAR(301, "CHAR"),
  VARCHAR(302, "VARCHAR"),
  TINYBLOB(303, "TINYBLOB"),
  TINYTEXT(304, "TINYTEXT"),
  BLOB(305, "BLOB"),
  TEXT(306, "TEXT"),
  MEDIUMBLOB(307, "MEDIUMBLOB"),
  MEDIUMTEXT(308, "MEDIUMTEXT"),
  LONGBLOB(309, "LONGBLOB"),
  LONGTEXT(310, "LONGTEXT"),

  ;

  private int code;
  private String desc;

  FieldTypeEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
}
