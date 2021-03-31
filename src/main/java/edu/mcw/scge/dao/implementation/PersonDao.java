package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.PersonInfoQuery;
import edu.mcw.scge.dao.spring.PersonQuery;
import edu.mcw.scge.dao.spring.StringListQuery;
import edu.mcw.scge.datamodel.Grant;
import edu.mcw.scge.datamodel.Person;

import edu.mcw.scge.datamodel.PersonInfo;
import edu.mcw.scge.datamodel.SCGEGroup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jthota on 8/20/2019.
 */
public class PersonDao extends AbstractDAO {
        GroupDAO gdao=new GroupDAO();
        GrantDao grantDao=new GrantDao();
    public void insert(Person p) throws Exception {

        int newId = 0;
        if (p.getId() == 0) {
            newId = getNextKey("person_seq");
        } else {
            newId = p.getId();
        }

        String sql="insert into person(person_id,name, name_lc, institution_id, email, email_lc," +
                "phone, address, google_id,status, created_date, modified_date, modified_by, other_id, " +
                "first_name," +
                "last_name)" +
                " values(?,?,?,?,?,?,?,?,?,?,current_date,current_date,?,?,?,?)";
        update(sql,
                newId, p.getName(),p.getName().toLowerCase(),
                p.getInstitution(),
                p.getEmail(),p.getEmail().toLowerCase(),
                p.getPhone(), p.getAddress(), p.getGoogleSub(),
                p.getStatus(),
            //    p.getGrantId(),
                p.getModifiedBy(),
                p.getOtherId(),
                p.getFirstName(),p.getLastName()
        );

    }
    public void update(Person p) throws Exception {
        System.out.println("in update person");
        String sql="update person set name=?, name_lc=?,institution_id=?,email=?,email_lc=?,status=?, other_id=?, modified_date=current_date where person_id=?";
        update(sql, p.getName(), p.getName().toLowerCase(), p.getInstitution(),p.getEmail(), p.getEmail().toLowerCase(),p.getStatus(),p.getOtherId(), p.getId());
    }

    public List<Person> getPerson(Person p) throws Exception{
      //  String sql="select * from person where name_lc=? and email_lc=? and status='ACTIVE' ";
        String sql="select * from person where email_lc=? and status='ACTIVE' ";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
         return execute(query, p.getEmail().toLowerCase());
    }
    //sql = "select distinct pi.person_id, pi.group_id, s.study_id from person_info pi, study s where pi.group_id=s.group_id""


    public List<Person> getPersonByEmailId(Person p) throws Exception{
        //  String sql="select * from person where name_lc=? and email_lc=? and status='ACTIVE' ";
        String sql="select * from person where email_lc=? ";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query, p.getEmail().toLowerCase());
    }
    public List<Person> getPersonById(int id) throws Exception{
        String sql="select * from person where person_id=? ";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query,id);
    }
    public List<Person> getPersonByGoogleId(String id) throws Exception{
        String sql="select * from person where google_id=?  ";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query,id);
    }
    public List<Person> getPersonByLastName(String lastName) throws Exception{
        String sql="select * from person where name like '%"+lastName+"%'  " ;
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query);
    }
    public String getPersonStatus(String subject) throws Exception{
        String sql="select status from person where google_id=?";
       // String sql="select status from person where email=?";
        StringListQuery query= new StringListQuery(this.getDataSource(), sql);
        return (String) execute(query, subject).get(0);
    }
    public List<Person> getPersonByEmail(String email) throws Exception{
        String sql="select * from person where email_lc=? or other_id=? or email=? ";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query, email.toLowerCase(), email, email);
    }
    public List<Person> getAllActiveMembers() throws Exception{
        String sql="select * from person where status='ACTIVE' order by name ";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return query.execute();
    }
    public List<Person> getAllMembers() throws Exception{
        String sql="select * from person order by name ";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return query.execute();
    }

    public List<String> getPersonGroups(Person p) throws Exception {
        String sql="select g.group_name from scge_group g, person_info r, person p " +
                "where g.group_id=r.group_id " +
                "and r.person_id=p.person_id " +
                "and p.status='ACTIVE' " +
                "and p.person_id=?";
        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q,p.getId());
    }
    public List<String> getGroups(Person p) throws Exception {
        String sql="select g.group_name from scge_group g , person_info pg where " +
                "g.group_id=pg.group_id and person_id=?";
        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, p.getId());
    }
    public List<Integer> getGroupIds(Person p) throws Exception {
        String sql="select g.group_id from scge_group g , person_info pg where " +
                "g.group_id=pg.group_id and person_id=?";
        IntListQuery q=new IntListQuery(this.getDataSource(), sql);
        return execute(q, p.getId());
    }
    /*public List<String> getGroupAccessLevel(Person p, String group) throws Exception {
        String sql="select a.access_level from person_group_access pga, accesses a where " +
                 " pga.person_id=? and " +
                "a.access_id = pga.access_id " +
                " and pga.group_key in (select group_key from scge_groups where group_name =?)";

        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, p.getId(),group);
    }*/
  /*  public void addMemberToGroup(int person_id, int group_key, int access_id) throws Exception {
        String sql="insert into person_group_access(person_id, group_key, access_id) values(" +
                "?,?,?)";
        update(sql, person_id, group_key, access_id);
    }
    public List<Person> getMembersOfGroup(int group_key) throws Exception {
       String sql="select p.* from person p, person_group_access pg where" +
               " p.person_id=pg.person_id and " +
               "pg.group_key=?";
        PersonQuery q=new PersonQuery(this.getDataSource(), sql);
        return execute(q, group_key);
    }
    */
    public List<Person> getAllUnauthorizedUsers() throws Exception {
        String sql="select * from person where status='processing'";
        PersonQuery q=new PersonQuery(this.getDataSource(), sql);
        return q.execute();
    }

    public void updateStatus(Person person) throws Exception {
        String sql="update person set status=? where email=?";
            update(sql, person.getStatus(), person.getEmail());
    }
    public void updateStatusToInactive(String status) throws Exception {
        String sql="update person set status=?";
        update(sql, status);
    }
    public void updateGoogleId(String googleId, int personId) throws Exception {
        String sql="update person set google_id=? where person_id=? ";
        update(sql, googleId, personId);
    }
    public void updatePersonNewAttributes(Person person) throws Exception {
        String sql="update person set first_name=?, last_name=?, google_id=?, other_id=? where email=? or email_lc=?";
        update(sql,  person.getFirstName(), person.getLastName(), person.getGoogleSub(), person.getOtherId() ,person.getEmail(),person.getEmail().toLowerCase());
    }
  /*  public int insertOrUpdateGrant(String grantTitle) throws Exception {
        String sql="select grant_id from scge_grants where grant_title=?";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        List<Integer> ids=execute(query, grantTitle);
        int id=0;
        if(ids==null || ids.size()==0){
           id= getNextKey("grant_seq");
            insertGrant(id, grantTitle);
        }else{
            id=ids.get(0);
        }
        return id;
    }*/
    public List<String> getRolesByPersonId(int personId, String groupName) throws Exception {
        String sql="select r.role from scge_roles r, person p, person_info pi, scge_group g where " +
                "r.role_key=pi.role_key " +
                "and p.person_id=pi.person_id " +
                "and g.group_id=pi.group_id " +
                "and p.status='ACTIVE' "  +
                "and p.person_id=? " +
                "and g.group_name=?"
                ;
        StringListQuery query=new StringListQuery(this.getDataSource(), sql);
        return execute(query, personId, groupName);

    }


 /*   public void insertSubgroup(int subgroup_id,String subgroupName) throws Exception {

        String sql="insert into subgroups values(?,?)";
        try{
            update(sql,subgroup_id, subgroupName);
        }catch (Exception e){
            System.err.println("subgroup: "+ subgroupName);
        }


    }*/
    public void insertInstitution(int id,String name) throws Exception {

        String sql="insert into institution values(?,?,?)";
        try {
            update(sql, id, name, name.toLowerCase());
        }catch (Exception e){
            System.err.println("Institution: "+ name);
            e.printStackTrace();
        }

    }
    public int insertOrUpdateInstitution(String name) throws Exception {
        String sql="select institution_id from institution where institution_name=?";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        List<Integer> ids= execute(query, name);
        int id= 0;
        if(ids==null || ids.size()==0){
            id= getNextKey("institution_seq");
            insertInstitution(id, name);
        }else{
            id=ids.get(0);
        }
        return id;
    }

    public void insertGrant(int grant_id,String grantTitle) throws Exception {

        String sql="insert into scge_grants values(?,?,?)";
        try {
            update(sql, grant_id, grantTitle, grantTitle.toLowerCase());
        }catch (Exception e){
            System.err.println("Grant TITLE:"+grantTitle);
        }

    }

    public int getGroupId(String groupName, String groupType) throws Exception {
        String sql = "select group_id from scge_group where group_name_lc=? and group_type=?";
        IntListQuery query = new IntListQuery(this.getDataSource(), sql);
        List<Integer> ids = execute(query, groupName.toLowerCase().trim(), groupType);
        int id = 0;
        if (ids != null && ids.size() > 0) {

           gdao.updateGroupName(ids.get(0), groupName.trim());
            return ids.get(0);
        }else{
            id=getNextKey("group_seq");
            SCGEGroup group=new SCGEGroup();
            group.setGroupId(id);
            group.setGroupName(groupName.trim());
            group.setGroupType(groupType);
            group.setGroupNameLC(groupName.toLowerCase().trim());
            gdao.insert(group);
        }
        return id;

    }
    public List<Integer> getRolesIds(List<String> roles) throws Exception {

        String sql="select role_key from scge_roles where role in (";
        boolean first=true;
        for(String role: roles){
        if(first){
            sql=sql+"'"+role+"'";
            first=false;
        }else{
            sql=sql+","+"'"+role+"'";
        }
        }
        sql=sql+")";
    //    System.out.println("SQL:"+ sql);
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);

        return query.execute();
    }


    public void delete(Person person) throws Exception{
        deletePersonInfo(person);
        String sql="delete from person where person_id=?" ;
        update(sql, person.getId() );


    }
    public void deletePersonInfo(Person person) throws Exception{

        String sql="delete from person_info where person_id=?" ;
        update(sql, person.getId() );


    }

    public void removeGroup(Person person, int groupId) throws Exception{

        String sql="delete from person_info where person_id=? and group_id=?" ;
        update(sql, person.getId(),groupId );


    }


    public void insertFromFile(String file) throws Exception {

        FileInputStream fs=new FileInputStream(new File(file));
        XSSFWorkbook workbook=new XSSFWorkbook(fs);
        XSSFSheet sheet=workbook.getSheet("directory");
        Iterator<Row> rowIterator=sheet.iterator();
        List<Person> persons= new ArrayList<>();
        List<String> names=new ArrayList<>();
        while(rowIterator.hasNext()) {
            Person p = new Person.Builder().build();
            String grantTitle=new String();
            String projectType=new String();
            String institution=new String();

            int grantId= 0;
            int subgroupId= 0;
            int institutionId=0;
            int groupId=0;
            Row row = rowIterator.next();
            String name=new String();
            Iterator<Cell> cellIterator = row.cellIterator();
            List<String> roles=new ArrayList<>();
            List<Integer> roleIds = new ArrayList<>();
            Grant grant=new Grant();
            if (row.getRowNum() != 0) {
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    int colIndex = cell.getColumnIndex();
                    if (colIndex == 0) {
                        projectType = cell.getStringCellValue();
                        groupId = getGroupId(projectType, "group");
                    }
                    if (colIndex == 1) {
                        grantTitle = cell.getStringCellValue();
                        grant.setGrantInitiative(projectType);
                        grant.setGrantTitle(grantTitle);
                        grant.setGrantTitleLc(grantTitle.toLowerCase());
                        if(!projectType.equalsIgnoreCase("NIH"))
                        grantId = grantDao.insertOrUpdate(grant);
                        subgroupId = getGroupId(grantTitle, "subgroup");
                    }

                    if (colIndex == 2) {
                        name = cell.getStringCellValue();
                        p.setName(name);
                        p.setName_lc(name.toLowerCase());
                        if (!names.contains(name.toLowerCase())) {
                            names.add(name.toLowerCase());
                        }
                    }
                   if (colIndex == 3) {

                        if (cell.getStringCellValue().equalsIgnoreCase("x")) {
                            int roleId = getRoleId("POC");
                            roleIds.add(roleId);
                        }

                    }
                    p.setStatus("ACTIVE");
                    //    if (colIndex == 4) {
                    if (colIndex == 4) {
                        String role1 = cell.getStringCellValue().toLowerCase();
                        if (role1.contains("administrative")) {
                            role1 = "administrative contact";
                        }
                        int roleId = getRoleId(role1);
                        roleIds.add(roleId);
                    }
                    if (colIndex == 5) {
                        p.setEmail(cell.getStringCellValue());
                        p.setEmail_lc(cell.getStringCellValue().toLowerCase());
                    }
                    if (colIndex == 6) {
                        institution = cell.getStringCellValue();
                        institutionId = insertOrUpdateInstitution(institution);
                        p.setInstitution(institutionId);
                        p.setInstitutionName(institution);
                    }
                    if (colIndex == 7) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                p.setPhone(String.valueOf(cell.getNumericCellValue()));
                                break;
                            case Cell.CELL_TYPE_STRING:
                                p.setPhone(cell.getStringCellValue());
                        }
                    }
                }
                int personId = insertOrUpdate(p);
                if (personId > 0) {
                    int defaultGoupId = getGroupId("consortium", "group");
                    gdao.makeAssociations(defaultGoupId, groupId);
                    if (groupId != 0 ) {
                        insertPersonInfo(personId, Arrays.asList(1), groupId, grantId);
                    }
                    if (subgroupId != 0)
                        insertPersonInfo(personId, roleIds, subgroupId, grantId);
                    gdao.makeAssociations(groupId, subgroupId);

                    insertPersonAuthority(personId);
                }
            }

           // }
        }
    fs.close();
        System.out.println(persons.size());

    }
    public void insertPersonAuthority(int personId) throws Exception {
        List<PersonInfo> personInfoList=getPersonInfo(personId);
        List<String> authorities= new ArrayList<>();
        for(PersonInfo i:personInfoList){
            String authroity="ROLE_GROUP"+i.getSubGroupId();
            if(!authorities.contains(authroity)){
                authorities.add(authroity);
                insertAuthority(i.getPersonId(), authroity);
            }
        }
    }
    public void insertAuthority(int personId, String authority) throws Exception {
        String sql="insert into person_authorities(person_id, authority) values (?,?)" ;

            update(sql, personId,authority );

    }
    public List<String> getPersonAuthorities(int personId) throws Exception{
        String sql="select authority from person_authorities where person_id=?";
        StringListQuery q=new StringListQuery(this.getDataSource(), sql);
        return execute(q, personId);
    }
    public int getRoleId(String role) throws Exception {
        int roleKey=0;
        String sql="select role_key from scge_roles where role=?";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        List<Integer> roles=execute(query, role);
        if(roles!=null && roles.size()>0){
            return roles.get(0);
        }else{
            roleKey=getNextKey("role_seq");
           insertRole(role, roleKey);
        }
        return roleKey;
    }
    public void insertRole(String role, int roleKey) throws Exception {
        String sql="insert into scge_roles (role_key, role) values (?,?)";
        update(sql, roleKey, role);
    }
    public String getRole(String role) throws Exception {
        String sql="select role from scge_roles where role=?";
        StringListQuery query=new StringListQuery(this.getDataSource(), sql);
        List<String> roles=execute(query, role);
        if(roles!=null && roles.size()>0){
            return roles.get(0);
        }else{
            return "member";
        }
    }
    public int getDefaultGroupId(String defaultGroupName) throws Exception {
        String sql="select group_id from scge_group where group_name=?";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        List<Integer> ids=execute(query, defaultGroupName);
        int id=0;
        if(ids!=null && ids.size()>0){
           id= ids.get(0);
        }else{
            id=getNextKey("group_seq");
            SCGEGroup group=new SCGEGroup();
            group.setGroupName(defaultGroupName);
            group.setGroupId(id);
            group.setGroupShortName("CG");
            gdao.insert(group);
        }
        return id;
    }
 /*   public int getDefaultSubgroupId(String defaultSubgroupName) throws Exception {
        String sql="select subgroup_id from subgroups where subgroup_name=?";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        List<Integer> ids=execute(query, defaultSubgroupName);
        int id=0;
        if(ids!=null && ids.size()>0){
            id= ids.get(0);
        }else{
            id=getNextKey("subgroup_seq");
            insertSubgroup(id,defaultSubgroupName);
        }
        return id;
    }*/
    public void insertPersonDetails(int personId, List<Integer> roleIds,int groupId, int subgroupId ) throws Exception {
        for(int roleId:roleIds){
            String sql="insert into person_role_group values(?,?,?,?)";
            try {
                update(sql, personId, roleId, groupId, subgroupId);
            }catch (Exception e){
                System.err.println("person:"+ personId+"\n================================");
              e.printStackTrace();
            }
        }
    }
    public void insertPersonInfo(int personId, List<Integer> roleIds,int groupId, int grantId ) throws Exception {
       for(int role:roleIds){
           if(!isPersonInfoExists(personId, role, groupId)){
               insertPersonInfo(personId, role, groupId,grantId);
           }
       }
    }
   public boolean isPersonInfoExists(int personId, int role, int groupId) throws Exception {
      List<Integer> personInfo= getPersonInfo(personId, role, groupId);
       if(personInfo!=null && personInfo.size()>0){
           return true;
       }else
           return false;
   }

    public List<Integer> getPersonInfo(int personId, int role, int groupId) throws Exception {
        String sql="select person_id from person_info where person_id=? and role_key=? and group_id=?";
        IntListQuery query=new IntListQuery(this.getDataSource(), sql);
        return execute(query, personId, role, groupId);
    }

    public void insertPersonInfo(int personId, int roleId,int groupId, int grantId ) throws Exception {

            String sql="insert into person_info(person_id, " +
                    "group_id," +
                    "role_key," +
                    "grant_id) values(?,?,?,?)";
            update(sql, personId,  groupId, roleId, grantId);


    }
    public  List<Person> getPersonRecords(Person p) throws Exception {
        List<Person> members=new ArrayList<>();
        String name=p.getName().replaceAll("[,.]", "");
        for(Person person:   getAllActiveMembers()){
            try {
                String str1 = person.getName().replaceAll("[,.]", "");
                if (name.equalsIgnoreCase(str1) ||
                        p.getEmail().toLowerCase().equalsIgnoreCase(person.getEmail().toLowerCase())) {
                    members.add(person);
                }

            }catch (Exception e){e.printStackTrace();}
        }
        return members;
    }
    public List<Person> getPersonByName(String name) throws Exception{
        String sql="select * from person where name_lc=? " ;
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query,name);
    }

    public boolean exists(Person p) throws Exception {
        int id=0;
        List<Person> members=new ArrayList<>();
        members=getPersonByName(p.getName().toLowerCase().trim());
        if(members==null || members.size()==0){
            members=getPersonRecords(p);

            if (members==null || members.size()==0) {
                return false;
            }
        }

        return true;

    }



    public int insertOrUpdate(Person p) throws Exception {
        int id=0;
        List<Person> members=new ArrayList<>();
        members=getPersonByName(p.getName().toLowerCase().trim());
        if(members==null || members.size()==0){
            members=getPersonRecords(p);

            if (members==null || members.size()==0) {

                id= getNextKey("person_seq");
                p.setId(id);
                try{
                    insert(p);
                }catch (Exception e){
                    e.printStackTrace();
                    return 0;
                }
            }
        }
        if(members!=null && members.size()>0){
            boolean active=false;
            for(Person person: members){
                if(person.getStatus().equalsIgnoreCase("ACTIVE")){
                    active=true;
                    id=person.getId();
                    break;
                }
            }
            if(!active) {
                p.setId(members.get(0).getId());

            }else{
                p.setId(id);

            }
            update(p);
        }

        return id;
    }


    public String getInstitutionName(int id) throws Exception {
        String sql="select institution_name from institution where institution_id=?";
        StringListQuery query= new StringListQuery(this.getDataSource(), sql);
        List<String> names=execute(query, id);
        if(names!=null && names.size()>0){
            return names.get(0);
        }else{
            return "";
        }
    }
    public List<PersonInfo> getPersonInfo(int personId) throws Exception {
        List<PersonInfo> records=getInfoIfBelongsToNIH( personId);
        if(records.size()==0) {
            String sql = "select p.person_id, g.group_name as group_name,g.group_id as group_id,sg.group_type, sg.group_name as subgroup_name,sg.group_id as subgroup_id, r.role , grnt.grant_title, grnt.grant_initiative,grnt.grant_id " +
                    " from scge_group g , person_info i, person p, scge_roles r, scge_group sg, group_associations a, scge_grants grnt " +
                    "                               where p.person_id=i.person_id   " +
                    "                                and sg.group_id=i.group_id   " +
                    "                                and r.role_key=i.role_key   " +
                    "                                  and p.status='ACTIVE'  " +
                    "                               and p.person_id =? " +
                    "                               and a.group_id=g.group_id  " +
                    "                               and a.subgroup_id=sg.group_id " +
                    "                               and grnt.grant_id=i.grant_id " +
                    "                               and sg.group_type='subgroup'";


            PersonInfoQuery q = new PersonInfoQuery(this.getDataSource(), sql);
            return execute(q, personId);
        }
        return records;
    }
    public List<PersonInfo> getInfoIfBelongsToNIH(int personId) throws Exception {
        String sql="select p.person_id, g.group_name as group_name,g.group_id as group_id,sg.group_type,  " +
                "sg.group_name as subgroup_name,sg.group_id as subgroup_id, r.role \n" +
                "                from scge_group g , person_info i, person p, scge_roles r, scge_group sg, group_associations a " +
                "                                               where p.person_id=i.person_id    " +
                "                                                and sg.group_id=i.group_id    " +
                "                                                and r.role_key=i.role_key    " +
                "                                                  and p.status='ACTIVE'   " +
                "                                                and p.person_id =? " +
                "                                               and a.group_id=g.group_id  " +
                "                                               and a.subgroup_id=sg.group_id " +
                "                                               and sg.group_type='subgroup' " +
                "                                               and g.group_name='NIH' " +

                "                                  ";
        PersonInfoQuery q=new PersonInfoQuery(this.getDataSource(), sql);
       return   execute(q,personId);

    }

}
