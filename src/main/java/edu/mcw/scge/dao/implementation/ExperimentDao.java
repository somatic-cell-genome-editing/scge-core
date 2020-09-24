package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.EditorQuery;
import edu.mcw.scge.dao.spring.ExperimentQuery;
import edu.mcw.scge.datamodel.Editor;
import edu.mcw.scge.datamodel.Experiment;

import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class ExperimentDao extends AbstractDAO {
    public List<Experiment> getAllExperiments() throws Exception {
        String sql="select * from experiment";
        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return (List<Experiment>)q.execute();
    }
}
