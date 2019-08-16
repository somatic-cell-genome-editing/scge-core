package edu.mcw.scge.dao;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Types;

/**
 * Created by jthota on 8/16/2019.
 */
public class AbstractDAO implements DAO {
    @Override
    public Connection getConnection() throws Exception {
        return DataSourceFactory.getInstance().getDataSource().getConnection();
    }
    public DataSource getDataSource() throws Exception{
        return DataSourceFactory.getInstance().getDataSource();
    }
    public int update(String sql, Object ... params) throws Exception {

        SqlUpdate su = new SqlUpdate(this.getDataSource(), sql);

        // declare parameters
        for( Object param: params ) {
            su.declareParameter(new SqlParameter(getParamType(param)));
        }
        su.compile();

        return su.update(params);
    }
    private int getParamType(Object param) {
        int paramType;
        if( param == null ) {
            paramType = Types.NULL;
        }
        else if( param instanceof String ) {
            paramType = Types.VARCHAR;
        }
        else if( param instanceof Integer ) {
            paramType = Types.INTEGER;
        }
        else if( param instanceof Long ) {
            paramType = Types.BIGINT;
        }
        else if( param instanceof java.util.Date ) {
            paramType = Types.TIMESTAMP;
        }
        else if( param instanceof Double ) {
            paramType = Types.DOUBLE;
        }
        else {
            paramType = Types.OTHER;
        }
        return paramType;
    }
}
