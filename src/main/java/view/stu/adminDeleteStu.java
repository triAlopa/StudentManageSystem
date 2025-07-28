package view.stu;

import mapper.adminMapper;
import mapper.userMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class adminDeleteStu extends JFrame implements ActionListener {

    // 创建学号输入框
    private JTextField stuIdText = new JTextField();
    // 创建提交按钮
    private JButton submitButton = new JButton("提交");
    private JComboBox<String> collegeComboBox;
    private JComboBox<String> majorComboBox;
    private JComboBox<String> classComboBox;
    private JComboBox<String> stuNameComboBox;
    private String[] majorOptions = {"请选择选项"};
    private String[] collegeOptions = {"请选择选项"};
    private String[] classOptions = {"请选择选项"};
    private String[] stuNameOptions = {"请选择选项"};
    private String classId;


    public adminDeleteStu() {
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("删除学生");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
//        closeWindows.createAndShowFrame(this);
        collegeOptions = userMapper.selectAllCollege();

        this.collegeComboBox = new JComboBox<>(collegeOptions);
        this.majorComboBox = new JComboBox<>(majorOptions);
        this.classComboBox = new JComboBox<>(classOptions);
        this.stuNameComboBox = new JComboBox<>(stuNameOptions);
        initView();
    }

    private void initView() {
        this.setLayout(null);

        JLabel collegeNameTextArea = new JLabel("学院:");
        collegeNameTextArea.setBounds(17, 230, 90, 41);
        collegeNameTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeNameTextArea);

        collegeComboBox.setEditable(false);
        collegeComboBox.setBounds(85, 240, 195, 25);
        collegeComboBox.setFont(new Font("宋体", Font.BOLD, 17));
        this.getContentPane().add(collegeComboBox);
        collegeComboBox.addActionListener(this);

        JLabel majorNameTextArea = new JLabel("专业:");
        majorNameTextArea.setBounds(17, 300, 90, 41);
        majorNameTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorNameTextArea);

        majorComboBox.setEditable(false);
        majorComboBox.setBounds(85, 310, 195, 25);
        majorComboBox.setFont(new Font("宋体", Font.BOLD, 17));
        this.getContentPane().add(majorComboBox);
        majorComboBox.addActionListener(this);

        JLabel classNameTextArea = new JLabel("班级:");
        classNameTextArea.setBounds(17, 370, 90, 41);
        classNameTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classNameTextArea);

        classComboBox.setEditable(false);
        classComboBox.setBounds(85, 380, 195, 25);
        classComboBox.setFont(new Font("宋体", Font.BOLD, 17));
        this.getContentPane().add(classComboBox);
        classComboBox.addActionListener(this);

        JLabel stuNameTextArea = new JLabel("学生:");
        stuNameTextArea.setBounds(17, 440, 90, 41);
        stuNameTextArea.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(stuNameTextArea);

        stuNameComboBox.setEditable(false);
        stuNameComboBox.setBounds(85, 450, 350, 25);
        stuNameComboBox.setFont(new Font("宋体", Font.BOLD, 17));
        this.getContentPane().add(stuNameComboBox);
        stuNameComboBox.addActionListener(this);

        // 创建学号标签
        JLabel stuIdLabel = new JLabel("学号:");
        stuIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        stuIdLabel.setBounds(17, 620, 75, 41); // 设置位置和大小，参考已有组件
        this.add(stuIdLabel);


        stuIdText.setFont(new Font("宋体", Font.BOLD, 22));
        stuIdText.setBounds(95, 627, 200, 27); // 设置位置和大小，参考已有组件
        this.add(stuIdText);

        submitButton.setFont(new Font("宋体", Font.BOLD, 22));
        submitButton.setBounds(300, 627, 150, 50); // 设置位置和大小，参考已有组件
        this.add(submitButton);
        submitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Pattern p = Pattern.compile("[0-9]{1,}");
        if (source == submitButton) {
            String stuIdTextText = stuIdText.getText();
            boolean result = adminMapper.deleteById(stuIdTextText);
            if (result) {
                ImageIcon successIcon = new ImageIcon("src/img/success.png");
                JOptionPane.showMessageDialog(this, "删除成功！", "success", JOptionPane.INFORMATION_MESSAGE, successIcon);
                stuIdText.setText("");
                stuNameComboBox.setSelectedIndex(0);
                String queryStu = "select S.name,S.student_id from student S join academicinfo A " +
                        "on A.student_id= S.student_id where  A.class_id = ?";
                stuNameOptions=adminMapper.selectItemById(queryStu,"student_id","name",classId);
                if (stuNameOptions != null) {
                    stuNameComboBox.setModel(new DefaultComboBoxModel<>(stuNameOptions));
                }
            } else {
                JOptionPane.showMessageDialog(this, "学号不存在！", "error", JOptionPane.ERROR_MESSAGE);
            }
        }
       else if (source == collegeComboBox) {
            String selectedItem = (String) collegeComboBox.getSelectedItem();
            if (!selectedItem.equals("请选择选项")) {

                //捕获 各个id
                Matcher m = p.matcher(selectedItem);
                //找到了 将捕获到的赋值
                String collegeId = null;
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
//                System.out.println(query);
            }
        }

       else if(source==majorComboBox){
            String selectedItem = (String) majorComboBox.getSelectedItem();
            if (!selectedItem.equals("请选择选项")) {
                String queryClass = "SELECT  cl.class_id , cl.class_name " +
                        "FROM   Class cl JOIN Major m ON cl.major_id = m.major_id WHERE   m.major_id = ? ";
                //找到了 将捕获到的赋值
                Matcher m = p.matcher(selectedItem);
                //捕获 各个id
                String MajorId = null;
                if (m.find()) {
                    MajorId = m.group();
                }
                classOptions = adminMapper.selectItemById(queryClass, "class_id", "class_name", MajorId);
                if (classOptions != null) {
                    classComboBox.setModel(new DefaultComboBoxModel<>(classOptions));
                }
//                query.append(" AND EXISTS (SELECT 1 FROM major m WHERE m.major_id = a.major_id AND m.major_id =  '").append(MajorId).append("' )");
                //传递 点击 创建的查询语句
//                System.out.println(query);
            }
        }

        else if (source == classComboBox) {
            String selectedItem = (String) classComboBox.getSelectedItem();
            if (!selectedItem.equals("请选择选项")) {
                String queryStu = "select S.name,S.student_id from student S join academicinfo A " +
                        "on A.student_id= S.student_id where  A.class_id = ?";
                //捕获 各个id
                Matcher m = p.matcher(selectedItem);
                //id 赋值
                 classId=null;
                if (m.find()) {
                     classId = m.group();
                }
                stuNameOptions=adminMapper.selectItemById(queryStu,"student_id","name",classId);
                if (stuNameOptions != null) {
                    stuNameComboBox.setModel(new DefaultComboBoxModel<>(stuNameOptions));
                }
            }
        }
        else if(source==stuNameComboBox){
            String selectedItem = (String) stuNameComboBox.getSelectedItem();
            if(!selectedItem.equals("请选择选项")){
                //捕获 各个id
                Matcher m = p.matcher(selectedItem);
                String stuId =null;
                if (m.find()) {
                    stuId = m.group();
                }
                stuIdText.setText(stuId);
            }
        }

    }
}
