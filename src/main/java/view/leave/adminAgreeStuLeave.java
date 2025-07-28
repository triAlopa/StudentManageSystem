package view.leave;

import entity.Leave_application;
import mapper.adminMapper;
import mapper.userMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminAgreeStuLeave extends JFrame implements ActionListener {
    private String thisLoginTea_id;
    private JLabel stuId = new JLabel();
    private JLabel stuName = new JLabel();
    private JLabel type = new JLabel();
    private JTextArea reason = new JTextArea();
    private Leave_application l;
    private  JButton approveButton = new JButton("批准");
    private JButton refuseButton=new JButton("否决");

    public adminAgreeStuLeave(String tea_id) {
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("请假审批");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);

        this.thisLoginTea_id = tea_id;
        initView();
        l = adminMapper.queryLeaveToStuByTeaId(tea_id);

        if (l == null) {
             JOptionPane.showMessageDialog(this,"你没有要审批的申请","error",JOptionPane.ERROR_MESSAGE);
             this.setVisible(false);
             return;
        }
        stuId.setText(l.getStudent_id());
        type.setText(l.getLeave_type());
        typeColor(type.getText());
        stuName.setText(userMapper.queryLoginStuName(l.getStudent_id()));
        reason.setText(l.getReason());
    }

    private void initView() {
        // 取消默认居中放置容器位置，取消了会根据设置的x，y坐标设置位置
        this.setLayout(null);

        // 学生id
        JLabel studentIdLabel = new JLabel("学生id:");
        studentIdLabel.setBounds(17, 20, 110, 41);
        studentIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(studentIdLabel);


        stuId.setBounds(130, 27, 200, 27);
        stuId.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(stuId);

        // 学生姓名
        JLabel NameLabel = new JLabel("学生姓名:");
        NameLabel.setBounds(17, 70, 110, 41);
        NameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(NameLabel);


        stuName.setBounds(130, 77, 200, 27);
        stuName.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(stuName);

        // 申请类别
        JLabel typeLabel = new JLabel("申请类别:");
        typeLabel.setBounds(17, 120, 110, 41);
        typeLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(typeLabel);


        type.setBounds(130, 127, 50, 27);
        type.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(type);

        // 申请原因
        JLabel reasonLabel = new JLabel("申请原因:");
        reasonLabel.setBounds(17, 170, 110, 41);
        reasonLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(reasonLabel);


        reason.setBounds(130, 177, 300, 350); // 设置文本区域的大小
        reason.setFont(new Font("宋体", Font.BOLD, 22));
        reason.setLineWrap(true);
        reason.setWrapStyleWord(true);
        this.getContentPane().add(reason);

        // 批准按钮

        approveButton.setBounds(330, 560, 100, 40); // 设置按钮的位置和大小
        approveButton.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(approveButton);
        approveButton.addActionListener(this);

        //否决按钮
        refuseButton= new JButton("否决");
        refuseButton.setBounds(150, 560, 100, 40);
        refuseButton.setFont(new Font("宋体", Font.BOLD, 22));
        refuseButton.addActionListener(this);
        this.getContentPane().add(refuseButton);
    }
    public void typeColor(String type){
        this.type.setOpaque(true);
        switch (type) {
            case "P0":
                this.type.setBackground(Color.RED);
                break;
            case "P1":
                this.type.setBackground(Color.magenta);
                break;
            case "P2":
                this.type.setBackground(Color.orange);
                break;
            default:
                this.type.setBackground(Color.green);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String leaveId = l.getLeave_id();
        if(e.getSource()==approveButton){
            boolean result = adminMapper.approveStuLeaveById(leaveId);
            if(result){
                ImageIcon successIcon = new ImageIcon("src/img/success.png");
                JOptionPane.showMessageDialog(this, "审批成功！", "success", JOptionPane.INFORMATION_MESSAGE, successIcon);
                this.setVisible(false);
                new adminAgreeStuLeave(thisLoginTea_id);
            }else {
                JOptionPane.showMessageDialog(this, "未知错误", "success", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if(e.getSource()==refuseButton){
            boolean result = adminMapper.refuseStuLeaveById(leaveId);
            if(result){
                ImageIcon successIcon = new ImageIcon("src/img/success.png");
                JOptionPane.showMessageDialog(this, "已拒绝审批", "success", JOptionPane.INFORMATION_MESSAGE, successIcon);
                this.setVisible(false);
                new adminAgreeStuLeave(thisLoginTea_id);
            }else {
                JOptionPane.showMessageDialog(this, "未知错误", "success", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
