package view.grade;

import config.closeWindows;
import mapper.adminMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminCRUDGrade extends JFrame implements ActionListener {
    private String thisLoginTeaId;

    private String[] courseList; // 假设这是获取讲授课程列表的方法
    private  JComboBox<String> courseComboBox;

    private String[] classList = {"请选择选项"}; // 假设这是获取讲授班级列表的方法
    private JComboBox<String> classComboBox = new JComboBox<>(classList);

    private String[] studentList = {"请选择选项"}; // 假设这是获取学生列表的方法
    private JComboBox<String> studentComboBox = new JComboBox<>(studentList);

    // 更新按钮
    private JButton updateOrAddButton = new JButton("更新&添加");
    private JButton deleteButton = new JButton("删除");

    private JTextField scoreTextField = new JTextField();

    private String selectedCourseItem;
    private String selectedStuItem;
    private String selectedClassItem;

    public adminCRUDGrade(String teacher_id) {
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("添加&更新&查询&删除");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        closeWindows.createAndShowFrame(this);
        this.thisLoginTeaId = teacher_id;
        courseList = adminMapper.selectCourseNameByTeaId(thisLoginTeaId);
        courseComboBox = new JComboBox<>(courseList);
        initView();
    }

    private void initView() {
        this.setLayout(null);

        // 计算组件的宽度和位置
        int textFieldWidth = 200;

        // 讲授课程JLabel和JComboBox
        JLabel courseLabel = new JLabel("讲授课程:");
        courseLabel.setBounds(17, 20, 130, 41);
        courseLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(courseLabel);


        courseComboBox.setBounds(150, 27, textFieldWidth, 27);
        courseComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(courseComboBox);
        courseComboBox.addActionListener(this);

        // 讲授班级JLabel和JComboBox
        JLabel classLabel = new JLabel("讲授班级:");
        classLabel.setBounds(17, 70, 130, 41);
        classLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classLabel);


        classComboBox.setBounds(150, 77, textFieldWidth, 27);
        classComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classComboBox);
        classComboBox.addActionListener(this);

        // 学生JLabel和JComboBox
        JLabel studentLabel = new JLabel("学生:");
        studentLabel.setBounds(17, 120, 130, 41);
        studentLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(studentLabel);


        studentComboBox.setBounds(150, 127, textFieldWidth, 27);
        studentComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        studentComboBox.addActionListener(this);
        this.getContentPane().add(studentComboBox);

        // 分数JLabel和JTextField
        JLabel scoreLabel = new JLabel("分数");
        scoreLabel.setBounds(17, 170, 130, 41);
        scoreLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(scoreLabel);


        scoreTextField.setBounds(150, 177, 200, 27);
        scoreTextField.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(scoreTextField);


        // 更新按钮
        updateOrAddButton.setFont(new Font("宋体", Font.BOLD, 22));
        updateOrAddButton.setBounds(17, 620, 150, 50);
        updateOrAddButton.addActionListener(this);
        this.getContentPane().add(updateOrAddButton);

        //删除按钮

        deleteButton.addActionListener(this);
        deleteButton.setFont(new Font("宋体", Font.BOLD, 22));
        deleteButton.setBounds(300, 620, 150, 50);
        this.getContentPane().add(deleteButton);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == courseComboBox) {
            this.selectedCourseItem = (String) courseComboBox.getSelectedItem();
            classList = adminMapper.selectClassNameByCourse_name(selectedCourseItem);
            classComboBox.setModel(new DefaultComboBoxModel<>(classList));
        }
        if (source == classComboBox) {
            this.selectedClassItem = (String) classComboBox.getSelectedItem();
            studentList = adminMapper.selectStuNameByClassName(selectedClassItem);
            studentComboBox.setModel(new DefaultComboBoxModel<>(studentList));
        }
        if (source == studentComboBox) {
            this.selectedStuItem = (String) studentComboBox.getSelectedItem();
            String score = adminMapper.selectStuGradeByStuNameOrCourseName(selectedStuItem, selectedCourseItem);
            scoreTextField.setText(score);
        }
        if (source == updateOrAddButton) {
            if (!scoreTextField.getText().isEmpty()) {
                float score = Float.parseFloat(scoreTextField.getText());
                if (score <= 100 && score >= 0) {
                    boolean result = adminMapper.UpdateGradeNOtoAdd(selectedStuItem, selectedCourseItem, score);
                    if (result) {
                        ImageIcon successIcon = new ImageIcon("src/img/success.png");
                        JOptionPane.showMessageDialog(this, "操作成功", "success", JOptionPane.ERROR_MESSAGE, successIcon);
                    } else {
                        JOptionPane.showMessageDialog(this, "未找到匹配的学生或课程", "error", JOptionPane.ERROR_MESSAGE);
                    }
                } else JOptionPane.showMessageDialog(this, "分数异常", "error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "请输入分数", "warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (source == deleteButton){
//            System.out.println("监听器被触发，当前时间: " + System.currentTimeMillis());
            if (!scoreTextField.getText().isEmpty()) {
                boolean result = adminMapper.deleteGradeForStuByStu_nameCourseName(selectedStuItem, selectedCourseItem);
                if (result) {
                    ImageIcon successIcon = new ImageIcon("src/img/success.png");
                    JOptionPane.showMessageDialog(this, "删除成功", "success", JOptionPane.ERROR_MESSAGE, successIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "未找到匹配的成绩记录", "error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "非法操作", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
