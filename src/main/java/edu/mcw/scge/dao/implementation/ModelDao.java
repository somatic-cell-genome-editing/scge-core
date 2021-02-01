package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ModelQuery;
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

        String sql = "insert into model ( model_id, type, name, organism, age, sex, genotype, stock_number," +
                "shot_name, age_range, rrid, source, transgene, subtype, annotated_map," +
                "transgene_description, transgene_reporter, reporter_db_ids )" +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int modelId = this.getNextKeyFromSequence("model_seq");


        update(sql, modelId, model.getType(), model.getName(), model.getOrganism(),model.getAge(),model.getSex(),
                model.getGenotype(),model.getStockNumber(),model.getShortName(),model.getAgeRange(),model.getRrid(),
                model.getSource(),model.getTransgene(),model.getSubtype(),model.getAnnotatedMap(),
                model.getTransgeneDescription(),model.getTransgeneReporter(),model.getReporterDbIds());

        return modelId;
    }

    public int getModelId(Model model) throws Exception {

        String sql = "select * from model where type =? and name=? and organism=?";

        List<Model> list = ModelQuery.execute(this,sql,model.getType(), model.getName(), model.getOrganism() );
        return list.isEmpty() ? 0 : list.get(0).getModelId();
    }
}
