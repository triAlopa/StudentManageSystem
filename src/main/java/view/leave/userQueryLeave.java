package view.leave;

import config.closeWindows;
import entity.Leave_application;
import mapper.userMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userQueryLeave extends JFrame implements ActionListener {
    private String loginStuId;
    // 创建并添加申请人姓名JLabel
    private JLabel applicantNameLabel = new JLabel();
    //申请教师名字
    private JLabel teacherName=new JLabel();
    private JTextArea reasonText=new JTextArea();
    private JLabel statusText=new JLabel();
    private JButton close=new JButton("关闭");
    public userQueryLeave(String loginStuId){
        this.setAlwaysOnTop(true);
        //
        this.setLayout(null);
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("请假申请");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        closeWindows.createAndShowFrame(this);
        this.loginStuId = loginStuId;
        //初始化申请人名字
        applicantNameLabel.setText(userMapper.queryLoginStuName(loginStuId));
        //
        Leave_application leave = userMapper.queryLeaveInfo(loginStuId);
        if (leave != null) {
            teacherName.setText(leave.getTeacher_id());
            reasonText.setText(leave.getReason());
            String status = GetStatus(leave.getIs_approved());
            statusText.setText(status);
        }else {
            JOptionPane.showMessageDialog(this,"没有申请假条","error",JOptionPane.ERROR_MESSAGE);
            this.setVisible(false);
        }


        initView();
    }

    private void initView() {

        this.setLayout(null);
        // 创建并添加“申请人”JLabel
        JLabel applicantLabel = new JLabel("申请人:");
        applicantLabel.setBounds(17, 56, 100, 41);
        applicantLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(applicantLabel);


        applicantNameLabel.setBounds(120, 56, 160, 41);
        applicantNameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(applicantNameLabel);

        // 创建并添加“申请教师”JLabel
        JLabel teacherLabel = new JLabel("审批教师:");
        teacherLabel.setBounds(17, 110, 150, 41);
        teacherLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(teacherLabel);

        //教师姓名显示
        teacherName.setBounds(130, 118, 160, 27);
        teacherName.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(teacherName);

        //申请理由
        JLabel reasonLabel=new JLabel("申请理由:");
        reasonLabel.setBounds(17, 160, 120, 41);
        reasonLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(reasonLabel);

        reasonText.setBounds(130, 169, 300, 300);
        reasonText.setFont(new Font("宋体", Font.BOLD, 22));
        reasonText.setLineWrap(true);
        reasonText.setWrapStyleWord(true);
        this.getContentPane().add(reasonText);

        JLabel approvedStatus=new JLabel("申请状态");
        approvedStatus.setBounds(17, 500, 120, 41);
        approvedStatus.setFont(new Font("宋体", Font.BOLD, 22) );
        this.getContentPane().add(approvedStatus);

        statusText.setBounds(130,509,100,41);
        statusText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(statusText);

        close.setBounds(198,630,90,70);
        close.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(close);
        close.addActionListener(this);
    }
    private String GetStatus(String status){
        this.statusText.setOpaque(true);
        if(status.equals("-1")) {
            statusText.setBackground(Color.RED);
            return "不通过";
        }
        else if(status.equals("0")) {
            statusText.setBackground(Color.YELLOW);
            return "未审批";
        }
        else if(status.equals("1")) {
            statusText.setBackground(Color.GREEN);
            return "通过";
        }
        else return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(close==e.getSource()){
            this.setVisible(false);
        }
    }
}
