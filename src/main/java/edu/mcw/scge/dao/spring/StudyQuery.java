package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.implementation.PersonDao;
import edu.mcw.scge.dao.implementation.StudyDao;
import edu.mcw.scge.datamodel.Person;
import edu.mcw.scge.datamodel.Study;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudyQuery extends MappingSqlQuery {
    public StudyQuery(DataSource ds, String sql){
        super(ds,sql);
    }
    @Override
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Study s=new Study();
        s.setLabId(rs.getInt("lab_id"));
        s.setStudyId(rs.getInt("study_id"));
        s.setStudy(rs.getString("study"));
        s.setTier(rs.getInt("tier"));
        s.setSubmissionDate(rs.getDate("submission_date"));
        s.setLastModifiedDate(rs.getDate("last_modified_date"));

        s.setSubmitterId(rs.getInt("submitter_id"));
        s.setRawData(rs.getString("raw_data"));
        s.setReference(rs.getString("reference"));
        s.setGroupId(rs.getInt("group_id"));
        s.setPiId(rs.getInt("pi_id"));
        try{
            s.setModifiedBy(rs.getInt("modified_by"));
        }catch (Exception e){}
        try{
            s.setPiId(rs.getInt("piId"));
        }catch (Exception e){}
        try {
            s.setLabName(rs.getString("institution_name"));

        }catch (Exception e){}
        try{
            s.setSubmitter(rs.getString("submitterName"));
        }catch (Exception e){}
        try {
            s.setPi(rs.getString("piName"));
            s.setPiFirstName(rs.getString("piFirstName"));

            s.setPiLastName(rs.getString("piLastName"));

        }catch (Exception e){
            try {
                s.setPi(rs.getString("name"));
                s.setPiFirstName(rs.getString("first_name"));

                s.setPiLastName(rs.getString("last_name"));
            }catch (Exception e1){}

        }
        try{
            s.setDeliveryPiId(rs.getInt("delivery_pi_id"));
        }catch (Exception e){}
        try{
            s.setIsValidationStudy(rs.getInt("is_validation_study"));
        }catch (Exception e){}

        mapPi(s);
        return s;
    }
    public void mapPi(Study s)  {
        StudyDao studyDao=new StudyDao();
        PersonDao personDao=new PersonDao();
        try {
            if (s.getPiId() > 0) {
                Person pi = personDao.getPersonById(s.getPiId()).get(0);
                s.setPiLastName(pi.getLastName());
                s.setPiFirstName(pi.getFirstName());
                s.setPi(pi.getName());
            }else{
               List<Person> pis=
                       studyDao.getStudyPi(s);
               s.setMultiplePis(pis);
            }
        }catch (Exception e){

        }
    }
}
