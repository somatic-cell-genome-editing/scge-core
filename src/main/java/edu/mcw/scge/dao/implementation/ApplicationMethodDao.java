package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ApplicationMethodQuery;
import edu.mcw.scge.datamodel.ApplicationMethod;

import java.util.List;

public class ApplicationMethodDao extends AbstractDAO {
    public List<ApplicationMethod> getApplicationMethod(int applicationId) throws Exception{
        String sql="select * from application_method where application_method_id=?";
        ApplicationMethodQuery q=new ApplicationMethodQuery(this.getDataSource(), sql);
        return execute(q, applicationId);
    }
    public List<ApplicationMethod> getAllApplicationMethods() throws Exception {
        String sql = "select * from application_method";
        ApplicationMethodQuery q = new ApplicationMethodQuery(this.getDataSource(), sql);
        return q.execute();
    }
    public int insertApplicationMethod(ApplicationMethod method) throws Exception{

        String sql = "insert into application_method ( application_method_id, application_type, site_of_application," +
                "editor_format, dosage, days_post_injection, injection_rate, injection_frequency, injection_volume," +
                "antidote_id, antidote_description ) values (?,?,?,?,?,?,?,?,?,?,?)";

        int methodId = this.getNextKeyFromSequence("method_seq");


        update(sql, methodId, method.getApplicationType(),method.getSiteOfApplication(),method.getEditorFormat(),
                method.getDosage(),method.getDaysPostInjection(),method.getInjectionRate(),method.getInjectionFrequency(),
                method.getInjectionVolume(),method.getAntidoteId(),method.getAntidoteDescription());

        return methodId;
    }
    public void updateApplicationMethod(ApplicationMethod method) throws Exception{

        String sql = "update application_method set application_type=?, site_of_application=?," +
                "editor_format=?, dosage=?, days_post_injection=?, injection_rate=?, injection_frequency=?, injection_volume=?," +
                "antidote_id=?, antidote_description=? where application_method_id=?";


        update(sql, method.getApplicationType(),method.getSiteOfApplication(),method.getEditorFormat(),
                method.getDosage(),method.getDaysPostInjection(),method.getInjectionRate(),method.getInjectionFrequency(),
                method.getInjectionVolume(),method.getAntidoteId(),method.getAntidoteDescription(),method.getApplicationId());
    }

    public int getAppMethodId(ApplicationMethod method) throws Exception {

        String sql = "SELECT * FROM application_method WHERE application_type=? AND dosage=? AND days_post_injection=? "+
                "AND site_of_application=? AND editor_format=? AND injection_frequency=?";

        List<ApplicationMethod> list = ApplicationMethodQuery.execute(this, sql,
                method.getApplicationType(), method.getDosage(), method.getDaysPostInjection(),
                method.getSiteOfApplication(), method.getEditorFormat(), method.getInjectionFrequency());
        return list.isEmpty() ? 0 : list.get(0).getApplicationId();
    }
}
