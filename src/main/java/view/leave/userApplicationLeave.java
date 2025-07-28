package view.leave;

import config.closeWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mapper.userMapper;

public class userApplicationLeave extends JFrame implements ActionListener {
    // 创建并添加请假性质JComboBox
    private JComboBox<String> leaveTypeComboBox = new JComboBox<>(new String[]{"请选择选项","P0", "P1", "P2", "P3"});
    //创建申请老师列表
    private String[] options = {"无老师"};
    private JComboBox<String> teacherComboBox;
    // 创建并添加申请人姓名JLabel
    private JLabel applicantNameLabel = new JLabel();
    // 创建并添加申请理由JTextArea
    private JTextArea reasonTextArea = new JTextArea();
    //登录id
    private String loginStuId;
    private String leaveType;
    private String teaId;
    //提交按钮
    private JButton submitButton;


    public userApplicationLeave(String loginStuId) {
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
        options = userMapper.queryTeaByStuId(loginStuId);
        if (options != null) teacherComboBox = new JComboBox<>(options);
        initView();
    }

    public void initView() {

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
        JLabel teacherLabel = new JLabel("申请教师:");
        teacherLabel.setBounds(17, 110, 230, 41);
        teacherLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(teacherLabel);

        // 创建并添加申请教师JComboBox

        teacherComboBox.setBounds(130, 118, 160, 27);
        teacherComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(teacherComboBox);
        teacherComboBox.addActionListener(this);

        // 创建并添加“请假性质”JLabel
        JLabel leaveTypeLabel = new JLabel("请假性质:");
        leaveTypeLabel.setBounds(17, 160, 120, 41);
        leaveTypeLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(leaveTypeLabel);


        leaveTypeComboBox.setBounds(127, 169, 160, 27);
        leaveTypeComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(leaveTypeComboBox);
        leaveTypeComboBox.addActionListener(this);

        // 创建并添加“申请理由”JLabel
        JLabel reasonLabel = new JLabel("申请理由:");
        reasonLabel.setBounds(17, 230, 120, 41);
        reasonLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(reasonLabel);

        // 创建并添加申请人姓名JLabel
        reasonTextArea.setBounds(120, 239, 300, 300);
        reasonTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        reasonTextArea.setLineWrap(true);
        reasonTextArea.setWrapStyleWord(true);
        this.getContentPane().add(reasonTextArea);

        // 在initView方法中创建一个JButton对象，并设置其位置、大小和字体
        submitButton = new JButton("提交");
        submitButton.setBounds(450, 560, 100, 50);
        submitButton.setFont(new Font("宋体", Font.BOLD, 22));
        submitButton.addActionListener(this);
        this.getContentPane().add(submitButton);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //选择请假类别
        if (source == leaveTypeComboBox) {
            leaveType = (String) leaveTypeComboBox.getSelectedItem();
        }
        //选择向谁批准
        else if (source == teacherComboBox) {
            String selectedItem = (String) teacherComboBox.getSelectedItem();
            if(!selectedItem.equals("请选择选项")){
                Pattern p = Pattern.compile("[T]{0,}[0-9]{1,}");
                Matcher m = p.matcher(selectedItem);
                if (m.find()) teaId = m.group();
            }
        }
        //提交按钮
        else if(source==submitButton){
            String reason = reasonTextArea.getText();
            if(teaId!=null&&!(reason.isEmpty())){
                boolean result = userMapper.addLeaveByStu(loginStuId, teaId, leaveType, reason);
                if(result){
                    ImageIcon successIcon = new ImageIcon("src/img/success.png");
                    JOptionPane.showMessageDialog(this, "提交成功！", "success", JOptionPane.WARNING_MESSAGE, successIcon);
                    this.setVisible(false);
                    new userApplicationLeave(loginStuId);
                }else {
                    JOptionPane.showMessageDialog(this, "请求错误！", "error", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(this, "填写完整表单！", "warning", JOptionPane.WARNING_MESSAGE);
            }

        }

    }
}

