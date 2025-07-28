package view.course;

import mapper.adminMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class adminQueryCourse extends JFrame implements ActionListener {
    private String LoginTeacher_id;
    private String[] courses; // 这里应该是传递过来的字符数组
    private JComboBox<String> courseComboBox;
    private JLabel creditLabel = new JLabel("学分:");
    private JLabel classroomLabel = new JLabel("开课教室:");
    private JLabel startTimeLabel = new JLabel("开课时间:");
    private JLabel endTimeLabel = new JLabel("结课时间:");


    public adminQueryCourse(String teacher_id) {
        this.LoginTeacher_id = teacher_id;
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("查询课程");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
//        closeWindows.createAndShowFrame(this);
        courses = adminMapper.querySourceNameByTeaId(LoginTeacher_id);
        courseComboBox = new JComboBox<>(courses);
        initView();
    }

    private void initView() {
        this.setLayout(null);
        String college_id = adminMapper.queryCoIdByTeacherId(LoginTeacher_id);
        JLabel collegeIdLabel = new JLabel("学院ID:" + college_id);
        collegeIdLabel.setBounds(17, 20, 150, 41);
        collegeIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdLabel);

        JLabel collegeNameLabel = new JLabel("学院名称:" + adminMapper.queryCollegeNameById(college_id));
        collegeNameLabel.setBounds(17, 70, 300, 41); // 调整X坐标
        collegeNameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeNameLabel);

        JLabel courseLabel = new JLabel("传授课程:");
        courseLabel.setBounds(17, 120, 130, 41);
        courseLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(courseLabel);

        courseComboBox.setBounds(145, 127, 200, 27);
        courseComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(courseComboBox);
        courseComboBox.addActionListener(this);


        creditLabel.setBounds(17, 170, 400, 41);
        creditLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(creditLabel);


        classroomLabel.setBounds(17, 220, 400, 41);
        classroomLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classroomLabel);


        startTimeLabel.setBounds(17, 270, 400, 41);
        startTimeLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(startTimeLabel);


        endTimeLabel.setBounds(17, 320, 400, 41);
        endTimeLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(endTimeLabel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == courseComboBox) {
            String selectedItem = (String) courseComboBox.getSelectedItem();
            Pattern p = Pattern.compile("[a-zA-Z]{0,}[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            if (m.find()) {
                String[] info = adminMapper.queryCourseInfo(m.group());
                creditLabel.setText("学分  " + info[0]);
                classroomLabel.setText("教室 " + info[1]);
                startTimeLabel.setText("开课时间 " + info[2]);
                endTimeLabel.setText("结课时间 " + info[3]);
            }
        }
    }
}
