package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentRecordQuery;
import edu.mcw.scge.datamodel.ExperimentRecord;

import java.util.List;

public class ExperimentRecordDao extends AbstractDAO {
    public List<ExperimentRecord> getExperimentRecords() throws Exception {
        String sql="select x.experiment_id,e.subtype, g.guide,g.detection_method, m.name, t.locus_id,t.locus_symbol, t.specificity_ratio " +
                "from experiment_record x, " +
                "editor e, " +
                "guide g, " +
                "model m, " +
                "target t "+
                " where e.editor_id=x.editor_id " +
                " and x.guide_id=g.guide_id " +
                " and x.target_id=t.target_id " +
                " and x.model_id=m.model_id";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q);
    }
}
