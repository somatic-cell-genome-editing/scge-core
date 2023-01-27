package edu.mcw.scge.dao.spring;

import edu.mcw.scge.dao.implementation.ExperimentResultDao;
import edu.mcw.scge.dao.implementation.GuideDao;
import edu.mcw.scge.dao.implementation.HRDonorDao;
import edu.mcw.scge.dao.implementation.VectorDao;
import edu.mcw.scge.datamodel.*;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExperimentRecordQuery extends MappingSqlQuery<ExperimentRecord> {
    GuideDao guideDao=new GuideDao();
    HRDonorDao hrDonorDao=new HRDonorDao();
    ExperimentResultDao resultDao=new ExperimentResultDao();
    VectorDao vectorDao=new VectorDao();
    public ExperimentRecordQuery(DataSource ds, String sql){
        super(ds, sql);
    }
    @Override
    protected ExperimentRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExperimentRecord e= new ExperimentRecord();
        e.setExperimentId(rs.getLong("experiment_id"));
        try {
            e.setExperimentName(rs.getString("name"));
        }catch (Exception exception){}
        e.setExperimentRecordName(rs.getString("name"));
        e.setStudyId(rs.getInt("study_id"));
     //   e.setGuideId(rs.getInt("guide_id"));
        e.setDeliverySystemId(rs.getLong("ds_id"));

        try {
            e.setTissueTerm(rs.getString("term"));
        } catch(Exception ignored) {

        }

        try {
            e.setCellTypeTerm(rs.getString("cellTerm"));
        } catch(Exception ignored) {

        }
        try {
            e.setIsTargetTissue(rs.getInt("is_target_tissue"));
        } catch(Exception ignored) {

        }
        try {
            e.setModelId(rs.getLong("model_id"));
            e.setModelName(rs.getString("modelName"));
        }catch (Exception ex){}
        try {
            e.setModelId(rs.getLong("model_id"));
            e.setModelDisplayName(rs.getString("display_name"));
        }catch (Exception ex){}
        try {
            e.setEditorId(rs.getLong("editor_id"));
            e.setEditorSymbol(rs.getString("symbol"));
        }catch (Exception e1){}
        try {
            e.setDeliverySystemName(rs.getString("ds_name"));
            e.setDeliverySystemType(rs.getString("ds_type"));
        }catch (Exception e2) {
        }
        try{
          //  e.setVector(rs.getString("vector"));
            e.setApplicationMethodId(rs.getInt("application_method_id"));
            e.setDosage(rs.getString("dosage"));
            if(rs.getString("injection_frequency")!=null)
            e.setInjectionFrequency(rs.getString("injection_frequency"));

        }catch (Exception e1){

        }
        try {
            e.setOrganSystemID(rs.getString("organ_system"));
            e.setTissueId(rs.getString("tissue_id"));
            e.setCellType(rs.getString("cell_type"));
        }catch (Exception e1){}
     //   e.setModelName(rs.getString("modelName"));
     //   e.setGuide(rs.getString("guide"));
        e.setExperimentRecordId(rs.getLong("experiment_record_id"));
     //   e.setVectorId(rs.getInt("vector_id"));
        if(rs.getInt("application_method_id")!=0)
        e.setApplicationMethodId(rs.getInt("application_method_id"));
       try {
           e.setDosage(rs.getString("dosage"));
       }catch (Exception e1){}
        try {
            e.setSex(rs.getString("sex"));
        }catch (Exception e1){}
        try{
            e.setHrdonorId(rs.getLong("hrdonor_id"));
            e.setHrdonorName(rs.getString("hrdonorName"));
        }catch (Exception e1){}
        try{
            mapGuide(e);
        }catch (Exception e1){

        }
        try{
            mapVecotr(e);
        }catch (Exception e1){

        }
        try{
            mapResultDetails(e);
        }catch (Exception e1){

        }
        try{
            mapHrDonor(e);
        }catch (Exception e1){

        }
        return e;





    }
    void mapGuide(ExperimentRecord e) throws Exception {
        List<Guide> guideList=guideDao.getGuidesByExpRecId(e.getExperimentRecordId());
        if(guideList!=null && guideList.size()>0){
            e.setGuides(guideList);
        }
    }
    void mapVecotr(ExperimentRecord e) throws Exception {
        List<Vector> vectors=vectorDao.getVectorsByExpRecId(e.getExperimentRecordId());
        if(vectors!=null && vectors.size()>0)
            e.setVectors(vectors);
    }
    void mapResultDetails(ExperimentRecord e) throws Exception {
        List<ExperimentResultDetail> resultDetails=resultDao.getResultsByExperimentRecId(e.getExperimentRecordId());
        if(resultDetails!=null && resultDetails.size()>0)
            e.setResultDetails(resultDetails);
    }
    void mapHrDonor(ExperimentRecord e) throws Exception {
            List<HRDonor> hrDonors=hrDonorDao.getHRDonorById(e.getHrdonorId());
        if(hrDonors!=null && hrDonors.size()>0)
            e.setHrDonors(hrDonors);
    }

}
