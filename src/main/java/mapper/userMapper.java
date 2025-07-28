package mapper;


import config.database;
import entity.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class userMapper {
    static Connection connection;

    public userMapper() throws SQLException {
        new database();
        connection = database.getConnection();
    }


    //搜索所有学院 初始化学院列表数据
    public static String[] selectAllCollege() {
        connection = database.getConnection();
        String querySql = "select college_id,college_name from college ORDER BY college_id ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            ArrayList<College> collegeArrayList = new ArrayList<>();
            while (rs.next()) {
                College college = new College();
                college.setCollegeId(rs.getString("college_id"));
                college.setCollegeName(rs.getString("college_name"));
                collegeArrayList.add(college);
            }
            rs.close();
            String[] options = new String[collegeArrayList.size() + 1];
            options[0] = "请选择选项";
            for (int i = 0; i < collegeArrayList.size(); i++) {
                College college = collegeArrayList.get(i);
                String collegeName = college.getCollegeName();
                String collegeId = college.getCollegeId();
                options[i + 1] = collegeId + " " + collegeName;
            }
            return options;
        } catch (SQLException e) {
            System.err.println("userMapper 54行 错误信息:  " + e.getMessage());
            return null;
        }
    }

    //根据查询点击 列表 搜索子级列表数据
    // 并返回
    /*
    学院-》专业->班级-
    getRowCount用于初始化数组长度
    而且 rs不允许倒转起初查询 写一个方法记录行数
    长度+1为了存放首个options【0】
     */

    public static String[] selectItemById(String querySql, String table_id, String table_name, String padding) {
        connection = database.getConnection();

        String[] options;
        try {
            options = new String[getRowCount(querySql, padding) + 1];
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, padding);
            ResultSet rs = ps.executeQuery();
            options[0] = "请选择选项";
            while (rs.next()) {
                options[rs.getRow()] = rs.getString(table_id) + " " + rs.getString(table_name);
            }
            return options;
        } catch (SQLException e) {
            System.err.println("userMapper 83行 错误信息" + e.getMessage());
            return null;
        }
    }


    //计数 初始化列表 字符串数组长度
    public static int getRowCount(String sql, String padding) throws SQLException {
        connection = database.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        if (padding != null) {
            ps.setString(1, padding);
        }
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count++;
        }
        return count;
    }

    //根据列表的选中
    //学院  //专业 //班级 来查询 学生
    public static ArrayList<Student> queryBySelectItem(String query, Integer pageNumber) {
        connection = database.getConnection();
        try {
            query = query + " LIMIT 10 OFFSET " + pageNumber;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getObject("gender"));
                student.setBirthDate(rs.getString("birth_date"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                student.setEnrollmentDate(rs.getString("enrollment_date"));
                student.setGraduationStatus(rs.getInt("graduation_status"));
                students.add(student);
            }
            rs.close();
            if (students.isEmpty()) {
                return null;
            }
            return students;
        } catch (SQLException e) {
            System.err.println("userMapper 130行 错误信息  " + e.getMessage());
            return null;
        }
    }

    //根据学生姓名查询学生表的信息
    public static ArrayList<Student> queryStuInfoByName(String stu_name) {
        connection = database.getConnection();
        String querySql = "select * from student where name= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, stu_name);
            ResultSet rs = ps.executeQuery();
            ArrayList<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getObject("gender"));
                student.setBirthDate(rs.getString("birth_date"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                student.setEnrollmentDate(rs.getString("enrollment_date"));
                student.setGraduationStatus(rs.getInt("graduation_status"));
                students.add(student);
            }
            rs.close();
            if (students.isEmpty()) return null;
            return students;
        } catch (SQLException e) {
            System.err.println("userMapper 159行 错误信息  " + e.getMessage());
            return null;
        }
    }

    //单个学生学业基本查询
    public static Academicinfo queryStuAcademicInfoById(String student_id) {
        Connection connection = database.getConnection();
        String querySql = "SELECT * FROM StudentDetailView WHERE student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, student_id);

            ResultSet rs = ps.executeQuery();
            //调用查询必须先使用next方法才会移动到下一行
            Academicinfo stuAcademicinfo = new Academicinfo();
            if (rs.next()) {
                stuAcademicinfo.setStudentId(rs.getString("student_id"));
                stuAcademicinfo.setCounselorId(rs.getString("counselor_name"));
                stuAcademicinfo.setClassId(rs.getString("class_name"));
                stuAcademicinfo.setMajorId(rs.getString("major_name"));
                stuAcademicinfo.setCollegeId(rs.getString("college_name"));
                stuAcademicinfo.setGrade(rs.getString("grade"));
                return stuAcademicinfo;
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("userMapper 185行 错误信息  " + e.getMessage());
        }
        return null;
    }

    //单个学生信息查询
    public static Student queryStuInfoByUid(String student_id) {
        Connection connection = database.getConnection();
        String querySql = "select * from student where student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, student_id);

            ResultSet rs = ps.executeQuery();
            //调用查询必须先使用next方法才会移动到下一行

            Student student = new Student();
            if (rs.next()) {
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getObject("gender"));
                student.setBirthDate(rs.getString("birth_date"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                student.setEnrollmentDate(rs.getString("enrollment_date"));
                student.setGraduationStatus(rs.getInt("graduation_status"));
                return student;

            }
            else return null;
        } catch (SQLException e) {
            System.err.println("userMapper 215行 错误信息  " + e.getMessage());
            return null;
        }
    }

    //查询多个学生 的基础信息
     /*
     主要用于 userQueryStud 的输入框的查询
     提高复用性
      */
    public static ArrayList<Academicinfo> baseQueryInfoByMixId(String mixId, String querySql) {
        Connection connection = database.getConnection();
        querySql += mixId;
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);

            ResultSet rs = ps.executeQuery();
            //调用查询必须先使用next方法才会移动到下一行
            ArrayList<Academicinfo> stuAcademicinfos = new ArrayList<>();

            while (rs.next()) {
                Academicinfo stuAcademicinfo = new Academicinfo();
                stuAcademicinfo.setStudentId(rs.getString("student_id"));
                stuAcademicinfo.setCounselorId(rs.getString("counselor_name"));
                stuAcademicinfo.setClassId(rs.getString("class_name"));
                stuAcademicinfo.setMajorId(rs.getString("major_name"));
                stuAcademicinfo.setCollegeId(rs.getString("college_name"));
                stuAcademicinfo.setGrade(rs.getString("grade"));
                if (stuAcademicinfos != null) {
                    stuAcademicinfos.add(stuAcademicinfo);
                }
            }
            rs.close();
            if(stuAcademicinfos.isEmpty()){
                return null;
            }else return stuAcademicinfos;
        }
        catch (SQLException e) {
            System.err.println("userMapper 249行 错误信息  " + e.getMessage());
            return null;
        }
    }


    //分页 查询 学生基本信息
    public static ArrayList<Academicinfo> queryAcademicinfoById(String query, Integer pageNumber) {
        connection = database.getConnection();
        try {
            query = query + " LIMIT 10 OFFSET " + pageNumber;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Academicinfo> stuAcademicinfos = new ArrayList<>();
            while (rs.next()) {
                Academicinfo stuAcademicinfo = new Academicinfo();
                stuAcademicinfo.setStudentId(rs.getString("student_id"));
                stuAcademicinfo.setCounselorId(rs.getString("counselor_name"));
                stuAcademicinfo.setClassId(rs.getString("class_name"));
                stuAcademicinfo.setMajorId(rs.getString("major_name"));
                stuAcademicinfo.setCollegeId(rs.getString("college_name"));
                stuAcademicinfo.setGrade(rs.getString("grade"));
                stuAcademicinfos.add(stuAcademicinfo);
            }
            rs.close();
            if (stuAcademicinfos.isEmpty()) return null;
            return stuAcademicinfos;
        } catch (SQLException e) {
            System.err.println("userMapper 272行 错误信息  " + e.getMessage());
            return null;
        }
    }


    //根据 学生登录的id 来查询他的课程成绩
    public static ArrayList<Grade> stuGradeInfoByStuId(String thisLoginStuId) {
        Connection connection = database.getConnection();

        String querySql = "SELECT  c.course_name, c.credit,  g.score, g.is_passed FROM  Grade g JOIN  Course c ON g.course_id = c.course_id WHERE   g.student_id = ? ORDER BY  g.score DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, thisLoginStuId);

            ResultSet rs = ps.executeQuery();
            //调用查询必须先使用next方法才会移动到下一行
            ArrayList<Grade> stuGradeInfoList = new ArrayList<>();

            while (rs.next()) {
                Grade stuGrade = new Grade();
                stuGrade.setCourseId(rs.getString("course_name"));
                stuGrade.setScore(rs.getBigDecimal("score"));
                stuGrade.setIsPassed(rs.getInt("is_passed"));
                stuGradeInfoList.add(stuGrade);
            }
            rs.close();
            return stuGradeInfoList;
        } catch (SQLException e) {
            System.err.println("userMapper 305行 错误信息  " + e.getMessage());
            return null;
        }
    }

    //根据学生id去搜索学生姓名
    public static String queryLoginStuName(String loginStuId){
        connection=database.getConnection();
        String querySql="select name from student where student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1,loginStuId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
//                System.out.println("get");
                return rs.getString("name");
            }
        } catch (SQLException e) {
            System.err.println("userMapper 320行 错误信息  " + e.getMessage());
        }
//        System.out.println("null get");
        return null;
    }

    //根据学生登录id去查找同学院老师id 和姓名
    public static String[] queryTeaByStuId(String loginStuId){
        connection=database.getConnection();
        String querySql=" select teacher_id,name from teacher t join academicinfo a On t.college_id=a.college_id where a.student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1,loginStuId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Teacher> teacherArrayList=new ArrayList<>();
            while (rs.next()){
                Teacher teacher=new Teacher();
                teacher.setTeacherId(rs.getString("teacher_id"));
                teacher.setName(rs.getString("name"));
                teacherArrayList.add(teacher);
            }
            String[] options=new String[teacherArrayList.size()];
            for (int i = 0; i <teacherArrayList.size(); i++) {
                Teacher teacher=teacherArrayList.get(i);
                options[i]=teacher.getTeacherId()+" "+teacher.getName();
            }
            return options;
        } catch (SQLException e) {
            System.err.println("userMapper 344行 错误信息  " + e.getMessage());
        }
        return null;
    }

    //添加学生请假事由
    public static boolean addLeaveByStu(String stu_id,String tea_id,String leave_type,String reason){
        connection=database.getConnection();
        String insertSql="INSERT INTO leave_application (student_id,teacher_id,leave_type, reason)" +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(insertSql);
            ps.setString(1,stu_id);
            ps.setString(2,tea_id);
            ps.setString(3,leave_type);
            ps.setString(4,reason);
            int result = ps.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            System.err.println("userMapper 367行 错误信息  " + e.getMessage());
            return false;
        }
    }

    //校验用户id和密码是否存在
    public static boolean checkUserPass(String user_id,String password){
        connection=database.getConnection();
        String updateSql="select * from user    where user_id= ?  AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(updateSql);
            ps.setString(1,user_id);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            int result=0;
            if(rs.next()) {
                result=result+rs.getRow();
            }
            return result>0;
        } catch (SQLException e) {
            System.err.println("userMapper 383行 错误信息  " + e.getMessage());
        }
        return false;
    }

    //修改用户密码
    public static boolean changeUserPass(String user_id,String changePass){
        connection=database.getConnection();
        String updateSql="UPDATE user set password = ? where user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(updateSql);
            ps.setString(1,changePass);
            ps.setString(2,user_id);
            int result = ps.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            System.err.println("userMapper 399行 错误信息  " + e.getMessage());
            return false;
        }
    }

    //查询请假，信息
    //1.教师id
    //2.申请理由
    //3.是否审批成功
    //修改用户密码
    public static Leave_application queryLeaveInfo(String user_id){
        connection=database.getConnection();
        String updateSql="select  T.name,l.reason,l.is_approved from leave_application l join teacher T on T.teacher_id=l.teacher_id where student_id= ?";
        Leave_application leave=new Leave_application();
        try {
            PreparedStatement ps = connection.prepareStatement(updateSql);
            ps.setString(1,user_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               leave.setTeacher_id(rs.getString("name"));
                leave.setReason(rs.getString("reason"));
                leave.setIs_approved(rs.getString("is_approved"));
            }
            return leave;
        } catch (SQLException e) {
            System.err.println("userMapper 428行 错误信息  " + e.getMessage());
            return null;
        }
    }
}
