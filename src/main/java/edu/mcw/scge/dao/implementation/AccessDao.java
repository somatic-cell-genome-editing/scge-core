package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.StringListQuery;

/**
 * Created by jthota on 9/10/2019.
 */
public class AccessDao extends AbstractDAO {
    public int getAccessId(String accessLevel) throws Exception {
        String sql="select access_id from experiment_access where access=?";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        return (int) execute(q, accessLevel).get(0);
    }
}
