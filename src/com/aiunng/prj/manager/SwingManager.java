package com.aiunng.prj.manager;

import static com.aiunng.prj.enumerate.FieldTypeEnum.getByDesc;
import static com.aiunng.prj.enumerate.KeyTypeEnum.getByCode;
import static com.aiunng.prj.enumerate.OperatorTypeEnum.ADD_FILED;
import static com.aiunng.prj.enumerate.OperatorTypeEnum.CREATE_TABLE;
import static com.aiunng.prj.util.Constant.TEXT_NORMAL;
import static com.aiunng.prj.util.Constant.TEXT_SMALL;
import static com.aiunng.prj.util.GenerateSqlUtil.createTable;
import static com.aiunng.prj.util.GenerateSqlUtil.updateTableAddFile;
import static com.aiunng.prj.util.GenerateSqlUtil.updateTableAddKey;
import static com.aiunng.prj.util.SwingUtil.addBooleanComboBox;
import static com.aiunng.prj.util.SwingUtil.addDefaultComboBox;
import static com.aiunng.prj.util.SwingUtil.addDefaultKeyComboBox;
import static com.aiunng.prj.util.SwingUtil.addFiledTypeComboBox;
import static com.aiunng.prj.util.SwingUtil.addJBScrollPane;
import static com.aiunng.prj.util.SwingUtil.addJButton;
import static com.aiunng.prj.util.SwingUtil.addJTextArea;
import static com.aiunng.prj.util.SwingUtil.addKeyTypeComboBox;
import static com.aiunng.prj.util.SwingUtil.addLabel;
import static com.aiunng.prj.util.SwingUtil.getAddFiledComponentSet;
import static com.aiunng.prj.util.SwingUtil.getCreateTableComponentSet;
import static java.util.stream.Collectors.toList;

import com.aiunng.prj.entity.Table;
import com.aiunng.prj.entity.TableField;
import com.aiunng.prj.entity.TableKey;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBUI;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.apache.commons.lang3.StringUtils;

/**
 * 生成sql
 *
 * @author：wangXinYu
 * @date：2021/7/16 4:22 下午
 */
public class SwingManager {

  private static Table table = new Table();
  private static final List<TableField> tableFields = new ArrayList<>();
  private static final List<TableKey> tableKeys = new ArrayList<>();

  public static void createAndShowGUI() {
    // 创建及设置窗口
    JFrame frame = new JFrame("Sql-Generate");
    frame.setBounds(360, 100, 860, 660);

    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    JPanel contentPanel = new JPanel();
    contentPanel.setBorder(JBUI.Borders.empty(5));
    contentPane.add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);

    int h = 28;
    int textX = 130;
    int boxX = 125;

    int y = 20;
    int yOffset = 33;
    int yOffsetBThen = 38;

    int boxWidth = 210;
    int boxHight = 33;

    int l2TitleX = 30;

    // 结果展示区
    JBScrollPane view = addJBScrollPane(TEXT_SMALL, 360, 80, 480, 510, contentPanel);
    JTextArea answer = new JTextArea(1, 1);
    view.setViewportView(answer);

    // 默认展示创建表格
    createTableZone(CREATE_TABLE.getCode(), contentPanel, h, textX, boxX, y, yOffset, yOffsetBThen, boxWidth, boxHight, l2TitleX, answer);

    // 卡片 - 创建表格
    JButton createTableButton = addJButton("", "create table", TEXT_NORMAL, 10, 10, 120, 40, contentPanel);
    createTableButton.addActionListener((o) -> {
      // 删除其他操作的展示内容
      getAddFiledComponentSet().forEach(contentPanel::remove);
      tableFields.clear();
      tableKeys.clear();
      table = new Table();
      answer.setText("");

      // 展示创建表格的内容
      createTableZone(CREATE_TABLE.getCode(), contentPanel, h, textX, boxX, y, yOffset, yOffsetBThen, boxWidth, boxHight, l2TitleX, answer);
      // 更新当前窗体
      contentPanel.updateUI();
    });

    // 卡片 - 表格新增字段
    JButton alertTableButton = addJButton("", "alert table", TEXT_NORMAL, 140, 10, 120, 40, contentPanel);
    alertTableButton.addActionListener((o) -> {
      // 删除其他操作的展示内容
      getCreateTableComponentSet().forEach(contentPanel::remove);
      tableFields.clear();
      tableKeys.clear();
      table = new Table();
      answer.setText("");

      // 展示新增字段的内容
      addFiledZone(ADD_FILED.getCode(), contentPanel, h, textX, boxX, y, yOffset, yOffsetBThen, boxWidth, boxHight, l2TitleX, answer);
      // 更新当前窗体
      contentPanel.updateUI();
    });

    // 关闭按钮
    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // 显示窗口
    frame.setVisible(true);
  }

  private static void createTableZone(String type, JPanel contentPanel, int h, int textX, int boxX, int y, int yOffset, int yOffsetBThen,
      int boxWidth,
      int boxHight,
      int l2TitleX, JTextArea answer) {
    y = y + 50;
    addLabel(type, "Table:", TEXT_NORMAL, 10, y, 100, h, contentPanel);
    y = y + yOffset;
    addLabel(type, "Name:", TEXT_NORMAL, l2TitleX, y, 100, h, contentPanel);
    JTextArea tableName = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "Desc:", TEXT_NORMAL, l2TitleX, y, 100, h, contentPanel);
    JTextArea tableDesc = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "Column:", TEXT_NORMAL, 10, y, 660, h, contentPanel);
    y = y + yOffset;
    addLabel(type, "Name:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fName = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "Type:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fType = addFiledTypeComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(type, "Lenth:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fLenth = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "NotNull:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fNotNullh = addBooleanComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(type, "Default:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fDefault = addDefaultComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(type, "Update:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fUpdate = addDefaultComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(type, "Increment:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fIncrement = addBooleanComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(type, "Comment:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fComment = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "Index:", TEXT_NORMAL, 10, y, 660, h, contentPanel);
    y = y + yOffset;
    addLabel(type, "Name:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea kname = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "Type:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox kType = addKeyTypeComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(type, "Columns:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox kFileds = addDefaultKeyComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    createTablebuttonZone(type, contentPanel, y, answer, tableName, tableDesc, fName, fType, fLenth, fNotNullh, fDefault, fUpdate, fIncrement,
        fComment,
        kname,
        kType, kFileds);
  }

  private static void addFiledZone(String type, JPanel contentPanel, int h, int textX, int boxX, int y, int yOffset, int yOffsetBThen, int boxWidth,
      int boxHight,
      int l2TitleX, JTextArea answer) {
    y = y + 50;
    addLabel(type, "Table:", TEXT_NORMAL, 10, y, 100, h, contentPanel);
    y = y + yOffset;
    addLabel(type, "Name:", TEXT_NORMAL, l2TitleX, y, 100, h, contentPanel);
    JTextArea tableName = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "Column:", TEXT_NORMAL, 10, y, 660, h, contentPanel);
    y = y + yOffset;
    addLabel(type, "Name:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fName = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "Type:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fType = addFiledTypeComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(type, "Lenth:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fLenth = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "NotNull:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fNotNullh = addBooleanComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(type, "Default:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fDefault = addDefaultComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(type, "Update:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fUpdate = addDefaultComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(type, "Increment:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fIncrement = addBooleanComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(type, "Comment:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fComment = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffsetBThen;
    addLabel(type, "After:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fAfter = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "Index:", TEXT_NORMAL, 10, y, 660, h, contentPanel);
    y = y + yOffset;
    addLabel(type, "Name:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea kname = addJTextArea(type, null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(type, "Type:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox kType = addKeyTypeComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(type, "Columns:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox kFileds = addDefaultKeyComboBox(type, TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    addFiledButtonZone(type, contentPanel, y, answer, tableName, fName, fType, fLenth, fNotNullh, fDefault, fUpdate, fIncrement, fComment, fAfter,
        kname, kType, kFileds);
  }

  private static void createTablebuttonZone(String type, JPanel contentPanel, int y, JTextArea answer, JTextArea tableName, JTextArea tableDesc,
      JTextArea fName, JComboBox fType, JTextArea fLenth, JComboBox fNotNullh, JComboBox fDefault, JComboBox fUpdate, JComboBox fIncrement,
      JTextArea fComment, JTextArea kname, JComboBox kType, JComboBox kFileds) {

    int buttonX = 355;
    int buttonXOffSet = 110;
    y = y + 15;
    // 新增字段按钮，点击后增量到右侧展示
    JButton filedButton = addJButton(type, "add column", TEXT_NORMAL, buttonX, y, 110, 25, contentPanel);
    filedButton.addActionListener((o) -> {

      List<TableField> collect = tableFields.stream().filter((n) -> StringUtils.equals(n.getName(), fName.getText())).collect(toList());

      if (collect.size() < 1) {
        TableField tableField = new TableField();
        tableField.setName(fName.getText());
        tableField.setComment(fComment.getText());
        tableField.setFieldType(getByDesc(fType.getSelectedItem().toString()));
        tableField.setLength(fLenth.getText());
        tableField.setAutoIncrement(Boolean.parseBoolean(fIncrement.getSelectedItem().toString()));
        tableField.setNotNull(Boolean.parseBoolean(fNotNullh.getSelectedItem().toString()));
        tableField.setDefaultValue(fDefault.getSelectedItem().toString());
        tableField.setOnUpdate(fUpdate.getSelectedItem().toString());
        tableFields.add(tableField);
        table.setTableFields(tableFields);
      }
      String tableText = createTable(SwingManager.table);
      answer.setText(tableText);

    });

    // 新增索引按钮，点击后增量到右侧展示
    buttonX = buttonX + buttonXOffSet;
    JButton keyButton = addJButton(type, "add index", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
    keyButton.addActionListener((o) -> {

      List<TableKey> collect = tableKeys.stream().filter((n) -> StringUtils.equals(n.getName(), kname.getText())).collect(toList());

      if (collect.size() < 1) {
        TableKey tableKey = new TableKey();
        tableKey.setName(kname.getText());
        tableKey.setType(getByCode(kType.getSelectedItem().toString()));
        tableKey.setFileds(kFileds.getSelectedItem().toString().split(","));
        tableKeys.add(tableKey);
        table.setTableKeys(tableKeys);
      }
      String tableText = createTable(SwingManager.table);
      answer.setText(tableText);
    });

    // 预览按钮
    buttonX = buttonX + buttonXOffSet;
    JButton viewButton = addJButton(type, "preview", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
    viewButton.addActionListener((o) -> {
      List<TableField> collectF = tableFields.stream().filter((n) -> StringUtils.equals(n.getName(), fName.getText())).collect(toList());
      if (collectF.size() < 1) {
        TableField tableField = new TableField();
        tableField.setName(fName.getText());
        tableField.setComment(fComment.getText());
        tableField.setFieldType(getByDesc(fType.getSelectedItem().toString()));
        tableField.setLength(fLenth.getText());
        tableField.setAutoIncrement(Boolean.parseBoolean(fIncrement.getSelectedItem().toString()));
        tableField.setNotNull(Boolean.parseBoolean(fNotNullh.getSelectedItem().toString()));
        tableField.setDefaultValue(fDefault.getSelectedItem().toString());
        tableField.setOnUpdate(fUpdate.getSelectedItem().toString());
        tableFields.add(tableField);
      }

      List<TableKey> collectK = tableKeys.stream().filter((n) -> StringUtils.equals(n.getName(), kname.getText())).collect(toList());

      if (collectK.size() < 1) {
        TableKey tableKey = new TableKey();
        tableKey.setName(kname.getText());
        tableKey.setType(getByCode(kType.getSelectedItem().toString()));
        tableKey.setFileds(kFileds.getSelectedItem().toString().split(","));
        tableKeys.add(tableKey);
      }

      table.setName(tableName.getText());
      table.setComment(tableDesc.getText());
      table.setTableFields(tableFields);
      table.setTableKeys(tableKeys);

      String tableText = createTable(SwingManager.table);
      answer.setText(tableText);
    });

    // 清空按钮，清空输入框、展示区、全局变量
    buttonX = buttonX + buttonXOffSet;
    JButton resetButton = addJButton(type, "reset", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
    resetButton.addActionListener((o) -> {

      tableName.setText("");
      tableDesc.setText("");
      fName.setText("");
      fType.setSelectedIndex(0);
      fLenth.setText("");
      fNotNullh.setSelectedIndex(0);
      fDefault.setSelectedIndex(0);
      fUpdate.setSelectedIndex(0);
      fIncrement.setSelectedIndex(0);
      fComment.setText("");
      kname.setText("");
      kType.setSelectedIndex(0);
      kFileds.setSelectedIndex(0);

      tableFields.clear();
      tableKeys.clear();
      table = new Table();
      answer.setText("");
    });
  }

  private static void addFiledButtonZone(String type, JPanel contentPanel, int y, JTextArea answer, JTextArea tableName,
      JTextArea fName, JComboBox fType, JTextArea fLenth, JComboBox fNotNullh, JComboBox fDefault, JComboBox fUpdate, JComboBox fIncrement,
      JTextArea fComment, JTextArea fAfter, JTextArea kname, JComboBox kType, JComboBox kFileds) {

    int buttonX = 355;
    int buttonXOffSet = 110;
    y = y + 15;
    // 新增字段按钮，点击后增量到右侧展示
    JButton filedButton = addJButton(type, "add column", TEXT_NORMAL, buttonX, y, 110, 25, contentPanel);
    filedButton.addActionListener((o) -> {

      List<TableField> collect = tableFields.stream().filter((n) -> StringUtils.equals(n.getName(), fName.getText())).collect(toList());

      if (collect.size() < 1) {
        TableField tableField = new TableField();
        tableField.setName(fName.getText());
        tableField.setComment(fComment.getText());
        tableField.setFieldType(getByDesc(fType.getSelectedItem().toString()));
        tableField.setLength(fLenth.getText());
        tableField.setAutoIncrement(Boolean.parseBoolean(fIncrement.getSelectedItem().toString()));
        tableField.setNotNull(Boolean.parseBoolean(fNotNullh.getSelectedItem().toString()));
        tableField.setDefaultValue(fDefault.getSelectedItem().toString());
        tableField.setOnUpdate(fUpdate.getSelectedItem().toString());
        tableField.setAfter(fAfter.getText());
        tableFields.add(tableField);
        table.setTableFields(tableFields);
      }
      String filedText = updateTableAddFile(SwingManager.table);
      String keyText = updateTableAddKey(SwingManager.table);
      answer.setText(filedText + keyText);

    });

    // 新增索引按钮，点击后增量到右侧展示
    buttonX = buttonX + buttonXOffSet;
    JButton keyButton = addJButton(type, "add index", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
    keyButton.addActionListener((o) -> {

      List<TableKey> collect = tableKeys.stream().filter((n) -> StringUtils.equals(n.getName(), kname.getText())).collect(toList());

      if (collect.size() < 1) {
        TableKey tableKey = new TableKey();
        tableKey.setName(kname.getText());
        tableKey.setType(getByCode(kType.getSelectedItem().toString()));
        tableKey.setFileds(kFileds.getSelectedItem().toString().split(","));
        tableKeys.add(tableKey);
        table.setTableKeys(tableKeys);
      }
      String filedText = updateTableAddFile(SwingManager.table);
      String keyText = updateTableAddKey(SwingManager.table);
      answer.setText(filedText + keyText);
    });

    // 预览按钮
    buttonX = buttonX + buttonXOffSet;
    JButton viewButton = addJButton(type, "preview", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
    viewButton.addActionListener((o) -> {

      List<TableField> collectF = tableFields.stream().filter((n) -> StringUtils.equals(n.getName(), fName.getText())).collect(toList());
      if (collectF.size() < 1) {
        TableField tableField = new TableField();
        tableField.setName(fName.getText());
        tableField.setComment(fComment.getText());
        tableField.setFieldType(getByDesc(fType.getSelectedItem().toString()));
        tableField.setLength(fLenth.getText());
        tableField.setAutoIncrement(Boolean.parseBoolean(fIncrement.getSelectedItem().toString()));
        tableField.setNotNull(Boolean.parseBoolean(fNotNullh.getSelectedItem().toString()));
        tableField.setDefaultValue(fDefault.getSelectedItem().toString());
        tableField.setOnUpdate(fUpdate.getSelectedItem().toString());
        tableField.setAfter(fAfter.getText());
        tableFields.add(tableField);
      }

      List<TableKey> collect = tableKeys.stream().filter((n) -> StringUtils.equals(n.getName(), kname.getText())).collect(toList());

      if (collect.size() < 1) {
        TableKey tableKey = new TableKey();
        tableKey.setName(kname.getText());
        tableKey.setType(getByCode(kType.getSelectedItem().toString()));
        tableKey.setFileds(kFileds.getSelectedItem().toString().split(","));
        tableKeys.add(tableKey);
      }

      table.setName(tableName.getText());
      table.setTableFields(tableFields);
      table.setTableKeys(tableKeys);

      String filedText = updateTableAddFile(SwingManager.table);
      String keyText = updateTableAddKey(SwingManager.table);
      answer.setText(filedText + keyText);
    });

    // 清空按钮，清空输入框、展示区、全局变量
    buttonX = buttonX + buttonXOffSet;
    JButton resetButton = addJButton(type, "reset", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
    resetButton.addActionListener((o) -> {

      tableName.setText("");
      fName.setText("");
      fType.setSelectedIndex(0);
      fLenth.setText("");
      fNotNullh.setSelectedIndex(0);
      fDefault.setSelectedIndex(0);
      fUpdate.setSelectedIndex(0);
      fIncrement.setSelectedIndex(0);
      fComment.setText("");
      fAfter.setText("");

      kname.setText("");
      kType.setSelectedIndex(0);
      kFileds.setSelectedIndex(0);

      tableKeys.clear();
      tableFields.clear();
      table = new Table();
      answer.setText("");
    });
  }

  public static void main(String[] args) {
    createAndShowGUI();
  }
}
