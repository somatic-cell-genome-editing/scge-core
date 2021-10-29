package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.OffTargetQuery;
import edu.mcw.scge.dao.spring.OffTargetSiteQuery;
import edu.mcw.scge.datamodel.OffTarget;
import edu.mcw.scge.datamodel.OffTargetSite;

import java.util.List;

public class OffTargetSiteDao extends AbstractDAO {

    public List<OffTargetSite> getOffTargetSitesByGuide(long guideId) throws Exception {
        String sql="select * from off_target_site where guide_id=? order by sort_key,start";
        OffTargetSiteQuery q= new OffTargetSiteQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }
	
    public void insertOffTargetSite(OffTargetSite o) throws Exception{

        String sql = "insert into off_target_site ( guide_id, sequence, chromosome,start,stop,strand," +
                "mismatches,target,seq_type,no_of_reads) values ( ?,?,?,?,?,?,?,?,?,?)";

        update(sql, o.getGuideId(),o.getOfftargetSequence(),o.getChromosome(),o.getStart(),o.getStop(),o.getStrand(),
                o.getMismatches(),o.getTarget(),o.getSeqType(),o.getNoOfReads());

    }

}
