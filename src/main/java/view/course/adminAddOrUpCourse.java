package view.course;

import config.closeWindows;
import mapper.adminMapper;
import org.jdatepicker.impl.JDatePickerImpl;
import util.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class adminAddOrUpCourse extends JFrame implements ActionListener {

    private JTextField courseIdText = new JTextField();
    private JTextField nameText = new JTextField();
    private String[] credits = {"1.0", "2.0", "3.0", "3.5", "4.0"};
    private JComboBox<String> creditComboBox = new JComboBox<>(credits);
    private String selectedCreditItem;
    private JTextField classroomText = new JTextField();
    private JDatePickerImpl startTimePicker = DatePicker.getDatePicker();
    private JDatePickerImpl endTimePicker = DatePicker.getDatePicker();
    private String[] classList; // 班级列表，从数据库获取
    private JComboBox<String> classListComboBox;

    private String collegeId;
    private String majorId;
    private String classId;
    private JButton updateButtonLeft = new JButton("更新");
    private JButton addButtonRight = new JButton("添加");
    private Date startDate;
    private Date endDate;
    private String LoginTeacherId;

    private String queryAllCollege = "SELECT college_id, college_name FROM  college ORDER BY college_id ASC";
    private JComboBox<String> collegeComboBox = new JComboBox<>(adminMapper.selectIdAndNameAll(queryAllCollege, "college_id", "college_name"));
    private String queryAllMajor = "SELECT major_id,major_name FROM  major ORDER BY major_id ASC";
    private JComboBox<String> majorComboBox = new JComboBox<>(adminMapper.selectIdAndNameAll(queryAllMajor, "major_id", "major_name"));
    private String queryAllClass = "SELECT class_id,class_name FROM  class ORDER BY class_id ASC";
    private JComboBox<String> classComboBox = new JComboBox<>(adminMapper.selectIdAndNameAll(queryAllClass, "class_id", "class_name"));

    public adminAddOrUpCourse(String id) {
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
        closeWindows.createAndShowFrame(this);
        this.LoginTeacherId = id;
        classList = adminMapper.selectClassListByTeaId(LoginTeacherId);
        classListComboBox = new JComboBox<>(classList);
        initView();
        dateActionListener();
    }

    private void initView() {
        // 取消默认居中放置容器位置，取消了会根据设置的x，y坐标设置位置
        this.setLayout(null);
        int textFieldWidth = 200;
        int textFieldRightX = 95 + textFieldWidth; // 计算最右侧的x坐标

        // ID输入框
        JLabel courseIdLabel = new JLabel("ID:");
        courseIdLabel.setBounds(17, 20, 75, 41);
        courseIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(courseIdLabel);


        courseIdText.setBounds(95, 27, textFieldWidth, 27);
        courseIdText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(courseIdText);

        // 名称输入框
        JLabel nameLabel = new JLabel("名称:");
        nameLabel.setBounds(17, 70, 75, 41);
        nameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameLabel);


        nameText.setBounds(95, 77, textFieldWidth, 27);
        nameText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameText);

        // 学分列表
        JLabel creditLabel = new JLabel("学分:");
        creditLabel.setBounds(17, 120, 75, 41);
        creditLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(creditLabel);


        creditComboBox.setBounds(95, 127, textFieldWidth, 27);
        creditComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(creditComboBox);
        creditComboBox.addActionListener(this);

        // 教室输入框
        JLabel classroomLabel = new JLabel("教室:");
        classroomLabel.setBounds(17, 170, 75, 41);
        classroomLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classroomLabel);


        classroomText.setBounds(95, 177, textFieldWidth, 27);
        classroomText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classroomText);

        // 开课时间日期选择器
        JLabel startTimeLabel = new JLabel("开课时间:");
        startTimeLabel.setBounds(17, 220, 130, 41);
        startTimeLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(startTimeLabel);


        startTimePicker.setBounds(150, 227, 195, 41);
        startTimePicker.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(startTimePicker);

        // 结课时间日期选择器
        JLabel endTimeLabel = new JLabel("结课时间:");
        endTimeLabel.setBounds(17, 270, 130, 41);
        endTimeLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(endTimeLabel);


        endTimePicker.setBounds(150, 277, 195, 41);
        endTimePicker.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(endTimePicker);

        JLabel collegeLabel = new JLabel("学院:");
        collegeLabel.setBounds(17, 320, 75, 41);
        collegeLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeLabel);

        collegeComboBox.setBounds(95, 327, 300, 27);
        collegeComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeComboBox);
        collegeComboBox.addActionListener(this);

        // 专业下拉列表
        JLabel majorLabel = new JLabel("专业:");
        majorLabel.setBounds(17, 360, 75, 41);
        majorLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorLabel);

        majorComboBox.setBounds(95, 367, 300, 27);
        majorComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorComboBox);
        majorComboBox.addActionListener(this);


        JLabel classLabel = new JLabel("开课班级:");
        classLabel.setBounds(17, 400, 130, 41);
        classLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classLabel);


        classComboBox.setBounds(120, 407, 300, 27);
        classComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classComboBox);
        classComboBox.addActionListener(this);


        // 更新按钮

        updateButtonLeft.setFont(new Font("宋体", Font.BOLD, 22));
        updateButtonLeft.setBounds(17, 440, 150, 50);
        this.getContentPane().add(updateButtonLeft);
        updateButtonLeft.addActionListener(this);


        addButtonRight.setFont(new Font("宋体", Font.BOLD, 22));
        addButtonRight.setBounds(300, 440, 150, 50);
        this.getContentPane().add(addButtonRight);
        addButtonRight.addActionListener(this);

        this.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == creditComboBox) {
            this.selectedCreditItem = (String) creditComboBox.getSelectedItem();
        }
        if (source == classComboBox) {
            String selectClassItem = (String) classComboBox.getSelectedItem();
            Pattern p = Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectClassItem);
            if (m.find()) {
                this.classId = m.group();
            }
        }
        if (source == addButtonRight) {
            String courseIdTex = courseIdText.getText();
            String course_name = nameText.getText();
            String classroomTextText = classroomText.getText();
            String college_id = adminMapper.queryCoIdByTeacherId(this.LoginTeacherId);
            if (!adminMapper.checkCourseExist(courseIdTex)) {
                boolean result = adminMapper.addCourseByTea(courseIdTex, course_name, selectedCreditItem, classroomTextText, startDate, endDate, this.LoginTeacherId, classId, college_id);
                if (result) {
                    ImageIcon successIcon = new ImageIcon("src/img/success.png");
                    JOptionPane.showMessageDialog(this, "添加成功！", "success", JOptionPane.INFORMATION_MESSAGE, successIcon);
                    this.setVisible(false);
                    new adminAddOrUpCourse(this.LoginTeacherId);
                } else {
                    JOptionPane.showMessageDialog(this, "非法操作！", "error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "课程号存在！", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (source == updateButtonLeft) {
            String courseIdTex = courseIdText.getText();
            String course_name = nameText.getText();
            String classroomTextText = classroomText.getText();
            //    String college_id = adminMapper.queryCoIdByTeacherId(this.LoginTeacherId);
            if(!courseIdTex.isEmpty()){
                if (adminMapper.checkCourseExist(courseIdTex)) {
                    if (course_name.isEmpty()) course_name = null;
                    boolean result = adminMapper.partialUpdateCourseInfo(course_name, selectedCreditItem, classroomTextText, startDate, endDate, courseIdTex);
                    if (result) {
                        ImageIcon successIcon = new ImageIcon("src/img/success.png");
                        JOptionPane.showMessageDialog(this, "更新成功！", "success", JOptionPane.INFORMATION_MESSAGE, successIcon);
                        this.setVisible(false);
                        new adminAddOrUpCourse(this.LoginTeacherId);
                    }
                } else JOptionPane.showMessageDialog(this, "课程号不存在！", "error", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "非法操作！", "error", JOptionPane.ERROR_MESSAGE);
            }

        }
        Pattern p = Pattern.compile("[0-9]{0,}");
        if (source == collegeComboBox) {
            String collegeSelectItem = (String) collegeComboBox.getSelectedItem();
            Matcher m = p.matcher(collegeSelectItem);
            if (m.find()) collegeId = m.group();

            String queryMajorById = "select major_id,major_name from major where college_id= ?";
            majorComboBox.setModel(new DefaultComboBoxModel<>(adminMapper.selectItemById(queryMajorById, "major_id", "major_name", collegeId)));
            String queryClassById = "SELECT  cl.class_id , cl.class_name FROM   Class cl JOIN Major m ON cl.major_id = m.major_id JOIN  College c ON m.college_id = c.college_id WHERE  c.college_id = ? ";
            classComboBox.setModel(new DefaultComboBoxModel<>(adminMapper.selectItemById(queryClassById, "class_id", "class_name", collegeId)));

        }
        if (source == majorComboBox) {
            String majorSelectItem = (String) majorComboBox.getSelectedItem();
            Matcher m = p.matcher(majorSelectItem);
            if (m.find()) majorId = m.group();

            String queryClassById = "SELECT  cl.class_id , cl.class_name FROM   Class cl JOIN Major m ON cl.major_id = m.major_id WHERE   m.major_id = ? ";
            classComboBox.setModel(new DefaultComboBoxModel<>(adminMapper.selectItemById(queryClassById, "class_id", "class_name", majorId)));
        }
        if (source == classComboBox) {
            String classSelectItem = (String) classComboBox.getSelectedItem();
            Matcher m = p.matcher(classSelectItem);
            if (m.find()) classId = m.group();

        }
    }

    public void dateActionListener() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        startTimePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDate = (java.util.Date) startTimePicker.getModel().getValue();
                // 格式必须为 "yyyy-MM-dd"
//                System.out.println(birthDate);
            }
        });
        endTimePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endDate = (java.util.Date) endTimePicker.getModel().getValue();
            }
        });
    }
}
