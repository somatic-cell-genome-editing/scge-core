package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentResultDetailQuery;
import edu.mcw.scge.datamodel.ExperimentResultDetail;

import java.util.List;

public class ExperimentResultDao extends AbstractDAO {
    public List<ExperimentResultDetail> getResultsByExperimentRecId(int expRecId) throws Exception {
        String sql="select r.*,d.replicate,d.result from experiment_result r inner join experiment_result_detail d " +
                "on r.result_id = d.result_id where r.experiment_record_id=? ";
        ExperimentResultDetailQuery q=new ExperimentResultDetailQuery(this.getDataSource(), sql);
        return execute(q, expRecId);
    }

	public int insertExperimentResult(ExperimentResultDetail expResult) throws Exception{

        String sql = "insert into experiment_result (result_id, tissue_id, cell_type," +
                "number_of_samples, units, assay_description, experiment_record_id, result_type  )" +
                " values (?,?,?,?,?,?,?,?)";
        
        int resultId = this.getNextKeyFromSequence("result_seq");
        
        update(sql, resultId,expResult.getTissueId(),expResult.getCellType(),
                expResult.getNumberOfSamples(),expResult.getUnits(),expResult.getAssayDescription(),
                expResult.getExperimentRecordId(),expResult.getResultType());

        return resultId;
    }

    public void insertExperimentResultDetail(ExperimentResultDetail expDetail) throws Exception{

        String sql = "insert into experiment_result_detail (result_id,replicate,result) values (?,?,?)";

        update(sql, expDetail.getResultId(),expDetail.getReplicate(),expDetail.getResult());
    }
}
