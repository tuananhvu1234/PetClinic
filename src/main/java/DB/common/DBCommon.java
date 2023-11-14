package DB.common;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBCommon<T> {

    public abstract T setResultSetValue(T object, ResultSet rs)
            throws SQLException;
}
