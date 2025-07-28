package UI.AboutUI;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class aboutAuthor extends JFrame {
    private JLabel CHEN = new JLabel("陈华鑫");
    private JLabel GUAN = new JLabel("管豪杰");
    private JLabel TANG = new JLabel("唐宝瑞");
    private JLabel XU = new JLabel("徐昊");
    private JLabel ZHONG = new JLabel("钟宇");
    private JLabel docLabel=null;

    public aboutAuthor() {
        //设置无管理容器
        this.setLayout(null);
        //设置宽高
        this.setSize(510, 500);
        //总是置顶显示
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //设置标题
        this.setTitle("作者");
        this.setVisible(true);
        initView();
    }

    public aboutAuthor returnThis(){
        return this;
    };

    private void initView() {
        CHEN.setBounds(45, 20, 120, 30);
        CHEN.setFont(new Font("宋体", Font.BOLD, 28));
        this.getContentPane().add(CHEN);

        GUAN.setBounds(300, 20, 120, 30);
        GUAN.setFont(new Font("宋体", Font.BOLD, 28));
        this.getContentPane().add(GUAN);

        TANG.setBounds(45, 100, 120, 30);
        TANG.setFont(new Font("宋体", Font.BOLD, 28));
        this.getContentPane().add(TANG);

        XU.setBounds(300, 100, 120, 30);
        XU.setFont(new Font("宋体", Font.BOLD, 28));
        this.getContentPane().add(XU);

        ZHONG.setBounds(45, 180, 120, 30);
        ZHONG.setFont(new Font("宋体", Font.BOLD, 28));
        this.getContentPane().add(ZHONG);

        JLabel document=new JLabel("设计文档");
        document.setBounds(100,265,100,50);
        document.setFont(new Font("宋体", Font.BOLD, 24));
        this.getContentPane().add(document);

        JLabel docLabel=new JLabel(new ImageIcon("src/img/doc.png"));
        docLabel.setBounds(237, 270, 32, 32);
        this.getContentPane().add(docLabel);
        docLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    returnThis().setVisible(false);
                    Desktop.getDesktop().open(new File("Doc.docx"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
