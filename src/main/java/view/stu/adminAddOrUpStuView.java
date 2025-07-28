package view.stu;

import config.closeWindows;
import mapper.adminMapper;
import org.jdatepicker.impl.JDatePickerImpl;

import util.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class adminAddOrUpStuView extends JFrame implements ActionListener {
    private JTextField stuIdText = new JTextField();
    private JTextField nameText = new JTextField();
    private String[] genderOptions = {"男", "女", "其它"};
    private JDatePickerImpl birthDatePicker = DatePicker.getDatePicker();
    private JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);
    private JTextField phoneText = new JTextField();
    private JTextField emailText = new JTextField();
    private JDatePickerImpl enrollmentDatePicker = DatePicker.getDatePicker();
    private String[] graduationStatusOptions = {"0.在读", "1.毕业", "2.肆业"};
    private JComboBox<String> graduationStatusBox = new JComboBox<>(graduationStatusOptions);
    private String genderSelectItem = "男";
    private String graduationSelectItem;
    private java.util.Date birthDate;
    private java.util.Date enrollmentDate;
    private String queryAllCollege = "SELECT college_id, college_name FROM  college ORDER BY college_id ASC";
    private JComboBox<String> collegeComboBox = new JComboBox<>(adminMapper.selectIdAndNameAll(queryAllCollege, "college_id", "college_name"));
    private String queryAllMajor = "SELECT major_id,major_name FROM  major ORDER BY major_id ASC";
    private JComboBox<String> majorComboBox = new JComboBox<>(adminMapper.selectIdAndNameAll(queryAllMajor, "major_id", "major_name"));
    private String queryAllClass = "SELECT class_id,class_name FROM  class ORDER BY class_id ASC";
    private JComboBox<String> classComboBox = new JComboBox<>(adminMapper.selectIdAndNameAll(queryAllClass, "class_id", "class_name"));
    private String queryAllCounselor = "SELECT counselor_id,name FROM  counselor ORDER BY counselor_id ASC";
    private JComboBox<String> counselorComboBox = new JComboBox<>(adminMapper.selectIdAndNameAll(queryAllCounselor, "counselor_id", "name"));
    private String[] gradeOptions = {"大一", "大二", "大三", "大四"};
    private JComboBox<String> gradeComboBox = new JComboBox<>(gradeOptions);
    private String collegeId;
    private String majorId;
    private String classId;
    private String counselorId;
    private String selectGradeItem;
    private boolean genderFlag = false;
    private boolean graduationFlag = false;


    private JButton addButton = new JButton("添加");
    private JButton updateButton = new JButton("更新");


    public adminAddOrUpStuView() {
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("添加学生");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        closeWindows.createAndShowFrame(this);
        initView();
        dateActionListener();
    }

    private void initView() {
        //取消默认居中放置容器位置，取消了会根据设置的x，y坐标设置位置
        this.setLayout(null);
        int textFieldWidth = 200;
        int textFieldRightX = 95 + textFieldWidth; // 计算最右侧的x坐标

        JLabel stuIdLabel = new JLabel("学号:");
        stuIdLabel.setBounds(17, 56, 75, 41);
        stuIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(stuIdLabel);

        stuIdText.setBounds(95, 65, textFieldWidth, 27);
        stuIdText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(stuIdText);


        JLabel nameLabel = new JLabel("姓名:");
        nameLabel.setBounds(17, 100, 75, 41);
        nameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameLabel);

        nameText.setBounds(95, 107, textFieldWidth, 27);
        nameText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameText);


        JLabel genderLabel = new JLabel("性别:");
        genderLabel.setBounds(17, 144, 75, 41);
        genderLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(genderLabel);


        genderComboBox.setBounds(95, 151, textFieldWidth, 27);
        genderComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(genderComboBox);
        genderComboBox.addActionListener(this);

//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 17;
//        gbc.gridy = 188;
//        gbc.insets = new Insets(0, 0, 0, 0);

        JLabel birthDateLabel = new JLabel("出生日期:");
        birthDateLabel.setBounds(17, 188, 130, 41);
        birthDateLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(birthDateLabel);


//        JDatePicker birthDatePicker = DatePicker.getDatePicker();
//
//        gbc.fill = GridBagConstraints.HORIZONTAL; // 水平填充
//        this.add((Component) birthDatePicker, gbc);


        birthDatePicker.setBounds(150, 195, 195, 41);
        birthDatePicker.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(birthDatePicker);
        birthDatePicker.addActionListener(this);


        JLabel phoneLabel = new JLabel("电话号码:");
        phoneLabel.setBounds(17, 232, 130, 41);
        phoneLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(phoneLabel);

        phoneText.setBounds(145, 239, 200, 27);
        phoneText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(phoneText);

        JLabel emailLabel = new JLabel("电子邮箱:");
        emailLabel.setBounds(17, 276, 130, 41);
        emailLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(emailLabel);

        emailText.setBounds(145, 283, 200, 27);
        emailText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(emailText);

        JLabel enrollmentDateLabel = new JLabel("入学日期:");
        enrollmentDateLabel.setBounds(17, 320, 130, 41);
        enrollmentDateLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(enrollmentDateLabel);


        enrollmentDatePicker.setBounds(150, 327, 195, 41);
        enrollmentDatePicker.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(enrollmentDatePicker);
        enrollmentDatePicker.addActionListener(this);


        JLabel graduationStatusLabel = new JLabel("学业状态:");
        graduationStatusLabel.setBounds(17, 364, 130, 41);
        graduationStatusLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(graduationStatusLabel);

        graduationStatusBox.setBounds(145, 371, 200, 27);
        graduationStatusBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(graduationStatusBox);
        graduationStatusBox.addActionListener(this);

        JLabel collegeLabel = new JLabel("学院:");
        collegeLabel.setBounds(17, 400, 75, 41);
        collegeLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeLabel);


        collegeComboBox.setBounds(95, 407, 200, 27);
        collegeComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeComboBox);
        collegeComboBox.addActionListener(this);

        // 专业下拉列表
        JLabel majorLabel = new JLabel("专业:");
        majorLabel.setBounds(17, 444, 75, 41);
        majorLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorLabel);


        majorComboBox.setBounds(95, 451, 200, 27);
        majorComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorComboBox);
        majorComboBox.addActionListener(this);

        // 班级下拉列表
        JLabel classLabel = new JLabel("班级:");
        classLabel.setBounds(17, 488, 75, 41);
        classLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classLabel);


        classComboBox.setBounds(95, 495, 200, 27);
        classComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classComboBox);
        classComboBox.addActionListener(this);

        // 辅导员ID和名字输入框
        JLabel counselorLabel = new JLabel("辅导员:");
        counselorLabel.setBounds(17, 532, 90, 41);
        counselorLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(counselorLabel);


        counselorComboBox.setBounds(95, 539, 200, 27);
        counselorComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(counselorComboBox);
        counselorComboBox.addActionListener(this);

        JLabel gradeLabel = new JLabel("年级:");
        gradeLabel.setBounds(17, 576, 75, 41);
        gradeLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(gradeLabel);


        gradeComboBox.setBounds(95, 583, 200, 27);
        gradeComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(gradeComboBox);
        gradeComboBox.addActionListener(this);

        addButton.setFont(new Font("宋体", Font.BOLD, 22));
        addButton.setBounds(300, 650, 150, 50); // Set the position and size of the button
        this.getContentPane().add(addButton);
        addButton.addActionListener(this);

        updateButton.setFont(new Font("宋体", Font.BOLD, 22));
        updateButton.setBounds(140, 650, 150, 50); // 设置位置和大小，使其位于添加按钮的左侧
        this.getContentPane().add(updateButton);
        updateButton.addActionListener(this);

        this.repaint();
    }


    private void initJComboBox(String id) {
        JComboBox<String> jComboBox = new JComboBox<>();
    }

//    private String[] getCollegeOptions() {
//          return null;
//    }

    public int getGraduationStatus(String Graduation) {
        if (Graduation == null) {
            return 0;
        }
        switch (Graduation.toLowerCase()) {
            case "0.在读": {
                return 0;
            }
            case "1.毕业":
                return 1;
            case "2.肆业":
                return 2;
            default:
                return 0;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Pattern p = Pattern.compile("[0-9]{1,}");
        if (source == genderComboBox) {
            this.genderFlag = true;
            genderSelectItem = (String) genderComboBox.getSelectedItem();
        }
        if (source == graduationStatusBox) {
            graduationSelectItem = (String) graduationStatusBox.getSelectedItem();
            this.graduationFlag = true;
        }
        if (source == collegeComboBox) {
            String collegeSelectItem = (String) collegeComboBox.getSelectedItem();
            Matcher m = p.matcher(collegeSelectItem);
            if (m.find()) collegeId = m.group();

            String queryMajorById = "select major_id,major_name from major where college_id= ?";
            majorComboBox.setModel(new DefaultComboBoxModel<>(adminMapper.selectItemById(queryMajorById, "major_id", "major_name", collegeId)));
            String queryClassById = "SELECT  cl.class_id , cl.class_name FROM   Class cl JOIN Major m ON cl.major_id = m.major_id JOIN  College c ON m.college_id = c.college_id WHERE  c.college_id = ? ";
            classComboBox.setModel(new DefaultComboBoxModel<>(adminMapper.selectItemById(queryClassById, "class_id", "class_name", collegeId)));
            String queryCounselorById = "SELECT   co.counselor_id,co.name  FROM   Counselor co JOIN   College c ON co.college_id = c.college_id  WHERE  c.college_id = ?";
            counselorComboBox.setModel(new DefaultComboBoxModel<>(adminMapper.selectItemById(queryCounselorById, "counselor_id", "name", collegeId)));
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

            stuIdText.setText(classId);

        }
        if (source == counselorComboBox) {
            String counselorSelectItem = (String) counselorComboBox.getSelectedItem();
            Matcher m = p.matcher(counselorSelectItem);
            if (m.find()) counselorId = m.group();

        }
        if (source == gradeComboBox) {
            selectGradeItem = (String) gradeComboBox.getSelectedItem();
        }

        if (source == addButton) {
            String student_id = stuIdText.getText();
            String name = nameText.getText();
            String gender = genderSelectItem;
            String phone = phoneText.getText();
            String email = emailText.getText();
            int graduationStatus = 0;
            graduationStatus = getGraduationStatus(graduationSelectItem);
            boolean result2 = false;
            boolean result1 = false;
            String stuIdRegex = "[525][0-9]{10}";
            String emailRegex="([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})";
            if (!student_id.isEmpty()) {
                if (!adminMapper.checkStuExist(student_id)) {
                    if (birthDate != null && enrollmentDate != null && (!name.isEmpty()) && (!phone.isEmpty())) {
                        if (student_id.matches(stuIdRegex)&&email.matches(emailRegex)) {
                            result2 = adminMapper.addStuInfo(student_id, name, gender, birthDate, phone, email, enrollmentDate, graduationStatus);
                            result1 = adminMapper.addStuBaseInfo(student_id, counselorId, classId, majorId, selectGradeItem, collegeId);
                            if (result1 && result2) {
                                ImageIcon successIcon = new ImageIcon("src/img/success.png");
                                JOptionPane.showMessageDialog(this, "添加成功！", "success", JOptionPane.INFORMATION_MESSAGE, successIcon);
                                this.setVisible(false);
                                new adminAddOrUpStuView();
                            } else {
                                JOptionPane.showMessageDialog(this, "非法操作！", "error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "添加信息不合法", "error", JOptionPane.ERROR_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "请输入信息", "error", JOptionPane.ERROR_MESSAGE);
                    }
                } else JOptionPane.showMessageDialog(this, "学号存在", "error", JOptionPane.ERROR_MESSAGE);

            } else JOptionPane.showMessageDialog(this, "请输入学号", "warning", JOptionPane.WARNING_MESSAGE);

        }
        if (source == updateButton) {
            String student_id = stuIdText.getText();
            String name = nameText.getText();
            String gender = genderSelectItem;
            String phone = phoneText.getText();
            String email = emailText.getText();
            int graduationStatus = 0;
            graduationStatus = getGraduationStatus(graduationSelectItem);
            if (!student_id.isEmpty()) {
                if (adminMapper.checkStuExist(student_id)) {
                    if (name.isEmpty()) name = null;
                    if (phone.isEmpty()) phone = null;
                    if (email.isEmpty()) email = null;
                    if (!genderFlag) gender = null;
                    if (!graduationFlag) graduationStatus = adminMapper.queryStatusByStuId(student_id);
                    boolean result1 = adminMapper.partialUpdateStudent(student_id, name, gender, birthDate, phone, email, enrollmentDate, graduationStatus);
                    boolean result2 = adminMapper.partialUpdateStudentBaseInfo(student_id, counselorId, classId, majorId, selectGradeItem, collegeId);
                    if (result1 || result2) {
                        ImageIcon successIcon = new ImageIcon("src/img/success.png");
                        JOptionPane.showMessageDialog(this, "更新成功！", "success", JOptionPane.INFORMATION_MESSAGE, successIcon);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "学号不存在", "error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "非法操作", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void dateActionListener() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        birthDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                birthDate = (java.util.Date) birthDatePicker.getModel().getValue();
                // 格式必须为 "yyyy-MM-dd"
//                System.out.println(birthDate);
            }
        });
        enrollmentDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollmentDate = (java.util.Date) enrollmentDatePicker.getModel().getValue();
            }
        });
    }
}
