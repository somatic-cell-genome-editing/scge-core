package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentResultDetailQuery;
import edu.mcw.scge.dao.spring.StringListQuery;
import edu.mcw.scge.datamodel.ExperimentResultDetail;

import java.util.List;

public class ExperimentResultDao extends AbstractDAO {
    public List<ExperimentResultDetail> getResultsByExperimentRecId(int expRecId) throws Exception {
        String sql="select r.*,d.replicate,d.result from experiment_result r inner join experiment_result_detail d " +
                "on r.result_id = d.result_id where r.experiment_record_id=? ";
        ExperimentResultDetailQuery q=new ExperimentResultDetailQuery(this.getDataSource(), sql);
        return execute(q, expRecId);
    }

    public List<ExperimentResultDetail> getResultsByExpResType(int expRecId,String resultType) throws Exception {
        String sql="select r.*,d.replicate,d.result from experiment_result r inner join experiment_result_detail d " +
                "on r.result_id = d.result_id where r.experiment_record_id=? and r.result_type = ?";
        ExperimentResultDetailQuery q=new ExperimentResultDetailQuery(this.getDataSource(), sql);
        return execute(q, expRecId,resultType);
    }

    public List<String> getResTypeByExpId(int expId) throws Exception {
        String sql="select distinct(r.result_type) from experiment_result r inner join experiment_record e " +
                "on e.experiment_record_id = r.experiment_record_id where e.experiment_id = ?";
        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, expId);
    }

	public int insertExperimentResult(ExperimentResultDetail expResult) throws Exception{

        String sql = "insert into experiment_result (result_id, number_of_samples, units," +
                " assay_description, experiment_record_id, result_type,edit_type  )" +
                " values (?,?,?,?,?,?,?)";
        
        int resultId = this.getNextKeyFromSequence("result_seq");
        
        update(sql, resultId,
                expResult.getNumberOfSamples(),expResult.getUnits(),expResult.getAssayDescription(),
                expResult.getExperimentRecordId(),expResult.getResultType(),expResult.getEditType());

        return resultId;
    }

    public void insertExperimentResultDetail(ExperimentResultDetail expDetail) throws Exception{

        String sql = "insert into experiment_result_detail (result_id,replicate,result) values (?,?,?)";

        update(sql, expDetail.getResultId(),expDetail.getReplicate(),expDetail.getResult());
    }
}
