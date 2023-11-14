package Entities;

import DB.main.DB;
import Models.RoleModel;
import java.util.Arrays;
import java.util.List;

public class RoleEntity {

    private final DB<RoleModel> db = new DB<>();

    private String query;
    private List condition;

    public boolean addRole(RoleModel role) {
        query = "INSERT INTO Role VALUES (?,?)";
        condition = Arrays.asList(role.getRoleId(), role.getRoleName());
        return db.setSqlDataRow(query, condition, role);
    }

    public List<RoleModel> getAllRole() {
        query = "SELECT * FROM Role";
        return db.getAll(query, new RoleModel());
    }

    public RoleModel getOneRole(int roleId) {
        query = "SELECT * FROM Role WHERE role_id = ?";
        condition = Arrays.asList(roleId);
        return db.getOne(query, condition, new RoleModel());
    }

    public boolean updateRole(RoleModel role) {
        query = "UPDATE Role SET role_name = ? WHERE role_id = ?";
        condition = Arrays.asList(role.getRoleName(), role.getRoleId());
        return db.setSqlDataRow(query, condition, role);
    }

    public boolean deleteRole(int roleId) {
        query = "DELETE FROM Role WHERE role_id = ?";
        condition = Arrays.asList(roleId);
        return db.setSqlDataRow(query, condition, new RoleModel());
    }
}
