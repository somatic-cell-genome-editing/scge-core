package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentQuery;
import edu.mcw.scge.datamodel.Experiment;

import java.util.List;

public class ExperimentDao extends AbstractDAO {
    public List<Experiment> getExperimentsByStudy(int studyId) throws Exception {
        String sql="select ex.*, e.symbol, d.ds_type, m.name, g.guide from experiment ex " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join guide g on ex.guide_id = g.guide_id " +
                "where ex.study_id=?";
        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return execute(q, studyId);
    }
    public Experiment getExperiment(int experimentId) throws Exception {
        String sql="select ex.*, e.symbol, d.ds_type, m.name, g.guide from experiment ex " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join guide g on ex.guide_id = g.guide_id " +
                "where ex.experiment_id=?";

        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return (Experiment) execute(q, experimentId).get(0);

    }
}
