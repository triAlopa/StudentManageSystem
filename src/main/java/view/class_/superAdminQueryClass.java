package view.class_;

import entity.Class;
import mapper.superAdminMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class superAdminQueryClass extends JFrame implements ActionListener {
    private String[] idList;
    private JComboBox collegeIdList;
    private String[] majorList;
    private JComboBox majorIdToNameList;
    private String collegeId;
    private String majorId;
    private String[] classList;
    private JComboBox classComboBox = new JComboBox();
    private String classId;

    private JButton updateButton = new JButton("更新");
    private JButton addButton = new JButton("添加");
    private String className;
    private JLabel classIdabel = new JLabel();
    private JLabel classNameabel = new JLabel();
    private JLabel  counselorNameabel=new JLabel();


    public  superAdminQueryClass(){
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("查询");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        idList = superAdminMapper.selectAllCollege();
        collegeIdList = new JComboBox<>(idList);
        majorList = new String[]{"请选择选项"};
        majorIdToNameList = new JComboBox<>(majorList);
        classList = new String[]{"请选择选项"};
        classComboBox = new JComboBox<>(classList);
        initView();
    }
    private void initView() {
        // 设置无管理容器
        this.setLayout(null);

        // 学院名称标签
        JLabel idLabel = new JLabel("学院");
        idLabel.setBounds(17, 56, 75, 41);
        idLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(idLabel);

        // 学院名称显示
        collegeIdList.setBounds(100, 65, 300, 27);
        collegeIdList.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdList);
        collegeIdList.addActionListener(this);

        // 专业名称标签
        JLabel nameLabel = new JLabel("专业");
        nameLabel.setBounds(17, 100, 60, 41);
        nameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameLabel);

        // 专业名称显示
        majorIdToNameList.setBounds(100, 107, 200, 27);
        majorIdToNameList.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorIdToNameList);
        majorIdToNameList.addActionListener(this);

        // 辅导员标签和组合框
        JLabel classLabel = new JLabel("班级");
        classLabel.setBounds(17, 144, 100, 41);
        classLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classLabel);

        classComboBox.setBounds(100, 151, 200, 27);
        classComboBox.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classComboBox);
        classComboBox.addActionListener(this);

        // 班级id标签和输入框
        JLabel classIdLabel = new JLabel("班级");
        classIdLabel.setBounds(17, 196, 100, 41);
        classIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classIdLabel);


        classIdabel.setBounds(130, 203, 300, 27);
        classIdabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classIdabel);

        // 班级名称标签和输入框
        JLabel classNameLabel = new JLabel("班级名称");
        classNameLabel.setBounds(17, 248, 100, 41);
        classNameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classNameLabel);


        classNameabel.setBounds(130, 255, 300, 27);
        classNameabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(classNameabel);

        // 辅导员标签和输入框
        JLabel counselorNameLabel = new JLabel("辅导员名称");
        counselorNameLabel.setBounds(17, 300, 150, 41);
        counselorNameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(counselorNameLabel);


        counselorNameabel.setBounds(170, 307, 300, 27);
        counselorNameabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(counselorNameabel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (collegeIdList == source) {
            this.repaint();
            this.validate();
            String selectedItem = (String) collegeIdList.getSelectedItem();
            Pattern p = Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            if (m.find()) collegeId = m.group();
            majorList = superAdminMapper.selectMajorByCoId(collegeId);
            majorIdToNameList.setModel(new DefaultComboBoxModel(majorList));
        }
        if (source == majorIdToNameList) {
            String selectedItem = (String) majorIdToNameList.getSelectedItem();
            Pattern p = Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            if (m.find()) majorId = m.group();
            classList = superAdminMapper.selectClassByCoId(majorId);
            classComboBox.setModel(new DefaultComboBoxModel(classList));
        }
        if (source == classComboBox) {
            String selectedItem = (String) classComboBox.getSelectedItem();
            Pattern p = Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            if (m.find()) classId = m.group();
            Class clazz=superAdminMapper.selectClassById(classId);
            classIdabel.setText(clazz.getClass_id());
            classNameabel.setText(clazz.getClass_name());
            counselorNameabel.setText(clazz.getCounselor_id());
        }
    }
}
