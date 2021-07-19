package com.aiunng.prj.enumerate;

/**
 * 操作类型
 *
 * @author：wangXinYu
 * @date：2021/7/18 4:11 下午
 */
public enum OperatorTypeEnum {
  /**
   * 操作类型
   */
  CREATE_TABLE("CREATE_TABLE", "创建表"),
  ALTER_TABLE("ALTER_TABLE", "修改表"),
  DATA_MODIFY("DATA_MODIFY", "数据变更"),

  ;

  private String code;
  private String desc;

  OperatorTypeEnum(String code, String desc) {
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
