package com.aiunng.prj.manager;

import static com.aiunng.prj.enumerate.FieldTypeEnum.getByDesc;
import static com.aiunng.prj.enumerate.KeyTypeEnum.getByCode;
import static com.aiunng.prj.enumerate.OperatorTypeEnum.ADD_FILED;
import static com.aiunng.prj.enumerate.OperatorTypeEnum.CREATE_TABLE;
import static com.aiunng.prj.util.Constant.TEXT_NORMAL;
import static com.aiunng.prj.util.Constant.TEXT_SMALL;
import static com.aiunng.prj.util.GenerateSqlUtil.createTable;
import static com.aiunng.prj.util.GenerateSqlUtil.updateTableAddFile;
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
    createTableZone(contentPanel, h, textX, boxX, y, yOffset, yOffsetBThen, boxWidth, boxHight, l2TitleX, answer);

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
      createTableZone(contentPanel, h, textX, boxX, y, yOffset, yOffsetBThen, boxWidth, boxHight, l2TitleX, answer);
      // 更新当前窗体
      contentPanel.updateUI();
    });

    // 卡片 - 表格新增字段
    JButton upDateTableButton = addJButton("", "add column", TEXT_NORMAL, 140, 10, 120, 40, contentPanel);
    upDateTableButton.addActionListener((o) -> {
      // 删除其他操作的展示内容
      getCreateTableComponentSet().forEach(contentPanel::remove);
      tableFields.clear();
      tableKeys.clear();
      table = new Table();
      answer.setText("");

      // 展示新增字段的内容
      addFiledZone(contentPanel, h, textX, boxX, y, yOffset, yOffsetBThen, boxWidth, boxHight, l2TitleX, answer);
      // 更新当前窗体
      contentPanel.updateUI();
    });

    // 关闭按钮
    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // 显示窗口
    frame.setVisible(true);
  }

  private static void createTableZone(JPanel contentPanel, int h, int textX, int boxX, int y, int yOffset, int yOffsetBThen, int boxWidth,
      int boxHight,
      int l2TitleX, JTextArea answer) {
    y = y + 50;
    addLabel(CREATE_TABLE.getCode(), "Table:", TEXT_NORMAL, 10, y, 100, h, contentPanel);
    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Name:", TEXT_NORMAL, l2TitleX, y, 100, h, contentPanel);
    JTextArea tableName = addJTextArea(CREATE_TABLE.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Desc:", TEXT_NORMAL, l2TitleX, y, 100, h, contentPanel);
    JTextArea tableDesc = addJTextArea(CREATE_TABLE.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Column:", TEXT_NORMAL, 10, y, 660, h, contentPanel);
    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Name:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fName = addJTextArea(CREATE_TABLE.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Type:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fType = addFiledTypeComboBox(CREATE_TABLE.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(CREATE_TABLE.getCode(), "Lenth:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fLenth = addJTextArea(CREATE_TABLE.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "NotNull:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fNotNullh = addBooleanComboBox(CREATE_TABLE.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Default:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fDefault = addDefaultComboBox(CREATE_TABLE.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Update:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fUpdate = addDefaultComboBox(CREATE_TABLE.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Increment:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fIncrement = addBooleanComboBox(CREATE_TABLE.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(CREATE_TABLE.getCode(), "Comment:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fComment = addJTextArea(CREATE_TABLE.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Index:", TEXT_NORMAL, 10, y, 660, h, contentPanel);
    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Name:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea kname = addJTextArea(CREATE_TABLE.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(CREATE_TABLE.getCode(), "Type:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox kType = addKeyTypeComboBox(CREATE_TABLE.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(CREATE_TABLE.getCode(), "Columns:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox kFileds = addDefaultKeyComboBox(CREATE_TABLE.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    createTablebuttonZone(contentPanel, y, answer, tableName, tableDesc, fName, fType, fLenth, fNotNullh, fDefault, fUpdate, fIncrement, fComment,
        kname,
        kType, kFileds);
  }

  private static void addFiledZone(JPanel contentPanel, int h, int textX, int boxX, int y, int yOffset, int yOffsetBThen, int boxWidth, int boxHight,
      int l2TitleX, JTextArea answer) {
    y = y + 50;
    addLabel(ADD_FILED.getCode(), "Table:", TEXT_NORMAL, 10, y, 100, h, contentPanel);
    y = y + yOffset;
    addLabel(ADD_FILED.getCode(), "Name:", TEXT_NORMAL, l2TitleX, y, 100, h, contentPanel);
    JTextArea tableName = addJTextArea(ADD_FILED.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(ADD_FILED.getCode(), "Column:", TEXT_NORMAL, 10, y, 660, h, contentPanel);
    y = y + yOffset;
    addLabel(ADD_FILED.getCode(), "Name:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fName = addJTextArea(ADD_FILED.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(ADD_FILED.getCode(), "Type:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fType = addFiledTypeComboBox(ADD_FILED.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(ADD_FILED.getCode(), "Lenth:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fLenth = addJTextArea(ADD_FILED.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffset;
    addLabel(ADD_FILED.getCode(), "NotNull:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fNotNullh = addBooleanComboBox(ADD_FILED.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(ADD_FILED.getCode(), "Default:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fDefault = addDefaultComboBox(ADD_FILED.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(ADD_FILED.getCode(), "Update:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fUpdate = addDefaultComboBox(ADD_FILED.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffset;
    addLabel(ADD_FILED.getCode(), "Increment:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JComboBox fIncrement = addBooleanComboBox(ADD_FILED.getCode(), TEXT_SMALL, boxX, y, boxWidth, boxHight, contentPanel);

    y = y + yOffsetBThen;
    addLabel(ADD_FILED.getCode(), "Comment:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fComment = addJTextArea(ADD_FILED.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);

    y = y + yOffsetBThen;
    addLabel(ADD_FILED.getCode(), "After:", TEXT_NORMAL, l2TitleX, y, 660, h, contentPanel);
    JTextArea fAfter = addJTextArea(ADD_FILED.getCode(), null, TEXT_SMALL, textX, y, 200, h, contentPanel);


    addFiledButtonZone(contentPanel, y, answer, tableName, fName, fType, fLenth, fNotNullh, fDefault, fUpdate, fIncrement, fComment,fAfter);
  }

  private static void createTablebuttonZone(JPanel contentPanel, int y, JTextArea answer, JTextArea tableName, JTextArea tableDesc,
      JTextArea fName, JComboBox fType, JTextArea fLenth, JComboBox fNotNullh, JComboBox fDefault, JComboBox fUpdate, JComboBox fIncrement,
      JTextArea fComment, JTextArea kname, JComboBox kType, JComboBox kFileds) {

    int buttonX = 355;
    int buttonXOffSet = 110;
    y = y + 15;
    // 新增字段按钮，点击后增量到右侧展示
    JButton filedButton = addJButton(CREATE_TABLE.getCode(), "add column", TEXT_NORMAL, buttonX, y, 110, 25, contentPanel);
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
    JButton keyButton = addJButton(CREATE_TABLE.getCode(), "add index", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
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
    JButton viewButton = addJButton(CREATE_TABLE.getCode(), "preview", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
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
    JButton resetButton = addJButton(CREATE_TABLE.getCode(), "reset", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
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

  private static void addFiledButtonZone(JPanel contentPanel, int y, JTextArea answer, JTextArea tableName,
      JTextArea fName, JComboBox fType, JTextArea fLenth, JComboBox fNotNullh, JComboBox fDefault, JComboBox fUpdate, JComboBox fIncrement,
      JTextArea fComment,JTextArea fAfter) {

    int buttonX = 355;
    int buttonXOffSet = 110;
    y = y + 150;
    // 新增字段按钮，点击后增量到右侧展示
    JButton filedButton = addJButton(ADD_FILED.getCode(), "add column", TEXT_NORMAL, buttonX, y, 110, 25, contentPanel);
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
      String tableText = updateTableAddFile(SwingManager.table);
      answer.setText(tableText);

    });

    // 预览按钮
    buttonX = buttonX + buttonXOffSet;
    JButton viewButton = addJButton(ADD_FILED.getCode(), "preview", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
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

      table.setName(tableName.getText());
      table.setTableFields(tableFields);

      String tableText = updateTableAddFile(SwingManager.table);
      answer.setText(tableText);
    });

    // 清空按钮，清空输入框、展示区、全局变量
    buttonX = buttonX + buttonXOffSet;
    JButton resetButton = addJButton(ADD_FILED.getCode(), "reset", TEXT_NORMAL, buttonX, y, 100, 25, contentPanel);
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

      tableFields.clear();
      table = new Table();
      answer.setText("");
    });
  }

  public static void main(String[] args) {
    createAndShowGUI();
  }
}
