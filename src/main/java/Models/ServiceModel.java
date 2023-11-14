package Models;

import DB.common.DBCommon;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceModel extends DBCommon<ServiceModel> {

    private int serviceId;
    private String serviceName;
    private String serviceType;
    private float servicePrice;

    public ServiceModel() {
    }

    public ServiceModel(String serviceName, String serviceType, float servicePrice) {
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
    }

    public ServiceModel(int serviceId, String serviceName, String serviceType, float servicePrice) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public float getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(float servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Override
    public String toString() {
        return "Service{"
                + "serviceId=" + serviceId
                + ", serviceName=" + serviceName
                + ", serviceType=" + serviceType
                + ", servicePrice=" + servicePrice
                + '}';
    }

    @Override
    public ServiceModel setResultSetValue(ServiceModel object, ResultSet rs)
            throws SQLException {
        object.setServiceId(rs.getInt("service_id"));
        object.setServiceName(rs.getString("service_name"));
        object.setServiceType(rs.getString("service_type"));
        object.setServicePrice(rs.getFloat("service_price"));
        return object;
    }

}
