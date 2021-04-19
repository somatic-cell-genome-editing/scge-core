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
        String sql="insert into scge_group(group_id, group_name, group_short_name,group_type, group_name_lc) values(?,?,?,?,?)";
        update(sql,
              g.getGroupId(), g.getGroupName(), g.getGroupShortName(),g.getGroupType(),g.getGroupNameLC());

    }
    public void updateGroupName(int groupId, String groupName) throws Exception{
        String sql="update scge_group set group_name=? where group_id=?" ;
        update(sql, groupName, groupId);

    }
    public List<SCGEGroup> getAllGroups() throws Exception {
        String sql="select * from scge_group order by group_name";
        GroupQuery q=new GroupQuery(this.getDataSource(), sql);
        return q.execute();
    }
    public int getGroupId(String groupName) throws Exception {
        String sql="select group_id from scge_group where group_name_lc=?";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        List<Integer> group=execute(q, groupName);
        return group != null && group.size() > 0 ? group.get(0) : 0;
    }
    public void makeAssociations(int groupId, int subgroupId) throws Exception {
        List<Integer> groupIds=getGroupIds(groupId, subgroupId);
        if(groupIds==null || groupIds.size()==0){
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

    public List<SCGEGroup> getSubGroupsByGroupId(int groupId) throws Exception {
        String sql="select * from scge_group where group_id in (" +
                "select subgroup_id from group_associations where group_id in (" +
                "select group_id from scge_group where group_id=?))";
        GroupQuery query= new GroupQuery(this.getDataSource(), sql);
        return execute(query, groupId);
    }


public List<Person> getGroupMembers(String groupName) throws Exception {
    String sql="select p.* from person p , person_info pi, scge_group g " +
            "where p.person_id=pi.person_id " +
            "and p.status='ACTIVE' " +
            "and g.group_id=pi.group_id " +
            "and g.group_name=? order by p.name";
    PersonQuery q=new PersonQuery(this.getDataSource(), sql);
    return execute(q, groupName);
}
    public List<Person> getGroupMembersByGroupId(int groupId) throws Exception {
        String sql="select p.* from person p , person_info pi, scge_group g " +
                "where p.person_id=pi.person_id " +
                "AND p.status= 'ACTIVE' " +
                "and g.group_id=pi.group_id " +
                "and g.group_id=? order by p.name";
        PersonQuery q=new PersonQuery(this.getDataSource(), sql);
        return execute(q, groupId);
    }
    public SCGEGroup getGroupById(int groupId) throws Exception{
        String sql="select * from scge_group where group_id=?";
        GroupQuery q=new GroupQuery(getDataSource(), sql);
        List<SCGEGroup> groups=execute(q, groupId);
        if(groups!=null){
            return groups.get(0);
        }
        return null;
    }
    public List<Integer> getDCCNIHGroupIds() throws Exception {
        String sql = "select subgroup_id from group_associations where group_id in (" +

                "select group_id from scge_group where group_name='DCC' or group_name='NIH')";
        IntListQuery q= new IntListQuery(this.getDataSource(), sql);

        return q.execute();
    }

    public List<Integer> getDCCNIHAncestorGroupIds() throws Exception {
        String sql="select group_id from scge_group where group_name in (?,?)" ;
        IntListQuery q= new IntListQuery(this.getDataSource(), sql);
        return execute(q, "DCC", "NIH");
    }

}
