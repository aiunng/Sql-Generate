package com.aiunng.prj.util;

import java.util.Collection;

/**
 * @author：wangXinYu
 * @date：2021/7/19 3:10 下午
 */
public class CollectionUtil {
  public static boolean isNotEmpty(Collection coll) {
    return !isEmpty(coll);
  }

  public static boolean isEmpty(Collection coll) {
    return coll == null || coll.isEmpty();
  }
}
