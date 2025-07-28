package UI;

import config.closeWindows;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class baseUI extends JFrame {
    public baseUI(){
        //宽高
        this.setSize(1100,650);
        //标题
        this.setTitle("bbu学生管理系统");
        //居中
        this.setLocationRelativeTo(null);
        //设置无位置管理器 采用绝对布局
        this.setLayout(null);
        //关闭结束运行
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //是否可见
        this.setVisible(true);
        closeWindows.createAndShowFrame(this);
        JPanel backgroundJpanel=new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                ImageIcon bg=new ImageIcon("src/img/bg.gif");
                Image image = bg.getImage();
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        };

        setContentPane(backgroundJpanel);
    }
}
