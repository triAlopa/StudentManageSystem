package view.teacher;

import mapper.superAdminMapper;
import org.jdatepicker.impl.JDatePickerImpl;
import util.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class superAdminAddTea extends JFrame implements ActionListener {
    private String[] idList;
    private JComboBox collegeIdList;
    private JTextField idTextField = new JTextField();
    private String collegeId;
    private String[] genderList={"男","女"};
    private JComboBox genderCombo=new JComboBox<>(genderList);
    private JButton addButton = new JButton("添加");
    private JTextField nameTextField = new JTextField();
    private JDatePickerImpl datePicker = DatePicker.getDatePicker();
    private String teaId ;
    private String teaName;
    private String gender;
    private Date hire_date;

    public superAdminAddTea(){
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
        dateActionListener();
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

        // 教师ID标签和文本框
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


        // 性别标签和文本框
        JLabel genderLabel = new JLabel("性别");
        genderLabel.setBounds(17, 200, 75, 41);
        genderLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(genderLabel);


        genderCombo.setBounds(100, 207, 300, 27);
        genderCombo.setFont(new Font("宋体", Font.BOLD, 22));
        genderCombo.addActionListener(this);
        this.getContentPane().add(genderCombo);

        JLabel dateLabel = new JLabel("入职日期");
        // 日期标签和日期选择器
        dateLabel.setBounds(17, 250, 100, 41);
        dateLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(dateLabel);


        datePicker.setBounds(120, 257, 300, 27);
        this.getContentPane().add(datePicker);
        datePicker.addActionListener(this);

        addButton.setFont(new Font("宋体", Font.BOLD, 22));
        addButton.setBounds(300, 650, 150, 50); // 设置位置和大小，使其位于窗口的底部
        this.getContentPane().add(addButton);
        addButton.addActionListener(this);

    }

    private void dateActionListener() {
        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hire_date = (Date) datePicker.getModel().getValue();
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (collegeIdList == source) {
            String selectedItem = (String) collegeIdList.getSelectedItem();
            Pattern p = Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            idTextField.setText("T");
            if (m.find()) collegeId = m.group();
        }
        if (genderCombo == source) {
            gender = (String) genderCombo.getSelectedItem();
        }

        if (source == addButton) {
            teaId = idTextField.getText();
            teaName= nameTextField.getText();
            boolean flag = superAdminMapper.checkTeaExist(teaId);
            if (!flag) {
                if (teaName.isEmpty() ||datePicker==null|| teaId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "非法操作", "error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean result = superAdminMapper.addTea(teaId,teaId,gender,hire_date,collegeId);
                    if (result) {
                        ImageIcon successIcon = new ImageIcon("src/img/success.png");
                        JOptionPane.showMessageDialog(this, "添加成功", "success", JOptionPane.ERROR_MESSAGE, successIcon);
                    }
                }
            }else {
                JOptionPane.showMessageDialog(this, "教师号存在", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
