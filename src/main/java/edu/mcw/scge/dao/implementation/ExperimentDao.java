package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentQuery;
import edu.mcw.scge.dao.spring.ExperimentRecordQuery;
import edu.mcw.scge.datamodel.Experiment;
import edu.mcw.scge.datamodel.ExperimentRecord;

import java.util.List;

public class ExperimentDao extends AbstractDAO {

    public List<ExperimentRecord> getExperimentRecords(int experimentId) throws Exception {
        String sql="select ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName, x.type, ot.term from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
              //  "left outer join guide g on ex.guide_id = g.guide_id " +
              //  "left outer join vector v on ex.vector_id = v.vector_id " +
                "where ex.experiment_id=?";

        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public List<ExperimentRecord> getAllExperimentRecords() throws Exception {
        String sql="select ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName, x.type, ot.term from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
              //  "left outer join guide g on ex.guide_id = g.guide_id " +
              //  "left outer join vector v on ex.vector_id = v.vector_id " +
                "inner join experiment_result r on ex.experiment_record_id = r.experiment_record_id";

        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q);
    }
    public List<Experiment> getExperimentsByStudy(int studyId) throws Exception {
        String sql="select * from experiment where study_id=?";

        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return execute(q, studyId);
    }
    public List<Experiment> getExperimentsByStudy(int studyId, int personId) throws Exception {
        String sql="select * from experiment e  " +
                "                left outer join study s  on s.study_id=e.study_id " +
                "                left outer join scge_group g on g.group_id=s.group_id  " +
                "                left outer join person_info i on i.group_id=g.group_id  " +
                "                left outer join person p on p.person_id=i.person_id  " +
                "                where e.study_id=? " +
                "                 and p. person_id=? ";

        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return execute(q, studyId, personId);
    }
    public Experiment getExperiment(int experimentId) throws Exception {
        String sql="select ex.* from experiment ex " +

                "where ex.experiment_id=?";

        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return (Experiment) execute(q, experimentId).get(0);

    }

    public List<ExperimentRecord> getExperimentsByEditor(int editorId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName, x.type, ot.term from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                // "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
               // "left outer join vector v on ex.vector_id = v.vector_id " +
                "where ex.editor_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, editorId);
    }

    public List<ExperimentRecord> getExperimentsByModel(int modelId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName, x.type, ot.term from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                // "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
               // "left outer join vector v on ex.vector_id = v.vector_id " +
                "where ex.model_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, modelId);
    }

    public List<ExperimentRecord> getExperimentsByDeliverySystem(int dsId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName, x.type, ot.term from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                //  "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
               // "left outer join vector v on ex.vector_id = v.vector_id " +
                "where ex.ds_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, dsId);
    }

    public List<ExperimentRecord> getExperimentsByVector(int vectorId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName, x.type, ot.term from experiment_record ex \n" +
                "left outer join experiment x on x.experiment_id=ex.experiment_id \n" +
                "left outer join editor e on ex.editor_id = e.editor_id \n" +
                "left outer join delivery_system d on ex.ds_id = d.ds_id \n" +
                "left outer join model m on ex.model_id = m.model_id \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                "left outer join study s on ex.study_id = s.study_id \n" +
                "left outer join vector_associations va on ex.experiment_record_id = va.experiment_record_id \n" +
                "where va.vector_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, vectorId);
    }

    public List<ExperimentRecord> getExperimentsByGuide(int guideId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName, x.type, ot.term from experiment_record ex " +
                "inner join guide_associations ga on ex.experiment_record_id=ga.experiment_record_id \n" +
                //"inner join guide g on ga.guide_id = g.guide_id\n" +
                "inner join experiment x on x.experiment_id=ex.experiment_id " +
                "inner join editor e on ex.editor_id = e.editor_id \n" +
                "inner join delivery_system d on ex.ds_id = d.ds_id " +
                "inner join model m on ex.model_id = m.model_id \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                //    "inner join vector v on ex.vector_id = v.vector_id " +
                "inner join study s on ex.study_id = s.study_id and ga.guide_id = ?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }

    public List<Experiment> getAllExperiments() throws Exception{
        String sql="select * from experiment" ;
        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return q.execute();
    }
	
	public void insertExperiment(Experiment experiment) throws Exception{

        String sql = "insert into experiment (experiment_id,name,study_id, " +
                "type  ) values (?,?,?,?)";

        int experimentId = this.getNextKeyFromSequence("experiment_seq");
        
        update(sql, experimentId,experiment.getName(),experiment.getStudyId(),experiment.getType());
    }

}
