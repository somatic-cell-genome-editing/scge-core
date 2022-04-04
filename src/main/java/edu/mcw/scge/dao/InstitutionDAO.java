package edu.mcw.scge.dao;

import edu.mcw.scge.dao.spring.InstitutionQuery;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.ModelQuery;
import edu.mcw.scge.dao.spring.StringListQuery;
import edu.mcw.scge.datamodel.Institution;
import edu.mcw.scge.datamodel.Model;

import java.util.List;

/**
 * Created by jthota on 10/9/2019.
 */
public class InstitutionDAO extends AbstractDAO {

    public List<Institution> getAll() throws Exception {
        String sql="select * from institution order by institution_name";
        InstitutionQuery q=new InstitutionQuery(this.getDataSource(), sql);
        q.compile();

        return q.execute();
    }

    public String getRoleByKey(int roleKey) throws Exception {

        String sql="select role from scge_roles where role_key=?";
        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        List<String> roles=execute(q, roleKey);
        return (roles!=null && roles.size()>0) ?roles.get(0):"";
    }
    public int getRoleKeyOfRole(String role) throws Exception {

        String sql="select role_key from scge_roles where role=?";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        List<Integer> roleKeys=execute(q, role);
        return (roleKeys!=null && roleKeys.size()>0) ?roleKeys.get(0):0;
    }
}
