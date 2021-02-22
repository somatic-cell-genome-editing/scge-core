package edu.mcw.scge.dao.spring;

import edu.mcw.scge.datamodel.AnimalTestingResultsSummary;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalTestingResultsSummaryQuery extends MappingSqlQuery {
    public AnimalTestingResultsSummaryQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        AnimalTestingResultsSummary s=new AnimalTestingResultsSummary();
        s.setExpRecId(rs.getInt("exp_rec_id"));
        s.setSummaryResultsId((rs.getInt("summary_results_id")));

       s.setNumberOfSamples(rs.getInt("number_of_samples"));

       s.setTissueId(rs.getInt("tissue_id"));
        s.setSignal(rs.getString("signal"));
       s.setSignalPresent(rs.getString("signal_present"));
        s.setSignalDescription(rs.getString("signal_description"));
        s.setImageLink(rs.getString("image_link"));
        s.setPercentCellsInROIWithSginal(rs.getString("percent_cells_in_roi_with_sginal"));
        s.setRoi(rs.getString("roi"));
        s.setROICoordinates(rs.getString("roi_coordinates"));
        s.setTissueTerm(rs.getString("tissue_term").trim());
        s.setParentTissueTerm(rs.getString("parent_tissue_term"));
        s.setSystem(rs.getString("tissue_system"));


        return s;
    }
}
