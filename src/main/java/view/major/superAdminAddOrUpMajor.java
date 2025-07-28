package view.major;

import mapper.superAdminMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class superAdminAddOrUpMajor extends JFrame implements ActionListener {
    private String[] idList;
    private JComboBox collegeIdList;
    private JTextField majorIdDisplay = new JTextField();
    private JTextField majorNameText = new JTextField();
    private JButton updateButton = new JButton("更新");
    private JButton addButton = new JButton("添加");
    private String collegeId;

    public superAdminAddOrUpMajor() {
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("添加&更新");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        idList = superAdminMapper.selectAllCollege();
        collegeIdList = new JComboBox<>(idList);
        initView();
    }

    private void initView() {
        this.setLayout(null);

        // ID标签
        JLabel idLabel = new JLabel("学院:");
        idLabel.setBounds(17, 56, 75, 41);
        idLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(idLabel);

        // ID列表
        collegeIdList.setBounds(100, 65, 300, 27);
        collegeIdList.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdList);
        collegeIdList.addActionListener(this);

        // 专业id标签
        JLabel majorIdLabel = new JLabel("专业id:");
        majorIdLabel.setBounds(17, 100, 90, 41);
        majorIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorIdLabel);

        // 专业id显示
        majorIdDisplay.setBounds(120, 110, 200, 27);
        majorIdDisplay.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorIdDisplay);


        JLabel majorName = new JLabel("专业名");
        majorName.setBounds(17, 144, 100, 41);
        majorName.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorName);


        majorNameText.setBounds(120, 155, 200, 27);
        majorNameText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorNameText);

        // 更新按钮
        updateButton.setFont(new Font("宋体", Font.BOLD, 22));
        updateButton.setBounds(17, 650, 150, 50); // 设置位置和大小，使其位于窗口的左下角
        this.getContentPane().add(updateButton);
        updateButton.addActionListener(this);

        // 添加按钮
        addButton.setFont(new Font("宋体", Font.BOLD, 22));
        addButton.setBounds(300, 650, 150, 50); // 设置位置和大小，使其位于窗口的右下角
        this.getContentPane().add(addButton);
        addButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (collegeIdList == source) {
            String selectedItem = (String) collegeIdList.getSelectedItem();
            Pattern p = Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            if (m.find()) collegeId = m.group();
            majorIdDisplay.setText(collegeId);
        }
        if (source == addButton) {
            String majorName = majorNameText.getText();
            String majorId = majorIdDisplay.getText();
            boolean flag = superAdminMapper.checkMajorExist(majorId);
            if (!flag) {
                if (majorName.isEmpty() || majorId.isEmpty() || collegeId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "非法操作", "error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean result = superAdminMapper.addMajor(collegeId, majorId, majorName);
                    if (result) {
                        ImageIcon successIcon = new ImageIcon("src/img/success.png");
                        JOptionPane.showMessageDialog(this, "添加成功", "success", JOptionPane.ERROR_MESSAGE, successIcon);
                        this.setVisible(false);
                        new superAdminAddOrUpMajor();
                    }
                }
            }else {
                JOptionPane.showMessageDialog(this, "专业号存在", "error", JOptionPane.ERROR_MESSAGE);
            }
        }if (source == updateButton) {
            String majorName = majorNameText.getText();
            String majorId = majorIdDisplay.getText();
            boolean flag = superAdminMapper.checkMajorExist(majorId);
                if (flag) {
                    boolean result = superAdminMapper.partialUpdateMajorInfo(majorId,majorName);
                    if (result) {
                        ImageIcon successImg = new ImageIcon("src/img/success.png");
                        JOptionPane.showMessageDialog(this, "更新成功", "success", JOptionPane.ERROR_MESSAGE, successImg);
                    } else {
                        JOptionPane.showMessageDialog(this, "更新失败", "error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "学院id不存在", "error", JOptionPane.ERROR_MESSAGE);
                }
        }

    }
}
