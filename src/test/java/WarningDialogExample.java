import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarningDialogExample {
    public static void main(JFrame mainFrame) {
        // 在事件分派线程中运行
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建警示框对话框
                JDialog warningDialog = new JDialog(mainFrame, "警示框示例", true);
                warningDialog.setSize(300, 150);
                warningDialog.setLocationRelativeTo(mainFrame);
                warningDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                warningDialog.setLayout(new BorderLayout());

                // 创建提示信息标签
                JLabel messageLabel = new JLabel("你确定要这样操作吗？", SwingConstants.CENTER);
                warningDialog.add(messageLabel, BorderLayout.CENTER);

                // 创建按钮面板
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                // 创建“是”按钮
                JButton yesButton = new JButton("是");
                yesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(mainFrame, "你点击了“是”按钮");
                        warningDialog.dispose(); // 关闭警示框
                    }
                });
                buttonPanel.add(yesButton);

                // 创建“取消”按钮
                JButton noButton = new JButton("取消");
                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        JOptionPane.showMessageDialog(mainFrame, "你点击了“取消”按钮");
                        warningDialog.dispose(); // 关闭警示框
                    }
                });
                buttonPanel.add(noButton);

                // 将按钮面板添加到对话框
                warningDialog.add(buttonPanel, BorderLayout.SOUTH);

                // 显示警示框
                warningDialog.setVisible(true);
            }
        });
    }
}
