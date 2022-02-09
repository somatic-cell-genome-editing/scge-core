package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentRecordQuery;
import edu.mcw.scge.datamodel.Editor;
import edu.mcw.scge.datamodel.ExperimentRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExperimentRecordDao extends AbstractDAO {

   /* public List<ExperimentRecord> getExperimentRecords() throws Exception {
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
    */
   /* public List<ExperimentRecord> getExperimentRecordsByLabId(int labId) throws Exception {

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
    */

    public List<ExperimentRecord> getExperimentRecordsByStudyId(int studyId) throws Exception {
        String sql="select s.study, r.*, app.*, e.symbol, d.ds_type, m.name as modelName,h.lab_id as hrdonorName, x.type from study s join experiment x on (s.study_id=x.study_id) \n" +
                "inner join experiment_record r on (r.experiment_id=x.experiment_id) \n" +
                "left outer join editor e on (e.editor_id= r.editor_id) \n" +
                "left outer join delivery_system d on (d.ds_id= r.ds_id) \n" +
                "left outer join hr_donor h on (h.hrdonor_id= r.hrdonor_id) \n" +
                "left outer join application_method app on (app.application_method_id= r.application_method_id) \n" +
                "left outer join model m on (m.model_id =r.model_id) \n" +
                "where s.study_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, studyId);
    }

    /*public List<ExperimentRecord> getExperimentRecordById(int expId) throws Exception {
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
    */

   public List<ExperimentRecord> getExperimentRecordById(long expRecId) throws Exception {
        String sql="select s.study, r.*, e.symbol, d.ds_type, m.name as modelName, g.guide, h.lab_id as hrdonorName, x.type" +
                " from  experiment x  " +
                "left join experiment_record r on (r.experiment_id=x.experiment_id) " +
                " left outer join study s on r.study_id = s.study_id " +
                "left join editor e on (e.editor_id= r.editor_id) " +
                "left join delivery_system d on (d.ds_id= r.ds_id) " +
                "left join guide g on (r.guide_id=g.guide_id) " +
                "left outer join hr_donor h on (h.hrdonor_id= r.hrdonor_id) \n" +
                //  "left join target t on (t.target_id=r.target_id) " +
                "left join model m on (m.model_id =r.model_id) " +
                "where r.experiment_record_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, expRecId);
    }

	public long insertExperimentRecord(ExperimentRecord expRecord) throws Exception{

        String sql = "insert into experiment_record (experiment_id,name,study_id, " +
                "editor_id,ds_id,model_id,hrdonor_id,application_method_id,experiment_record_id,age, genotype,sex," +
                "tissue_id, cell_type,organ_system  ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        long experimentId = this.getNextKeyFromSequenceLong("experiment_seq");
        
        update(sql, expRecord.getExperimentId(),expRecord.getExperimentName(),expRecord.getStudyId(), expRecord.getEditorId(),
                expRecord.getDeliverySystemId(),expRecord.getModelId(),expRecord.getHrdonorId(),
                expRecord.getApplicationMethodId(),experimentId, expRecord.getAge(),expRecord.getGenotype(),
                expRecord.getSex(),expRecord.getTissueId(),expRecord.getCellType(),expRecord.getOrganSystemID());

        return experimentId;
    }
    public long getExpRecordId(ExperimentRecord experimentRecord) throws Exception {
        String sql="select * from experiment_record where name =? and study_id=? and editor_id = ? and ds_id = ?  and model_id = ? and sex = ?" +
                "and application_method_id = ? and tissue_id = ? and cell_type = ? ";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        List<ExperimentRecord> list = execute(q,sql,experimentRecord.getExperimentName(),  experimentRecord.getStudyId(),experimentRecord.getEditorId(),experimentRecord.getDeliverySystemId(),
                experimentRecord.getModelId(),experimentRecord.getSex(),experimentRecord.getApplicationMethodId(),experimentRecord.getTissueId(),experimentRecord.getCellType());
        return list.isEmpty() ? 0 : list.get(0).getExperimentRecordId();
    }
    public void addTargetTissue( List<Long> experimentRecordIds) throws Exception{

        String sql = "update experiment_record set is_target_tissue=1 "+
                " where experiment_record_id in (";
        String ids=  experimentRecordIds.stream().map(Object::toString).collect(Collectors.joining(","));

        sql=sql+ ids +")";
     //   System.out.println("SQL:"+sql);
        update(sql);

    }
    public void deleteTargetTissue(long experimentId) throws Exception{

        String sql = "update experiment_record set is_target_tissue=null"+
                " where experiment_id=?";
     //   System.out.println("SQL DELETE:"+ sql);
       update(sql, experimentId);

    }
    public void updateTargetTissue(long experimentId, List<Long> experimentRecordIds) throws Exception{

        deleteTargetTissue(experimentId);
        addTargetTissue(experimentRecordIds);

    }
}
