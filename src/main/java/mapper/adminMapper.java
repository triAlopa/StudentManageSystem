package mapper;

import com.sun.org.apache.bcel.internal.generic.NEW;
import config.database;
import entity.Leave_application;

import javax.swing.*;
import java.lang.annotation.Target;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class adminMapper {

    static Connection connection;

    public adminMapper() throws SQLException {
        new database();
        connection = database.getConnection();
    }


    //学号删除学生
    public static boolean deleteById(String student_id) {
        connection = database.getConnection();

        String deleteSql = "delete from student where student_id= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(deleteSql);
            ps.setString(1, student_id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println(deleteSql);
            System.err.println("adminMapper 33行错误信息" + e.getMessage());
            return false;
        }
    }


    //添加学生mapper
    public static boolean addStuInfo(String student_id, String name, String gender, Date birth_date, String phone, String email, Date enrollment_date, int graduation_status) {
        String insertSql = "INSERT INTO Student (student_id, name, gender, birth_date, phone, email, enrollment_date, graduation_status ) VALUES (?,?,?,?,?,?,?,? )";
        int result;
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String birthFormat;
        if (birth_date != null) {
            birthFormat = spf.format(birth_date);
        } else {
            birthFormat = "1970-01-01";
        }
        String enrollmentFormat;
        if (enrollment_date != null) {
            enrollmentFormat = spf.format(enrollment_date);
        } else {
            enrollmentFormat = "1970-01-01";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(insertSql);
            ps.setString(1, student_id);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setString(4, birthFormat);
            ps.setString(5, phone);
            ps.setString(6, email);
            ps.setString(7, enrollmentFormat);
            ps.setInt(8, graduation_status);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("adminMapper 70行错误信息" + e.getMessage());
            return false;
        }
        return result > 0;
    }

    //添加学生基础信息
    public static boolean addStuBaseInfo(String student_id, String counselor_id, String class_id, String major_id, String grade, String college_id) {
        connection = database.getConnection();
        String addBaseSql = "INSERT INTO AcademicInfo ( student_id, counselor_id,class_id,major_id, grade,college_id) VALUES (?,?,?,?,?,?)";
        int result;
        try {
            PreparedStatement ps = connection.prepareStatement(addBaseSql);
            ps.setString(1, student_id);
            ps.setString(2, counselor_id);
            ps.setString(3, class_id);
            ps.setString(4, major_id);
            ps.setString(5, grade);
            ps.setString(6, college_id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("adminMapper 91行错误信息" + e.getMessage());
            return false;
        }
        return result > 0;
    }

    //检查学生是否存在
    public static boolean checkStuExist(String student_id) {
        connection = database.getConnection();
        String checkSql = "select student_id from student where student_id=?";
        int result = -1;
        try {
            PreparedStatement ps = connection.prepareStatement(checkSql);
            ps.setString(1, student_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result++;
            }
        } catch (SQLException e) {
            System.err.println("adminMapper 110行错误信息" + e.getMessage());
            return false;
        }
        return result >= 0;
    }

    //检查课程号是否存在
    public static boolean checkCourseExist(String course_id) {
        connection = database.getConnection();
        String checkSql = "select course_id from course where course_id =?";
        int result = -1;
        try {
            PreparedStatement ps = connection.prepareStatement(checkSql);
            ps.setString(1, course_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result++;
            }
        } catch (SQLException e) {
            System.err.println("adminMapper 127行错误信息" + e.getMessage());
            return false;
        }
        return result >= 0;
    }

    //更新学生信息（可以部分更新
    public static boolean partialUpdateStudent(String studentId, String name, String gender,
                                               Date birthDate, String phone, String email,
                                               Date enrollmentDate, Integer graduationStatus) {
        connection = database.getConnection();
        String sql = "UPDATE Student SET " +
                "name = COALESCE(?, name), " +
                "gender = COALESCE(?, gender), " +
                "birth_date = COALESCE(?, birth_date), " +
                "phone = COALESCE(?, phone), " +
                "email = COALESCE(?, email), " +
                "enrollment_date = COALESCE(?, enrollment_date), " +
                "graduation_status = COALESCE(?, graduation_status) " +
                "WHERE student_id = ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatBirthDate;
        String formatEnrollmentDate;
        if (birthDate != null) {
            formatBirthDate = sdf.format(birthDate);
        } else formatBirthDate = null;
        if (enrollmentDate != null) {
            formatEnrollmentDate = sdf.format(enrollmentDate);
        } else formatEnrollmentDate = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // 设置参数
            ps.setString(1, name);  // 姓名
            ps.setString(2, gender);  // 性别
            ps.setString(3, formatBirthDate);  // 生日
            ps.setString(4, phone);  // 电话
            ps.setString(5, email);  // 邮箱
            ps.setString(6, formatEnrollmentDate);  // 入学日期
            ps.setInt(7, graduationStatus);  // 毕业状态
            ps.setString(8, studentId);  // 学生ID

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("adminMapper 172行错误信息" + e.getMessage());
            return false;
        }
    }

    //更新学生基本信息表
    public static boolean partialUpdateStudentBaseInfo(String studentId, String counselor_id, String class_id, String major_id, String grade, String college_id) {
        connection = database.getConnection();
        String sql = "UPDATE academicinfo SET " +
                "counselor_id = COALESCE(?, counselor_id), " +
                "class_id = COALESCE(?, class_id), " +
                "major_id = COALESCE(?, major_id), " +
                "grade = COALESCE(?, grade), " +
                "college_id = COALESCE(?, college_id)" +
                "WHERE student_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // 设置参数
            ps.setString(1, counselor_id);  // 辅导员
            ps.setString(2, class_id);  // 班级
            ps.setString(3, major_id);  // 专业
            ps.setString(4, grade);  // 年级
            ps.setString(5, college_id);  // 学院
            ps.setString(6, studentId);  // 学生id

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("adminMapper 200行错误信息" + e.getMessage());
            System.err.println("部分更新学生信息失败: " + e.getMessage());
            return false;
        }
    }

    //更新课程表(可以部分信息
    public static boolean partialUpdateCourseInfo(String course_name, String credit, String classroom, Date start_date, Date end_date, String course_id) {
        connection = database.getConnection();
        String sql = "UPDATE course SET " +
                "course_name = COALESCE(?, course_name), " +
                "credit = COALESCE(?, credit), " +
                "classroom = COALESCE(?, classroom), " +
                "start_date = COALESCE(?, start_date), " +
                "end_date = COALESCE(?, end_date)" +
                "WHERE course_id = ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatStart_date;
        String formatEnd_date;
        if (start_date != null) {
            formatStart_date = sdf.format(start_date);
        } else formatStart_date = null;
        if (end_date != null) {
            formatEnd_date = sdf.format(end_date);
        } else formatEnd_date = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            // 设置参数
            ps.setString(1, course_name);//名字
            ps.setString(2, credit);  // 学风
            ps.setString(3, classroom);  // 教室
            ps.setString(4, formatStart_date);  // 开始
            ps.setString(5, formatEnd_date);  // 结束
            ps.setString(6, course_id);  // id

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("adminMapper 238行 部分更新课程信息失败: " + e.getMessage());
            return false;
        }
    }

    //查询教师id所在学院id
    public static String queryCoIdByTeacherId(String teacher_id) {
        connection = database.getConnection();
        String querySql = "select college_id from teacher where teacher_id= ?";
        String college_id = null;
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, teacher_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                college_id = rs.getString("college_id");
            }
        } catch (SQLException e) {
            System.out.println("adminMapper 256行错误信息" + e.getMessage());
            return null;
        }
        return college_id;
    }

    //根据课程id查询
    //[0]  学分，[1] 开课教室 [2]开课时间 [3] 结课时间
    public static String[] queryCourseInfo(String course_id) {
        connection = database.getConnection();
        String querySql = "select credit,classroom,start_date,end_date from course where course_id= ?";
        String[] info = new String[4];
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, course_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                info[0] = rs.getString("credit");
                info[1] = rs.getString("classroom");
                info[2] = rs.getString("start_date");
                info[3] = rs.getString("end_date");
            }
        } catch (SQLException e) {
            System.out.println("adminMapper 279行错误信息" + e.getMessage());
            return null;
        }
        return info;
    }


    //根据教师id查询教师表
    //讲授课程
    public static String[] querySourceNameByTeaId(String teacher_id) {
        connection = database.getConnection();
//        String querySql="select credit,classroom,start_date,end_date from source from teacher_id= ?";
        String querySql = "select course_id,course_name from course where teacher_id= ?";
        String[] options;
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, teacher_id);
            ResultSet rs = ps.executeQuery();
            options = new String[getRowCount(querySql, teacher_id)];
            while (rs.next()) {
                options[rs.getRow() - 1] = rs.getString("course_id") + " " + rs.getString("course_name");
            }
            ps.execute();
        } catch (SQLException e) {
            System.out.println("adminMapper 303行错误信息" + e.getMessage());
            return null;
        }
        return options;
    }

    //查询所有学院
    public static String[] selectIdAndNameAll(String query, String id, String name) {
        connection = database.getConnection();
//        String queryAllCollege="SELECT college_id, college_name FROM  college ORDER BY college_id ASC";
        String[] options;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            options = new String[getRowCount(query, null) + 1];
            options[0] = "请选择选项";
            while (rs.next()) {
                options[rs.getRow()] = rs.getString(id) + " " + rs.getString(name);
            }
        } catch (SQLException e) {
            System.out.println("adminMapper 323行错误信息" + e.getMessage());
            return null;
        }

        return options;
    }

    //根据学院id查学院名称
    public static String queryCollegeNameById(String college_id) {
        connection = database.getConnection();
        String querySql = "select college_name from college where college_id= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, college_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("college_name");
        } catch (SQLException e) {
            System.out.println("adminMapper 340行错误信息" + e.getMessage());
            return null;
        }
        return null;
    }

    //计数
    public static int getRowCount(String sql, String id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sql);
        if (id != null) ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count++;
        }
        return count;
    }

    //添加课程信息
    public static boolean addCourseByTea(String Course_id, String course_name, String credit, String classroom, Date start_date, Date end_date, String teacher_id, String class_id, String college_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatStartDate = null;
        String formatEndDate = null;
        try {
            formatStartDate = sdf.format(start_date);
            formatEndDate = sdf.format(end_date);
        } catch (Exception e) {
            System.err.println("error:  date is"+e.getMessage()+" 不会选日期啊？？？？");
            return false;
        }
        connection = database.getConnection();
        String insertSql = "INSERT INTO Course (course_id,course_name,credit,classroom,start_date,end_date,teacher_id,class_id,college_id ) VALUES (?,?,?,?,?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(insertSql);
            ps.setString(1, Course_id);
            ps.setString(2, course_name);
            ps.setString(3, credit);
            ps.setString(4, classroom);
            ps.setString(5, formatStartDate);
            ps.setString(6, formatEndDate);
            ps.setString(7, teacher_id);
            ps.setString(8, class_id);
            ps.setString(9, college_id);
            result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("adminMapper 387行 错误信息:"+e.getMessage());
            return false;
        }
    }

    //根据学院
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
        } catch (SQLException e) {
            System.out.println("adminMapper 407行错误信息" + e.getMessage());
            return null;
        }
        return options;
    }

    //根据学号查询入学状态
    public static int queryStatusByStuId(String student_id) {
        String querySql = "select graduation_status from student where student_id= ?";
        connection = database.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, student_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("graduation_status");
        } catch (SQLException e) {
            System.out.println("adminMapper 423行错误信息" + e.getMessage());
        }
        return 0;
    }

    //课程号删除课程
    public static boolean deleteCourseById(String course_id) {
        connection = database.getConnection();

        String deleteSql = "delete from course where course_id= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(deleteSql);
            ps.setString(1, course_id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("adminMapper 439行 错误信息" + e.getMessage());
            return false;
        }
    }

    //根据教师id搜索讲授 班级列表(id + name)
    public static String[] selectClassListByTeaId(String tea_id) {
        connection = database.getConnection();
        String querySql = "SELECT DISTINCT  c.class_id,  c.class_name FROM Class c JOIN   Course co ON c.class_id = co.class_id  WHERE  co.teacher_id = ?";
        String[] options;
        try {
            options = new String[getRowCount(querySql, tea_id) + 1];
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, tea_id);
            options[0] = "请选择选项";
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                options[rs.getRow()] = rs.getString("class_id") + " " + rs.getString("class_name");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("adminMapper 460行错误信息" + e.getMessage());
            return null;
        }
        return options;
    }

    //根据教师id查所教课程名称
    public static String[] selectCourseNameByTeaId(String teacher_id) {
        connection = database.getConnection();
        String querySql = "select DISTINCT  course_name from course where teacher_id =?";
        String[] options;
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, teacher_id);
            ResultSet rs = ps.executeQuery();
            options = new String[getRowCount(querySql, teacher_id) + 1];
            options[0] = "请选择选项";
            while (rs.next()) {
                options[rs.getRow()] = rs.getString("course_name");
            }
        } catch (SQLException e) {
            System.out.println("adminMapper 481行错误信息" + e.getMessage());
            return null;
        }
        return options;
    }

    //根据课程名称查班级
    public static String[] selectClassNameByCourse_name(String course_name) {
        connection = database.getConnection();
        String querySql = "select   CL.class_name  from class CL  join course Co On CL.class_id=Co.class_id    where co.course_name= ?";
        String[] options;
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, course_name);
            options = new String[getRowCount(querySql, course_name) + 1];
            options[0] = "请选择选项";
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                options[rs.getRow()] = rs.getString("class_name");
            }
        } catch (SQLException e) {
            System.out.println("adminMapper 502行错误信息" + e.getMessage());
            return null;
        }
        return options;
    }

    //根据课程名称查班级
    public static String[] selectStuNameByClassName(String class_name) {
        connection = database.getConnection();
        String querySql = "select s.name from student s join academicinfo a On s.student_id=a.student_id join class cl on cl.class_id=a.class_id where cl.class_name = ?";
        String[] options;
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, class_name);
            options = new String[getRowCount(querySql, class_name) + 1];
            options[0] = "请选择选项";
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                options[rs.getRow()] = rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("adminMapper 523行错误信息" + e.getMessage());
            return null;
        }
        return options;
    }

    //根据学生名 和 课程名确定 学生成绩
    public static String selectStuGradeByStuNameOrCourseName(String stu_name, String course_name) {
        connection = database.getConnection();
        String querySql = "SELECT " +
                "    g.score " +
                "FROM    Grade g JOIN     Student s ON g.student_id = s.student_id " +
                "JOIN      Course c ON g.course_id = c.course_id  WHERE     s.name = ?    AND c.course_name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, stu_name);
            ps.setString(2, course_name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())  return rs.getString("score");
            else return "";
        } catch (SQLException e) {
            System.out.println("adminMapper 544行错误信息" + e.getMessage());
            return null;
        }
    }

    //根据学生名 和 课程名 来添加/更新 该学生课程的成绩
    public  static  boolean UpdateGradeNOtoAdd(String studentName, String courseName,
                                         float score) {
        connection=database.getConnection();
        try {
            connection.setAutoCommit(false);

            // 1. 获取学生ID和课程ID
            String query = "SELECT s.student_id, c.course_id " +
                    "FROM Student s, Course c " +
                    "WHERE s.name = ? AND c.course_name = ?";

            String studentId = null;
            String courseId = null;

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, studentName);
                ps.setString(2, courseName);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        studentId = rs.getString("student_id");
                        courseId = rs.getString("course_id");
                    } else {
                        throw new SQLException("未找到匹配的学生或课程");
                    }
                }
            }

            // 2. 检查成绩记录是否存在
            boolean exists = false;
            try (PreparedStatement ps = connection.prepareStatement(
                    "SELECT 1 FROM Grade WHERE student_id = ? AND course_id = ?")) {
                ps.setString(1, studentId);
                ps.setString(2, courseId);
                try (ResultSet rs = ps.executeQuery()) {
                    exists = rs.next();
                }
            }

            // 3. 执行插入或更新
            String operation;
            String sql;
            if (exists) {
                operation = "更新";
                sql = "UPDATE Grade SET score = ?  WHERE student_id = ? AND course_id = ?";
            } else {
                operation = "添加";
                sql = "INSERT INTO Grade (student_id, course_id, score) VALUES (?, ?, ?)";
            }

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                if (exists) {
                    ps.setFloat(1, score);
                    ps.setString(2, studentId);
                    ps.setString(3, courseId);
                } else {
                    ps.setString(1, studentId);
                    ps.setString(2, courseId);
                    ps.setFloat(3, score);
                }

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException(operation + "成绩失败");
                }

                connection.commit();
                return true;
            }

        } catch (SQLException e) {
            if(connection!=null) {
                try {
                    connection.rollback();
                    System.err.println("事务已回滚: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    System.err.println("回滚失败: " + rollbackEx.getMessage());
                    // 记录原始异常和回滚异常
                    e.addSuppressed(rollbackEx);
                }
            }
            System.err.println("操作失败: " + e.getMessage());
            return false;
        } finally {
            try {
                if (connection != null) connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //删除学生成绩
    public static boolean deleteGradeForStuByStu_nameCourseName(String studentName, String courseName) {
        try {
            connection = database.getConnection();
            connection.setAutoCommit(false); // 开启事务

            // 1. 先查询学生ID和课程ID
            String query = "SELECT s.student_id, c.course_id " +
                    "FROM Student s, Course c " +
                    "WHERE s.name = ? AND c.course_name = ? " +
                    "FOR UPDATE"; // 加锁防止并发问题

            String studentId = null;
            String courseId = null;

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, studentName);
                ps.setString(2, courseName);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        studentId = rs.getString("student_id");
                        courseId = rs.getString("course_id");
                    } else {
                        throw new SQLException("未找到匹配的学生或课程");
                    }
                }
            }

            // 2. 执行删除
            String deleteSql = "DELETE FROM Grade WHERE student_id = ? AND course_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(deleteSql)) {
                ps.setString(1, studentId);
                ps.setString(2, courseId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("未找到匹配的成绩记录");
                }
            }

            connection.commit(); // 提交事务
            return true;

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // 回滚事务
                    System.err.println("事务已回滚: " + e.getMessage());
                }
            } catch (SQLException rollbackEx) {
                System.err.println("回滚失败: " + rollbackEx.getMessage());
                e.addSuppressed(rollbackEx);
            }
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true); // 恢复自动提交
//                    connection.close();
                }
            } catch (SQLException finallyEx) {
                System.err.println("资源释放失败: " + finallyEx.getMessage());
            }
        }
    }

    //凭教师id  查询学生没有批注 的请假
    public static Leave_application queryLeaveToStuByTeaId(String tea_id){
        connection= database.getConnection();
        String querySql="SELECT leave_id,student_id, leave_type,reason FROM leave_application  WHERE   teacher_id = ? AND is_approved = FALSE";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1,tea_id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Leave_application l= new Leave_application();
                l.setLeave_id(rs.getString("leave_id"));
                l.setStudent_id(rs.getString("student_id"));
                l.setLeave_type(rs.getString("leave_type"));
                l.setReason(rs.getString("reason"));
                return l;
            }else return null;
        } catch (SQLException e) {
            System.out.println("adminMapper 730行 错误信息："+e.getMessage());
        }
        return null;
    }

    //同意学生请假审批
    public static boolean approveStuLeaveById(String id){
        connection=database.getConnection();
        String updateSql="UPDATE leave_application SET is_approved = TRUE WHERE leave_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(updateSql);
            ps.setString(1,id);
            int result = ps.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            System.err.println("userMapper 744行 错误信息  " + e.getMessage());
            return false;
        }
    }
    //否决学生请假学生审批
    public static boolean refuseStuLeaveById(String id){
        connection=database.getConnection();
        String updateSql="UPDATE leave_application SET is_approved = ? WHERE leave_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(updateSql);
            ps.setString(1,"-1");
            ps.setString(2,id);
            int result = ps.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            System.err.println("userMapper 759行 错误信息  " + e.getMessage());
            return false;
        }
    }
}
