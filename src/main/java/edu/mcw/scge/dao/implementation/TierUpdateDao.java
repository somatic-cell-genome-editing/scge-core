package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.TierUpdateQuery;
import edu.mcw.scge.datamodel.StudyTierUpdate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TierUpdateDao extends AbstractDAO {
    private JdbcTemplate jdbcTemplateObject;

    public List<StudyTierUpdate> updates() throws Exception {
        String sql = "select * from study_tier_updates";
         return (List<StudyTierUpdate>) new TierUpdateQuery(this.getDataSource(), sql);
    }

    public void batchUpdate(final List<StudyTierUpdate> updates) throws Exception {
        this.jdbcTemplateObject=new JdbcTemplate();
        this.jdbcTemplateObject.setDataSource(this.getDataSource());
        String sql="insert into study_tier_updates(" +
                "study_tier_update_id," +
                "study_id," +
                "tier," +
                "modified_date," +
                "modified_by," +
                "associated_group_id," +
                "status," +
                "action," +
                "member_id," +
                "modified_time)" +
                " values(?,?,?,?,?,?,?,?,?,?)";
        int[] updateCounts = jdbcTemplateObject.batchUpdate(sql,
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, updates.get(i).getStudyTierUpdateId());
                        ps.setInt(2, updates.get(i).getStudyId());
                        ps.setInt(3, updates.get(i).getTier());
                        ps.setDate(4,  updates.get(i).getModifiedDate());
                        ps.setInt(5, updates.get(i).getModifiedBy());
                        ps.setInt(6, updates.get(i).getAssociatedGroupId());
                        ps.setString(7, updates.get(i).getStatus());
                        ps.setString(8, updates.get(i).getAction());
                        ps.setInt(9, updates.get(i).getMemberId());
                        ps.setTime(10, updates.get(i).getModifiedTime());

                    }
                    public int getBatchSize() {
                        return updates.size();
                    }
                });
    }
    public void delete(int studyId) throws Exception {
        String sql="delete from study_tier_updates where study_id=?";
        update(sql,studyId );
    }
    public void insertTierLog(int studyId) throws Exception {
        String sql="insert into study_tier_log  select study_tier_update_id, " +
                "study_id, " +
                "tier, " +
                "modified_date, " +
                "associated_group_id, " +
                "status, " +
                "action, " +
                "modified_by, " +
                "modified_time " +
                "from study_tier_updates where study_id=?";
        update(sql,studyId );
    }

    public List<StudyTierUpdate> getStudyTierUpdatesByStudyId(int studyId) throws Exception {
        String sql="select * from study_tier_updates where study_id=?";
        TierUpdateQuery q=new TierUpdateQuery(this.getDataSource(), sql);
        return execute(q,studyId);
    }
    public List<StudyTierUpdate> getStudyTierUpdates() throws Exception {
        String sql="select * from study_tier_updates ";
        TierUpdateQuery q=new TierUpdateQuery(this.getDataSource(), sql);
        return execute(q);
    }
    public void updateStudyTier(StudyTierUpdate u) throws Exception{
        String sql="update scge_tier set tier=?, last_modified_by=?, " +
                "last_modified_date=? where scge_id=?";
        update(sql, u.getTier(),  String.valueOf(u.getModifiedBy()), u.getModifiedDate(), u.getStudyId());
    }
    public void updateObjectTier(StudyTierUpdate u, long objectId) throws Exception{
        String sql="update scge_tier set tier=?, last_modified_by=?, " +
                "last_modified_date=? where scge_id=?";

        update(sql, u.getTier(),  String.valueOf(u.getModifiedBy()), u.getModifiedDate(), objectId);
    }
}
