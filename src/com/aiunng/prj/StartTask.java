package com.aiunng.prj;

import static com.aiunng.prj.manager.SwingManager.createAndShowGUI;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @author：wangXinYu
 * @date：2021/7/16 1:56 下午
 */
public class StartTask extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent anActionEvent) {
    createAndShowGUI();
  }
}
