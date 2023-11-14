package Models;

import DB.common.DBCommon;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel extends DBCommon<UserModel> {

    private int userId;
    private int role;//1-admin 2-doctor 3-client
    private String fullName;
    private int gender;//0-male 1-female 2-other
    private Date dob;
    private String email;
    private String address;
    private String phoneNumber;
    private AccountModel account;

    public UserModel() {
    }

    public UserModel(
            String fullName, int gender, Date dob,
            String email, String address, String phoneNumber,
            AccountModel account
    ) {
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    public UserModel(int role,
            String fullName, int gender, Date dob,
            String email, String address, String phoneNumber,
            AccountModel account
    ) {
        this.role = role;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    public UserModel(int userId, int role, String fullName, int gender, Date dob, String email, String address, String phoneNumber) {
        this.userId = userId;
        this.role = role;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public UserModel(int userId, int role,
            String fullName, int gender, Date dob,
            String email, String address, String phoneNumber,
            AccountModel account
    ) {
        this.userId = userId;
        this.role = role;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{"
                + "userId=" + userId
                + ", role=" + role
                + ", fullName=" + fullName
                + ", gender=" + gender
                + ", dob=" + dob
                + ", email=" + email
                + ", address=" + address
                + ", phoneNumber=" + phoneNumber
                + '}';
    }

    @Override
    public UserModel setResultSetValue(UserModel user, ResultSet rs)
            throws SQLException {
        user.setUserId(rs.getInt("user_id"));
        user.setRole(rs.getInt("role_id"));
        user.setFullName(rs.getString("full_name"));
        user.setGender(rs.getInt("gender"));
        user.setDob(rs.getDate("dob"));
        user.setEmail(rs.getString("email"));
        user.setAddress(rs.getString("address"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setAccount(
                new AccountModel(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("status")
                ));
        return user;
    }
}
