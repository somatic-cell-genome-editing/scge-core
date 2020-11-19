package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ExperimentQuery;
import edu.mcw.scge.datamodel.Experiment;

import java.util.List;

public class ExperimentDao extends AbstractDAO {
    public List<Experiment> getExperimentsByStudy(int studyId) throws Exception {
        String sql="select * from experiment where study_id=?";
        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return execute(q, studyId);
    }
    public Experiment getExperiment(int experimentId) throws Exception {
        String sql="select * from experiment where experiment_id=?";
        ExperimentQuery q=new ExperimentQuery(this.getDataSource(), sql);
        return (Experiment) execute(q, experimentId).get(0);

    }
}
