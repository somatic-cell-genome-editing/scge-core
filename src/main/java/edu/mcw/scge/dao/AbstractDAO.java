package edu.mcw.scge.dao;

import edu.mcw.scge.dao.spring.CountQuery;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

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
    public List execute(MappingSqlQuery q, Object ... params) {

        // declare parameters
        for( Object param: params ) {
            q.declareParameter(new SqlParameter(getParamType(param)));
        }

        // compile sql statement
        q.compile();

        // execute the statement
        return q.execute(params);
    }
    public int getNextKey(String seqName) throws Exception {
        return this.getNextKeyFromSequence(seqName);
    }
    public int getNextKeyFromSequence(String seqName) throws Exception {
        String query = "SELECT nextval('"+seqName+"')";
        return this.getCount(query, new Object[0]);
    }
    public int getCount(String sqlQuery, Object... params) throws Exception {
        CountQuery q = new CountQuery(this.getDataSource(), sqlQuery);
        List results = this.execute(q, params);
        return ((Integer)results.get(0)).intValue();
    }
    public int executeBatch(BatchSqlUpdate bsu) {
        bsu.flush();

        // compute nr of rows affected
        int totalRowsAffected = 0;
        for( int rowsAffected: bsu.getRowsAffected() ) {
            totalRowsAffected += rowsAffected;
        }
        return totalRowsAffected;
    }

}
