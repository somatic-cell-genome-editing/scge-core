package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.OffTargetSite;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OffTargetSiteQuery extends MappingSqlQuery<OffTargetSite> {
    public OffTargetSiteQuery(DataSource ds, String sql){
        super(ds,sql);
    }

    @Override
    protected OffTargetSite mapRow(ResultSet rs, int rowNum) throws SQLException {

        OffTargetSite o = new OffTargetSite();
        o.setGuideId(rs.getLong("guide_id"));
        o.setOfftargetSequence(rs.getString("sequence"));
        o.setChromosome(rs.getString("chromosome"));
        o.setStart(rs.getString("start"));
        o.setStop(rs.getString("stop"));
        o.setStrand(rs.getString("strand"));
        o.setMismatches(rs.getInt("mismatches"));
        o.setTarget(rs.getString("target"));
        o.setSeqType(rs.getString("seq_type"));
        o.setNoOfReads(rs.getInt("no_of_reads"));
        return o;
    }
}



