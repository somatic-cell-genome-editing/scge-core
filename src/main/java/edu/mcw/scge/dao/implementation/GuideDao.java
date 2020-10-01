package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.GuideQuery;
import edu.mcw.scge.datamodel.Guide;

import java.util.List;

public class GuideDao extends AbstractDAO {
    public List<Guide> getGuideById(int guideId) throws Exception {
        String sql="select * from guide where guide_id=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }
}
