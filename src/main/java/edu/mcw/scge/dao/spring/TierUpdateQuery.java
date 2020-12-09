package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.StudyTierUpdate;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TierUpdateQuery extends MappingSqlQuery {

    public TierUpdateQuery(DataSource ds,String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudyTierUpdate object=new StudyTierUpdate();
        object.setStudyTierUpdateId(rs.getInt("study_tier_update_id"));
        object.setStudyId(rs.getInt("study_id"));
        object.setGroupId(rs.getInt("group_id"));
        object.setMemberId(rs.getInt("member_id"));
        object.setTier(rs.getInt("tier"));
        object.setAction(rs.getString("action"));
        object.setStatus(rs.getString("status"));
        object.setModifiedBy(rs.getInt("modified_by"));
        object.setModifiedTime(rs.getTime("modified_time"));
        object.setModifiedDate(rs.getDate("modified_date"));

        return object;
    }
}
