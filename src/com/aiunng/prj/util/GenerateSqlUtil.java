package com.aiunng.prj.util;

import com.aiunng.prj.entity.Table;
import com.aiunng.prj.entity.TableField;
import com.aiunng.prj.entity.TableKey;
import com.aiunng.prj.enumerate.FieldTypeEnum;
import com.aiunng.prj.enumerate.KeyTypeEnum;
import java.util.ArrayList;
import java.util.List;

/**
 * @author：wangXinYu
 * @date：2021/7/16 2:03 下午
 */
public class GenerateSqlUtil {

  public static String createTable(Table table) {

    StringBuilder result = new StringBuilder("CREATE TABLE IF NOT EXISTS `" + table.getName() + "` (\n");
    table.getTableFields().forEach((o) -> {
      result.append(o.getFieldText());
    });
    table.getTableKeys().forEach((o) -> {
      result.append(o.getKeyText());
    });
    result.deleteCharAt(result.lastIndexOf(","));
    result.append("\n) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT ='" + table.getComment() + "';");
    return result.toString();
  }



  public static void main(String[] args) {
    System.out.println(createTable(initTestData()));

  }

  /**
   * 测试数据
   *
   * @return
   */
  private static Table initTestData() {
    List<TableField> tableFields = new ArrayList<>();
    TableField tableField = new TableField();
    tableField.setName("id");
    tableField.setComment("主键");
    tableField.setFieldType(FieldTypeEnum.BIGINT);
    tableField.setLength("20");
    tableField.setAutoIncrement(true);
    tableField.setNotNull(true);
    tableFields.add(tableField);

    TableField tableField1 = new TableField();
    tableField1.setName("name");
    tableField1.setComment("姓名");
    tableField1.setFieldType(FieldTypeEnum.VARCHAR);
    tableField1.setLength("20");
    tableField1.setAutoIncrement(false);
    tableField1.setNotNull(false);
    tableField1.setDefaultValue("NULL");
    tableFields.add(tableField1);

    TableField tableField2 = new TableField();
    tableField2.setName("date");
    tableField2.setComment("日期");
    tableField2.setFieldType(FieldTypeEnum.TIMESTAMP);
    tableField2.setAutoIncrement(false);
    tableField2.setNotNull(true);
    tableField2.setDefaultValue("CURRENT_TIMESTAMP(3)");
    tableField2.setOnUpdate("CURRENT_TIMESTAMP(3)");
    tableFields.add(tableField2);

    List<TableKey> tableKeys = new ArrayList<>();
    TableKey tableKey = new TableKey();
    tableKey.setType(KeyTypeEnum.PRIMARY);
    tableKey.setFileds(new String[]{"id"});
    tableKeys.add(tableKey);

    TableKey tableKey1 = new TableKey();
    tableKey1.setName("index_name");
    tableKey1.setType(KeyTypeEnum.INDEX);
    tableKey1.setFileds(new String[]{"name"});
    tableKeys.add(tableKey1);

    TableKey tableKey2 = new TableKey();
    tableKey2.setName("index_name_date");
    tableKey2.setType(KeyTypeEnum.INDEX);
    tableKey2.setFileds(new String[]{"name","date"});
    tableKeys.add(tableKey2);

    Table table = new Table();
    table.setName("aiu_test");
    table.setComment("测试");
    table.setTableFields(tableFields);
    table.setTableKeys(tableKeys);
    return table;
  }
}
