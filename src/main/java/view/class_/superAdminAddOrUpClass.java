package view.class_;

import mapper.superAdminMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class superAdminAddOrUpClass extends JFrame implements ActionListener {
    private String[] idList;
    private JComboBox collegeIdList;
    private String[] majorList;
    private JComboBox majorIdToNameList;
    private String collegeId;
    private String majorId;
    private String[] counselorList;
    private JComboBox counselorComboBox = new JComboBox();
    private String counselorId;
    private JTextField classIdTextField = new JTextField();
    private JTextField classNameTextField = new JTextField();
    private JButton updateButton = new JButton("更新");
    private JButton addButton = new JButton("添加");
    private String classId;
    private String className;

    public superAdminAddOrUpClass() {
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("添加&&更新");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        idList = superAdminMapper.selectAllCollege();
        collegeIdList = new JComboBox<>(idList);
        majorList = new String[]{"请选择选项"};
        majorIdToNameList = new JComboBox<>(majorList);
        counselorList = new String[]{"请选择选项"};
        counselorComboBox = new JComboBox<>(counselorList);
        initView();
    }

    private void initView() {
        // 设置无管理容器
        this.setLayout(null);

        // 学院名称标签
        JLabel idLabel = new JLabel("学院");
        idLabel.setBounds(17, 56, 75, 41);
        idLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(idLabel);

        // 学院名称显示
        collegeIdList.setBounds(100, 65, 300, 27);
        collegeIdList.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdList);
        collegeIdList.addActionListener(this);

        // 专业名称标签
        JLabel nameLabel = new JLabel("专业");
        nameLabel.setBounds(17, 100, 60, 41);
        nameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameLabel);

        // 专业名称显示
        majorIdToNameList.setBounds(100, 107, 260, 27);
        majorIdToNameList.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorIdToNameList);
        majorIdToNameList.addActionListener(this);

        // 辅导员标签和组合框
        JLabel counselorLabel = new JLabel("辅导员");
        counselorLabel.setBounds(17, 144, 100, 41);
        counselorLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(counselorLabel);

        counselorComboBox.setBounds(100, 151, 260, 27);
        counselorComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(counselorComboBox);
        counselorComboBox.addActionListener(this);

        // 班级id标签和输入框
        JLabel classIdLabel = new JLabel("班级");
        classIdLabel.setBounds(17, 196, 100, 41);
        classIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classIdLabel);

        classIdTextField.setBounds(130, 203, 300, 27);
        classIdTextField.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classIdTextField);

        // 班级名称标签和输入框
        JLabel classNameLabel = new JLabel("班级名称");
        classNameLabel.setBounds(17, 248, 100, 41);
        classNameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classNameLabel);

        classNameTextField.setBounds(130, 255, 300, 27);
        classNameTextField.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classNameTextField);

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
            majorList = superAdminMapper.selectMajorByCoId(collegeId);
            majorIdToNameList.setModel(new DefaultComboBoxModel(majorList));
            counselorList = superAdminMapper.selectCByCounselorByCoId(collegeId);
            counselorComboBox.setModel(new DefaultComboBoxModel(counselorList));
        }
        if (source == majorIdToNameList) {
            String selectedItem = (String) majorIdToNameList.getSelectedItem();
            Pattern p = Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            if (m.find()) majorId = m.group();
            classIdTextField.setText(majorId);
        }
        if (source == counselorComboBox) {
            String selectedItem = (String) counselorComboBox.getSelectedItem();
            Pattern p = Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            if (m.find()) counselorId = m.group();
        }
        if (source == addButton) {
            classId = classIdTextField.getText();
            className = classNameTextField.getText();
            boolean flag = superAdminMapper.checkClassExist(classId);
            if (!flag) {
                if (className.isEmpty() || classId.isEmpty() ) {
                    JOptionPane.showMessageDialog(this, "非法操作", "error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean result = superAdminMapper.addClass(classId,className,majorId,counselorId);
                    if (result) {
                        ImageIcon successIcon = new ImageIcon("src/img/success.png");
                        JOptionPane.showMessageDialog(this, "添加成功", "success", JOptionPane.ERROR_MESSAGE, successIcon);
                        this.repaint();
                        this.setVisible(false);
                        new superAdminAddOrUpClass();
                    }
                }
            }else {
                JOptionPane.showMessageDialog(this, "班级号存在", "error", JOptionPane.ERROR_MESSAGE);
            }
        }if (source == updateButton) {
            classId = classIdTextField.getText();
            className = classNameTextField.getText();
            boolean flag = superAdminMapper.checkClassExist(classId);
            if (flag) {
                boolean result = superAdminMapper.partialUpdateClassInfo(className,majorId,counselorId);
                if (result) {
                    ImageIcon successImg = new ImageIcon("src/img/success.png");
                    JOptionPane.showMessageDialog(this, "更新成功", "success", JOptionPane.ERROR_MESSAGE, successImg);
                } else {
                    JOptionPane.showMessageDialog(this, "更新失败", "error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "班级id不存在", "error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
