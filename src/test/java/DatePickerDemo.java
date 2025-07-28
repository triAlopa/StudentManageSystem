import org.jdatepicker.impl.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class DatePickerDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("日期选择示例");
        frame.setLayout(new FlowLayout());

        // 创建日期选择器
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "今天");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        // 添加日期选择监听器
        datePicker.addActionListener(e -> {
            Date selectedDate = (Date) datePicker.getModel().getValue();
            if (selectedDate != null) {
                System.out.println(selectedDate);
                java.sql.Date date=new java.sql.Date(selectedDate.getTime());
                System.out.println(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                JOptionPane.showMessageDialog(frame, "选择的日期: " + sdf.format(selectedDate));
            }
        });

        frame.add(new JLabel("选择日期:"));
        frame.add(datePicker);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}