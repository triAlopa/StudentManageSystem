package util;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.*;
import java.util.Map;
import java.util.Properties;

public class DatePicker {

    public static JDatePickerImpl getDatePicker() {
        //获取时间模型
        //空参 默认获取现在时间
        UtilDateModel u=new UtilDateModel();
        //为了格式化 日期选择器文本
        Properties p=new Properties();
//        p.put("text.day.1", "日");
//        p.put("text.day.2", "一");
//        p.put("text.day.3", "二");
//        p.put("text.day.4", "三");
//        p.put("text.day.5", "四");
//        p.put("text.day.6", "五");
//        p.put("text.day.7", "六");
        p.put("text.today","今天");
        p.put("text.month","月");
        p.put("text.year","年");

        //创建存放 日期的容器
        JDatePanelImpl jDatePanel=new JDatePanelImpl(u,p);
        jDatePanel.setPreferredSize(new Dimension(400, 270));
        jDatePanel.setFont(new Font("宋体",Font.BOLD,18));
        //允许 用户交互选择日期
        jDatePanel.setEnabled(true);
        return new JDatePickerImpl(jDatePanel, new DateComponentFormatter());
    }
}
