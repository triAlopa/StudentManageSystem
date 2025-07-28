import org.jdatepicker.JDatePicker;
import util.DatePicker;

import javax.swing.*;
import java.awt.*;

public class DateTest extends JFrame {

    DateTest(){
        this.setSize(300,200);
        JDatePicker datePicker = DatePicker.getDatePicker();
        this.add((Component) datePicker);
        this.setVisible(true);
    }

    public static void main(String[] args) {
           new DateTest();
    }

}
