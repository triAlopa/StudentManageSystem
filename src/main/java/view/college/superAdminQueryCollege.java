package view.college;

import entity.College;
import mapper.superAdminMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class superAdminQueryCollege extends JFrame implements ActionListener {

    private String[] idList;
    private JComboBox collegeIdList;
    private JLabel nameDisplay = new JLabel();
    private JLabel establishmentDateDisplay = new JLabel();

    public superAdminQueryCollege(){
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("添加&更新");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        idList= superAdminMapper.selectAllCollege();
        collegeIdList=new JComboBox<>(idList);
        initView();
    }

    private void initView() {
        // 设置无管理容器
        this.setLayout(null);

        // ID标签
        JLabel idLabel = new JLabel("学院");
        idLabel.setBounds(17, 56, 100, 41);
        idLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(idLabel);

        // ID列表
        collegeIdList.setBounds(130, 65, 300, 27);
        collegeIdList.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdList);
        collegeIdList.addActionListener(this);

        // 学院名称标签
        JLabel nameLabel = new JLabel("学院名称");
        nameLabel.setBounds(17, 100, 120, 41);
        nameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameLabel);

        // 学院名称显示
        nameDisplay.setBounds(145, 107, 200, 27);
        nameDisplay.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(nameDisplay);

        // 成立时间标签
        JLabel establishmentDateLabel = new JLabel("成立时间");
        establishmentDateLabel.setBounds(17, 144, 130, 41);
        establishmentDateLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(establishmentDateLabel);

        // 成立时间显示

        establishmentDateDisplay.setBounds(145, 151, 200, 27);
        establishmentDateDisplay.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(establishmentDateDisplay);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(collegeIdList==e.getSource()){
            String selectedItem = (String)collegeIdList.getSelectedItem();
            Pattern p=Pattern.compile("[0-9]{1,}");
            Matcher m = p.matcher(selectedItem);
            String collegeId="";
            if (m.find()) collegeId= m.group();
            if(collegeId.isEmpty()){
                JOptionPane.showMessageDialog(this,"error","未知错误",JOptionPane.ERROR_MESSAGE);
            }else {
                College college=superAdminMapper.queryByCo_id(collegeId);
                nameDisplay.setText(college.getCollegeName());
                establishmentDateDisplay.setText(college.getEstablishedDate());
            }
        }
    }
}
