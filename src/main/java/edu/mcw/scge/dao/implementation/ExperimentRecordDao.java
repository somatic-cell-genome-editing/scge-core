package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentRecordQuery;
import edu.mcw.scge.datamodel.ExperimentRecord;

import java.util.List;

public class ExperimentRecordDao extends AbstractDAO {
    public List<ExperimentRecord> getExperimentRecords() throws Exception {
        String sql="select x.experiment_id,e.subtype, g.guide,g.detection_method, m.name, t.target_site,t.locus_symbol, t.specificity_ratio " +
                "from experiment x, experiment_record r, " +
                "editor e, " +
                "guide g, " +
                "model m, " +
                "target t "+
                " where x.experiment_id = r.experiment_id and e.editor_id=x.editor_id " +
                " and x.guide_id=g.guide_id " +
                " and x.target_id=r.target_id " +
                " and x.model_id=m.model_id";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q);
    }
    public List<ExperimentRecord> getExperimentRecordsByLabId(int labId) throws Exception {

      String sql="select * from study s join experiment x on (s.study_id=x.study_id) " +
              "left join experiment_record r on (r.experiment_id=x.experiment_id) " +
              "left join editor e on (e.editor_id= x.editor_id) " +
              "left join delivery_system d on (d.ds_id= x.ds_id) " +
              "left join application_method app on (app.application_method_id= x.application_method_id) " +

              "left join guide g on (g.guide_id=x.guide_id) " +
              "left join target t on (t.target_id=r.target_id) " +
              "left join model m on (m.model_id =x.model_id) " +
              "where s.lab_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, labId);
    }
    public List<ExperimentRecord> getExperimentRecordsByStudyId(int studyId) throws Exception {
        String sql="select s.study, r.*, e.symbol, d.ds_type, m.name as modelName, g.guide, x.type from study s join experiment x on (s.study_id=x.study_id) " +
                "left join experiment_record r on (r.experiment_id=x.experiment_id) " +
                "left join editor e on (e.editor_id= r.editor_id) " +
                "left join delivery_system d on (d.ds_id= r.ds_id) " +
                "left join application_method app on (app.application_method_id= r.application_method_id) " +

                "left join guide g on (r.guide_id=g.guide_id) " +
            //    "left join target t on (t.target_id=r.target_id) " +
                "left join model m on (m.model_id =r.model_id) " +
                "where s.study_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, studyId);
    }
    public List<ExperimentRecord> getExperimentRecordById(int expId) throws Exception {
        String sql="select s.study, r.*, e.symbol, d.ds_type, m.name as modelName, g.guide, x.type from  experiment x  " +
                "left join experiment_record r on (r.experiment_id=x.experiment_id) " +
                "left join editor e on (e.editor_id= r.editor_id) " +
                "left join delivery_system d on (d.ds_id= r.ds_id) " +
                "left join guide g on (r.guide_id=g.guide_id) " +
              //  "left join target t on (t.target_id=r.target_id) " +
                "left join model m on (m.model_id =r.model_id) " +
                "where x.experiment_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, expId);
    }
    public List<ExperimentRecord> getExperimentRecordByExpRecId(int expRecId) throws Exception {
        String sql="select s.study, r.*, e.symbol, d.ds_type, m.name as modelName, g.guide, x.type" +
                " from  experiment x  " +
                "left join experiment_record r on (r.experiment_id=x.experiment_id) " +
                " left outer join study s on r.study_id = s.study_id " +
                "left join editor e on (e.editor_id= r.editor_id) " +
                "left join delivery_system d on (d.ds_id= r.ds_id) " +
                "left join guide g on (r.guide_id=g.guide_id) " +
                //  "left join target t on (t.target_id=r.target_id) " +
                "left join model m on (m.model_id =r.model_id) " +
                "where r.experiment_record_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, expRecId);
    }
	public int insertExperimentRecord(ExperimentRecord expRecord) throws Exception{

        String sql = "insert into experiment_record (experiment_id,name,study_id, " +
                "editor_id,ds_id,model_id,guide_id,sample_prep,application_method_id,experiment_record_id,age, genotype,sex," +
                "vector_id,tissue_id, cell_type  ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        int experimentId = this.getNextKeyFromSequence("experiment_seq");
        
        update(sql, expRecord.getExperimentId(),expRecord.getExperimentName(),expRecord.getStudyId(), expRecord.getEditorId(),
                expRecord.getDeliverySystemId(),expRecord.getModelId(), expRecord.getGuideId(),expRecord.getSamplePrep(),
                expRecord.getApplicationMethodId(),experimentId, expRecord.getAge(),expRecord.getGenotype(),
                expRecord.getSex(),expRecord.getVectorId(),expRecord.getTissueId(),expRecord.getCellType());

        return experimentId;
    }
}
