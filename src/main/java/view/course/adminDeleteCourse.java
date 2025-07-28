package view.course;

import mapper.adminMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminDeleteCourse extends JFrame implements ActionListener {

    // 创建课程号输入框
    private JTextField courseId = new JTextField();
    // 创建提交按钮
    private JButton submitButton = new JButton("提交");


    public adminDeleteCourse() {
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("删除课程");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
//        closeWindows.createAndShowFrame(this);
        initView();
    }

    private void initView() {
        this.setLayout(null);
        // 创建学号标签
        JLabel stuIdLabel = new JLabel("id:");
        stuIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        stuIdLabel.setBounds(17, 620, 75, 41); // 设置位置和大小，参考已有组件
        this.add(stuIdLabel);


        courseId.setFont(new Font("宋体", Font.BOLD, 22));
        courseId.setBounds(95, 627, 200, 27); // 设置位置和大小，参考已有组件
        this.add(courseId);

        submitButton.setFont(new Font("宋体", Font.BOLD, 22));
        submitButton.setBounds(300, 627, 150, 50); // 设置位置和大小，参考已有组件
        this.add(submitButton);
        submitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == submitButton) {
            String stuIdTextText = courseId.getText();
            if (!stuIdTextText.isEmpty()) {
                boolean result = adminMapper.deleteCourseById(stuIdTextText);
                if (result) {
                    ImageIcon successIcon = new ImageIcon("src/img/success.png");
                    JOptionPane.showMessageDialog(this, "删除成功！", "success", JOptionPane.INFORMATION_MESSAGE, successIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "课程号不存在！", "error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "非法操作！", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
