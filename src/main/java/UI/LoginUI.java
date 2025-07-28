package UI;

import config.closeWindows;
import config.database;
import util.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class LoginUI extends baseUI implements ActionListener, MouseListener {

    private JLabel loginJlabel = new JLabel("登录系统");
    private JTextField userText = new JTextField();
    private JPasswordField passwordText = new JPasswordField();
    private JTextField codeText = new JTextField();
    private JTextArea codeArea = new JTextArea();
    private String eqCode = Code.randomCode();
    private JLabel refresh = new JLabel(new ImageIcon("src/img/refresh.png"));
    private JButton jButton = new JButton("登录");
    private JLabel _33 = new JLabel(new ImageIcon("src/img/33Open.png"));
    private JLabel _22 = new JLabel(new ImageIcon("src/img/22Open.png"));
    private JLabel eye = new JLabel(new ImageIcon("src/img/closeEye.png"));

    public LoginUI() {
        super();
        initView();
//        closeWindows.createAndShowFrame(this);
    }

    public void initView() {

        loginJlabel.setBounds(400, 35, 200, 100);
        loginJlabel.setFont(new Font("宋体", Font.BOLD, 45));
        this.getContentPane().add(loginJlabel);
        JLabel userName = new JLabel(new ImageIcon("src/img/user.png"));
        JLabel password = new JLabel(new ImageIcon("src/img/password.png"));
        JLabel code = new JLabel(new ImageIcon("src/img/code.png"));
        //取消默认居中放置容器位置，取消了会根据设置的x，y坐标设置位置
        this.setLayout(null);

        jButton.setFont(new Font("宋体", Font.BOLD, 22));
        jButton.setBounds(485, 440, 100, 50);
        this.getContentPane().add(jButton);
        jButton.addActionListener(this);


        userName.setBounds(237, 199, 48, 48);
        this.getContentPane().add(userName);


        userText.setBounds(320, 206, 400, 37);
        userText.setFont(new Font("宋体", Font.BOLD, 21));
//        userText.addMouseListener(this);
        this.getContentPane().add(userText);

        password.setBounds(237, 271, 48, 48);
        this.getContentPane().add(password);

        passwordText.setBounds(320, 283, 400, 37);
        passwordText.setFont(new Font("宋体", Font.BOLD, 21));
        this.getContentPane().add(passwordText);
        passwordText.addMouseListener(this);

        eye.setBounds(720, 283, 32, 32);
        eye.addMouseListener(this);
        this.getContentPane().add(eye);

        code.setBounds(237, 340, 48, 48);
        this.getContentPane().add(code);

        codeText.setBounds(320, 351, 150, 37);
        codeText.setFont(new Font("宋体", Font.BOLD, 21));
        this.getContentPane().add(codeText);

        codeArea.setText(eqCode);
        codeArea.setBounds(500, 358, 65, 30);
        codeArea.setFont(new Font("宋体", Font.BOLD, 21));
        this.getContentPane().add(codeArea);

        refresh.setBounds(570, 358, 32, 32);
        this.getContentPane().add(refresh);
        refresh.addMouseListener(this);

        _22.setBounds(182, 4, 222, 202);
        this.getContentPane().add(_22);

        _33.setBounds(584, 0, 200, 208);
        this.getContentPane().add(_33);


        this.revalidate(); // 确保组件被正确添加和布局
        this.repaint(); // 重绘窗口以显示新添加的组件
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == jButton) {
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                String user = userText.getText();
                String password = passwordText.getText();
                String code = codeText.getText();
                new database();
                Connection connection = database.getConnection();
                // 创建一个Statement对象
                statement = connection.createStatement();

                String codeRegex = "[a-zA-Z0-9]{5}";
                if (!(user.isEmpty() || password.isEmpty())) {
                    if (!code.isEmpty()) {
                        if (code.matches(codeRegex)) {  //
                            if (code.equals(eqCode)) {
                                // 执行SQL查询
                                String sql = "SELECT * FROM user WHERE user_id = '" + user + "' AND password = '" + password + "'";
                                resultSet = statement.executeQuery(sql);
                                if (resultSet.next()) {
                                    int role = resultSet.getInt("role");
                                    String Id = resultSet.getString("user_id");
                                    ImageIcon successIcon = new ImageIcon("src/img/success.png");
                                    if (role == 0) {
                                        JOptionPane.showMessageDialog(null, "学生登录成功！", "success", JOptionPane.WARNING_MESSAGE, successIcon);
                                        this.setVisible(false);
                                        closeWindows.openFrameCount--;
                                        closeWindows.openFrameCount--;
                                        new userUI(Id);
                                    } else if (role == 1) {
                                        JOptionPane.showMessageDialog(null, "教师登录成功！", "success", JOptionPane.WARNING_MESSAGE, successIcon);
                                        this.setVisible(false);
                                        closeWindows.openFrameCount--;
                                        closeWindows.openFrameCount--;
                                        new adminUI(Id);
                                    } else if (role == 2) {
                                        JOptionPane.showMessageDialog(null, "管理员登录成功！", "success", JOptionPane.WARNING_MESSAGE, successIcon);
                                        this.setVisible(false);
                                        closeWindows.openFrameCount--;
                                        closeWindows.openFrameCount--;
                                        new superAdminUI();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "不存在用户或密码错误！", "Warning", JOptionPane.ERROR_MESSAGE);
                                    String tempCode=Code.randomCode();
                                    eqCode=tempCode;
                                    codeArea.setText(tempCode);
                                    codeArea.repaint();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "验证码输入错误", "Warning", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "验证码格式不正确", "Warning", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {

                        JOptionPane.showMessageDialog(null, "请输入验证码！", "Warning", JOptionPane.ERROR_MESSAGE);
                    }
                } else JOptionPane.showMessageDialog(null, "请输入用户或密码", "Warning", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == refresh) {
            String tempCode = Code.randomCode();
            codeArea.setText(tempCode);
            eqCode=tempCode;
            codeArea.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == eye) {
            passwordText.setEchoChar((char) 0);
            eye.setIcon(new ImageIcon("src/img/openEye.png"));
            _33.setIcon(new ImageIcon("src/img/33Close.png"));
            _22.setIcon(new ImageIcon("src/img/22Close.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getSource();
        if (source == eye) {
            passwordText.setEchoChar('•');
            eye.setIcon(new ImageIcon("src/img/closeEye.png"));
            _33.setIcon(new ImageIcon("src/img/33Open.png"));
            _22.setIcon(new ImageIcon("src/img/22Open.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        if (source == passwordText) {
            _33.setIcon(new ImageIcon("src/img/33Close.png"));
            _22.setIcon(new ImageIcon("src/img/22Close.png"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
        if (source == passwordText) {
            _33.setIcon(new ImageIcon("src/img/33Open.png"));
            _22.setIcon(new ImageIcon("src/img/22Open.png"));
        }
    }

}

