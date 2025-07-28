package view.stu;

import mapper.userMapper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userChangePass extends JFrame implements ActionListener, DocumentListener {
    private JTextField originalPass = new JTextField();
    private JTextField changePass = new JTextField();
    private JTextField eqPass = new JTextField();
    private JButton changeButton;
    private String login_id;
    private JLabel warning;

    public userChangePass(String login_id) {
        //宽高
        this.setSize(485, 783);
        //标题
        this.setTitle("学生修改密码");
        //居中
        this.setLocationRelativeTo(null);
        //是否可见
        this.setVisible(true);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.login_id = login_id;
        this.setAlwaysOnTop(true);
        initView();
    }

    private void initView() {
        this.setLayout(null);
        // 密码
        JLabel orPassLabel = new JLabel("原来密码");
        orPassLabel.setBounds(17, 56, 100, 41);
        orPassLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(orPassLabel);

        // 密码输入框
        originalPass.setBounds(130, 65, 260, 27);
        originalPass.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(originalPass);

        // 修改的密码
        JLabel changePassLabel = new JLabel("修改密码");
        changePassLabel.setBounds(17, 156, 100, 41);
        changePassLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(changePassLabel);

        //修改密码输入框
        changePass.setBounds(130, 165, 260, 27);
        changePass.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(changePass);
        changePass.getDocument().addDocumentListener(this);

        // 修改的密码
        JLabel eqPassLabel = new JLabel("确认密码");
        eqPassLabel.setBounds(17, 256, 100, 41);
        eqPassLabel.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(eqPassLabel);

        eqPass.setBounds(130, 265, 260, 27);
        eqPass.setFont(new Font("宋体", Font.BOLD, 22));
        eqPass.getDocument().addDocumentListener(this);
        this.getContentPane().add(eqPass);


        warning = new JLabel("密码不一致");
        warning.setBounds(90, 224, 200, 41);
        warning.setFont(new Font("宋体", Font.BOLD, 22));
        this.getContentPane().add(warning);
        warning.setVisible(false);

        changeButton = new JButton("修改");
        changeButton.setBounds(190, 310, 90, 60);
        this.getContentPane().add(changeButton);
        changeButton.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == changeButton) {
            boolean flag = userMapper.checkUserPass(login_id, originalPass.getText());
            if (flag) {
                if (eqPass.getText().isEmpty() || changePass.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "请输入修改的密码", "error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String passRegex = "[0-9A-Za-z_]{6,15}";
                    if (changePass.getText().matches(passRegex)) {
                        if (eqPass.getText().equals(changePass.getText())) {
                            if (!originalPass.getText().equals(eqPass.getText())) {
                                boolean result = userMapper.changeUserPass(login_id, changePass.getText());
                                if (result) {
                                    ImageIcon successIcon = new ImageIcon("src/img/success.png");
                                    JOptionPane.showMessageDialog(this, "修改成功", "success", JOptionPane.ERROR_MESSAGE, successIcon);
                                    this.repaint();
                                    this.setVisible(false);
                                    new userChangePass(login_id);
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "修改密码不得和原来一致", "warning", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "密码不一致", "error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "格式错误，只能输入数字或字母下划线，且长度6-15", "error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(this, "原来密码错误", "error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void checkPassword() {
        String changePassText = changePass.getText();
        String eqPassText = eqPass.getText();
        if (changePassText.isEmpty() || eqPassText.isEmpty()) {
            warning.setVisible(false);
            return;
        }
        if (changePassText.equals(eqPassText)) {
            warning.setVisible(false);
        } else warning.setVisible(true);

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        checkPassword();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        checkPassword();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        checkPassword();
    }
}
