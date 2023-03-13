package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.implementation.GrantDao;
import edu.mcw.scge.datamodel.Grant;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GrantQuery extends MappingSqlQuery {
    GrantDao grantDao=new GrantDao();
    public GrantQuery(DataSource ds, String sql){
        super(ds,sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Grant g= new Grant();
        g.setGrantId(rs.getInt("grant_id"));
        g.setGroupId(rs.getInt("group_id"));
        g.setGrantTitle(rs.getString("grant_title"));
        g.setGrantTitleLc(rs.getString("grant_title_lc"));
        g.setGrantInitiative(rs.getString("grant_initiative"));
        g.setDescription(rs.getString("description"));
        try {
            g.setCurrentGrantNumber(grantDao.getCurrentGrantNumberByGrantId(g.getGrantId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            g.setFormerGrantNumbers(grantDao.getFormerGrantNumbersByGrantId(g.getGrantId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String link=grantDao.getCurrentNihReportLink(g.getGrantId());
            if(!link.equals(""))
            g.setNihReporterLink(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }


}
