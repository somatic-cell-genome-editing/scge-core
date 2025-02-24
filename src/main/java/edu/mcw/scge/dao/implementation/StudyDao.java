package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.PersonQuery;
import edu.mcw.scge.dao.spring.StudyAssociationQuery;
import edu.mcw.scge.dao.spring.StudyQuery;
import edu.mcw.scge.datamodel.*;

import java.io.IOException;
import java.util.*;

public class StudyDao extends AbstractDAO {
    public List<Study> getStudyByExperimentId(long experimentId,int personId ) throws Exception {
        String sql = "select s.* from study s inner join experiment e on s.study_id=e.study_id " +
                "                inner join person_info p on p.group_id=s.group_id " +
                "                where p.person_id=? " +
                "                and e.experiment_id=? " +
                "                " ;

        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q,personId, experimentId);
    }
    public List<Study> getStudyByExperimentId(long experimentId) throws Exception {
        /*String sql = "select s.* p.*, pi.* from study s inner join experiment e on s.study_id=e.study_id " +
                "                inner join person_info pi on pi.group_id=s.group_id " +
                " inner join person p on p.person_id=pi.person_id " +
                "                where " +
                "                 e.experiment_id=? " +
                "                " ;
*/
        String sql="select s.* from study s ," +
                " experiment e " +
                " where s.study_id=e.study_id                 " +
                "and  e.experiment_id=?";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, experimentId);
    }
    public List<Study> getStudiesByLab(int labId) throws Exception{
        String sql="select * from study where lab_id=?";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q,labId);
    }

    public Study getStudyByStudyId(int studyId) throws Exception{
        String sql="select * from study where study_id=?";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        List<Study>studies= execute(q,studyId);
        if(studies!=null && studies.size()>0){
            return studies.get(0);
        }
        return null;
    }
    public List<StudyAssociation> getStudyAssociations(int studyId) throws Exception {
        String sql="select * from study_associations where study_id=? and status='ACTIVE'";
        StudyAssociationQuery q=new StudyAssociationQuery(getDataSource(), sql);
        return execute(q, studyId);

    }
    public boolean existsAssociation(StudyTierUpdate u) throws Exception {
        String sql="select id from study_associations where study_id=? and group_id=? and tier=?";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        return execute(q, u.getStudyId(), u.getAssociatedGroupId(), u.getTier()).size()>0;
    }
    public List<Integer> getAssociations(StudyTierUpdate u) throws Exception {
        String sql="select id from study_associations where study_id=? and group_id=? and tier=?";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        return execute(q, u.getStudyId(), u.getAssociatedGroupId(), u.getTier());
    }
    public void updateStudyAssociation(StudyAssociation a) throws Exception {

        String sql="update study_associations set " +
                "study_id=?," +
                "group_id=? ," +
                "modified_date=?," +
                "modified_by=?," +
                "tier=?," +
                "status=? where study_id=? and group_id=? and tier=?";
        update(sql, a.getStudyId(), a.getGroupId(), a.getModifiedDate(),a.getModifiedBy(),a.getTier(),
                "ACTIVE", a.getStudyId(), a.getGroupId(), a.getTier());

    }
    public void disableStudyAssociations(int studyId) throws Exception {
        String sql="update study_associations set status=? where study_id=?" ;
        update(sql, "INACTIVE", studyId);
    }
    public void insertStudyAssociations(StudyAssociation a) throws Exception {
        String sql="insert into study_associations(" +
                "id," +
                "study_id," +
                "group_id," +
                "created_date," +
                "created_by," +
                "modified_date," +
                "modified_by," +
                "tier," +
                "status " +
                ") values(?,?,?,?,?,?,?,?,?)";
        update(sql, a.getAssociationId(), a.getStudyId(),
                a.getGroupId(), a.getCreatedDate(), a.getCreatedBy(),a.getModifiedDate(), a.getModifiedBy()
        ,a.getTier(),"ACTIVE");
    }
    public List<Study> getStudies(Person p) throws Exception {


       /* PersonDao pDAO = new PersonDao();
        List<PersonInfo> pInfo = pDAO.getPersonInfo(p.getId());

        StringBuilder groups = new StringBuilder();

        boolean first=true;
        for (PersonInfo pi: pInfo) {
            if (!first) {
                groups.append(",");

            }
            first=false;
            groups.append(pi.getGroupId());
           // groups.append(pi.getGroupId());
        }

        String sql="select s.*, i.institution_name, p.name as submitterName, pi.person_id as piId," +
                " pi.name as piName, " +
                " pi.first_name as piFirstName , pi.last_name as piLastName"+
                " from study s, institution i, person p, person pi where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.pi_id=pi.person_id " +
                " and s.group_id in (" + groups + ") order by piLastName";*/
       String sql="select s.*, i.institution_name, p.name as submitterName, pi.person_id as piId,\n" +
               "                pi.name as piName, \n" +
               "                pi.first_name as piFirstName , pi.last_name as piLastName\n" +
               "                from study s, institution i, person p, person pi where s.lab_id=i.institution_id and s.submitter_id=p.person_id and pi.person_id=? \n" +
               "                and s.group_id in (select group_id from person_info where person_id=?) order by piLastName\n" +
               "                ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q,p.getId(),p.getId());

    }

    public List<Study> getStudies() throws Exception{
        String sql="select s.*, i.institution_name, p.name as submitterName " +
                " from study s, institution i, person p " +
                " where s.lab_id=i.institution_id and s.submitter_id=p.person_id " ;

        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q);
    }
    public List<Person> getStudyPi(Study study) throws Exception {
        String sql="select * from  person where person_id in (select person_id from person_info where group_id in (\n" +
                " select group_id from study where group_id=?) and role_key in (select role_key from scge_roles where role='pi'))";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query, study.getGroupId());
    }
    public List<Study> getSharedTier2Studies(int personId) throws Exception{
        String sql="select s.*, i.institution_name, p.name as submitterName " +
               // "pi.person_id as piId, pi.name as piName" +
              //  ", pi.first_name as piFirstName , pi.last_name as piLastName "+
                " from study s, " +
                " institution i, person p,  study_associations sa, person_info pinfo" +
                " where s.lab_id=i.institution_id and s.submitter_id=p.person_id  and s.study_id=sa.study_id and (pinfo.group_id=s.group_id or pinfo.group_id=sa.group_id) and pinfo.person_id=?" +
                " order by s.tier desc, s.submission_date desc";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q,personId);
    }



    public List<Study> getStudiesByInitiative(String initiativeName) throws Exception{
        String sql = "select s.*, i.institution_name, p.name as submitterName"+
                " from study s, institution i, person p where s.lab_id=i.institution_id and s.submitter_id=p.person_id and s.group_id in (select group_id from scge_grants where grant_initiative = ?) order by s.tier desc, s.submission_date desc";

        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, initiativeName);

    }
    public List<Study> getTier4StudiesOfInitiative(String initiativeName) throws Exception{
        String sql = " select * from study where tier=4 and group_id in (\n" +
                "            select group_id from scge_grants where grant_initiative_lc=?)";

        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, initiativeName);

    }

    public List<Study> getStudiesByGrantId(int grantId) throws Exception{
        String sql="select s.*, i.institution_name, p.name as submitterName"+
                " from study s, institution i, person p, scge_grants sg " +
                "    where s.lab_id=i.institution_id " +
                "    and s.submitter_id=p.person_id " +
                "    and sg.group_id=s.group_id " +
                "    and sg.grant_id=?";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, grantId);
    }


    public List<Study> getStudyById(int studyId) throws Exception{
        String sql="select s.*, i.institution_name, p.name as submitterName from study s, " +
                "institution i, person p where s.study_id=? and s.lab_id=i.institution_id and s.submitter_id=p.person_id";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q,studyId);
    }
    public boolean verifyStudyAccessByPesonId(int studyId, int personId) throws Exception {
        String sql="select count(*) from study s left outer join  scge_group g on s.group_id=g.group_id " +
                "left outer join person_info i on i.group_id=g.group_id " +
                "left outer join person p on p.person_id=i.person_id " +
                "where s.study_id=? " +
                "and p.person_id=?";
      //  CountQuery q= new CountQuery(this.getDataSource(), sql);

        return getCount(sql,studyId, personId)>0;
    }

    public List<Study> getStudiesByEditor(long editorId) throws Exception{
        String sql = "select distinct s.*, i.institution_name, p.name as submitterName " +
                "from study s, institution i, person p,  editor e, experiment_record ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and  e.editor_id=? and ex.editor_id=e.editor_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, editorId);
    }

    public List<Study> getStudiesByDeliverySystem(long deliveryId) throws Exception{
        String sql = "select distinct s.*, i.institution_name, p.name as submitterName " +
                "from study s, institution i, person p,  delivery_system d, experiment_record ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id  and d.ds_id=? and ex.ds_id=d.ds_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, deliveryId);
    }

    public List<Study> getStudiesByVector(long vectorId) throws Exception{
        String sql = "select distinct s.*, i.institution_name, p.name as submitterName " +
                "from study s inner join institution i on s.lab_id=i.institution_id \n" +
                "inner join person p on s.submitter_id=p.person_id \n" +

                "inner join vector_associations va on va.vector_id=? \n" +
                "inner join experiment_record ex on ex.experiment_record_id = va.experiment_record_id and ex.study_id=s.study_id  ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, vectorId);
    }

    public List<Study> getStudiesByModel(long modelId) throws Exception{
        String sql = "select distinct s.*, i.institution_name, p.name as submitterName " +
                "from study s, institution i, person p,  model m, experiment_record ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id and m.model_id=? and ex.model_id=m.model_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, modelId);
    }

    public List<Study> getStudiesByHrDonor(long hrdonorId) throws Exception{
        String sql = "select distinct s.*, i.institution_name, p.name as submitterName " +
                "from study s, institution i, person p, hr_donor h, experiment_record ex " +
                "where s.lab_id=i.institution_id and s.submitter_id=p.person_id  and h.hrdonor_id=? and ex.hrdonor_id=h.hrdonor_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, hrdonorId);
    }

    public List<Study> getStudiesByGuide(long guideId) throws Exception{
        String sql = "select distinct s.*, i.institution_name, p.name as submitterName \n" +
                "from study s inner join institution i on s.lab_id=i.institution_id \n" +
                "inner join person p on s.submitter_id=p.person_id \n" +

                "inner join guide_associations ga on ga.guide_id=? \n" +
                "inner join experiment_record ex on ex.experiment_record_id = ga.experiment_record_id and ex.study_id=s.study_id ";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, guideId);
    }
    public List<Study> getStudiesByAntibody(long antibodyId) throws Exception{
        String sql = "select distinct s.*, i.institution_name, p.name as submitterName \n" +
                "                from study s inner join institution i on s.lab_id=i.institution_id \n" +
                "                inner join person p on s.submitter_id=p.person_id \n" +
                "                inner join antibody_associations assoc on assoc.antibody_id=?\n" +
                "                inner join experiment_record ex on ex.experiment_record_id = assoc.experiment_record_id and ex.study_id=s.study_id \n" +
                "   " ;
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, antibodyId);
    }


    /**
     * inserts into temporary table study_tier_updates until the change is approved by PI
     *
     */
    public void insertStudyTier(int studyId, int tier, int groupId, int sequenceKey, String action,
                                String status, int modifiedByPersonId) throws Exception {
        String sql="insert into study_tier_updates(" +
                "study_tier_update_id," +
                "study_id," +
                "tier," +
                "modified_date," +
                "modified_by," +
                "group_id," +
                "status," +
                "action )" +
                " values(?,?,?,?,?,?,?,?)";
        update(sql,
               sequenceKey, studyId, tier,new Date(),modifiedByPersonId, groupId,status,action
        );
    }

	public void insertStudy(Study s) throws Exception{
        String sql = "insert into study (study_id,study,lab_id,tier,submission_date,\n" +
                "submitter_id,modified_by,last_modified_date,raw_data,\n" +
                "reference,group_id,initiative ) values (?,?,?,?,?,?,?,?,?,?,?)";
        int studyId = this.getNextKeyFromSequence("study_seq");
        update(sql, studyId,s.getStudy(),s.getLabId(),s.getTier(),s.getSubmissionDate(),
                s.getSubmitterId(),null,null,s.getRawData(),s.getReference(),
                null,null);
    }
    public void updateStudyTier(StudyTierUpdate u) throws Exception{
        String sql="update study set tier=?, modified_by=?, " +
                "last_modified_date=? where study_id=?";
        update(sql, u.getTier(),  String.valueOf(u.getModifiedBy()), u.getModifiedDate(), u.getStudyId());
    }

    public void updateStudyGrantNGroup(int groupId, int personId) throws Exception {
        String sql="update study set group_id=? where pi_id=?";
        update(sql, groupId,personId);
    }

    public List<Study> getStudiesByGroupId(int groupId) throws Exception {
        String sql="select s.*, i.institution_name, p.name as submitterName " +

                "from study s, institution i, person p " +
                "where s.lab_id=i.institution_id " +
                "and s.submitter_id=p.person_id " +
                "  " +
                " and s.group_id=? order by s.study_id";
        StudyQuery q=new StudyQuery(this.getDataSource(), sql);
        return execute(q, groupId);
    }
    public List<Integer> getAllSubmittedGrantIds() throws Exception {
        String sql="select distinct(group_id) from study";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        return query.execute();
    }
    public List<Person> getStudyPOC(int studyId) throws Exception{
        String sql="select * from person where person_id in " +
                "(select person_id from person_info where role_key in (" +
                "select role_key from scge_roles where role='poc' or role='POC') " +
                "and group_id in (select group_id from study where study_id=?))";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query, studyId);
    }
    public static void main(String[] args){
        StudyDao sdao=new StudyDao();
        PersonDao pdao=new PersonDao();
        try {
            List<Study> studies= sdao.getStudies();
            for(Study s:studies){
             //   int piId=s.getPiId(); // refactored table, removed pi_id column.
                int piId=0;// need to put a work around due to above refactoring of model.
                int grantId=0;
                int groupId=0;
                List<PersonInfo> infoList=pdao.getPersonInfo(piId);
                List<Integer> groupIds=new ArrayList<>();
                List<Integer> grantIds=new ArrayList<>();
                for(PersonInfo i:infoList){
                    if(!groupIds.contains(i.getGroupId()))
                    groupIds.add(i.getGroupId());
                    if(!grantIds.contains(i.getGrantId()))
                    grantIds.add(i.getGrantId());
                }
                if(groupIds.size()==1){
                   groupId= groupIds.get(0);
                }else{
                    System.out.println("PERSON ID: "+ piId +"\tGROUPS:"+groupIds.size());
                }
                if(grantIds.size()==1){
                    grantId=grantIds.get(0);
                }else{
                    System.out.println("PERSON ID: "+ piId +"\tGRANTS:"+grantIds.size());

                }
             //   System.out.println("PI_ID: "+ piId+"\tGRANT_ID: "+grantId+"\tGROUP_ID:"+groupId);
              sdao.updateStudyGrantNGroup(groupId,piId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done!!");
    }
}
