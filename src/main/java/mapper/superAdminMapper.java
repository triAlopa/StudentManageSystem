package mapper;

import config.database;
import entity.Class;
import entity.College;
import entity.Counselor;
import entity.Major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class superAdminMapper {
    static Connection connection;

    superAdminMapper() throws SQLException {
        new database();
        connection = database.getConnection();
    }

    //查询学院id是否存在
    public static boolean checkCollegeExist(String college_id) {
        connection = database.getConnection();
        String querySql = "SELECT 1 FROM College WHERE college_id = ? LIMIT 1";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, college_id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("错误信息:" + e.getMessage());
            return false;
        }
    }

    //添加学院
    public static boolean addCollege(String college_id, String college_name, Date established_date) {
        connection = database.getConnection();
        if (established_date == null) return false;
        String insertSql = "insert into college(college_id,college_name,established_date) VALUES(?,?,?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String estDate = sdf.format(established_date);
        try {
            PreparedStatement ps = connection.prepareStatement(insertSql);
            ps.setString(1, college_id);
            ps.setString(2, college_name);
            ps.setString(3, estDate);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("错误信息：" + e.getMessage());
            return false;
        }
    }

    //根据学院id查创立时间
    public static String selectCollegeEstDate(String college_id) {
        String querySql = "select established_date from college where college_id=?";
        connection = database.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, college_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("established_date");
        } catch (SQLException e) {
            System.err.println("错误信息：" + e.getMessage());
        }
        return "null";
    }

    //更新学院
    public static boolean partialUpdateCollegeInfo(String college_id, String college_name, Date established_date) {
        connection = database.getConnection();
        String upDateSql = "UPDATE college SET " +
                "college_name = COALESCE(?, college_name), " +
                "established_date = COALESCE(?, established_date)" +
                "WHERE college_id = ?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = selectCollegeEstDate(college_id);
        if (established_date != null) date = sdf.format(established_date);
        try {
            PreparedStatement ps = connection.prepareStatement(upDateSql);
            ps.setString(1, college_name);
            ps.setString(2, date);
            ps.setString(3, college_id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("错误信息：" + e.getMessage());
        }
        return false;
    }

    //搜索所有学院
    public static String[] selectAllCollege(){
        connection=database.getConnection();
        String querySql="select college_id,college_name from college ORDER BY college_id ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ResultSet rs = ps.executeQuery();
            ArrayList<College> collegeArrayList=new ArrayList<>();
            while (rs.next()){
              College college=new College();
              college.setCollegeId(rs.getString("college_id"));
              college.setCollegeName(rs.getString("college_name"));
              collegeArrayList.add(college);
            }
            rs.close();
            String[] options=new String[collegeArrayList.size()+1];
            options[0]="请选择";
            for (int i = 0; i < collegeArrayList.size(); i++) {
                College college = collegeArrayList.get(i);
                String collegeName = college.getCollegeName();
                String collegeId = college.getCollegeId();
                options[i+1]=collegeId+" "+collegeName;
            }
            return options;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return null;
        }
    }

    //根据学院id查询学院名称 成立时间
    public static  College queryByCo_id(String college_id){
        connection=database.getConnection();
        String query="select college_name,established_date from college where college_id= ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,college_id);
            ResultSet rs = ps.executeQuery();
            College college = new College();
            if(rs.next()){
                college.setCollegeName(rs.getString("college_name"));
                college.setEstablishedDate(rs.getString("established_date"));
            }
            return college;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return null;
        }
    }

    //根据 学院id 专业id 专业名插入专业表
    public static boolean addMajor(String college_id,String major_id,String major_name){
        connection=database.getConnection();
        String insertSql="INSERT INTO Major " +
                " (major_id,major_name,college_id) VALUES (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(insertSql);
            ps.setString(1,major_id);
            ps.setString(2,major_name);
            ps.setString(3,college_id);
            int result = ps.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return false;
        }
    }

    //查询专业id是否存在
    public static boolean checkMajorExist(String college_id) {
        connection = database.getConnection();
        String querySql = "SELECT 1 FROM major WHERE major_id = ? LIMIT 1";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, college_id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("错误信息:" + e.getMessage());
            return false;
        }
    }

    //更新专业信息
    public static boolean partialUpdateMajorInfo(String major_id, String major_name) {
        connection = database.getConnection();
        String upDateSql = "UPDATE major SET " +
                "major_name = COALESCE(?, major_name) " +
                "WHERE major_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(upDateSql);
            ps.setString(1, major_name);
            ps.setString(2, major_id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("错误信息：" + e.getMessage());
        }
        return false;
    }

    //根据学院id查学院里专业id+名字
    public static String[] selectMajorByCoId(String college_id){
        connection=database.getConnection();
        String querySql="select major_id,major_name from major  where college_id=?  ORDER BY college_id ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1,college_id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Major> majorArrayList =new ArrayList<>();
            while (rs.next()){
                Major major =new Major();
                major.setMajorId(rs.getString("major_id"));
                major.setMajorName(rs.getString("major_name"));
                majorArrayList.add(major);
            }
            rs.close();
            String[] options=new String[majorArrayList.size()+1];
            options[0]="请选择";
            for (int i = 0; i < majorArrayList.size(); i++) {
                Major major = majorArrayList.get(i);
                String majorName = major.getMajorName();
                String majorId = major.getMajorId();
                options[i+1]= majorId +" "+majorName;
            }
            return options;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return null;
        }
    }

    public static String[] selectCByCounselorByCoId(String college_id){
        connection=database.getConnection();
        String querySql="SELECT  counselor_id,name FROM  Counselor WHERE college_id = ? ORDER BY   counselor_id ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1,college_id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Counselor> counselorArrayList =new ArrayList<>();
            while (rs.next()){
                Counselor counselor =new Counselor();
                counselor.setCounselorId(rs.getString("counselor_id"));
                counselor.setName(rs.getString("name"));
                counselorArrayList.add(counselor);
            }
            rs.close();
            String[] options=new String[counselorArrayList.size()+1];
            options[0]="请选择";
            for (int i = 0; i < counselorArrayList.size(); i++) {
                Counselor counselor = counselorArrayList.get(i);
                String counselorId = counselor.getCounselorId();
                String counselorName = counselor.getName();
                options[i+1]= counselorId +" "+ counselorName;
            }
            return options;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return null;
        }
    }

    //查询学院id是否存在
    public static boolean checkClassExist(String class_id) {
        connection = database.getConnection();
        String querySql = "SELECT 1 FROM class WHERE class_id = ? LIMIT 1";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, class_id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("错误信息:" + e.getMessage());
            return false;
        }
    }

    //根据 班级id 班级名字 专业id 辅导员id 专业名插入专业表
    public static boolean addClass(String class_id,String class_name,String major_id,String counselor_id){
        connection=database.getConnection();
        String insertSql="INSERT INTO class " +
                " (class_id,class_name,major_id,counselor_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(insertSql);
            ps.setString(1,class_id);
            ps.setString(2,class_name);
            ps.setString(3,major_id);
            ps.setString(4,counselor_id);
            int result = ps.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return false;
        }
    }

    //更新班级信息
    public static boolean partialUpdateClassInfo(String class_name, String major_id,String counselor_id ) {
        connection = database.getConnection();
        String upDateSql = "UPDATE class SET " +
                "class_name = COALESCE(?, class_name) " +
                "major_id = COALESCE(?, major_id) " +
                "counselor_id = COALESCE(?, counselor_id) " +
                "WHERE class_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(upDateSql);
            ps.setString(1, class_name);
            ps.setString(2, major_id);
            ps.setString(3,counselor_id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("错误信息：" + e.getMessage());
        }
        return false;
    }



    //用专业id 搜素班级id+名字
    public static String[] selectClassByCoId(String major_id){
        connection=database.getConnection();
        String querySql="select class_id,class_name from class  where major_id=?  ORDER BY class_id ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1,major_id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Class> classArrayList =new ArrayList<>();
            while (rs.next()){
                Class aClass =new Class();
                aClass.setClass_id(rs.getString("class_id"));
                aClass.setClass_name(rs.getString("class_name"));
                classArrayList.add(aClass);
            }
            rs.close();
            String[] options=new String[classArrayList.size()+1];
            options[0]="请选择";
            for (int i = 0; i < classArrayList.size(); i++) {
                Class aClass = classArrayList.get(i);
                String className = aClass.getClass_name();
                String class_id = aClass.getClass_id();
                options[i+1]= class_id +" "+className;
            }
            return options;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return null;
        }
    }

    //根据班级id 查询班级id 班级名称 辅导员名称
    public static Class selectClassById(String class_id){
        connection=database.getConnection();
        String querySql="select class_id,class_name,counselor_id from class  where class_id=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1,class_id);
            ResultSet rs = ps.executeQuery();
            Class aClass =new Class();
            if (rs.next()){
                aClass.setClass_id(rs.getString("class_id"));
                aClass.setClass_name(rs.getString("class_name"));
                String counselorId=rs.getString("counselor_id");
                String counselor = selectCounselorById(counselorId);
                aClass.setCounselor_id(counselor);
            }
            rs.close();
            return aClass;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return null;
        }
    }
    public static String selectCounselorById(String counselor_id){
        connection=database.getConnection();
        String querySql="select name,counselor_id from counselor  where counselor_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1,counselor_id);
            ResultSet rs = ps.executeQuery();
            String counselor="";
            if (rs.next()){
                counselor=new String(rs.getString("counselor_id")+" "+rs.getString("name"));
            }
            rs.close();
            return counselor;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return null;
        }
    }



    //添加辅导员信息
    public static boolean addCounselor(String counselor_id,String name,String phone,String college_id){
        connection=database.getConnection();
        String insertSql="INSERT INTO Counselor " +
                " (counselor_id,name,phone,college_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(insertSql);
            ps.setString(1,counselor_id);
            ps.setString(2,name);
            ps.setString(3,phone);
            ps.setString(4,college_id);
            int result = ps.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return false;
        }
    }

    //查询通过辅导员id是否存在
    public static boolean checkCounselorExist(String college_id) {
        connection = database.getConnection();
        String querySql = "SELECT 1 FROM counselor WHERE counselor_id = ? LIMIT 1";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, college_id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("错误信息:" + e.getMessage());
            return false;
        }
    }

    //查询通过教师id是否存在
    public static boolean checkTeaExist(String teacher_id) {
        connection = database.getConnection();
        String querySql = "SELECT 1 FROM teacher WHERE teacher_id = ? LIMIT 1";
        try {
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, teacher_id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("错误信息:" + e.getMessage());
            return false;
        }
    }

    //添加教师信息
    public static boolean addTea(String teacher_id,String name,String gender,Date hire_date,String college_id){
        connection=database.getConnection();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if (hire_date==null) return false;
        String formatDate = sdf.format(hire_date);
        String insertSql="INSERT INTO teacher " +
                " (teacher_id,name,gender,college_id,hire_date) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(insertSql);
            ps.setString(1,teacher_id);
            ps.setString(2,name);
            ps.setString(3,gender);
            ps.setString(4,college_id);
            ps.setString(5,formatDate);
            int result = ps.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            System.err.println("错误信息:"+e.getMessage());
            return false;
        }
    }
}
