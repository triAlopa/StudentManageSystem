package view.counselor;

import mapper.superAdminMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class superAdminAddCounselor extends JFrame implements ActionListener {
    private String[] idList;
    private JComboBox collegeIdList;
    private JTextField idTextField = new JTextField();
    private JTextField nameTextField = new JTextField();
    private JTextField phoneTextField = new JTextField();
    private JButton addButton = new JButton("添加");
    private String collegeId;
    private String counselorId;
    private  String counselorName;
    private String counselorPhone;

    public superAdminAddCounselor(){
        this.setLayout(null);
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("添加");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        idList = superAdminMapper.selectAllCollege();
        collegeIdList = new JComboBox<>(idList);
        initView();
    }

    private void initView() {
        // ID标签
        JLabel collegeIdLabel = new JLabel("学院:");
        collegeIdLabel.setBounds(17, 56, 75, 41);
        collegeIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdLabel);

        // ID列表
        collegeIdList.setBounds(100, 65, 300, 27);
        collegeIdList.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdList);
        collegeIdList.addActionListener(this);

        // 辅导员ID标签和文本框
        JLabel idLabel = new JLabel("id");
        idLabel.setBounds(17, 100, 75, 41);
        idLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(idLabel);

        idTextField.setBounds(100, 107, 300, 27);
        idTextField.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(idTextField);

        // 姓名标签和文本框
        JLabel nameLabel = new JLabel("姓名");
        nameLabel.setBounds(17, 150, 75, 41);
        nameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameLabel);

        nameTextField.setBounds(100, 157, 300, 27);
        nameTextField.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameTextField);


        // 电话标签和文本框
        JLabel phoneLabel = new JLabel("电话");
        phoneLabel.setBounds(17, 200, 75, 41);
        phoneLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(phoneLabel);

        phoneTextField.setBounds(100, 207, 300, 27);
        phoneTextField.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(phoneTextField);



        addButton.setFont(new Font("宋体", Font.BOLD, 22));
        addButton.setBounds(300, 650, 150, 50); // 设置位置和大小，使其位于窗口的底部
        this.getContentPane().add(addButton);
        addButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (collegeIdList == source) {
            String selectedItem = (String) collegeIdList.getSelectedItem();
            Pattern p = Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            if (m.find()) collegeId = m.group();
        }
        if (source == addButton) {
            counselorId = idTextField.getText();
            counselorName= nameTextField.getText();
            counselorPhone = phoneTextField.getText();
            boolean flag = superAdminMapper.checkCounselorExist(counselorId);
            if (!flag) {
                if (counselorId.isEmpty() || counselorName.isEmpty() || counselorPhone.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "非法操作", "error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean result = superAdminMapper.addCounselor(counselorId,counselorName,counselorPhone,collegeId);
                    if (result) {
                        ImageIcon successIcon = new ImageIcon("src/img/success.png");
                        JOptionPane.showMessageDialog(this, "添加成功", "success", JOptionPane.ERROR_MESSAGE, successIcon);
                    }
                }
            }else {
                JOptionPane.showMessageDialog(this, "辅导员号存在", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
