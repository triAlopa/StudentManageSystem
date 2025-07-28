package entity;

/**
 * @TableName Leave_application
 */

public class Leave_application {

    private String leave_id;

    private String student_id;

    private String teacher_id;

    private String leave_type;

    private String reason;

    private String is_approved;

    public String getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(String leave_id) {
        this.leave_id = leave_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(String is_approved) {
        this.is_approved = is_approved;
    }

    @Override
    public String toString() {
        return "Leave_application{" +
                "leave_id='" + leave_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", leave_type='" + leave_type + '\'' +
                ", reason='" + reason + '\'' +
                ", is_approved='" + is_approved + '\'' +
                '}';
    }
}
