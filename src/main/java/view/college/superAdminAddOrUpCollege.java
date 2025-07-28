package view.college;

import mapper.superAdminMapper;
import org.jdatepicker.impl.JDatePickerImpl;
import util.DatePicker;
import util.WarningDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class superAdminAddOrUpCollege extends JFrame implements ActionListener {
    private JTextField collegeIdText = new JTextField();
    private JTextField collegeNameText = new JTextField();
    private JDatePickerImpl establishmentDatePicker = DatePicker.getDatePicker();
    private JButton addButton = new JButton("添加");
    private JButton updateButton = new JButton("更新");
    private Date estDate;

    public superAdminAddOrUpCollege() {
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
        initView();
        dateActionListener();
    }


    private void initView() {

        this.setLayout(null);

        // 学院ID
        JLabel collegeIdLabel = new JLabel("学院ID:");
        collegeIdLabel.setBounds(17, 56, 100, 41);
        collegeIdLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdLabel);

        collegeIdText.setBounds(145, 65, 200, 27);
        collegeIdText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeIdText);

        // 学院名称
        JLabel collegeNameLabel = new JLabel("学院名称:");
        collegeNameLabel.setBounds(17, 100, 120, 41);
        collegeNameLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeNameLabel);

        collegeNameText.setBounds(145, 107, 200, 27);
        collegeNameText.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(collegeNameText);

        // 成立时间
        JLabel establishmentDateLabel = new JLabel("成立时间:");
        establishmentDateLabel.setBounds(17, 144, 130, 41);
        establishmentDateLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(establishmentDateLabel);

        establishmentDatePicker.setBounds(150, 151, 195, 41);
        establishmentDatePicker.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(establishmentDatePicker);
        establishmentDatePicker.addActionListener(this);

        //添加按钮
        addButton.setFont(new Font("宋体", Font.BOLD, 22));
        addButton.setBounds(300, 650, 150, 50); // 设置位置和大小，使其位于窗口的右下角
        this.getContentPane().add(addButton);
        addButton.addActionListener(this);

        // 更新按钮
        updateButton.setFont(new Font("宋体", Font.BOLD, 22));
        updateButton.setBounds(17, 650, 150, 50); // 设置位置和大小，使其位于窗口的左下角
        this.getContentPane().add(updateButton);
        updateButton.addActionListener(this);

        this.repaint();
    }


    private void dateActionListener() {
        establishmentDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estDate = (Date) establishmentDatePicker.getModel().getValue();
            }
        });
    }
    private superAdminAddOrUpCollege returnThis(){
        return this;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addButton) {
            String collegeId = collegeIdText.getText();

            String collegeName = collegeNameText.getText();
            if (collegeId.isEmpty() || collegeName.isEmpty() || estDate == null) {
                JOptionPane.showMessageDialog(this, "非法操作", "error", JOptionPane.ERROR_MESSAGE);
            } else {  WarningDialog.showWarningDialog(this, new Runnable() {
                boolean flag = superAdminMapper.checkCollegeExist(collegeId);
                @Override
                public void run() {
                    JOptionPane optionPane = new JOptionPane();

                    if (!flag) {
                        boolean result = superAdminMapper.addCollege(collegeId, collegeName, estDate);
                        if (result) {
//                            System.out.println("-------------");
//                            ImageIcon successIcon=new ImageIcon("src/img/success.png");
//                            JOptionPane.showMessageDialog(null, "添加成功", "success", JOptionPane.ERROR_MESSAGE, successIcon);
                            ImageIcon successIcon = new ImageIcon("src/img/success.png");
                            JDialog dialog = optionPane.createDialog("success");
                            optionPane.setMessage("添加成功");
                            optionPane.setIcon(successIcon);
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                            returnThis().setVisible(false);
                            returnThis().repaint();
                            new superAdminAddOrUpCollege();
                        } else {
                            optionPane.setMessage("添加失败，未知错误");
                            JDialog dialog = optionPane.createDialog("error");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                        }
                    } else {
                        optionPane.setMessage("id已存在");
                        JDialog dialog = optionPane.createDialog("error");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                    }
                }
            });
            }
        }
        if (source == updateButton) {
            String collegeId = collegeIdText.getText();
            boolean flag = superAdminMapper.checkCollegeExist(collegeId);
            if (collegeId.isEmpty() && collegeNameText.getText().isEmpty() && estDate == null) {
                JOptionPane.showMessageDialog(this, "非法操作", "error", JOptionPane.ERROR_MESSAGE);
            }else {
                if (flag) {
                    boolean result = superAdminMapper.partialUpdateCollegeInfo(collegeId, collegeNameText.getText(), estDate);
                    if (result) {
                        ImageIcon successImg = new ImageIcon("src/img/success.png");
                        JOptionPane.showMessageDialog(this, "更新成功", "success", JOptionPane.ERROR_MESSAGE, successImg);
                    } else {
                        JOptionPane.showMessageDialog(this, "更新失败", "error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "学院id不存在", "error", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }
}
