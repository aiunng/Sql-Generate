// package com.aiunng.prj.enumerate;
//
// import com.aiunng.prj.util.StringUtil;
//
// /**
//  * 数据变更类型
//  *
//  * @author：wangXinYu
//  * @date：2021/7/19 4:57 下午
//  */
// public enum DateModifyTypeEnum {
//   /**
//    * 数据变更类型
//    */
//   INSERT("INSERT", "新增"),
//   UPDATE("UPDATE", "更新"),
//   DELETE("DELETE", "删除"),
//   ;
//
//   private String code;
//   private String desc;
//
//   DateModifyTypeEnum(String code, String desc) {
//     this.code = code;
//     this.desc = desc;
//   }
//
//   public String getCode() {
//     return code;
//   }
//
//   public String getDesc() {
//     return desc;
//   }
//
//   public static DateModifyTypeEnum getDateModifyTypeByCode(String code) {
//     for (DateModifyTypeEnum value : DateModifyTypeEnum.values()) {
//       if (StringUtil.equals(code, value.getCode())) {
//         return value;
//       }
//     }
//     return null;
//   }
// }
