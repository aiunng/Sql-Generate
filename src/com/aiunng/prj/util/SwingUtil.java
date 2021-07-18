package com.aiunng.prj.util;

import static com.aiunng.prj.enumerate.OperatorTypeEnum.ADD_FILED;
import static com.aiunng.prj.enumerate.OperatorTypeEnum.CREATE_TABLE;

import com.aiunng.prj.enumerate.FieldTypeEnum;
import com.aiunng.prj.enumerate.KeyTypeEnum;
import com.intellij.ui.components.JBScrollPane;
import java.awt.Component;
import java.awt.Font;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author：wangXinYu
 * @date：2021/7/16 4:56 下午
 */
public class SwingUtil {

  private static final Set<Component> createTableComponentSet = new HashSet<>();
  private static final Set<Component> addFiledComponentSet = new HashSet<>();

  public static Set<Component> getCreateTableComponentSet() {
    return createTableComponentSet;
  }

  public static Set<Component> getAddFiledComponentSet() {
    return addFiledComponentSet;
  }

  /**
   * 文本展示
   *
   * @param text
   * @param font
   * @param x
   * @param y
   * @param width
   * @param height
   * @param contentPanel
   * @return
   */
  public static JLabel addLabel(String type, String text, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    JLabel label = new JLabel(text);
    label.setFont(font);
    label.setBounds(x, y, width, height);
    contentPanel.add(label);
    if (StringUtil.equals(CREATE_TABLE.getCode(), type)) {
      createTableComponentSet.add(label);
    } else if (StringUtil.equals(ADD_FILED.getCode(), type)) {
      addFiledComponentSet.add(label);
    }
    return label;
  }

  /**
   * 文本输入
   *
   * @param text
   * @param font
   * @param x
   * @param y
   * @param width
   * @param height
   * @param contentPanel
   * @return
   */
  public static JTextArea addJTextArea(String type, String text, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    JTextArea textArea = new JTextArea();
    textArea.setFont(font);
    textArea.setBounds(x, y, width, height);
    textArea.setText(text);
    contentPanel.add(textArea);
    if (StringUtil.equals(CREATE_TABLE.getCode(), type)) {
      createTableComponentSet.add(textArea);
    } else if (StringUtil.equals(ADD_FILED.getCode(), type)) {
      addFiledComponentSet.add(textArea);
    }
    return textArea;
  }

  /**
   * 按钮
   *
   * @param text
   * @param font
   * @param x
   * @param y
   * @param width
   * @param height
   * @param contentPanel
   * @return
   */
  public static JButton addJButton(String type, String text, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    JButton button = new JButton(text);
    button.setFont(font);
    button.setBounds(x, y, width, height);
    contentPanel.add(button);

    if (StringUtil.equals(CREATE_TABLE.getCode(), type)) {
      createTableComponentSet.add(button);
    } else if (StringUtil.equals(ADD_FILED.getCode(), type)) {
      addFiledComponentSet.add(button);
    }
    return button;
  }

  public static JBScrollPane addJBScrollPane(Font font, int x, int y, int width, int height, JPanel contentPanel) {
    JBScrollPane scrollPane = new JBScrollPane();
    scrollPane.setFont(font);
    scrollPane.setBounds(x, y, width, height);
    // 滚动条
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    contentPanel.add(scrollPane);
    return scrollPane;
  }

  public static JComboBox addFiledTypeComboBox(String type, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    // 创建下拉框
    JComboBox comboBox = new JComboBox();

    // 绑定下拉框选项
    comboBox.addItem("");
    for (FieldTypeEnum value : FieldTypeEnum.values()) {
      comboBox.addItem(value.getDesc());
    }

    comboBox.setFont(font);
    comboBox.setBounds(x, y, width, height);
    comboBox.setSelectedIndex(0);
    comboBox.setEditable(true);
    contentPanel.add(comboBox);

    if (StringUtil.equals(CREATE_TABLE.getCode(), type)) {
      createTableComponentSet.add(comboBox);
    } else if (StringUtil.equals(ADD_FILED.getCode(), type)) {
      addFiledComponentSet.add(comboBox);
    }

    return comboBox;
  }

  public static JComboBox addKeyTypeComboBox(String type, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    // 创建下拉框
    JComboBox comboBox = new JComboBox();

    // 绑定下拉框选项
    comboBox.addItem("");
    for (KeyTypeEnum value : KeyTypeEnum.values()) {
      comboBox.addItem(value.getCode());
    }

    comboBox.setFont(font);
    comboBox.setBounds(x, y, width, height);
    comboBox.setSelectedIndex(0);
    comboBox.setEditable(true);
    contentPanel.add(comboBox);

    if (StringUtil.equals(CREATE_TABLE.getCode(), type)) {
      createTableComponentSet.add(comboBox);
    } else if (StringUtil.equals(ADD_FILED.getCode(), type)) {
      addFiledComponentSet.add(comboBox);
    }

    return comboBox;
  }

  public static JComboBox addBooleanComboBox(String type, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    // 创建下拉框
    JComboBox comboBox = new JComboBox();

    // 绑定下拉框选项
    comboBox.addItem("");
    comboBox.addItem("false");
    comboBox.addItem("true");

    comboBox.setFont(font);
    comboBox.setBounds(x, y, width, height);
    comboBox.setSelectedIndex(0);
    comboBox.setEditable(true);
    contentPanel.add(comboBox);

    if (StringUtil.equals(CREATE_TABLE.getCode(), type)) {
      createTableComponentSet.add(comboBox);
    } else if (StringUtil.equals(ADD_FILED.getCode(), type)) {
      addFiledComponentSet.add(comboBox);
    }

    return comboBox;
  }

  public static JComboBox addDefaultComboBox(String type, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    // 创建下拉框
    JComboBox comboBox = new JComboBox();

    // 绑定下拉框选项
    comboBox.addItem("");
    comboBox.addItem("NULL");
    comboBox.addItem("0(false)");
    comboBox.addItem("1(true)");
    comboBox.addItem("CURRENT_TIMESTAMP(3)");

    comboBox.setFont(font);
    comboBox.setBounds(x, y, width, height);
    comboBox.setSelectedIndex(0);
    comboBox.setEditable(true);
    contentPanel.add(comboBox);

    if (StringUtil.equals(CREATE_TABLE.getCode(), type)) {
      createTableComponentSet.add(comboBox);
    } else if (StringUtil.equals(ADD_FILED.getCode(), type)) {
      addFiledComponentSet.add(comboBox);
    }

    return comboBox;
  }

  public static JComboBox addDefaultKeyComboBox(String type, Font font, int x, int y, int width, int height, JPanel contentPanel) {
    // 创建下拉框
    JComboBox comboBox = new JComboBox();

    // 绑定下拉框选项
    comboBox.addItem("");
    comboBox.addItem("filed1,filed2,filed3");

    comboBox.setFont(font);
    comboBox.setBounds(x, y, width, height);
    comboBox.setSelectedIndex(0);
    comboBox.setEditable(true);
    contentPanel.add(comboBox);

    if (StringUtil.equals(CREATE_TABLE.getCode(), type)) {
      createTableComponentSet.add(comboBox);
    } else if (StringUtil.equals(ADD_FILED.getCode(), type)) {
      addFiledComponentSet.add(comboBox);
    }

    return comboBox;
  }

}
