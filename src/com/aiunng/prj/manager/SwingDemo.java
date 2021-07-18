package com.aiunng.prj.manager;

import static com.aiunng.prj.enumerate.OperatorTypeEnum.CREATE_TABLE;
import static com.aiunng.prj.util.Constant.TEXT_NORMAL;
import static com.aiunng.prj.util.SwingUtil.addJButton;
import static com.aiunng.prj.util.SwingUtil.addLabel;

import com.intellij.util.ui.JBUI;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author：wangXinYu
 * @date：2021/7/18 10:34 上午
 */
public class SwingDemo {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Sql-Generate");
    frame.setBounds(360, 100, 860, 660);

    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    JPanel contentPanel = new JPanel();
    contentPanel.setBorder(JBUI.Borders.empty(5));
    contentPane.add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);

    // 关闭按钮
    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // 显示窗口
    frame.setVisible(true);
  }
}
