package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentRecordQuery;
import edu.mcw.scge.datamodel.ExperimentRecord;

import java.util.List;

public class ExperimentRecordDao extends AbstractDAO {
    public List<ExperimentRecord> getExperimentRecords() throws Exception {
        String sql="select x.experiment_id,e.subtype, g.guide,g.detection_method, m.name, t.target_site,t.locus_symbol, t.specificity_ratio " +
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
    public List<ExperimentRecord> getExperimentRecordsByLabId(int labId) throws Exception {
      /*  String sql="select x.experiment_id,e.subtype, g.guide,g.detection_method, m.name, t.locus_id,t.locus_symbol, t.specificity_ratio " +
                "from experiment_record x, " +
                "experiment exp, " +
                " study s, " +
                " editor e, " +
                "guide g, " +
                "model m, " +
                "target t "+
                " where s.study_id=exp.study_id" +
                " and x.experiment_id=exp.experiment_id" +
                " and e.editor_id=x.editor_id " +
                " and x.guide_id=g.guide_id " +
                " and x.target_id=t.target_id " +
                " and x.model_id=m.model_id " +
                " and s.lab_id=?";*/
      String sql="select * from study s join experiment x on (s.study_id=x.study_id) " +
              "left join experiment_record r on (r.experiment_id=x.experiment_id) " +
              "left join editor e on (e.editor_id= r.editor_id) " +
              "left join delivery_system d on (d.delivery_system_id= r.delivery_system_id) " +
              "left join application_method app on (app.application_method_id= r.application_method_id) " +

              "left join guide g on (r.guide_id=g.guide_id) " +
              "left join target t on (t.target_id=r.target_id) " +
              "left join model m on (m.model_id =r.model_id) " +
              "where s.lab_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, labId);
    }
    public List<ExperimentRecord> getExperimentRecordById(int expId) throws Exception {
        String sql="select * from  experiment x  " +
                "left join experiment_record r on (r.experiment_id=x.experiment_id) " +
                "left join editor e on (e.editor_id= r.editor_id) " +
                "left join delivery_system d on (d.delivery_system_id= r.delivery_system_id) " +
                "left join guide g on (r.guide_id=g.guide_id) " +
                "left join target t on (t.target_id=r.target_id) " +
                "left join model m on (m.model_id =r.model_id) " +
                "where x.experiment_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, expId);
    }
}