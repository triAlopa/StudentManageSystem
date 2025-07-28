package view.stu;


import config.queryPage;
import entity.Academicinfo;
import entity.Grade;
import entity.Student;
import mapper.userMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class userQueryView extends JFrame implements ActionListener {
    private Student student = null;
    private Academicinfo academicinfo = null;
    private ArrayList<Academicinfo> academicinfos = null;
    private ArrayList<Student> students = null;
    private ArrayList<Grade> gradeArrayList;
    private DefaultTableModel tableModel;

    //查询 类别
    private int queryType;
    //单个查询  在构造方法 对查询 类别赋值
    private static final int ONE_QUERY = 0;
    //多个查询 在构造方法 对查询 类别赋值
    private static final int MANY_QUERY = 1;
    private String title[];
    // 分页查询
    private Integer pageOffset = 10;
    private JButton beforeButton = new JButton("上一页");
    private JButton nextButton = new JButton("下一页");
    //显示当前页数
    private JLabel pageJlabel = new JLabel("当前第" + (queryPage.pageNum + 1) + "页");
    private String academicinfoQuerySql;


    public userQueryView(Student student) {
        this.title = new String[]{"学号", "姓名", "性别", "出生日期", "电话", "邮箱", "入学日期", "毕业状态"};
        this.queryType = ONE_QUERY;
        this.student = student;
        this.nextButton.setVisible(false);
        this.beforeButton.setVisible(false);
        initializeUI();
    }

    public userQueryView(ArrayList<Student> students) {
        this.title = new String[]{"学号", "姓名", "性别", "出生日期", "电话", "邮箱", "入学日期", "毕业状态"};
        this.queryType = MANY_QUERY;
        this.students = students;
        initializeUI();
    }

    public userQueryView(Academicinfo academicinfo) {
        this.title = new String[]{"学号", "辅导员", "班级", "专业", "学院", "年级"};
        this.queryType = ONE_QUERY;
        this.academicinfo = academicinfo;
        this.nextButton.setVisible(false);
        this.beforeButton.setVisible(false);
        initializeUI();
    }

    public userQueryView(ArrayList<Academicinfo> academicinfos, String sql) {
        this.title = new String[]{"学号", "辅导员", "班级", "专业", "学院", "年级"};
        this.queryType = MANY_QUERY;
        this.academicinfos = academicinfos;
        if (!sql.equals("nul")) {
            this.academicinfoQuerySql = sql;
        }
        initializeUI();
    }

    public userQueryView(ArrayList<Grade> gradeArrayList, boolean a) {
        this.title = new String[]{"课程名称", "分数", "是否及格"};
        this.gradeArrayList = gradeArrayList;
        this.nextButton.setVisible(false);
        this.beforeButton.setVisible(false);
        initializeUI();
    }


    private void initView() {
        this.setLayout(null);
//        String[] title={"学号", "姓名", "性别", "出生日期", "电话", "邮箱", "入学日期", "毕业状态"};
////            title = new String[]{"学号", "辅导员","班级","专业", "学院", "年级"};
        //通过构造方法初始化title    来添加表头
        tableModel = new DefaultTableModel(title, 0);
        tableModel.addRow(title);
        //创建表格 添加数据模型
        JTable table = new JTable(tableModel);
        table.setFont(new Font("宋体", Font.BOLD, 22));
        table.setRowHeight(35);

//        // 清空表格模型
//        tableModel.setRowCount(0);

        //对接收来的 数据处理 校验类型
        if (student != null || students != null) {
            if (queryType == ONE_QUERY) {
                addSingleStudentToTable(student);
            } else if (queryType == MANY_QUERY) {
                for (Student student : students) {
                    addSingleStudentToTable(student);
                }
            }
        } else if (academicinfo != null || academicinfos != null) {
            if (queryType == ONE_QUERY) {
                addSingleAcademicInfoToTable(academicinfo);
            } else if (queryType == MANY_QUERY) {
                for (Academicinfo academicinfo : academicinfos) {
                    addSingleAcademicInfoToTable(academicinfo);
                }
            }
        } else if (gradeArrayList != null) {
            for (Grade grade : gradeArrayList) {
                addSingleGradeInfoToTable(grade);
            }
        }

        // 设置表格位置和大小
        table.setBounds(0, 0, 1300, 388);
        //设置不可编辑
        table.setEnabled(false);

        if (gradeArrayList==null) {
            //设置指定列的 宽长
            TableColumnModel columnModel = table.getColumnModel();
            TableColumn nameColumn = columnModel.getColumn(1);
            nameColumn.setPreferredWidth(10);
        /*
        注意 对接受数据的不同 但是指定列的宽长都会变化 所以命名不是很正确
         */
            TableColumn genderColumn = columnModel.getColumn(2);
            genderColumn.setPreferredWidth(30);
            TableColumn phoneColumn = columnModel.getColumn(4);
            phoneColumn.setPreferredWidth(130);
            TableColumn mailColumn = columnModel.getColumn(5);
            mailColumn.setPreferredWidth(200);
        }
        //添加到容器里面
        this.getContentPane().add(table);
        //置顶显示
        this.setAlwaysOnTop(true);
        //设置位置 大小 字体 和 添加监听
        beforeButton.setBounds(0, 430, 100, 50);
        nextButton.setBounds(1200, 430, 100, 50);
        pageJlabel.setBounds(570, 430, 200, 50);
        pageJlabel.setFont(new Font("宋体", Font.BOLD, 26));
        this.getContentPane().add(pageJlabel);
        nextButton.addActionListener(this);
        beforeButton.addActionListener(this);
        this.getContentPane().add(beforeButton);
        this.getContentPane().add(nextButton);

        //加入监听 表示 如果要关闭窗口了 初始化页面查询数据
        this.addWindowListener(new WindowAdapter() {
            //监听到查询页面被关闭，初始化查询数据
            @Override
            public void windowClosing(WindowEvent e) {
                // 在窗口关闭时调用queryPage.InitPage()方法
                queryPage.InitPage();
            }
        });

        this.revalidate();
        this.repaint();
    }


//    public  void initView() {
//        //取消默认居中放置容器位置，取消了会根据设置的x，y坐标设置位置
//        this.setLayout(null);
//
//        String[] title = new String[]{"学号", "姓名", "性别", "出生日期", "电话", "邮箱", "入学日期", "毕业状态"};
//        tableModel = new DefaultTableModel(title, 0);
//        tableModel.addRow(title);
//        JTable table = new JTable(tableModel);
//        table.setFont(new Font("宋体", Font.CENTER_BASELINE, 12));
//        table.setRowHeight(35);
//        int graduationStatus = student.getGraduationStatus();
//        String statusText = "";
//        switch (graduationStatus) {
//            case 0:
//                statusText = "在读";
//                break;
//            case 1:
//                statusText = "毕业";
//                break;
//            case 2:
//                statusText = "肆业";
//                break;
//            default:
//                statusText = "未知状态";
//                break;
//        }
//        for (Student student : students) {
//            int graduationStatuss = student.getGraduationStatus();
//            String statusTexts = "";
//            switch (graduationStatuss) {
//                case 0:
//                    statusTexts = "在读";
//                    break;
//                case 1:
//                    statusTexts = "毕业";
//                    break;
//                case 2:
//                    statusTexts = "肆业";
//                    break;
//                default:
//                    statusTexts = "未知状态";
//                    break;
//            }
//            tableModel.addRow(new Object[]{
//                    student.getStudentId(),
//                    student.getName(),
//                    student.getGender(),
//                    student.getBirthDate(),
//                    student.getPhone(),
//                    student.getEmail(),
//                    student.getEnrollmentDate(),
//                    statusTexts
//            });
//        }

    // 设置表格位置和大小
//        table.setBounds(0, 0, 890, 500);
//        this.getContentPane().add(table);

    /// /       清空表格模型
    /// /       tableModel.setRowCount(1);
//        // 添加查询结果到表格模型
//        tableModel.addRow(new Object[]{student.getStudentId(), student.getName(), student.getGender(), student.getBirthDate(), student.getPhone(), student.getEmail(), student.getEnrollmentDate(), statusText});
//
//        this.revalidate();
//        this.repaint();
    private void initializeUI() {
        // 设置窗体属性
        this.setSize(1300, 580);
        this.setTitle("查询结果");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(null);
//        //关闭结束运行
//        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//
        initView();


    }

    //添加表格数据
    private void addSingleAcademicInfoToTable(Academicinfo academicinfo) {

        tableModel.addRow(new Object[]{
                academicinfo.getStudentId(),
                academicinfo.getCounselorId(),
                academicinfo.getClassId(),
                academicinfo.getMajorId(),
                academicinfo.getCollegeId(),
                academicinfo.getGrade()
        });

    }

    //添加表格数据
    private void addSingleStudentToTable(Student student) {
        int graduationStatus = student.getGraduationStatus();
        String statusText = getStatusText(graduationStatus);
        tableModel.addRow(new Object[]{
                student.getStudentId(),
                student.getName(),
                student.getGender(),
                student.getBirthDate(),
                student.getPhone(),
                student.getEmail(),
                student.getEnrollmentDate(),
                statusText
        });
    }

    //替换学业状态
    /* 0->"在读"
      1->"毕业"
      2->"肆业"
     */
    private String getStatusText(int graduationStatus) {
        switch (graduationStatus) {
            case 0:
                return "在读";
            case 1:
                return "毕业";
            case 2:
                return "肆业";
            default:
                return "未知状态";
        }
    }

    //添加表格数据
    private void addSingleGradeInfoToTable(Grade grade) {
        if(grade.getScore()==null){
            tableModel.addRow(new Object[]{
                    grade.getCourseId(),
                    grade.getScore(),
                    ""
            });
            return;
        }
        String isPassed = getIsPassed(grade.getIsPassed());
        tableModel.addRow(new Object[]{
                grade.getCourseId(),
                grade.getScore(),
                isPassed
        });
    }

    private String getIsPassed(int is_passed) {
        switch (is_passed) {
            case 0:
                return "不及格 :(";
            case 1:
                return "及格";
            default:
                return "未知状态";
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String query = userQueryStu.getQuery();
        if (source == nextButton) {
            if (academicinfos == null) {
                if (students != null) {
                    //检验查到的数据是否满页
                    //主要 校验传过来的数据来源名字查询、还是 全体的查询
                    if (this.students.size() == 10) {

                        //处理 页数 跳过前面多少条，达到翻页的目的。

                        //页数自增
                        queryPage.pageNum++;

                        //计算跳过多少条
                        pageOffset = (queryPage.pageNum) * queryPage.pageSize;
                        ArrayList<Student> studentList = userMapper.queryBySelectItem(query, pageOffset);
                        if (studentList == null) {
                            JOptionPane.showMessageDialog(this, "没有数据！", "error", JOptionPane.ERROR_MESSAGE);
                            nextButton.setVisible(false);
                            //修正 页数 虽然前面自增 但是 没有数据了 点击上一页那就会重复传递数据
                            queryPage.pageNum--;
                            return;
                        }

                        //更新页码
                        pageJlabel.setText("当前第" + (queryPage.pageNum + 1) + "页");
                        this.repaint();
                        this.setVisible(false);
                        new userQueryView(studentList);
                    } else {
                        JOptionPane.showMessageDialog(this, "没有数据！", "error", JOptionPane.ERROR_MESSAGE);
                        nextButton.setVisible(false);
                        return;
                    }
                } else {
//                    System.out.println("-----------------");
                    JOptionPane.showMessageDialog(this, "没有数据！", "error", JOptionPane.ERROR_MESSAGE);
                    nextButton.setVisible(false);
                    return;
                }
            } else {
                System.out.println(academicinfos.size());
                if (this.academicinfos.size() >= 10) {

                    //处理 页数 跳过前面多少条，达到翻页的目的。

                    //页数自增
                    queryPage.pageNum++;

                    //更新页码
                    pageJlabel.setText("当前第" + (queryPage.pageNum + 1) + "页");

                    //计算跳过多少条
                    pageOffset = (queryPage.pageNum) * queryPage.pageSize;
                    ArrayList<Academicinfo> academicinfoArrayList = userMapper.queryAcademicinfoById(academicinfoQuerySql, pageOffset);
                    if (academicinfoArrayList == null) {
                        JOptionPane.showMessageDialog(this, "没有数据！", "error", JOptionPane.ERROR_MESSAGE);
                        queryPage.InitPage();
                        nextButton.setVisible(false);
                        return;
                    }
                    this.repaint();
                    this.setVisible(false);
                    System.out.println(academicinfoArrayList.size());
                    new userQueryView(academicinfoArrayList, academicinfoQuerySql);
                } else {
                    JOptionPane.showMessageDialog(this, "没有数据！", "error", JOptionPane.ERROR_MESSAGE);
                    nextButton.setVisible(false);
                    return;
                }
            }
        }


        if (source == beforeButton) {
            if (queryPage.pageNum != 0) {
                //页数自减
                queryPage.pageNum--;

                //更新页码
                pageJlabel.setText("当前第" + (queryPage.pageNum + 1) + "页");

                //计算跳转到前面多少条
                pageOffset = (queryPage.pageNum) * queryPage.pageSize;
                this.setVisible(false);
                this.repaint();

                if(academicinfos==null){
                    ArrayList<Student> studentList = userMapper.queryBySelectItem(query, pageOffset);
                    new userQueryView(studentList);
                }else{
                    ArrayList<Academicinfo> academicinfoArrayList = userMapper.queryAcademicinfoById(academicinfoQuerySql, pageOffset);
                    new userQueryView(academicinfoArrayList,academicinfoQuerySql);
                }

            } else {
                JOptionPane.showMessageDialog(this, "没有上一页！", "error", JOptionPane.ERROR_MESSAGE);
                queryPage.InitPage();
                beforeButton.setVisible(false);

            }
        }
    }
}






