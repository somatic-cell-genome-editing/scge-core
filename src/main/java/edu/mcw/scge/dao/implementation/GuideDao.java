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
    public List<Guide> getGuides() throws Exception {
        String sql="select * from guide";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q);
    }
    public List<Guide> getGuidesByEditor(int editorId) throws Exception {
        String sql="select g.* from experiment e, guide g where e.guide_id=g.guide_id and e.editor_id=?";
        GuideQuery q= new GuideQuery(this.getDataSource(), sql);
        return execute(q,editorId);
    }



}
