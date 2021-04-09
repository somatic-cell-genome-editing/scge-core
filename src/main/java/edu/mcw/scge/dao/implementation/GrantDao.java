package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.GrantQuery;
import edu.mcw.scge.dao.spring.StringListQuery;
import edu.mcw.scge.datamodel.Grant;

import java.util.List;

public class GrantDao extends AbstractDAO {
    public void insert(Grant g) throws Exception {
        String sql="insert into scge_grants (grant_id, group_id, grant_number, grant_title, grant_title_lc, grant_initiative) values(?,?,?,?,?)";
        update(sql,g.getGrantId(), g.getGroupId(), g.getGrantNumber(), g.getGrantTitle(), g.getGrantTitleLc(), g.getGrantInitiative());
    }
    public Grant getGrantByTitle(String grantTitle) throws Exception {
        String sql="select * from scge_grants where grant_title_lc=?" ;
        GrantQuery q=new GrantQuery(this.getDataSource(), sql);
        List<Grant> grantList=execute(q, grantTitle);
        return (grantList!=null && grantList.size()>0)?grantList.get(0):null;
    }

    public Grant getGrantByGroupId(int groupId) throws Exception {
        String sql="select * from scge_grants where group_id=?" ;
        System.out.println(sql);
        GrantQuery q=new GrantQuery(this.getDataSource(), sql);
        List<Grant> grantList=execute(q, groupId);
        return (grantList!=null && grantList.size()>0)?grantList.get(0):null;
    }

    public Grant getGrantByNumber(String grantNumber){
        return null;
    }
    public Grant getGrantById(int id){
        return null;
    }
    public int insertOrUpdate(Grant grant) throws Exception {
        int grantId=0;
        Grant g=getGrantByTitle(grant.getGrantTitleLc());

        if(g==null){

             grantId=getNextKey("grant_seq");
            grant.setGrantId(grantId);
            insert(grant);
        }else{
            grantId=g.getGrantId();
        }
        return grantId;
    }
    public void getAllGrants(){

    }
    public List<String> getAllDistinctInitiatives() throws Exception {
        String sql="select distinct(grant_initiative) from scge_grants where project_type='grant'";
        StringListQuery q= new StringListQuery(this.getDataSource(), sql);
        return q.execute();
    }
    public List<Grant> getGrantsByInitiative(String initiative) throws Exception {
        String sql="select * from scge_grants where grant_initiative=?";
        GrantQuery q=new GrantQuery(this.getDataSource(), sql);
        return execute(q, initiative);
    }
}
