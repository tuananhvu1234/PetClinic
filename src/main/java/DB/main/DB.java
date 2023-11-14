package DB.main;

import DB.common.DBCommon;
import DB.dao.JDBCConnect;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB<T> {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;

    private T object;

    public DB() {
    }

    public DB(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Class<?> getClassObject() {
        try {
            String className = getObject().getClass().getName();
            return Class.forName(className);
        } catch (ClassNotFoundException ex) {
            System.out.println("getClassObject Fail !" + ex.getMessage());
        }
        return null;
    }

    public T getNewInstance() {
        try {
            T obj = (T) getClassObject().getDeclaredConstructor().newInstance();
            return obj;
        } catch (NoSuchMethodException
                | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public <T extends DBCommon<T>>
            T getOne(String query, List condition, T classObject) {
        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            setPreparedStatement(condition);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return classObject.setResultSetValue(classObject, rs);
            }
        } catch (SQLException ex) {
            System.out.println("getOne Fail ! SQL Error !" + ex.getMessage());
        } finally {
            JDBCConnect.closeJDBCConnection(rs, preparedStatement, connection);
        }
        return null;
    }

    public <T extends DBCommon<T>>
            ArrayList<T> getAll(String query, T classObject) {
        ArrayList<T> arrayList = new ArrayList<>();
        try {
            String className = classObject.getClass().getName();
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                T obj = (T) Class.forName(className).getDeclaredConstructor().newInstance();
                arrayList.add(obj.setResultSetValue(obj, rs));
            }
            return arrayList;
        } catch (SQLException ex) {
            System.out.println("getAll Fail !SQL Error !" + ex.getMessage());
        } catch (SecurityException | IllegalArgumentException
                | ClassNotFoundException | NoSuchMethodException
                | InstantiationException | IllegalAccessException
                | InvocationTargetException ex) {
            System.out.println(ex);
        } finally {
            JDBCConnect.closeJDBCConnection(rs, preparedStatement, connection);
        }
        return null;
    }

    public <T extends DBCommon<T>>
            ArrayList<T> getAll(String query, List condition, T classObject) {
        ArrayList<T> arrayList = new ArrayList<>();
        try {
            String className = classObject.getClass().getName();
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            setPreparedStatement(condition);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                T obj = (T) Class.forName(className).getDeclaredConstructor().newInstance();
                arrayList.add(obj.setResultSetValue(obj, rs));
            }
            return arrayList;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("getAll Fail !SQL Error !" + ex.getMessage());
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException
                | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
            System.out.println(ex);
        } finally {
            JDBCConnect.closeJDBCConnection(rs, preparedStatement, connection);
        }
        return null;
    }

    public <T extends DBCommon<T>>
            boolean setSqlDataRow(String query, List condition, T classObject) {
        try {
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            setPreparedStatement(condition);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("setSqlDataRow Fail ! SQL Error !" + ex.getMessage());
        } finally {
            JDBCConnect.closeJDBCConnection(rs, preparedStatement, connection);
        }
        return false;
    }

    private void setPreparedStatement(List list) {
        try {
            int index = 1;
            if (!list.isEmpty()) {
                for (Object obj : list) {
                    preparedStatement.setObject(index, obj);
                    index++;
                }
            }
        } catch (SQLException ex) {
            System.out.println("setPreparedStatement Fail !" + ex.getMessage());
        }
    }
}
