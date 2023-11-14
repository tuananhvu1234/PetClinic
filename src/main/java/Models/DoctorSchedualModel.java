package Models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorSchedualModel extends UserModel {

    private int doctorSchedualId;
    private Date doctorSchedualDate;
    private String doctorSchedualTime;

    public DoctorSchedualModel() {
    }

    public DoctorSchedualModel(Date doctorSchedualDate, String doctorSchedualTime) {
        this.doctorSchedualDate = doctorSchedualDate;
        this.doctorSchedualTime = doctorSchedualTime;
    }

    public DoctorSchedualModel(int doctorSchedualId, Date doctorSchedualDate, String doctorSchedualTime) {
        this.doctorSchedualId = doctorSchedualId;
        this.doctorSchedualDate = doctorSchedualDate;
        this.doctorSchedualTime = doctorSchedualTime;
    }

    public DoctorSchedualModel(
            Date doctorSchedualDate, String doctorSchedualTime,
            UserModel user) {
        super(user.getFullName(),
                user.getGender(),
                user.getDob(),
                user.getEmail(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getAccount());
        this.doctorSchedualDate = doctorSchedualDate;
        this.doctorSchedualTime = doctorSchedualTime;
    }

    public DoctorSchedualModel(
            int doctorSchedualId, Date doctorSchedualDate, String doctorSchedualTime,
            UserModel user) {
        super(user.getFullName(),
                user.getGender(),
                user.getDob(),
                user.getEmail(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getAccount());
        this.doctorSchedualId = doctorSchedualId;
        this.doctorSchedualDate = doctorSchedualDate;
        this.doctorSchedualTime = doctorSchedualTime;
    }

    public int getDoctorSchedualId() {
        return doctorSchedualId;
    }

    public void setDoctorSchedualId(int doctorSchedualId) {
        this.doctorSchedualId = doctorSchedualId;
    }

    public Date getDoctorSchedualDate() {
        return doctorSchedualDate;
    }

    public void setDoctorSchedualDate(Date doctorSchedualDate) {
        this.doctorSchedualDate = doctorSchedualDate;
    }

    public String getDoctorSchedualTime() {
        return doctorSchedualTime;
    }

    public void setDoctorSchedualTime(String doctorSchedualTime) {
        this.doctorSchedualTime = doctorSchedualTime;
    }

    @Override
    public String toString() {
        return "DoctorSchedual{"
                + "doctorSchedualId=" + doctorSchedualId
                + ", doctorSchedualDate=" + doctorSchedualDate
                + ", doctorSchedualTime=" + doctorSchedualTime
                + '}';
    }

    @Override
    public UserModel setResultSetValue(UserModel user, ResultSet rs)
            throws SQLException {
        super.setResultSetValue(user, rs);
        this.setDoctorSchedualId(rs.getInt("doctor_schedual_id"));
        this.setDoctorSchedualDate(rs.getDate("doctor_schedual_date"));
        this.setDoctorSchedualTime(rs.getString("doctor_schedual_time"));
        return user;
    }
}
