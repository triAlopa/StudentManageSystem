package view.major;

import entity.College;
import mapper.superAdminMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class superAdminQueryMajor extends JFrame implements ActionListener {

    private String[] idList;
    private JComboBox collegeIdList;
    private String[] majorList;
    private JComboBox majorIdToNameList;
    private JLabel establishmentDateDisplay = new JLabel();

    public superAdminQueryMajor(){
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
        idList= superAdminMapper.selectAllCollege();
        collegeIdList=new JComboBox<>(idList);
        majorList= new String[]{"请选择选项"};
        majorIdToNameList=new JComboBox<>(majorList);
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
        majorIdToNameList.setBounds(80, 107, 200, 27);
        majorIdToNameList.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(majorIdToNameList);
        majorIdToNameList.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(collegeIdList==e.getSource()){
            String selectedItem = (String)collegeIdList.getSelectedItem();
            Pattern p=Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            String collegeId="";
            if (m.find()) collegeId= m.group();
            majorList= superAdminMapper.selectMajorByCoId(collegeId);
            majorIdToNameList.setModel(new DefaultComboBoxModel(majorList));

        }
    }
}
