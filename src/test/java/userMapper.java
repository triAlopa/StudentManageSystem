import config.closeWindows;
import config.database;
import entity.Academicinfo;
import entity.Grade;
import entity.Student;
import view.stu.userQueryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class userMapper extends JFrame implements ActionListener {
    private JTextField stuIdText = new JTextField();
    private JButton queryByStuIdButton = new JButton("详细");
    private JButton baseQueryByStuIdButton = new JButton("基础");

    private JTextField classIdText = new JTextField();
    private JButton queryByClassIdButton = new JButton("详细");
    private JButton baseQueryByClassIdButton = new JButton("基础");

    private JTextField majorText = new JTextField();
    private JButton queryByMajorIdButton = new JButton("详细");
    private JButton baseQueryByMajorIdButton = new JButton("基础");

    private JTextField collegeIdText = new JTextField();
    private JButton queryByColegeIdButton = new JButton("详细");
    private JButton baseQueryByColegeIdButton = new JButton("基础");

    private JTextField stuNameText = new JTextField();

    private JComboBox<String> collegeComboBox = null;

    private JComboBox<String> majorComboBox = null;

    private String queryMajorSql = "select m.major_name from major m";

    private JComboBox<String> classComboBox = null;

    private String queryClassSql = "select c.class_name from class c";

    private Academicinfo academicInfo = null;
    private Student student = null;

    String[] majorOptions = initComboBox(queryMajorSql, "major_name");
    String[] collegeOptions = initComboBox("select college_name from college", "college_name");
    private JButton queryButton=new JButton(new ImageIcon("src/img/query.png"));

    public userMapper() throws SQLException {
        new database();
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("查询学生");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        closeWindows.createAndShowFrame(this);

        this.collegeComboBox = new JComboBox<>(collegeOptions);

        this.majorComboBox = new JComboBox<>(majorOptions);
        String[] classOptions = initComboBox(queryClassSql, "class_name");
        this.classComboBox = new JComboBox<>(classOptions);
        initView();
    }

    public userMapper(String thisLoginStuId) throws SQLException {
        ArrayList<Grade> grades = stuGradeInfo(thisLoginStuId);
//        closeWindows.createAndShowFrame(this);
        new userQueryView(grades, true);
    }

    //学生学业基本查询
    public Academicinfo queryAcademicInfoById(String student_id) throws SQLException {
        Connection connection = database.getConnection();
        String querySql = "SELECT * FROM StudentDetailView WHERE student_id = ?";
        PreparedStatement ps = connection.prepareStatement(querySql);

        ps.setString(1, student_id);

        ResultSet rs = ps.executeQuery();
        //调用查询必须先使用next方法才会移动到下一行

        Academicinfo stuAcademicinfo = new Academicinfo();
        if (rs.next()) {
            stuAcademicinfo.setStudentId(rs.getString("student_id"));
            stuAcademicinfo.setCounselorId(rs.getString("counselor_name"));
            stuAcademicinfo.setClassId(rs.getString("class_name"));
            stuAcademicinfo.setMajorId(rs.getString("major_name"));
            stuAcademicinfo.setCollegeId(rs.getString("college_name"));
            stuAcademicinfo.setGrade(rs.getString("grade"));
        } else stuAcademicinfo = null;
        rs.close();
        return stuAcademicinfo;
    }

    public Student queryInfoByUid(String student_id) throws SQLException {
        Connection connection = database.getConnection();
        String querySql = "select * from student where student_id = ?";

        PreparedStatement ps = connection.prepareStatement(querySql);

        ps.setString(1, student_id);

        ResultSet rs = ps.executeQuery();
        //调用查询必须先使用next方法才会移动到下一行

        Student student = new Student();
        if (rs.next()) {
            student.setStudentId(rs.getString("student_id"));
            student.setName(rs.getString("name"));
            student.setGender(rs.getObject("gender"));
            student.setBirthDate(rs.getString("birth_date"));
            student.setPhone(rs.getString("phone"));
            student.setEmail(rs.getString("email"));
            student.setEnrollmentDate(rs.getString("enrollment_date"));
            student.setGraduationStatus(rs.getInt("graduation_status"));
        } else student = null;
        rs.close();
        return student;
    }

    public ArrayList<Student> queryInfoByMixId(String mixId, String querySql) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement ps = connection.prepareStatement(querySql);

        ps.setString(1, mixId);

        ResultSet rs = ps.executeQuery();
        //调用查询必须先使用next方法才会移动到下一行
        ArrayList<Student> students = new ArrayList<>();
        int findCount = 0;

        while (rs.next()) {
            Student student = new Student();
            student.setStudentId(rs.getString("student_id"));
            student.setName(rs.getString("name"));
            student.setGender(rs.getObject("gender"));
            student.setBirthDate(rs.getString("birth_date"));
            student.setPhone(rs.getString("phone"));
            student.setEmail(rs.getString("email"));
            student.setEnrollmentDate(rs.getString("enrollment_date"));
            student.setGraduationStatus(rs.getInt("graduation_status"));
            findCount = rs.getRow();
            students.add(student);
        }
        if (findCount == 0) students = null;

        rs.close();
        return students;
    }

    public ArrayList<Academicinfo> baseQueryInfoByMixId(String mixId, String querySql) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement ps = connection.prepareStatement(querySql);

        ps.setString(1, mixId);

        ResultSet rs = ps.executeQuery();
        //调用查询必须先使用next方法才会移动到下一行
        ArrayList<Academicinfo> stuAcademicinfos = new ArrayList<>();
        int findCount = 0;

        while (rs.next()) {
            Academicinfo stuAcademicinfo = new Academicinfo();
            stuAcademicinfo.setStudentId(rs.getString("student_id"));
            stuAcademicinfo.setCounselorId(rs.getString("counselor_name"));
            stuAcademicinfo.setClassId(rs.getString("class_name"));
            stuAcademicinfo.setMajorId(rs.getString("major_name"));
            stuAcademicinfo.setCollegeId(rs.getString("college_name"));
            stuAcademicinfo.setGrade(rs.getString("grade"));
            findCount = rs.getRow();
            stuAcademicinfos.add(stuAcademicinfo);

        }
        if (findCount == 0) stuAcademicinfos = null;

        rs.close();
        return stuAcademicinfos;
    }

    public ArrayList<Grade> stuGradeInfo(String thisLoginStuId) throws SQLException {
        Connection connection = database.getConnection();

        String querySql = "SELECT  c.course_name,     c.credit,    g.score,   g.is_passed FROM    Grade g JOIN     Course c ON g.course_id = c.course_id WHERE   g.student_id = ? ORDER BY  g.score DESC";

        PreparedStatement ps = connection.prepareStatement(querySql);

        ps.setString(1, thisLoginStuId);

        ResultSet rs = ps.executeQuery();
        //调用查询必须先使用next方法才会移动到下一行
        ArrayList<Grade> stuGradeInfoList = new ArrayList<>();
        int findCount = 0;

        while (rs.next()) {
            Grade stuGrade = new Grade();
            stuGrade.setCourseId(rs.getString("course_name"));
            stuGrade.setScore(rs.getBigDecimal("score"));
            stuGrade.setIsPassed(rs.getInt("is_passed"));
            stuGradeInfoList.add(stuGrade);
            findCount++;
        }
        if (findCount == 0) stuGradeInfoList = null;

        rs.close();
        return stuGradeInfoList;
    }


    public void initView() {
        //this.setAlwaysOnTop(true);
        //取消默认居中放置容器位置，取消了会根据设置的x，y坐标设置位置
        this.setLayout(null);

        JLabel jTextArea = new JLabel("学号:");
        jTextArea.setBounds(17, 56, 75, 41);
        jTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(jTextArea);


        stuIdText.setBounds(95, 65, 160, 27);
        stuIdText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(stuIdText);


        queryByStuIdButton.setBounds(270, 65, 85, 27);
        queryByStuIdButton.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(queryByStuIdButton);

        queryByStuIdButton.addActionListener(this);

        baseQueryByStuIdButton.setBounds(370, 65, 85, 27);
        baseQueryByStuIdButton.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(baseQueryByStuIdButton);

        baseQueryByStuIdButton.addActionListener(this);

        /// ////

        JLabel classTextArea = new JLabel("班号:");
        classTextArea.setBounds(17, 100, 75, 41);
        classTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classTextArea);


        classIdText.setBounds(95, 107, 160, 27);
        classIdText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classIdText);


        queryByClassIdButton.setBounds(270, 107, 85, 27);
        queryByClassIdButton.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(queryByClassIdButton);

        queryByClassIdButton.addActionListener(this);

        baseQueryByClassIdButton.setBounds(370, 107, 85, 27);
        baseQueryByClassIdButton.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(baseQueryByClassIdButton);

        baseQueryByClassIdButton.addActionListener(this);

        /// ////

        JLabel majorTextArea = new JLabel("专业id:");
        majorTextArea.setBounds(17, 144, 90, 41);
        majorTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorTextArea);


        majorText.setBounds(105, 151, 150, 27);
        majorText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorText);


        queryByMajorIdButton.setBounds(270, 151, 85, 27);
        queryByMajorIdButton.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(queryByMajorIdButton);

        queryByMajorIdButton.addActionListener(this);

        baseQueryByMajorIdButton.setBounds(370, 151, 85, 27);
        baseQueryByMajorIdButton.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(baseQueryByMajorIdButton);

        baseQueryByMajorIdButton.addActionListener(this);


        /// ////

        JLabel collegeTextArea = new JLabel("学院id:");
        collegeTextArea.setBounds(17, 188, 90, 41);
        collegeTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeTextArea);


        collegeIdText.setBounds(105, 195, 150, 27);
        collegeIdText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdText);


        queryByColegeIdButton.setBounds(270, 195, 85, 27);
        queryByColegeIdButton.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(queryByColegeIdButton);

        queryByColegeIdButton.addActionListener(this);

        baseQueryByColegeIdButton.setBounds(370, 195, 85, 27);
        baseQueryByColegeIdButton.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(baseQueryByColegeIdButton);

        baseQueryByColegeIdButton.addActionListener(this);

        JLabel stuNameTextArea = new JLabel("姓名:");
        stuNameTextArea.setBounds(17, 310, 90, 41);
        stuNameTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(stuNameTextArea);

        stuNameText.setBounds(85, 318, 150, 27);
        stuNameText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(stuNameText);

        /// ///


        JLabel collegeNameTextArea = new JLabel("学院:");
        collegeNameTextArea.setBounds(17, 355, 90, 41);
        collegeNameTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeNameTextArea);

        collegeComboBox.setEditable(false);
        collegeComboBox.setBounds(85, 365, 195, 25);
        collegeComboBox.setFont(new Font("宋体", Font.BOLD, 17));
        this.getContentPane().add(collegeComboBox);
        collegeComboBox.addActionListener(this);

/// //////////////
        JLabel majorNameTextArea = new JLabel("专业:");
        majorNameTextArea.setBounds(17, 402, 90, 41);
        majorNameTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorNameTextArea);

        majorComboBox.setEditable(false);
        majorComboBox.setBounds(85, 412, 195, 25);
        majorComboBox.setFont(new Font("宋体", Font.BOLD, 17));
        this.getContentPane().add(majorComboBox);
        majorComboBox.addActionListener(this);
/// ///////////////////////
        JLabel classNameTextArea = new JLabel("班级:");
        classNameTextArea.setBounds(17, 449, 90, 41);
        classNameTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classNameTextArea);

        classComboBox.setEditable(false);
        classComboBox.setBounds(85, 459, 195, 25);
        classComboBox.setFont(new Font("宋体", Font.BOLD, 17));
        this.getContentPane().add(classComboBox);
        classComboBox.addActionListener(this);

        queryButton.setBounds(65,490,128,128);
        this.getContentPane().add(queryButton);
        queryButton.setContentAreaFilled(false);
        queryButton.setBorderPainted(false);
        queryButton.addActionListener(this);

        this.revalidate(); // 确保组件被正确添加和布局
        this.repaint(); // 重绘窗口以显示新添加的组件
    }

    public String[] initComboBox(String querySql, String name, String paddingName) throws SQLException {

//        System.out.println("--------------------------");
//        System.out.println(major_name);
        Connection connection = database.getConnection();
//        System.out.println(Option);

        PreparedStatement ps = connection.prepareStatement(querySql);
        ps.setString(1, paddingName);

        ResultSet rs = ps.executeQuery();
        int optionLength = (getRowCount(querySql, name, paddingName) + 1);
        String[] Options;
        Options = new String[optionLength];
        Options[0] = "请选择选项";
//            System.out.println(getRowCount(querySql));
//            System.out.println(rs.getRow());
        while (rs.next()) {
            // int index = (rs.getRow() - 1);
            Options[rs.getRow()] = rs.getString(name);
            //  System.out.println(rs.getRow());
//                System.out.println(Options[rs.getRow()]-1);
        }

        return Options;
    }

    private String[] initComboBox(String querySql, String name) {
        Connection connection = database.getConnection();
        String[] Options;
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            int optionLength = (getRowCount(querySql, null, null) + 1);
            Options = new String[optionLength];
            Options[0] = "请选择选项";
//            System.out.println(getRowCount(querySql));
//            System.out.println(rs.getRow());
            while (rs.next()) {
                // int index = (rs.getRow() - 1);
                Options[rs.getRow()] = rs.getString(name);
                //System.out.println(rs.getRow());
//                System.out.println(Options[rs.getRow()]-1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Options;
    }

    public int getRowCount(String querySql, String name, String paddingName) {
        Connection connection = database.getConnection();
        int rowCount = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            if (name != null) ps.setString(1, paddingName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rowCount++;
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowCount;
    }

    private String buildQuery() {
        StringBuilder query = new StringBuilder("SELECT s.* FROM student s JOIN academicinfo a ON s.student_id = a.student_id WHERE 1=1");
        String major = (String) majorComboBox.getSelectedItem();
        String college = (String) collegeComboBox.getSelectedItem();
        String clazz = (String) classComboBox.getSelectedItem();

        if (!college.equals("请选择选项")) {
            query.append(" AND EXISTS ( SELECT 1 FROM college c WHERE c.college_id = a.college_id AND c.college_name =  '").append(college).append("' )");
        }
        if (!major.equals("请选择选项")) {
            query.append(" AND EXISTS (SELECT 1 FROM major m WHERE m.major_id = a.major_id AND m.major_name =  '").append(major).append("' )");
        }
        if (!clazz.equals("请选择选项")) {
            query.append(" AND EXISTS (SELECT 1 FROM class cl WHERE cl.class_id = a.class_id AND cl.class_name = '").append(clazz).append("' )");
        }
        return query.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == queryByStuIdButton) {
            String queryInputId = stuIdText.getText();
            if (!queryInputId.isEmpty()) {
                try {
                    student = queryInfoByUid(queryInputId);
                    if (student != null) {
                        new userQueryView(student);
                        this.student = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "不存在该id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        if (source == baseQueryByStuIdButton) {
            String queryInputId = stuIdText.getText();
            if (!queryInputId.isEmpty()) {
                try {
                    academicInfo = queryAcademicInfoById(queryInputId);
                    if (academicInfo != null) {
                        new userQueryView(academicInfo);
                    } else {
                        JOptionPane.showMessageDialog(null, "不存在该id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        /// ////////////////////////////////////////////////

        /// ///////////////////////////////////////////////
        if (source == queryByClassIdButton) {
            String queryInputClassId = classIdText.getText();
            if (!queryInputClassId.isEmpty()) {
                String querySql = "SELECT s.* FROM Student s  INNER JOIN AcademicInfo a ON s.student_id = a.student_id WHERE a.class_id = ?";
                try {
                    ArrayList<Student> students = queryInfoByMixId(queryInputClassId, querySql);
                    if (students == null) {
                        JOptionPane.showMessageDialog(null, "不存在该班号学生", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        new userQueryView(students);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);

        }
        if (source == baseQueryByClassIdButton) {
            String queryInputClassId = classIdText.getText();
            if (!queryInputClassId.isEmpty()) {
                String querySql = "SELECT * FROM StudentDetailView WHERE class_id = ?";
                try {
                    ArrayList<Academicinfo> academicinfos = baseQueryInfoByMixId(queryInputClassId, querySql);
                    if (academicinfos == null) {
                        JOptionPane.showMessageDialog(null, "不存在该班号学生", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        new userQueryView(academicinfos, "12w3");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        /// ////////////////////////////////////////////////

        /// ///////////////////////////////////////////////
        if (source == queryByMajorIdButton) {
            String queryInputMajorId = majorText.getText();
            if (!queryInputMajorId.isEmpty()) {
                String querySql = "SELECT s.* FROM Student s  INNER JOIN AcademicInfo a ON s.student_id = a.student_id WHERE a.major_id = ?";
                try {
                    ArrayList<Student> students = queryInfoByMixId(queryInputMajorId, querySql);
                    if (students == null) {
                        JOptionPane.showMessageDialog(null, "不存在该专业id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        new userQueryView(students);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);

        }
        if (source == baseQueryByMajorIdButton) {
            String queryInputMajorId = majorText.getText();
            if (!queryInputMajorId.isEmpty()) {
                String querySql = "SELECT * FROM StudentDetailView WHERE major_id = ?";
                try {
                    ArrayList<Academicinfo> academicinfos = baseQueryInfoByMixId(queryInputMajorId, querySql);
                    if (academicinfos == null) {
                        JOptionPane.showMessageDialog(null, "不存在该专业id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        new userQueryView(academicinfos, "true");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);

        }
        /// ////////////////////////////////////////////////

        /// ///////////////////////////////////////////////
        if (source == queryByColegeIdButton) {
            String queryInputCollegeId = collegeIdText.getText();
            if (!queryInputCollegeId.isEmpty()) {
                String querySql = "SELECT s.* FROM Student s  INNER JOIN AcademicInfo a ON s.student_id = a.student_id WHERE a.college_id = ?";
                try {
                    ArrayList<Student> students = queryInfoByMixId(queryInputCollegeId, querySql);
                    if (students == null) {
                        JOptionPane.showMessageDialog(null, "不存在该学院id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        new userQueryView(students);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        if (source == baseQueryByColegeIdButton) {
            String queryInputCollegeId = collegeIdText.getText();
            if (!queryInputCollegeId.isEmpty()) {
                String querySql = "SELECT * FROM StudentDetailView WHERE college_id = ?";
                try {
                    ArrayList<Academicinfo> academicinfos = baseQueryInfoByMixId(queryInputCollegeId, querySql);
                    if (academicinfos == null) {
                        JOptionPane.showMessageDialog(null, "不存在该学院id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        new userQueryView(academicinfos, "true");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        //
        if (source == collegeComboBox) {
//            String selectedOption = (String) collegeComboBox.getSelectedItem();
//            switch (selectedOption) {
//                case "马克思主义学院": {
////                    queryMajorSql+="JOIN  College c ON m.college_id = c.college_id WHERE     c.college_name ='马克思主义学院' ";
////                    this.repaint();
////                    this.revalidate();
////                    majorComboBox.repaint();
//                    queryMajorSql = "select m.major_name from major m JOIN  College c ON m.college_id = c.college_id WHERE c.college_name ='马克思主义学院'";
//                    String[] majorOptions = initComboBox(queryMajorSql, "major_name");
//                    majorComboBox.setModel(new DefaultComboBoxModel<>(majorOptions));
//                    break;
//                }
//                case "计算机学院": {
//                    queryMajorSql = "select m.major_name from major m JOIN  College c ON m.college_id = c.college_id WHERE c.college_name ='计算机学院'";
//                    String[] majorOptions = initComboBox(queryMajorSql, "major_name");
//                    majorComboBox.setModel(new DefaultComboBoxModel<>(majorOptions));
//                    break;
//                }
//                case "电子信息学院": {
//                    queryMajorSql = "select m.major_name from major m JOIN  College c ON m.college_id = c.college_id WHERE c.college_name ='电子信息学院'";
//                    String[] majorOptions = initComboBox(queryMajorSql, "major_name");
//                    majorComboBox.setModel(new DefaultComboBoxModel<>(majorOptions));
//                    break;
//                }
//            }
                String selectedOption = (String) collegeComboBox.getSelectedItem();
                if (!selectedOption.equals("请选择选项")) {
                    String querySql = "select m.major_name from major m JOIN  College c ON m.college_id = c.college_id WHERE c.college_name = ?";
                    String[] majorOptions;
                    for (int i = 1; i < collegeOptions.length; i++) { // 从 1 开始，跳过提示项
                        if (selectedOption.equals(collegeOptions[i])) {
                            try {
                                majorOptions = initComboBox(querySql, "major_name", selectedOption);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            majorComboBox.setModel(new DefaultComboBoxModel<>(majorOptions));
                            break;
                        }
                    }
                    String queryClassSql = "SELECT cl.class_name FROM   Class cl JOIN   Major m ON cl.major_id = m.major_id JOIN     College c ON m.college_id = c.college_id WHERE   c.college_name = ?";
                    String[] classOptions;
                    for (int i = 1; i < collegeOptions.length; i++) { // 从 1 开始，跳过提示项
                        if (selectedOption.equals(collegeOptions[i])) {
                            try {
                                classOptions = initComboBox(queryClassSql, "class_name", selectedOption);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            classComboBox.setModel(new DefaultComboBoxModel<>(classOptions));
                            break;
                        }
                    }
                    buildQuery();

                }

        }
        if (source == majorComboBox) {
            String selectedOption = (String) majorComboBox.getSelectedItem();
            if (!selectedOption.equals("请选择选项")) {
                String querySql = "SELECT c.class_name FROM class c JOIN Major m ON c.major_id = m.major_id WHERE m.major_name = ?";
                String[] classOptions;
                for (int i = 1; i < majorOptions.length; i++) { // 从 1 开始，跳过提示项
                    if (selectedOption.equals(majorOptions[i])) {
                        try {
                            classOptions = initComboBox(querySql, "class_name", selectedOption);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        classComboBox.setModel(new DefaultComboBoxModel<>(classOptions));
                        break;
                    }
                }
                buildQuery();
            }
        }
        if (source==classComboBox){
            buildQuery();
        }
        if(source==queryButton){
            String inputName=stuNameText.getText();
            if(inputName.isEmpty()){
                String query = buildQuery();
                Connection connection = database.getConnection();
                try {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    ArrayList<Student> studentArrayList=new ArrayList<>();
                    while (rs.next()){
                        Student student=new Student();
                        student.setStudentId(rs.getString("student_id"));
                        student.setName(rs.getString("name"));
                        student.setGender(rs.getObject("gender"));
                        student.setBirthDate(rs.getString("birth_date"));
                        student.setPhone(rs.getString("phone"));
                        student.setEmail(rs.getString("email"));
                        student.setEnrollmentDate(rs.getString("enrollment_date"));
                        student.setGraduationStatus(rs.getInt("graduation_status"));
                        studentArrayList.add(student);
                    }
                    System.out.println(studentArrayList.size());
                    new userQueryView(studentArrayList);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }else {
               String queryByStuName="select * from student where name= '"+inputName+"' ";
                Connection connection = database.getConnection();
                try {
                    PreparedStatement ps = connection.prepareStatement(queryByStuName);
                    ResultSet rs = ps.executeQuery();
                    ArrayList<Student> studentArrayList=new ArrayList<>();
                    while (rs.next()){
                        Student student=new Student();
                        student.setStudentId(rs.getString("student_id"));
                        student.setName(rs.getString("name"));
                        student.setGender(rs.getObject("gender"));
                        student.setBirthDate(rs.getString("birth_date"));
                        student.setPhone(rs.getString("phone"));
                        student.setEmail(rs.getString("email"));
                        student.setEnrollmentDate(rs.getString("enrollment_date"));
                        student.setGraduationStatus(rs.getInt("graduation_status"));
                        studentArrayList.add(student);
                    }
                    new userQueryView(studentArrayList);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }
}
