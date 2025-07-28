package config;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class closeWindows  {
    public static int openFrameCount = 0;

    public static void createAndShowFrame(JFrame frame) {
        // 添加窗口关闭监听器
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                // 窗体关闭时，减少打开窗体的计数
                openFrameCount--;
             //  System.out.println("窗体在减少");
                // 如果没有其他打开的窗体，则退出应用程序
                if (openFrameCount == 0) {
                    System.exit(0);
                }
            }
        });
//        System.out.println(openFrameCount);
        // 增加打开窗体的计数
    //    System.out.println("窗体在增加");
        openFrameCount++;
       // System.out.println(openFrameCount);
    }
}

