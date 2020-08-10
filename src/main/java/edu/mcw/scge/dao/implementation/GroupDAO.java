package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.*;
import edu.mcw.scge.datamodel.Person;
import edu.mcw.scge.datamodel.PersonInfo;
import edu.mcw.scge.datamodel.SCGEGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jthota on 9/10/2019.
 */
public class GroupDAO extends AbstractDAO {

    public void insert(SCGEGroup g) throws Exception{
        String sql="insert into scge_group values(?,?,?,?)";
        update(sql,
              g.getGroupId(), g.getGroupName(), g.getGroupShortName(),g.getGroupType()
        );

    }
    public List<SCGEGroup> getAllGroups() throws Exception {
        String sql="select * from scge_group";
        GroupQuery q=new GroupQuery(this.getDataSource(), sql);
        return q.execute();
    }
    public int getGroupId(String groupName) throws Exception {
        String sql="select group_id from scge_group where group_name=?";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        List<Integer> group=execute(q, groupName);
        return group != null && group.size() > 0 ? group.get(0) : 0;
    }
    public void makeAssociations(int groupId, int subgroupId) throws Exception {
        List<Integer> groupIds=getGroupIds(groupId, subgroupId);
        if(groupIds!=null && groupIds.size()>0){

        }else{
            insertGroupAssociations(groupId, subgroupId);
        }

    }
    public List<Integer> getGroupIds(int groupId, int subgroupId) throws Exception {
        String sql="select group_id from group_associations where group_id=? and subgroup_id=?";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        return execute(query, groupId, subgroupId);
    }
    public void insertGroupAssociations(int groupId, int subgroupId) throws Exception {
        String sql="insert into group_associations values(?,?)";
        update(sql,groupId, subgroupId);
    }
    public Map<String, List<String>> getGroupsNRoles(String userName) throws Exception {
        String sql="select g.group_name, r.role  from scge_group g , person_info i, person p, scge_roles r " +
                "where p.person_id=i.person_id " +
                "and g.group_id=i.group_id " +
                "and r.role_key=i.role_key " +
                "and p.person_id in (select person_id from person where name=?) ";
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Map<String, List<String>> groupRoleMap=new HashMap<>();
        try{
            conn=this.getDataSource().getConnection();
            stmt=conn.prepareStatement(sql);
            stmt.setString(1, userName);
            rs=stmt.executeQuery();

           // List<String> roles= new ArrayList<>();
            while(rs.next()){
                String group=rs.getString("group_name");
                String role=rs.getString("role");
                List<String> roles=new ArrayList<>();
                if(groupRoleMap.get(group)!=null){

                    roles.addAll(groupRoleMap.get(group));
                    roles.add(role);
                }else{
                    roles.add(role);
                }
                groupRoleMap.put(group, roles);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return groupRoleMap;
    }
    public Map<String, List<String>> getGroupsNRolesByMemberId(int personId) throws Exception {
      String sql="select g.group_name, r.role  from scge_group g , person_info i, person p, scge_roles r " +
                "where p.person_id=i.person_id " +
                "and g.group_id=i.group_id " +
                "and r.role_key=i.role_key " +
                "and p.person_id =? ";

        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Map<String, List<String>> groupRoleMap=new HashMap<>();
        try{
            conn=this.getDataSource().getConnection();
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,personId);
            rs=stmt.executeQuery();

            // List<String> roles= new ArrayList<>();
            while(rs.next()){
                String group=rs.getString("group_name");
                String role=rs.getString("role");
                List<String> roles=new ArrayList<>();
                if(groupRoleMap.get(group)!=null){

                    roles.addAll(groupRoleMap.get(group));
                    roles.add(role);
                }else{
                    roles.add(role);
                }
                groupRoleMap.put(group, roles);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return groupRoleMap;
    }


    public List<PersonInfo> getGroupsNRolesByPersonId(int personId) throws Exception {

        String sql="select p.person_id, g.group_name as group_name, sg.group_name as subgroup_name, r.role  from scge_group g , person_info i, person p, scge_roles r, scge_group sg, group_associations a " +
                "                where p.person_id=i.person_id  " +
                "                and sg.group_id=i.group_id  " +
                "                and r.role_key=i.role_key  " +
                "               and p.person_id =? " +
                "               and a.group_id=g.group_id " +
                "               and a.subgroup_id=sg.group_id";
        PersonInfoQuery q=new PersonInfoQuery(this.getDataSource(), sql);
        return execute(q, personId);
    }
    public List<SCGEGroup> getGroupByPersonIdNType(int personId, String type) throws Exception {

        String sql="select sg.group_name, sg.group_id, sg.group_type from scge_group g , scge_group sg, group_associations a where " +
                "a.group_id=g.group_id " +
                "and a.subgroup_id=sg.group_id " +
                "and g.group_name=? " +
                "and sg.group_id in (select group_id from person_info where person_id=?) ";
        GroupQuery q=new GroupQuery(this.getDataSource(), sql);
        return execute(q,type, personId);
    }

    public List<String> getSubGroupsByGroupName(String groupName) throws Exception {
        String sql="select sg.group_name from  scge_group g , scge_group sg where " +
                "g.group_id in (select group_id from scge_group where group_name=? ) " +
                "and sg.group_id in (select subgroup_id from group_associations where group_id in (select group_id from scge_group where group_name=? ))";
        StringListQuery query= new StringListQuery(this.getDataSource(), sql);
        return execute(query, groupName, groupName);
    }

public List<Person> getGroupMembers(String groupName) throws Exception {
    String sql="select p.* from person p , person_info pi, scge_group g " +
            "where p.person_id=pi.person_id " +
            "and g.group_id=pi.group_id " +
            "and g.group_name=? order by p.name";
    PersonQuery q=new PersonQuery(this.getDataSource(), sql);
    return execute(q, groupName);
}
    public static void main(String[] args) throws Exception {
        GroupDAO groupDAO=new GroupDAO();
       List<Person> pList= groupDAO.getGroupMembers("consortium group");
        for(Person p:pList){
            System.out.println(p.getName());
        }
        System.out.println("PERSONS: " + pList.size());
    }

}
