package Models;

import DB.common.DBCommon;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetModel extends DBCommon<PetModel> {

    private int petId;
    private int userId;
    private String petName;
    private String petType;
    private int petGender;
    private float petWeight;
    private int petAge;

    public PetModel() {
    }

    public PetModel(int userId, String petName, String petType, int petGender, float petWeight, int petAge) {
        this.userId = userId;
        this.petName = petName;
        this.petType = petType;
        this.petGender = petGender;
        this.petWeight = petWeight;
        this.petAge = petAge;
    }

    public PetModel(int petId, int userId, String petName, String petType, int petGender, float petWeight, int petAge) {
        this.petId = petId;
        this.userId = userId;
        this.petName = petName;
        this.petType = petType;
        this.petGender = petGender;
        this.petWeight = petWeight;
        this.petAge = petAge;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getPetGender() {
        return petGender;
    }

    public void setPetGender(int petGender) {
        this.petGender = petGender;
    }

    public float getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(float petWeight) {
        this.petWeight = petWeight;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    @Override
    public String toString() {
        return "Pet{" + "petId=" + petId
                + ", userId=" + userId
                + ", petName=" + petName
                + ", petType=" + petType
                + ", petGender=" + petGender
                + ", petWeight=" + petWeight
                + ", petAge=" + petAge
                + '}';
    }

    @Override
    public PetModel setResultSetValue(PetModel object, ResultSet rs)
            throws SQLException {
        object.setPetId(rs.getInt("pet_id"));
        object.setUserId(rs.getInt("user_id"));
        object.setPetType(rs.getString("pet_type"));
        object.setPetName(rs.getString("pet_name"));
        object.setPetGender(rs.getInt("pet_gender"));
        object.setPetWeight(rs.getFloat("pet_weight"));
        object.setPetAge(rs.getInt("pet_age"));
        return object;
    }
}
