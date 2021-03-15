package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.StringListQuery;
import edu.mcw.scge.datamodel.*;

import java.util.Date;
import java.util.List;

/**
 * Created by jthota on 9/10/2019.
 */
public class AccessDao extends AbstractDAO {
    public int getAccessId(String accessLevel) throws Exception {
        String sql="select access_id from experiment_access where access=?";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        return (int) execute(q, accessLevel).get(0);
    }

    public boolean verifyEditorAccess(Editor e, Person p) throws Exception {
        if (e.getTier() == 3 || e.getTier()==4) {
            return true;
        }

        String sql = "select distinct er.editor_id from person_info pi, study s, experiment_record er where pi.person_id=? and er.editor_id=? and pi.group_id=s.group_id and er.study_id=s.study_id and s.tier=1 or s.tier=2";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        List<Integer> found = execute(q,p.getId(),e.getId());

        System.out.println("running sql " + sql);

        if (found.size()>0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean verifyModelAccess(Model m, Person p) throws Exception {
        if (m.getTier() == 3 || m.getTier()==4) {
            return true;
        }

        String sql = "select distinct er.model_id from person_info pi, study s, experiment_record er where pi.person_id=? and er.model_id=? and pi.group_id=s.group_id and er.study_id=s.study_id and s.tier=1 or s.tier=2";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        List<Integer> found = execute(q,p.getId(),m.getModelId());

        System.out.println("running sql " + sql);

        if (found.size()>0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean verifyDeliveryAccess(Delivery d, Person p) throws Exception {
        if (d.getTier() == 3 || d.getTier()==4) {
            return true;
        }

        String sql = "select distinct er.ds_id from person_info pi, study s, experiment_record er where pi.person_id=? and er.ds_id=? and pi.group_id=s.group_id and er.study_id=s.study_id and s.tier=1 or s.tier=2";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        List<Integer> found = execute(q,p.getId(),d.getId());

        System.out.println("running sql " + sql);

        if (found.size()>0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean verifyVectorAccess(Vector v, Person p) throws Exception {
        if (v.getTier() == 3 || v.getTier()==4) {
            return true;
        }

        String sql = "select distinct er.vector_id from person_info pi, study s, experiment_record er where pi.person_id=? and er.vector_id=? and pi.group_id=s.group_id and er.study_id=s.study_id and s.tier=1 or s.tier=2";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        List<Integer> found = execute(q,p.getId(),v.getVectorId());

        System.out.println("running sql " + sql);

        if (found.size()>0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean verifyGuideAccess(Guide g, Person p) throws Exception {
        if (g.getTier() == 3 || g.getTier()==4) {
            return true;
        }

        String sql = "select distinct er.guide_id from person_info pi, study s, experiment_record er where pi.person_id=? and er.guide_id=? and pi.group_id=s.group_id and er.study_id=s.study_id and s.tier=1 or s.tier=2";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        List<Integer> found = execute(q,p.getId(),g.getGuide_id());

        System.out.println("running sql " + sql);

        if (found.size()>0) {
            return true;
        }else {
            return false;
        }
    }

    public int insertAccessRequest(String firstName, String lastName, String googleEmail, String institution, String institutionalEmail, String pi) throws Exception {

        String sql = "insert into access_request (first_name, last_name,google_email, institution, institutional_email, pi, date) \n" +
                " values (?,?,?,?,?,?,?)";

        update(sql, firstName,lastName,googleEmail,institution,institutionalEmail,pi,new Date());

        return 1;
    }


}
