package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentRecordQuery;
import edu.mcw.scge.dao.spring.StringMapQuery;
import edu.mcw.scge.datamodel.ExperimentRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExperimentRecordDao extends AbstractDAO {

    public List<ExperimentRecord> getExperimentRecordsByStudyId(int studyId) throws Exception {
        String sql="select s.study, r.*, app.*, e.symbol, d.ds_type, m.display_name as modelName,h.lab_id as hrdonorName, x.type from study s join experiment x on (s.study_id=x.study_id) \n" +
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
    public List<ExperimentRecord> getExperimentRecordById(long expRecId) throws Exception {
        String sql="select s.study, r.*, e.symbol, d.ds_type, m.display_name as modelName, g.guide, h.lab_id as hrdonorName, x.type" +
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

	public long insertExperimentRecord(ExperimentRecord r) throws Exception{

        String sql = "insert into experiment_record (experiment_id,name,study_id, " +
                "editor_id,ds_id,model_id,hrdonor_id,application_method_id,experiment_record_id,age, genotype,sex," +
                "tissue_id, cell_type,organ_system,qualifier,time_point ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        long experimentId = this.getNextKeyFromSequenceLong("experiment_seq");
        
        update(sql, r.getExperimentId(), r.getExperimentName(), r.getStudyId(), r.getEditorId(), r.getDeliverySystemId(),
                r.getModelId(), r.getHrdonorId(), r.getApplicationMethodId(), experimentId, r.getAge(), r.getGenotype(),
                r.getSex(), r.getTissueId(), r.getCellType(), r.getOrganSystemID(), r.getQualifier(), r.getTimePoint());

        return experimentId;
    }

    public long getExpRecordId(ExperimentRecord r) throws Exception {
        String sql = "SELECT MAX(experiment_record_id) FROM experiment_record WHERE name=? AND study_id=? AND editor_id=? AND ds_id=? "+
                "AND model_id=? AND application_method_id=? AND experiment_id=? "+
                // handle NULLs or blanks in columns 'sex','tissue_id','cell_type'
                "AND COALESCE(sex,'') = COALESCE(?,'') "+
                "AND COALESCE(tissue_id,'') = COALESCE(?,'') "+
                "AND COALESCE(cell_type,'') = COALESCE(?,'') "+
                "AND COALESCE(organ_system,'') = COALESCE(?,'') "+
                "AND COALESCE(qualifier,'') = COALESCE(?,'') "+
                "AND COALESCE(time_point,'') = COALESCE(?,'')";
        return getLongCount(sql, r.getExperimentName(), r.getStudyId(), r.getEditorId(), r.getDeliverySystemId(),
                r.getModelId(), r.getApplicationMethodId(), r.getExperimentId(), r.getSex(), r.getTissueId(), r.getCellType(),
                r.getOrganSystemID(), r.getQualifier(), r.getTimePoint());
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
        if(experimentRecordIds.size()>0)
            addTargetTissue(experimentRecordIds);
    }


    /////////////////
    ///// to handle EXPERIMENT_DETAILS table
    public Map<String,String> getExperimentRecordDetails(long expRecId) throws Exception {
        String sql = "SELECT name,value FROM experiment_details WHERE experiment_record_id=?";
        List<StringMapQuery.MapPair> list = StringMapQuery.execute(this, sql, expRecId);
        Map<String,String> details = new HashMap<>();
        for( StringMapQuery.MapPair pair: list ) {
            details.put(pair.keyValue, pair.stringValue);
        }
        return details;
    }

    public void insertExperimentRecordDetails(long expRecId, String name, String value) throws Exception {
        String sql = "INSERT INTO experiment_details (experiment_record_id,name,value) VALUES(?,?,?)";
        update(sql, expRecId, name, value);
    }

    public void updateExperimentRecordDetails(long expRecId, String name, String value) throws Exception {
        String sql = "UPDATE experiment_details SET value=? WHERE experiment_record_id=? AND name=?";
        update(sql, value, expRecId, name);
    }

    public void deleteExperimentRecordDetails(long expRecId, String name) throws Exception {
        String sql = "DELETE experiment_details WHERE experiment_record_id=? AND name=?";
        update(sql, expRecId, name);
    }
}
