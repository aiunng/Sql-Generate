// package com.aiunng.prj.entity;
//
// import com.aiunng.prj.util.StringUtil;
//
// /**
//  * 更新数据条件匹配类型
//  *
//  * @author：wangXinYu
//  * @date：2021/7/19 6:15 下午
//  */
// public enum MatchTypeEnum {
//   /**
//    * 条件匹配类型
//    */
//   EQUAL("=", "相等"),
//   IN("IN", "存在"),
//   ;
//
//   private String code;
//   private String desc;
//
//   MatchTypeEnum(String code, String desc) {
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
//   public static MatchTypeEnum getMatchTypeByCode(String code) {
//     for (MatchTypeEnum value : MatchTypeEnum.values()) {
//       if (StringUtil.equals(code, value.getCode())) {
//         return value;
//       }
//     }
//     return null;
//   }
// }
