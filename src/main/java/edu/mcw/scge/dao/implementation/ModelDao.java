package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ModelQuery;
import edu.mcw.scge.datamodel.Model;

import java.util.List;

public class ModelDao extends AbstractDAO {
    public Model getModelById(int modelId) throws Exception {
        String sql="select * from model where model_id=?";
        ModelQuery q=new ModelQuery(this.getDataSource(), sql);
        List<Model> models=execute(q, modelId);
        if(models!=null && models.size()>0)
            return models.get(0);
        else
            return new Model();
    }

}
