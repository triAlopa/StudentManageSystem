# 本项目使用 jdk1.8+Mysql8.0.41

navicat 恢复表结构及数据


1.创建stumanage数据库
     默认编码即可

     
2.在使用stumanage.sql导入脚本时 !取消勾选“在每个运行中运行多个查询”，会因为出现当时设计 leave表id存在过多的反斜杠 恢复时被当成转义字符，暂时无法解决，两个方法

 2.1.使用navicat 命令行界面或 输入
CREATE TABLE leave_application (
    leave_id VARCHAR(32) PRIMARY KEY DEFAULT (REPLACE(UUID(), '-', '')), 
    student_id VARCHAR(20) NOT NULL,
    teacher_id VARCHAR(20),
    leave_type ENUM('P0', 'P1', 'P2', 'P3') NOT NULL,
    reason TEXT NOT NULL,
    is_approved BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
自行插入数据测试

2.2 .(推荐)**直接恢复备份 20250614220117.nb3**

3.在项目 src/main/java/config/database 修改为本地数据库端口默认3306(使用sql命令行界面status)、用户密码

4.appstart类启动
