package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.CountQuery;
import edu.mcw.scge.dao.spring.EditorQuery;
import edu.mcw.scge.datamodel.Editor;

import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class StatsDao extends AbstractDAO {
    public Integer getStudyCount() throws Exception {
        String sql="select count(*) from study";
        CountQuery q=new CountQuery(this.getDataSource(), sql);
        return (Integer) q.execute().get(0);
    }

    public Integer getStudyCount(int tier) throws Exception {
        String sql="select count(*) from study where tier = ?";
        CountQuery q=new CountQuery(this.getDataSource(), sql);
        return (Integer) q.execute(tier).get(0);
    }

    public Integer getExperimentCount() throws Exception {
        String sql="select count(*) from experiment";
        CountQuery q=new CountQuery(this.getDataSource(), sql);
        return (Integer) q.execute().get(0);
    }

    public Integer getModelCount() throws Exception {
        String sql="select count(*) from model";
        CountQuery q=new CountQuery(this.getDataSource(), sql);
        return (Integer) q.execute().get(0);
    }

    public Integer getEditorCount() throws Exception {
        String sql="select count(*) from editor";
        CountQuery q=new CountQuery(this.getDataSource(), sql);
        return (Integer) q.execute().get(0);
    }

    public Integer getDeliveryCount() throws Exception {
        String sql="select count(*) from delivery_system";
        CountQuery q=new CountQuery(this.getDataSource(), sql);
        return (Integer) q.execute().get(0);
    }

    public Integer getGuideCount() throws Exception {
        String sql="select count(*) from guide";
        CountQuery q=new CountQuery(this.getDataSource(), sql);
        return (Integer) q.execute().get(0);
    }

    public Integer getVectorCount() throws Exception {
        String sql="select count(*) from vector";
        CountQuery q=new CountQuery(this.getDataSource(), sql);
        return (Integer) q.execute().get(0);
    }

}
