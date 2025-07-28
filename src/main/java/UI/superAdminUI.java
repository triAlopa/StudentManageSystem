package UI;

import config.closeWindows;
import view.class_.superAdminAddOrUpClass;
import view.class_.superAdminQueryClass;
import view.college.superAdminAddOrUpCollege;
import view.college.superAdminQueryCollege;
import view.counselor.superAdminAddCounselor;
import view.major.superAdminAddOrUpMajor;
import view.major.superAdminQueryMajor;
import view.teacher.superAdminAddTea;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class superAdminUI extends adminUI {
    private JMenuItem addOrUPCollege;
    private JMenuItem queryCollege;
    private JMenuItem addOrUpMajor;
    private JMenuItem queryMajor;
    private JMenuItem addOrUpClass;
    private JMenuItem queryClass;
    private JMenuItem addTeaItem ;
    private JMenuItem addCounselorItem;


    public superAdminUI() {
        super();
        closeWindows.createAndShowFrame(this);
    }

    protected void initJMenuBar() {
        super.initJMenuBar();
        JMenuBar jMenuBar = super.getJMenuBar();

        JMenu collegeJMenu = new JMenu("学院");

        queryCollege = new JMenuItem("查询学院");
        addOrUPCollege = new JMenuItem("添加&&更新");
        collegeJMenu.add(addOrUPCollege);
        collegeJMenu.add(queryCollege);
        addOrUPCollege.addActionListener(this);
        queryCollege.addActionListener(this);


        JMenu majorJMenu = new JMenu("专业");
        addOrUpMajor = new JMenuItem("添加&更新");
        addOrUpMajor.addActionListener(this);
        queryMajor = new JMenuItem("查询专业");
        queryMajor.addActionListener(this);
        majorJMenu.add(addOrUpMajor);
        majorJMenu.add(queryMajor);


        JMenu classJMenu = new JMenu("班级");
        addOrUpClass = new JMenuItem("添加&&更新");
        addOrUpClass.addActionListener(this);
        queryClass = new JMenuItem("查询班级");
        queryClass.addActionListener(this);
        classJMenu.add(addOrUpClass);
        classJMenu.add(queryClass);


        JMenu workerJmenu = new JMenu("教务人员");
        addTeaItem = new JMenuItem("添加教师");
        addTeaItem.addActionListener(this);
        addCounselorItem = new JMenuItem("添加辅导员");
        addCounselorItem.addActionListener(this);

        workerJmenu.add(addTeaItem);
        workerJmenu.add(addCounselorItem);

        jMenuBar.add(collegeJMenu, 0);
        jMenuBar.add(majorJMenu, 1);
        jMenuBar.add(classJMenu, 2);
        jMenuBar.add(workerJmenu,4);

        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        Object source = e.getSource();
        if (source == addOrUPCollege) {
            new superAdminAddOrUpCollege();
        }
        if (source == queryCollege) {
            new superAdminQueryCollege();
        }
        if (source == addOrUpMajor) {
            new superAdminAddOrUpMajor();
        }
        if (source == queryMajor) {
            new superAdminQueryMajor();
        }
        if (source == addOrUpClass) {
            new superAdminAddOrUpClass();
        }
        if (source == queryClass) {
            new superAdminQueryClass();
        }
        if(source==addCounselorItem){
            new superAdminAddCounselor();
        }
        if(source==addTeaItem){
            new superAdminAddTea();
        }
    }
}
