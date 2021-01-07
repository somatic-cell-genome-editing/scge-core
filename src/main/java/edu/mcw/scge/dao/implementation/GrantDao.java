package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.GrantQuery;
import edu.mcw.scge.datamodel.Grant;

import java.util.List;

public class GrantDao extends AbstractDAO {
    public void insert(Grant g) throws Exception {
        String sql="insert into scge_grants (grant_id, grant_number, grant_title, grant_title_lc, grant_initiative) values(?,?,?,?,?)";
        update(sql,g.getGrantId(), g.getGrantNumber(), g.getGrantTitle(), g.getGrantTitleLc(), g.getGrantInitiative());
    }
    public Grant getGrantByTitle(String grantTitle) throws Exception {
        String sql="select * from scge_grants where grant_title_lc=?" ;
        GrantQuery q=new GrantQuery(this.getDataSource(), sql);
        List<Grant> grantList=execute(q, grantTitle);
        return (grantList!=null && grantList.size()>0)?grantList.get(0):null;
    }
    public Grant getGrantByNumber(String grantNumber){
        return null;
    }
    public Grant getGrantById(int id){
        return null;
    }
    public void insertOrUpdate(Grant grant) throws Exception {
        Grant g=getGrantByTitle(grant.getGrantTitleLc());
        if(g==null){
            int grantId=getNextKey("grant_seq");
            grant.setGrantId(grantId);
            insert(grant);
        }
    }
}
