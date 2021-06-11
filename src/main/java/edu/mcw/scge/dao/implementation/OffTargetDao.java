package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.OffTargetQuery;
import edu.mcw.scge.datamodel.OffTarget;


import java.util.List;

public class OffTargetDao extends AbstractDAO {

    public List<OffTarget> getOffTargetByGuide(long guideId) throws Exception {
        String sql="select * from off_target where guide_id=?";
        OffTargetQuery q= new OffTargetQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }
	
    public void insertOffTarget(OffTarget offTarget) throws Exception{

        String sql = "insert into off_target ( guide_id, detection_method, no_of_sites_detected) " +
                "values ( ?,?,?)";

        update(sql, offTarget.getGuideId(),offTarget.getDetectionMethod(),offTarget.getNoOfSitesDetected());

    }

}
