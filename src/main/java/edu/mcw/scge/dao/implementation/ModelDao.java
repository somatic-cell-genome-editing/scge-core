package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.EditorQuery;
import edu.mcw.scge.dao.spring.ModelQuery;
import edu.mcw.scge.datamodel.Editor;
import edu.mcw.scge.datamodel.Model;

import java.util.List;

public class ModelDao extends AbstractDAO {
    public List<Model> getModels() throws Exception {
        String sql="select * from model";
        ModelQuery q=new ModelQuery(this.getDataSource(), sql);
        List<Model> models=execute(q);
        return models;
    }

    public Model getModelById(int modelId) throws Exception {
        String sql="select * from model where model_id=?";
        ModelQuery q=new ModelQuery(this.getDataSource(), sql);
        List<Model> models=execute(q, modelId);
        if(models!=null && models.size()>0)
            return models.get(0);
        else
            return new Model();
    }

    public int insertModel(Model model) throws Exception{

        String sql = "insert into model ( model_id, type, name, organism, sex, rrid, source, transgene, subtype, annotated_map," +
                "transgene_description, transgene_reporter,model_description,parental_origin,strain_code,strain_alias )" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int modelId = this.getNextKeyFromSequence("model_seq");


        update(sql, modelId, model.getType(), model.getName(), model.getOrganism(),model.getSex(), model.getRrid(),
                model.getSource(),model.getTransgene(),model.getSubtype(),model.getAnnotatedMap(),
                model.getTransgeneDescription(),model.getTransgeneReporter(),model.getDescription(),model.getParentalOrigin(),
                model.getStrainCode(),model.getStrainAlias());

        return modelId;
    }

    public int getModelId(Model model) throws Exception {

        String sql = "select * from model where type =? and name=? and organism=?";

        List<Model> list = ModelQuery.execute(this,sql,model.getType(), model.getName(), model.getOrganism() );
        return list.isEmpty() ? 0 : list.get(0).getModelId();
    }
    public boolean verifyModelAccess(int modelId, int personId) throws Exception {
        String sql="(select e.* from model e left outer join experiment_record r on e.model_id=r.model_id " +
                "left outer join experiment x on x.experiment_id=r.experiment_id " +
                "left outer join study s on s.study_id =x.study_id " +
                "left outer join person_info p on p.group_id=s.group_id " +
                "where p.person_id=? and e.model_id=? ) union " +
                "(select ed.* from model ed  where ed.tier=4 and ed.model_id=?)";
        ModelQuery q=new ModelQuery(this.getDataSource(), sql);
        List<Model> modelList= execute(q, personId, modelId, modelId);
        return modelList.size() > 0;
    }
    public void updateModelTier(int tier, int modelId) throws Exception{
        String sql="update model set tier=? where model_id=?";
        update(sql, tier, modelId);
    }
}
