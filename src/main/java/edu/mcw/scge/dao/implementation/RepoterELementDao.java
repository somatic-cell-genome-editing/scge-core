package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ReporterElementQuery;
import edu.mcw.scge.datamodel.ReporterElement;

import java.util.List;

public class RepoterELementDao extends AbstractDAO {
    public List<ReporterElement> getReporterElements(int experimentRecordId) throws Exception {
        String sql="select * from reporter_element where experiment_record_id=?";
        ReporterElementQuery query=new ReporterElementQuery(this.getDataSource(), sql);
        return execute(query, experimentRecordId);
    }
}
