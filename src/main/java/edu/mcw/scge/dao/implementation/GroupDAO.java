package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.GroupQuery;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.datamodel.SCGEGroup;

import java.util.List;

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
        return (int) execute(q, groupName).get(0);
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

}
