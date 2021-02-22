package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.StudyAssociation;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyAssociationQuery extends MappingSqlQuery {
    public StudyAssociationQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudyAssociation a=new StudyAssociation();
        a.setAssociationId(rs.getInt("id"));
        a.setStudyId(rs.getInt("study_id"));
        a.setGroupId(rs.getInt("group_id"));
        a.setCreatedDate(rs.getDate("created_date"));
        a.setCreatedBy(rs.getString("created_by"));
        a.setModifiedDate(rs.getDate("modified_date"));
        a.setModifiedBy(rs.getString("modified_by"));
        a.setTier(rs.getInt("tier"));
        return a;
    }
}
