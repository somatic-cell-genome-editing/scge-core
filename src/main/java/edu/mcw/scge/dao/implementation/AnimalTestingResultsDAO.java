package edu.mcw.scge.dao.implementation;


import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.AnimalTestingResultsDetailsQuery;
import edu.mcw.scge.dao.spring.AnimalTestingResultsSummaryQuery;
import edu.mcw.scge.datamodel.AnimalTestingResultsSummary;
import edu.mcw.scge.datamodel.Sample;

import java.util.List;

public class AnimalTestingResultsDAO extends AbstractDAO {
  public List<AnimalTestingResultsSummary> getResultsByExperimentRecId(int expRecId) throws Exception {
      String sql="select r.*, t.* from satc_experiment_record r, tissue t where r.exp_rec_id=? and " +
              " r.tissue_id=t.tissue_id ";
      AnimalTestingResultsSummaryQuery  q=new AnimalTestingResultsSummaryQuery(this.getDataSource(), sql);
      return execute(q, expRecId);
  }
    public List<Sample> getSampleDetailsByResultId(int summaryResultsId, int experimentRecordId) throws Exception {
        String sql="Select * from satc_experiment_record_detail d where summary_results_id=? and exp_rec_id=? ";
        AnimalTestingResultsDetailsQuery q=new AnimalTestingResultsDetailsQuery(this.getDataSource(), sql);
        return execute(q, summaryResultsId, experimentRecordId);
    }
}
