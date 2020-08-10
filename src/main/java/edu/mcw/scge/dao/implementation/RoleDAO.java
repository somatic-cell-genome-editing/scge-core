package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.StringListQuery;

import java.util.List;

/**
 * Created by jthota on 10/9/2019.
 */
public class RoleDAO extends AbstractDAO {

    public void insert(int rolekey, String role) throws Exception {
        String sql="insert into scge_roles(role_key, role) values(?,?)";
        update(sql, rolekey, role);
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
