package UI.AboutUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class showAboutSchool extends JDialog {


        private showAboutSchool getThis(){
         return this;
    }
    public  showAboutSchool(){
        //设置无管理容器
        this.setLayout(null);
        //设置宽高
        this.setSize(510,500);
        //总是置顶显示
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //设置标题
        this.setTitle("关于蚌埠学院");
        //
//        // 设置为模态对话框

         //添加图片 并设置位置大小，加入容器
        JLabel jLabel=new JLabel(new ImageIcon("src/img/aboutSchool.png"));
        jLabel.setBounds(65,0,352,340);
        this.getContentPane().add(jLabel);


        //设置文字
        JLabel text=new JLabel("学院官网:");
        text.setBounds(138,355,120,50);
        text.setFont(new Font("宋体",Font.CENTER_BASELINE,23));
        this.getContentPane().add(text);

        //跳转
        JLabel jLabelNet = new JLabel(new ImageIcon("src/img/about-college.png"));
        jLabelNet.setBounds(266,350,64,64);
        this.getContentPane().add(jLabelNet);

        jLabelNet.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e){
                try {
//                    System.out.println("---------");
                    getThis().setVisible(false);
                    Desktop.getDesktop().browse(new URI("https://www.bbc.edu.cn/main.htm"));
                } catch (IOException |URISyntaxException ex) {
                    System.err.println(ex.getMessage());
                }
            }

        });
        this.setModal(true);
        this.setVisible(true);
    }

}
