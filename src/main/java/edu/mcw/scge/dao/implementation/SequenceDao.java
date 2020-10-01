package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.StringListQuery;

import java.util.List;

public class SequenceDao extends AbstractDAO {
    public String getSequecneById(int id) throws Exception {
        String sql="select sequence from sequence where sequence_id=?";
        StringListQuery q= new StringListQuery(this.getDataSource(), sql);
        List<String> sequences=execute(q, id);
        if(sequences!=null){
            return sequences.get(0);
        }else return "";
    }
}
