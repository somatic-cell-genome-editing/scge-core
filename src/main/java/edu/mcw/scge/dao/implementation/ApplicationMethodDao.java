package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ApplicationMethodQuery;
import edu.mcw.scge.datamodel.ApplicationMethod;

import java.util.List;

public class ApplicationMethodDao extends AbstractDAO {
    public List<ApplicationMethod> getApplicationMethod(int applicationId) throws Exception{
        String sql="select * from application_method where application_id=?";
        ApplicationMethodQuery q=new ApplicationMethodQuery(this.getDataSource(), sql);
        return execute(q, applicationId);
    }
}
