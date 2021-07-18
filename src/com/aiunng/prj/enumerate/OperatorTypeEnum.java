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
  ADD_FILED("ADD_FILED", "新增表字段"),
  ADD_INDEX("ADD_INDEX", "新增索引"),

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
