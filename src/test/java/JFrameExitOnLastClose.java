import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JFrameExitOnLastClose {
    // 静态变量，用于跟踪当前打开的 JFrame 实例数量
    private static int openFrameCount = 0;

    public static void main(String[] args) {
        // 创建并显示第一个 JFrame
        createAndShowFrame();

        // 创建并显示第二个 JFrame
        createAndShowFrame();
    }

    private static void createAndShowFrame() {
        JFrame frame = new JFrame("Frame " + (openFrameCount + 1));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        // 添加窗口关闭监听器
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // 窗体关闭时，减少打开窗体的计数
                openFrameCount--;
                // 如果没有其他打开的窗体，则退出应用程序
                if (openFrameCount == 0) {
                    System.exit(0);
                }
            }
        });

        // 增加打开窗体的计数
        openFrameCount++;

        frame.setVisible(true);
    }
}
