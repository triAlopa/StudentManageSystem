package UI;

import UI.AboutUI.aboutAuthor;
import config.closeWindows;
import util.WarningDialog;
import view.course.adminAddOrUpCourse;
import view.course.adminDeleteCourse;
import view.course.adminQueryCourse;
import view.grade.adminCRUDGrade;
import view.leave.adminAgreeStuLeave;
import view.stu.adminAddOrUpStuView;
import view.stu.adminDeleteStu;
import UI.AboutUI.showAboutSchool;
import view.stu.userQueryStu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminUI extends baseUI implements ActionListener {

    private String LoginTeacher_id;
    private JMenuBar jMenuBar = new JMenuBar();
    private JMenuItem queryStudent = new JMenuItem("查询学生");
    private JMenuItem addOrUpStudent = new JMenuItem("添加&更新");
    private JMenuItem deleteStudent = new JMenuItem("删除学生");

    private JMenuItem queryCourse = new JMenuItem("查询课程");
    private JMenuItem addOrUpCourse = new JMenuItem("添加&更新");
    private JMenuItem deleteCourse = new JMenuItem("删除课程");

    private JMenuItem mixDoGrade = new JMenuItem("CRUD_GRADE");
    private JMenuItem agreeLeave = new JMenuItem("审批请假");

    private JMenuItem aboutSchool = new JMenuItem("学校");
    private JMenuItem aboutAuthor = new JMenuItem("作者");
    private JMenuItem UnLogin = new JMenuItem("退出登录");


    public adminUI() {
        initJMenuBar();
        closeWindows.createAndShowFrame(this);
    }

    public adminUI(String teacher_id) {
        this.LoginTeacher_id = teacher_id;
        initCourseOrGradeBar();
        initLeaveApplication();
        initJMenuBar();
        closeWindows.createAndShowFrame(this);
    }

    public void initCourseOrGradeBar() {
        JMenu courseJMenu = new JMenu("课程");
        courseJMenu.add(addOrUpCourse);
        courseJMenu.add(deleteCourse);
        courseJMenu.add(queryCourse);
        queryCourse.addActionListener(this);
        addOrUpCourse.addActionListener(this);
        deleteCourse.addActionListener(this);
        jMenuBar.add(courseJMenu);
        JMenu gradeJmenu = new JMenu("成绩");
        gradeJmenu.add(mixDoGrade);
        mixDoGrade.addActionListener(this);
        jMenuBar.add(gradeJmenu);
    }

    public void initLeaveApplication() {
        JMenu leave = new JMenu("请假");
        leave.add(agreeLeave);
        agreeLeave.addActionListener(this);
        jMenuBar.add(leave);
    }

    //添加功能栏目
    protected void initJMenuBar() {


        JMenu studentJMenu = new JMenu("学生");

        this.queryStudent.addActionListener(this);
        this.addOrUpStudent.addActionListener(this);
        this.deleteStudent.addActionListener(this);

        studentJMenu.add(addOrUpStudent);
        studentJMenu.add(deleteStudent);
        studentJMenu.add(queryStudent);


        JMenu about = new JMenu("关于");
        aboutSchool.addActionListener(this);
        UnLogin.addActionListener(this);


        aboutAuthor.addActionListener(this);


        about.add(aboutSchool);
        about.add(aboutAuthor);
        about.add(UnLogin);

        this.jMenuBar.add(studentJMenu);
        this.jMenuBar.add(about);

        this.jMenuBar.setPreferredSize(new Dimension(1100, 35));

        this.setJMenuBar(jMenuBar);
    }

    //获取自身
    private adminUI returnThis() {
        return this;
    }

    ;


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == queryStudent) {
            new userQueryStu();
        }
        if (source == addOrUpStudent) {
            new adminAddOrUpStuView();
        }
        if (source == deleteStudent) {
            new adminDeleteStu();
        }
        if (source == queryCourse) {
            new adminQueryCourse(LoginTeacher_id);
        }
        if (source == addOrUpCourse) {
            new adminAddOrUpCourse(LoginTeacher_id);
        }
        if (source == deleteCourse) {
            new adminDeleteCourse();
        }
        if (source == mixDoGrade) {
            new adminCRUDGrade(LoginTeacher_id);
        }
        if (source == aboutSchool) {
            new showAboutSchool();
        }
        if (source == agreeLeave) {
            new adminAgreeStuLeave(this.LoginTeacher_id);
        }
        if (source == UnLogin) {
            WarningDialog.showWarningDialog(this, new Runnable() {
                @Override
                public void run() {
                    returnThis().setVisible(false);
                    closeWindows.openFrameCount--;
                    closeWindows.openFrameCount--;
                    new LoginUI();
                }
            });
        }
        if (source ==aboutAuthor){
            new aboutAuthor();
        }
    }
}
