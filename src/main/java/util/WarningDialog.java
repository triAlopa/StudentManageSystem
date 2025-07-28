package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import config.closeWindows;

public class WarningDialog {

    public static void showWarningDialog(JFrame parent, Runnable onYes) {

        //初始化对话框对象
        /*
        1.父级组件  调用方法的时候，点击，依赖于调用者的界面
        2.标题
        3.对话框 阻塞父级面板操作 一直得等到关闭才可以
         */
       JDialog warning=new JDialog(parent,"确认框",true);
       //大小
       warning.setSize(300,150);
       //居中于父级
       warning.setLocationRelativeTo(parent);
       //位置 布局管理器
       warning.setLayout(new BorderLayout());

       //对话内容
       JLabel messageLabel=new JLabel("你确定要这么操作吗？",SwingUtilities.CENTER);
       messageLabel.setFont(new Font("宋体",Font.BOLD,18));
       warning.add(messageLabel,BorderLayout.CENTER);

       //按钮面板
       JPanel buttonPanel=new JPanel();

       //是
       JButton yesButton=new JButton("是");
       //监听事件
       yesButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //运行该方法，父级调用会重写Runnable接口的run方法
               onYes.run();
               //对话框关闭
               warning.dispose();
           }
       });
       buttonPanel.add(yesButton);

       JButton noButton=new JButton("否");
       noButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               warning.dispose();
           }
       });
       buttonPanel.add(noButton);

       //设置添加为对话框底部
       warning.add(buttonPanel,BorderLayout.SOUTH);

       closeWindows.createAndShowFrame(parent);
       //可见
       warning.setVisible(true);
    }
}
