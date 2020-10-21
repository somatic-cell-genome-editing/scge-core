package edu.mcw.scge.dao.implementation;


import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.AnimalTestingResultsSummaryQuery;
import edu.mcw.scge.datamodel.AnimalTestingResultsSummary;

import java.util.List;

public class AnimalTestingResultsDAO extends AbstractDAO {
  public List<AnimalTestingResultsSummary> getResultsByExperimentRecId(int expRecId) throws Exception {
      String sql="select r.*, t.* from satc_results_summary r, tissue t where r.exp_rec_id=? and " +
              " r.tissue_id=t.tissue_id ";
      AnimalTestingResultsSummaryQuery  q=new AnimalTestingResultsSummaryQuery(this.getDataSource(), sql);
      return execute(q, expRecId);
  }
}
