package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentQuery;
import edu.mcw.scge.dao.spring.ExperimentRecordQuery;
import edu.mcw.scge.dao.spring.StringListQuery;
import edu.mcw.scge.dao.spring.VectorQuery;
import edu.mcw.scge.datamodel.Experiment;
import edu.mcw.scge.datamodel.ExperimentRecord;

import java.util.List;

public class ExperimentDao extends AbstractDAO {

    public List<String> getExperimentRecordTissueList(long experimentId) throws Exception {
        String sql="select distinct ot.term from experiment_record ex " +
                "inner join ont_terms ot on ex.tissue_id=ot.term_acc " +
                "where ex.experiment_id=? order by ot.term";

        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public List<String> getExperimentRecordCellTypeList(long experimentId) throws Exception {
        String sql="select distinct ot.term from experiment_record ex " +
                "inner join ont_terms ot on ex.cell_type=ot.term_acc " +
                "where ex.experiment_id=? order by ot.term";

        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public List<String> getExperimentRecordConditionList(long experimentId) throws Exception {
        String sql="select distinct ex.name from experiment_record ex " +
                " where ex.experiment_id=? order by name";

        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public List<String> getExperimentRecordEditorList(long experimentId) throws Exception {
        String sql="select distinct e.symbol from experiment_record ex " +
                " inner join experiment x on x.experiment_id=ex.experiment_id " +
                " inner join editor e on ex.editor_id = e.editor_id " +
                " where ex.experiment_id=? order by e.symbol";

        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public List<String> getExperimentRecordVectorList(long experimentId) throws Exception {
        String sql="select distinct v.name from vector v, experiment e, experiment_record er, vector_associations va " +
                "     where e.experiment_id=? and e.experiment_id=er.experiment_id and va.vector_id=v.vector_id and va.experiment_record_id=er.experiment_record_id order by v.name";
        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public List<String> getExperimentRecordGuideList(long experimentId) throws Exception {
        String sql="select distinct g.guide from guide g, experiment e, experiment_record er, guide_associations ga " +
                "     where e.experiment_id=? and e.experiment_id=er.experiment_id and ga.guide_id=g.guide_id and ga.experiment_record_id=er.experiment_record_id order by g.guide ";
        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }
    public List<String> getExperimentRecordGuideTargetLocusList(long experimentId) throws Exception {
        String sql="select distinct gi.target_locus from guide g, experiment e, experiment_record er, guide_associations ga,genome_info gi " +
                "     where g.guide_id = gi.genome_id and e.experiment_id=? and e.experiment_id=er.experiment_id and ga.guide_id=g.guide_id " +
                "and ga.experiment_record_id=er.experiment_record_id order by gi.target_locus ";
        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }
    public List<String> getExperimentRecordModelList(long experimentId) throws Exception {
        String sql="select distinct m.name from experiment_record ex " +
                " inner join model m on ex.model_id = m.model_id " +
                " where ex.experiment_id=? order by m.name";

        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }
    public List<String> getExperimentRecordDeliverySystemList(long experimentId) throws Exception {
        String sql="select distinct d.ds_name from experiment_record ex " +
                "inner join delivery_system d on ex.ds_id = d.ds_id " +
                "where ex.experiment_id=? order by d.ds_name";

        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        List<String> returnVal = execute(q, experimentId);
        System.out.println("size = " + returnVal.size());

        return returnVal;
    }
    public List<String> getExperimentRecordSexList(long experimentId) throws Exception {
        String sql="select distinct ex.sex from experiment_record ex " +
                "where ex.experiment_id=? and ex.sex is not null and ex.sex != ''";

        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        List<String> returnVal = execute(q, experimentId);

        return returnVal;
    }
    public List<String> getExperimentRecordHrdonorList(long experimentId) throws Exception {
        String sql="select distinct h.lab_id from experiment_record ex " +
                " inner join experiment x on x.experiment_id=ex.experiment_id " +
                " inner join hr_donor h on ex.hrdonor_id = h.hrdonor_id " +
                " where ex.experiment_id=? order by h.lab_id";

        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }
    public List<ExperimentRecord> getExperimentRecords(long experimentId) throws Exception {
        String sql="select ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName,h.lab_id as hrdonorName, x.type, ot.term, ct.term as cellTerm, a.*  from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join application_method a on ex.application_method_id = a.application_method_id " +

                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join hr_donor h on (h.hrdonor_id= ex.hrdonor_id) \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                "left outer join ont_terms ct on ex.cell_type=ct.term_acc " +
                "where ex.experiment_id=? order by ex.record_order";

        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }

    public List<ExperimentRecord> getAllExperimentRecords() throws Exception {
        String sql="select ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName, h.lab_id as hrdonorName,x.type, ot.term, ct.term as cellTerm from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join hr_donor h on (h.hrdonor_id= ex.hrdonor_id) \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                "left outer join ont_terms ct on ex.cell_type=ct.term_acc " +
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
    public Experiment getExperiment(long experimentId) throws Exception {
        String sql="select ex.* from experiment ex " +

                "where ex.experiment_id=?";

        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return (Experiment) execute(q, experimentId).get(0);

    }

    public List<ExperimentRecord> getExperimentsByEditor(long editorId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName,h.lab_id as hrdonorName, x.type, ot.term, ct.term as cellTerm from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join hr_donor h on (h.hrdonor_id= ex.hrdonor_id) \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                "left outer join ont_terms ct on ex.cell_type=ct.term_acc " +
                // "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
               // "left outer join vector v on ex.vector_id = v.vector_id " +
                "where ex.editor_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, editorId);
    }
    public List<ExperimentRecord> getExperimentsByEditorNExperiment(long editorId, long experimentId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName,h.lab_id as hrdonorName, x.type, ot.term, ct.term as cellTerm from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join hr_donor h on (h.hrdonor_id= ex.hrdonor_id) \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                "left outer join ont_terms ct on ex.cell_type=ct.term_acc " +
                // "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join study s on ex.study_id = s.study_id " +
                // "left outer join vector v on ex.vector_id = v.vector_id " +
                "where ex.editor_id=?" +
                " and ex.experiment_id=? ";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, editorId, experimentId);
    }

    public List<ExperimentRecord> getExperimentsByModel(long modelId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName, h.lab_id as hrdonorName,x.type, ot.term, ct.term as cellTerm from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join hr_donor h on (h.hrdonor_id= ex.hrdonor_id) \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                // "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join ont_terms ct on ex.cell_type=ct.term_acc " +
                "left outer join study s on ex.study_id = s.study_id " +
               // "left outer join vector v on ex.vector_id = v.vector_id " +
                "where ex.model_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, modelId);
    }

    public List<ExperimentRecord> getExperimentsByDeliverySystem(long dsId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName,h.lab_id as hrdonorName, x.type, ot.term, ct.term as cellTerm from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join hr_donor h on (h.hrdonor_id= ex.hrdonor_id) \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                //  "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join ont_terms ct on ex.cell_type=ct.term_acc " +
                "left outer join study s on ex.study_id = s.study_id " +
               // "left outer join vector v on ex.vector_id = v.vector_id " +
                "where ex.ds_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, dsId);
    }

    public List<ExperimentRecord> getExperimentsByHrdonor(long hrdonorId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName,h.lab_id as hrdonorName, x.type, ot.term, ct.term as cellTerm from experiment_record ex " +
                " left outer join experiment x on x.experiment_id=ex.experiment_id " +
                "left outer join editor e on ex.editor_id = e.editor_id " +
                "left outer join delivery_system d on ex.ds_id = d.ds_id " +
                "left outer join model m on ex.model_id = m.model_id " +
                "left outer join hr_donor h on (h.hrdonor_id= ex.hrdonor_id) \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                //  "left outer join guide g on ex.guide_id = g.guide_id " +
                "left outer join ont_terms ct on ex.cell_type=ct.term_acc " +
                "left outer join study s on ex.study_id = s.study_id " +
                // "left outer join vector v on ex.vector_id = v.vector_id " +
                "where ex.hrdonor_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, hrdonorId);
    }

    public List<ExperimentRecord> getExperimentsByVector(long vectorId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName,h.lab_id as hrdonorName, x.type, ot.term, ct.term as cellTerm from experiment_record ex \n" +
                "left outer join experiment x on x.experiment_id=ex.experiment_id \n" +
                "left outer join editor e on ex.editor_id = e.editor_id \n" +
                "left outer join delivery_system d on ex.ds_id = d.ds_id \n" +
                "left outer join model m on ex.model_id = m.model_id \n" +
                "left outer join hr_donor h on (h.hrdonor_id= ex.hrdonor_id) \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                "left outer join ont_terms ct on ex.cell_type=ct.term_acc " +
                "left outer join study s on ex.study_id = s.study_id \n" +
                "left outer join vector_associations va on ex.experiment_record_id = va.experiment_record_id \n" +
                "where va.vector_id=?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, vectorId);
    }

    public List<ExperimentRecord> getExperimentsByGuide(long guideId) throws Exception {
        String sql="select s.study, ex.*, e.symbol, d.ds_type, d.ds_name, m.name as modelName,h.lab_id as hrdonorName, x.type, ot.term, ct.term as cellTerm from experiment_record ex " +
                "inner join guide_associations ga on ex.experiment_record_id=ga.experiment_record_id \n" +
                //"inner join guide g on ga.guide_id = g.guide_id\n" +
                "inner join experiment x on x.experiment_id=ex.experiment_id " +
                "inner join editor e on ex.editor_id = e.editor_id \n" +
                "inner join delivery_system d on ex.ds_id = d.ds_id " +
                "inner join model m on ex.model_id = m.model_id \n" +
                "left outer join hr_donor h on (h.hrdonor_id= ex.hrdonor_id) \n" +
                "left outer join ont_terms ot on ex.tissue_id=ot.term_acc " +
                "left outer join ont_terms ct on ex.cell_type=ct.term_acc " +
                //    "inner join vector v on ex.vector_id = v.vector_id " +
                "inner join study s on ex.study_id = s.study_id and ga.guide_id = ?";
        ExperimentRecordQuery q=new ExperimentRecordQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }

    public List<Experiment> getAllExperiments() throws Exception{
        String sql="select * from experiment" ;
        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return execute(q);
    }
	
	public void insertExperiment(Experiment experiment) throws Exception{

        String sql = "insert into experiment (experiment_id,name,study_id, " +
                "type  ) values (?,?,?,?)";

        long experimentId = this.getNextKeyFromSequence("experiment_seq");
        
        update(sql, experimentId,experiment.getName(),experiment.getStudyId(),experiment.getType());
    }

}
