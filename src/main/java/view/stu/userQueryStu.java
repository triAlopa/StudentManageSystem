package view.stu;


import config.closeWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Academicinfo;
import entity.Grade;
import entity.Student;
import mapper.adminMapper;
import mapper.userMapper;

public class userQueryStu extends JFrame implements ActionListener {
    private String thisLoginStu_id;

    private JTextField stuIdText = new JTextField();
    private JButton queryByStuIdButton = new JButton("详细");
    private JButton baseQueryByStuIdButton = new JButton("基础");


    private JTextField classIdText = new JTextField();
    private JButton baseQueryByClassIdButton = new JButton("基础");


    private JTextField majorText = new JTextField();
    private JButton baseQueryByMajorIdButton = new JButton("基础");


    private JTextField collegeIdText = new JTextField();
    private JButton baseQueryByColegeIdButton = new JButton("基础");

    private JTextField stuNameText = new JTextField();

    private JComboBox<String> collegeComboBox = null;
    private JComboBox<String> majorComboBox = null;
    private JComboBox<String> classComboBox = null;

    private String[] majorOptions = {"请选择选项"};
    private String[] collegeOptions = {"请选择选项"};
    private String[] classOptions = {"请选择选项"};


    //组件 查询按钮
    private JButton queryButton = new JButton(new ImageIcon("src/img/query.png"));

    //id  无须多言
    private String collegeId;
    private String MajorId;
    private String classId;

    //用于列表 点击 查询 按钮的查询语句
   private static String query = "SELECT s.* FROM student s JOIN academicinfo a ON s.student_id = a.student_id WHERE 1=1 ";
//   private  static  String tempQuery;

    public userQueryStu() {
        initJFrame();
        initView();
    }

    //boolean 变量无意义 只是为了构造方法重载
    //识别出 传来的数组列表是 grade类型的 还是 student 别的类型的
    //😅想不到好方法了
    public userQueryStu(String thisLoginStu_id) {
        this.thisLoginStu_id = thisLoginStu_id;
        ArrayList<Grade> grades = userMapper.stuGradeInfoByStuId(thisLoginStu_id);
        new userQueryView(grades,true);
    }


    //初始化 界面
    private void initJFrame() {
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

        collegeOptions = userMapper.selectAllCollege();
        this.collegeComboBox = new JComboBox<>(collegeOptions);

        this.majorComboBox = new JComboBox<>(majorOptions);

        this.classComboBox = new JComboBox<>(classOptions);
    }


    //设置组件的位置 大小 和字体设置
    //初始化组件
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


        baseQueryByClassIdButton.setBounds(310, 107, 85, 27);
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


        baseQueryByMajorIdButton.setBounds(310, 151, 85, 27);
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


        baseQueryByColegeIdButton.setBounds(310, 195, 85, 27);
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

        queryButton.setBounds(65, 490, 128, 128);
        this.getContentPane().add(queryButton);
        queryButton.setContentAreaFilled(false);
        queryButton.setBorderPainted(false);
        queryButton.addActionListener(this);

        this.revalidate(); // 确保组件被正确添加和布局
        this.repaint(); // 重绘窗口以显示新添加的组件
    }


    //通过点击事件 构建 用于按钮的查询语句
    //正则表达式  获取学院 、专业、班级的 id
    private String buildQuery() {
        StringBuilder query = new StringBuilder("SELECT s.* FROM student s JOIN academicinfo a ON s.student_id = a.student_id WHERE 1=1");
        String college = (String) collegeComboBox.getSelectedItem();
        String major = (String) majorComboBox.getSelectedItem();
        String clazz = (String) classComboBox.getSelectedItem();
        Pattern p = Pattern.compile("[0-9]{1,}");
        if (!college.equals("请选择选项")) {
            Matcher m = p.matcher(college);
            if (m.find()) {
                collegeId = m.group();
            }
            query.append(" AND EXISTS ( SELECT 1 FROM college c WHERE c.college_id = a.college_id AND c.college_id=  '").append(collegeId).append("' )");
        }
        if (!major.equals("请选择选项")) {
            Matcher m = p.matcher(major);
            if (m.find()) {
                MajorId = m.group();
            }
            query.append(" AND EXISTS (SELECT 1 FROM major m WHERE m.major_id = a.major_id AND m.major_id =  '").append(MajorId).append("' )");
        }
        if (!clazz.equals("请选择选项")) {
            Matcher m = p.matcher(clazz);
            if (m.find()) {
                classId = m.group();
            }
            query.append(" AND EXISTS (SELECT 1 FROM class cl WHERE cl.class_id = a.class_id AND cl.class_id = '").append(classId).append("' )");
//            System.out.println(query);
//            this.tempQuery=query.toString();
        }
        return query.toString();
    }


    //外界获取查询按钮的查询语句 的方法
    //主要用于 分页查询获取语句
    public static String getQuery() {
//        System.out.println(query);
//        System.out.println(tempQuery);
        return query;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取触发事件对象
        Object source = e.getSource();

        //正则表达式
        Pattern p = Pattern.compile("[0-9]{1,}");
        if (source == collegeComboBox) {
            String selectedItem = (String) collegeComboBox.getSelectedItem();
            if (!selectedItem.equals("请选择选项")) {

                //捕获 各个id
                Matcher m = p.matcher(selectedItem);
                //找到了 将捕获到的赋值
                if (m.find()) {
                    collegeId = m.group();
                }
//                query.append(" AND EXISTS ( SELECT 1 FROM college c WHERE c.college_id = a.college_id AND c.college_id =  '").append(collegeId).append("' )");

//                System.out.println(query.toString());
                //更新子级列表
                String queryMajor = "select major_id,major_name from major where college_id= ?";
                majorOptions = userMapper.selectItemById(queryMajor, "major_id", "major_name", collegeId);
                majorComboBox.setModel(new DefaultComboBoxModel<>(majorOptions));
                String queryClass = "SELECT  cl.class_id , cl.class_name " +
                        "FROM   Class cl JOIN Major m ON cl.major_id = m.major_id " +
                        "JOIN  College c ON m.college_id = c.college_id  WHERE  c.college_id = ? ";
                classOptions = userMapper.selectItemById(queryClass, "class_id", "class_name", collegeId);
                classComboBox.setModel(new DefaultComboBoxModel<>(classOptions));
                //传递点击的查询语句
                query = buildQuery();
//                System.out.println(query);
            }
        }

        //专业列表点击事件
        else if (source == majorComboBox) {
            String selectedItem = (String) majorComboBox.getSelectedItem();
            if (!selectedItem.equals("请选择选项")) {
                String queryClass = "SELECT  cl.class_id , cl.class_name " +
                        "FROM   Class cl JOIN Major m ON cl.major_id = m.major_id WHERE   m.major_id = ? ";
                //找到了 将捕获到的赋值
                Matcher m = p.matcher(selectedItem);
                //捕获 各个id
                if (m.find()) {
                    MajorId = m.group();
                }
                classOptions = adminMapper.selectItemById(queryClass, "class_id", "class_name", MajorId);
                classComboBox.setModel(new DefaultComboBoxModel<>(classOptions));
//                query.append(" AND EXISTS (SELECT 1 FROM major m WHERE m.major_id = a.major_id AND m.major_id =  '").append(MajorId).append("' )");
                //传递 点击 创建的查询语句
                query = buildQuery();
//                System.out.println(query);
            }
        }
        //班级列表点击事件
        else if (source == classComboBox) {
            String selectedItem = (String) classComboBox.getSelectedItem();
            if (!selectedItem.equals("请选择选项")) {
                //捕获 各个id
                Matcher m = p.matcher(selectedItem);
                //id 赋值
                if (m.find()) {
                    classId = m.group();
                }
//                query.append(" AND EXISTS (SELECT 1 FROM class cl WHERE cl.class_id = a.class_id AND cl.class_id = '").append(classId).append("' )");
                //将点击 列表的id 进行赋值
                query = buildQuery();
//                System.out.println(query);
            }
        }

        //查询按钮
        else if (source == queryButton) {
            String stuName = stuNameText.getText();
            ArrayList<Student> students;
            // 判断 识别用户是否要 用姓名查询
            if (stuName.isEmpty()) {
                students = userMapper.queryBySelectItem(query, 0);
            } else {
                students = userMapper.queryStuInfoByName(stuName);
            }
            if (students == null) {
                //基本判断
                JOptionPane.showMessageDialog(null, "查询不到学生", "error", JOptionPane.ERROR_MESSAGE);
                //没有 就返回 不创建视图，以免创造空指针错误
                return;
            }
            //创建视图
            new userQueryView(students);

            this.setVisible(false);
            // 重置所有的选择项和输入框
            collegeComboBox.setSelectedIndex(0);
            majorComboBox.setSelectedIndex(0);
            classComboBox.setSelectedIndex(0);
            stuIdText.setText("");
            classIdText.setText("");
            majorText.setText("");
            collegeIdText.setText("");
            stuNameText.setText("");
//            query = "SELECT s.* FROM student s JOIN academicinfo a ON s.student_id = a.student_id WHERE 1=1";
            // 重新显示当前页面
            this.setVisible(true);
        }

        //id查询 单个 学生 详细信息
        else if (source == queryByStuIdButton) {
            String queryInputId = stuIdText.getText();
            if (!queryInputId.isEmpty()) {
                Student student = userMapper.queryStuInfoByUid(queryInputId);
                //基本判断   是否为空
                if (student != null) {
                    new userQueryView(student);
                } else {
                    JOptionPane.showMessageDialog(this, "不存在该id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else JOptionPane.showMessageDialog(this, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        //id查询 单个 学生 基本信息
        else if (source == baseQueryByStuIdButton) {
            String queryInputId = stuIdText.getText();
            if (!queryInputId.isEmpty()) {
                Academicinfo academicInfo = userMapper.queryStuAcademicInfoById(queryInputId);
                if (academicInfo != null) {
                    new userQueryView(academicInfo);
                } else {
                    JOptionPane.showMessageDialog(this, "不存在该id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else JOptionPane.showMessageDialog(this, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        //班级id 查询 该班所有学生
        else if (source == baseQueryByClassIdButton) {
            classId = classIdText.getText();
            if (!classId.isEmpty()) {
                String querySqlByClassId = "SELECT * FROM StudentDetailView WHERE class_id = ";
                ArrayList<Academicinfo> academicinfos = userMapper.baseQueryInfoByMixId(classId, querySqlByClassId);
                if (academicinfos == null) {
                    JOptionPane.showMessageDialog(null, "不存在该班号学生", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    new userQueryView(academicinfos, querySqlByClassId + classId);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        //专业 id 查询 该班所有学生
        else if (source == baseQueryByMajorIdButton) {
            MajorId = majorText.getText();
            if (!MajorId.isEmpty()) {
                String querySqlByMajorId = "SELECT * FROM StudentDetailView WHERE major_id = ";
                ArrayList<Academicinfo> academicinfos = userMapper.baseQueryInfoByMixId(MajorId, querySqlByMajorId);
                if (academicinfos == null) {
                    JOptionPane.showMessageDialog(null, "不存在该专业id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    new userQueryView(academicinfos, querySqlByMajorId + MajorId);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        //学院 id 查询 该班所有学生
        else if (source == baseQueryByColegeIdButton) {
            collegeId = collegeIdText.getText();
            if (!collegeId.isEmpty()) {
                String querySqlByCollegeId = "SELECT * FROM StudentDetailView WHERE college_id = ";
                ArrayList<Academicinfo> academicinfos = userMapper.baseQueryInfoByMixId(collegeId, querySqlByCollegeId);
                if (academicinfos == null) {
                    JOptionPane.showMessageDialog(null, "不存在该学院id学生", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    new userQueryView(academicinfos, querySqlByCollegeId + collegeId);
                }
            } else JOptionPane.showMessageDialog(null, "请输入id", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
