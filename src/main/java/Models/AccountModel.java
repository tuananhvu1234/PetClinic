package Models;

import DB.common.DBCommon;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountModel extends DBCommon<AccountModel> {

    private String username;
    private String password;
    private int status;//0-inactive 1-active

    public AccountModel() {
    }

    public AccountModel(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = 1;
    }

    public AccountModel(String username, String password, int status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccountModel{"
                + "username=" + username
                + ", password=" + password
                + ", status=" + status
                + '}';
    }

    @Override
    public AccountModel setResultSetValue(AccountModel object, ResultSet rs)
            throws SQLException {
        object.setUsername(rs.getString("username"));
        object.setPassword(rs.getString("password"));
        object.setStatus(rs.getInt("status"));
        return object;
    }

}
