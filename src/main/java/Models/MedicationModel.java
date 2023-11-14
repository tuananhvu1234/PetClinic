package Models;

import DB.common.DBCommon;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicationModel extends DBCommon<MedicationModel> {

    private int medicationId;
    private String medicationName;
    private String description;
    private float price;

    public MedicationModel() {
    }

    public MedicationModel(int medicationId, String medicationName, String description, float price) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.description = description;
        this.price = price;
    }

    public MedicationModel(String medicationName, String description, float price) {
        this.medicationName = medicationName;
        this.description = description;
        this.price = price;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MedicationModel{"
                + "medicationId=" + medicationId
                + ", medicationName=" + medicationName
                + ", description=" + description
                + ", price=" + price
                + '}';
    }

    @Override
    public MedicationModel setResultSetValue(MedicationModel object, ResultSet rs)
            throws SQLException {
        object.setMedicationId(rs.getInt("medication_id"));
        object.setMedicationName(rs.getString("medication_name"));
        object.setDescription(rs.getString("description"));
        object.setPrice(rs.getFloat("price"));
        return object;
    }

}
