package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentQuery;
import edu.mcw.scge.dao.spring.ExperimentRecordQuery;
import edu.mcw.scge.datamodel.Experiment;
import edu.mcw.scge.datamodel.ExperimentRecord;

import java.util.List;

public class ExperimentDao extends AbstractDAO {

    public List<ExperimentRecord> getExperimentRecords(int experimentId) throws Exception {
        String sql="select ex.*, e.symbol, d.ds_type, m.name as modelName, g.guide , x.type from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join guide g on ex.guide_id = g.guide_id " +
                "where ex.experiment_id=?";

        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public List<Experiment> getExperimentsByStudy(int studyId) throws Exception {
        String sql="select * from experiment where study_id=?";

        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return execute(q, studyId);
    }
    public Experiment getExperiment(int experimentId) throws Exception {
        String sql="select ex.*, e.symbol, d.ds_type, m.name as modelName, g.guide, v.name as vectorName , x.tyupefrom experiment ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join guide g on ex.guide_id = g.guide_id " +
                "where ex.experiment_id=?";

        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return (Experiment) execute(q, experimentId).get(0);

    }

    public List<ExperimentRecord> getExperimentsByEditor(int editorId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, m.name as modelName, g.guide, x.type from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
                "where ex.editor_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, editorId);
    }

    public List<ExperimentRecord> getExperimentsByModel(int modelId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, m.name as modelName, g.guide, x.type from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
                "where ex.model_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, modelId);
    }

    public List<ExperimentRecord> getExperimentsByDeliverySystem(int dsId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, m.name as modelName, g.guide, x.type from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
                "where ex.ds_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, dsId);
    }

    public List<ExperimentRecord> getExperimentsByVector(int vectorId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, m.name as modelName, g.guide, x.type from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
                "where ex.ds_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, vectorId);
    }

    public List<ExperimentRecord> getExperimentsByGuide(int guideId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, m.name as modelName, g.guide,x.type from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
                "where ex.guide_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }

    public List<Experiment> getAllExperiments() throws Exception{
        String sql="select * from experiment" ;
        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return q.execute();
    }

}
