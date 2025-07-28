import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxWithHintExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ComboBox With Hint Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // 创建一个下拉列表并添加提示项
        String[] options = {"请选择选项", "选项 1", "选项 2", "选项 3"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setSize(20,20);
        comboBox.setEditable(false);
        String toolTipText = comboBox.getToolTipText();
        System.out.println(toolTipText);

        // 设置提示项不可被选中
        comboBox.setSelectedIndex(0);
        comboBox.setPrototypeDisplayValue("请选择选项");
        // 添加一个事件监听器来处理选择事件
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String selectedOption = (String) comboBox.getSelectedItem();
                System.out.println(selectedOption);
                // 如果选中的是提示项，则不做任何操作
                switch (selectedOption) {
                    case "选项 1":{
                        System.out.println("你选择了选项Yi");
                    }

                }
            }
        });

        // 将下拉列表添加到窗体
        frame.getContentPane().add(comboBox, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}
