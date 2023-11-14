package Models;

import DB.common.DBCommon;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionModel extends DBCommon<PrescriptionModel> {

    private int prescriptionId;
    private Date prescriptionDate;

    public PrescriptionModel() {
    }

    public PrescriptionModel(int prescriptionId, Date prescriptionDate) {
        this.prescriptionId = prescriptionId;
        this.prescriptionDate = prescriptionDate;
    }

    public PrescriptionModel(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public PrescriptionModel(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    @Override
    public String toString() {
        return "Prescription{"
                + "prescriptionId=" + prescriptionId
                + ", prescriptionDate=" + prescriptionDate
                + '}';
    }

    @Override
    public PrescriptionModel setResultSetValue(PrescriptionModel object, ResultSet rs)
            throws SQLException {
        object.setPrescriptionId(rs.getInt("prescription_id"));
        object.setPrescriptionDate(rs.getDate("prescription_date"));
        return object;
    }

}
