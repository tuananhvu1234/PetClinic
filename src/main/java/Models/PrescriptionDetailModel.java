package Models;

import DB.common.DBCommon;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionDetailModel extends DBCommon<PrescriptionDetailModel> {

    private int medicationManageId;
    private int prescriptionId;
    private int medicationId;

    public PrescriptionDetailModel() {
    }

    public PrescriptionDetailModel(int prescriptionId, int medicationId) {
        this.prescriptionId = prescriptionId;
        this.medicationId = medicationId;
    }

    public PrescriptionDetailModel(int medicationManageId, int prescriptionId, int medicationId) {
        this.medicationManageId = medicationManageId;
        this.prescriptionId = prescriptionId;
        this.medicationId = medicationId;
    }

    public int getMedicationManageId() {
        return medicationManageId;
    }

    public void setMedicationManageId(int medicationManageId) {
        this.medicationManageId = medicationManageId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(int medicationId) {
        this.medicationId = medicationId;
    }

    @Override
    public String toString() {
        return "PrescriptionDetailModel{"
                + "medicationManageId=" + medicationManageId
                + ", prescriptionId=" + prescriptionId
                + ", medicationId=" + medicationId
                + '}';
    }

    @Override
    public PrescriptionDetailModel setResultSetValue(PrescriptionDetailModel object, ResultSet rs)
            throws SQLException {
        object.setMedicationManageId(rs.getInt("medication_manage_id"));
        object.setPrescriptionId(rs.getInt("prescription_id"));
        object.setMedicationId(rs.getInt("medication_id"));
        return object;
    }

}
