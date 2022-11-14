package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.datamodel.Study;
import org.apache.http.util.Asserts;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;



public class StudyDaoTest {
    StudyDao studyDao=new StudyDao();

    @Test
    public void getStudyByExperimentId() throws Exception {
        List<Study> studies=studyDao.getStudyByExperimentId(18000000011L);
        Asserts.check(studies.size()==1, "Study by experiment id returns one study");
    }

    @Test
    public void testGetStudyByExperimentId() throws Exception {
        int dccMember=1269;
        int projectMember=1137;
        int nonDCCMember=950;
     /*   Assert.assertFalse(studyDao.getStudyByExperimentId(18000000011L, projectMember).isEmpty());
        Assert.assertFalse(studyDao.getStudyByExperimentId(18000000011L, dccMember).isEmpty());
        Assert.assertTrue(studyDao.getStudyByExperimentId(18000000011L,nonDCCMember).isEmpty());
*/

    }

    @Test
    public void getStudiesByLab() {
    }

    @Test
    public void getStudyByStudyId() {
    }

    @Test
    public void getStudyAssociations() {
    }

    @Test
    public void existsAssociation() {
    }

    @Test
    public void getAssociations() {
    }

    @Test
    public void updateStudyAssociation() {
    }

    @Test
    public void disableStudyAssociations() {
    }

    @Test
    public void insertStudyAssociations() {
    }

    @Test
    public void getStudies() {
    }

    @Test
    public void testGetStudies() {
    }

    @Test
    public void getStudyPi() {
    }

    @Test
    public void getSharedTier2Studies() {
    }

    @Test
    public void getStudiesByInitiative() {
    }

    @Test
    public void getStudiesByGrantId() {
    }

    @Test
    public void getStudyById() {
    }

    @Test
    public void verifyStudyAccessByPesonId() {
    }

    @Test
    public void getStudiesByEditor() {
    }

    @Test
    public void getStudiesByDeliverySystem() {
    }

    @Test
    public void getStudiesByVector() {
    }

    @Test
    public void getStudiesByModel() {
    }

    @Test
    public void getStudiesByHrDonor() {
    }

    @Test
    public void getStudiesByGuide() {
    }

    @Test
    public void insertStudyTier() {
    }

    @Test
    public void insertStudy() {
    }

    @Test
    public void updateStudyTier() {
    }

    @Test
    public void updateStudyGrantNGroup() {
    }

    @Test
    public void getStudiesByGroupId() {
    }

    @Test
    public void getAllSubmittedGrantIds() {
    }

    @Test
    public void getStudyPOC() {
    }
}
