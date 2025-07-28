package UI;

import UI.AboutUI.aboutAuthor;
import UI.AboutUI.showAboutSchool;
import config.closeWindows;
import javafx.scene.layout.Border;
import mapper.userMapper;
import util.WarningDialog;
import view.leave.userApplicationLeave;
import view.leave.userQueryLeave;
import view.stu.userChangePass;
import view.stu.userQueryStu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class userUI extends baseUI implements ActionListener {

    private JMenuItem aboutSchool = new JMenuItem("学校");
    private JMenuItem queryStudent = new JMenuItem("查询学生");
    private JMenuItem queryGrade = new JMenuItem("查询成绩");
    private JMenuItem aboutAuthor = new JMenuItem("作者");
    private JMenuItem changePassword=new JMenuItem("修改密码");
    private JMenuItem unLogin = new JMenuItem("退出登录");
    private JMenuItem leave_application = new JMenuItem("请假申请");
    private JMenuItem queryLeave=new JMenuItem("请假查询");
    private String thisLoginStuId;


    public userUI(String user_id) {
        this.thisLoginStuId = user_id;
        //设置功能栏目
        initJMenuBar();
        closeWindows.createAndShowFrame(this);
    }

    //添加功能栏目
    protected void initJMenuBar() {
        this.setLayout(null);
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setFont(new Font("宋体", Font.BOLD, 22));

        JMenu about = new JMenu("关于");

        about.add(aboutSchool);
        about.add(aboutAuthor);
        about.add(unLogin);
        about.add(changePassword);
        aboutAuthor.addActionListener(this);
        aboutSchool.addActionListener(this);
        unLogin.addActionListener(this);
        changePassword.addActionListener(this);


        JMenu Student = new JMenu("学生");
        JMenu Grade = new JMenu("成绩");
        JMenu leave = new JMenu("请假");
        leave.add(leave_application);
        leave.add(queryLeave);
        queryLeave.addActionListener(this);
        leave_application.addActionListener(this);


        jMenuBar.add(Student);
        jMenuBar.add(Grade);
        jMenuBar.add(leave);
        jMenuBar.add(about);


        Student.add(queryStudent);
        Grade.add(queryGrade);


        queryStudent.addActionListener(this);
        queryGrade.addActionListener(this);



        this.setJMenuBar(jMenuBar);

        JLabel hello=new JLabel("welcome");
       // hello.setFont(new Font("Brush Script MT", Font.PLAIN, 108));//52501011001
        hello.setFont(new Font("Algerian", Font.BOLD, 64));//52501011001

        hello.setBounds(380,20,640,200);
        this.getContentPane().add(hello);

        JLabel LoginName=new JLabel();
        LoginName.setText(userMapper.queryLoginStuName(thisLoginStuId));
        System.out.println(LoginName.getText());
        LoginName.setFont(new Font("楷体",Font.BOLD,60));
        LoginName.setBounds(446,180,300,100);
        this.getContentPane().add(LoginName);

        //时间设置
        JLabel timeLabel=new JLabel();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timer timerStart=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long timeMillis = System.currentTimeMillis();
                String formatTime = sdf.format(new Date(timeMillis));
                timeLabel.setText(formatTime);

            }
        });
        timerStart.start();
        timeLabel.setFont(new Font("楷体",Font.BOLD,34));
        timeLabel.setBounds(360,200,500,230);
        this.getContentPane().add(timeLabel);
    }
    private userUI returnThis(){ return  this;}

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == queryStudent) {
            new userQueryStu();
        }
        if (source == queryGrade) {
            new userQueryStu(thisLoginStuId);
        }
        if (source == aboutSchool) {
            new showAboutSchool();
        }
        if (source == leave_application) {
            new userApplicationLeave(thisLoginStuId);
        }
        if(source==unLogin){
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
        if(source==aboutAuthor){
            new aboutAuthor();
        }
        if(source==changePassword){
            new userChangePass(thisLoginStuId);
        }
        if(source==queryLeave){
            new userQueryLeave(thisLoginStuId);
        }
    }
}
