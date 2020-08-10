package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.IntListQuery;
import edu.mcw.scge.dao.spring.PersonQuery;
import edu.mcw.scge.dao.spring.StringListQuery;
import edu.mcw.scge.datamodel.Person;

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
    public void insert(Person p) throws Exception{
        String sql="insert into person values(?,?,?,?,?,?,?,?,?,?,?,current_date,current_date,?,?,?,?)";
        update(sql,
                p.getId(), p.getName(),p.getName().toLowerCase(),
                p.getInstitution(),
                p.getEmail(),p.getEmail().toLowerCase(),
                p.getPhone(), p.getAddress(), p.getGoogleSub(),
                p.getStatus(),
                p.getGrantId(),
                p.getModifiedBy(),
                p.getOtherId(),
                p.getFirstName(),p.getLastName()
        );

    }
    public int generateNewPersonKey() throws Exception {
          return getNextKey("PERSON_SEQ");
    }
    public List<Person> getPerson(Person p) throws Exception{
        String sql="select * from person where name_lc=? and email_lc=?";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
         return execute(query,p.getName().toLowerCase(), p.getEmail().toLowerCase());
    }
    public List<Person> getPersonById(int id) throws Exception{
        String sql="select * from person where person_id=?";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query,id);
    }
    public List<Person> getPersonByGoogleId(String id) throws Exception{
        String sql="select * from person where google_id=?";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query,id);
    }
    public List<Person> getPersonByLastName(String lastName) throws Exception{
        String sql="select * from person where name like '%"+lastName+"%'";
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
        String sql="select * from person where email_lc=? or other_id=? or email=?";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return execute(query, email.toLowerCase(), email, email);
    }
    public List<Person> getAllMembers() throws Exception{
        String sql="select * from person where status='approved'";
        PersonQuery query=new PersonQuery(this.getDataSource(), sql);
        return query.execute();
    }
    public List<String> getPersonGroups(Person p) throws Exception {
        String sql="select g.group_name from scge_group g, person_info r, person p " +
                "where g.group_id=r.group_id " +
                "and r.person_id=p.person_id " +
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
    public void updateGoogleId(String googleId, int personId) throws Exception {
        String sql="update person set google_id=? where person_id=? ";
        update(sql, googleId, personId);
    }
    public void updatePersonNewAttributes(Person person) throws Exception {
        String sql="update person set first_name=?, last_name=?, google_id=?, other_id=? where email=? or email_lc=?";
        update(sql,  person.getFirstName(), person.getLastName(), person.getGoogleSub(), person.getOtherId() ,person.getEmail(),person.getEmail().toLowerCase());
    }
    public int insertOrUpdateGrant(String grantTitle) throws Exception {
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
    }
    public List<String> getRolesByPersonId(int personId, String groupName) throws Exception {
        String sql="select r.role from scge_roles r, person p, person_info pi, scge_group g where " +
                "r.role_key=pi.role_key " +
                "and p.person_id=pi.person_id " +
                "and g.group_id=pi.group_id " +
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
        String sql = "select group_id from scge_group where group_name=? and group_type=?";
        IntListQuery query = new IntListQuery(this.getDataSource(), sql);
        List<Integer> ids = execute(query, groupName.trim(), groupType);
        int id = 0;
        if (ids != null && ids.size() > 0) {
            return ids.get(0);
        }else{
            id=getNextKey("group_seq");
            SCGEGroup group=new SCGEGroup();
            group.setGroupId(id);
            group.setGroupName(groupName);
            group.setGroupType(groupType);
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


    public void delete(Person person){

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

            if (row.getRowNum() != 0) {
                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    int colIndex = cell.getColumnIndex();
                    if (colIndex == 0) {
                       projectType=cell.getStringCellValue();
                       groupId=getGroupId(projectType, "group");
                    }
                    if (colIndex == 1) {
                        grantTitle=cell.getStringCellValue();
                       grantId= insertOrUpdateGrant(grantTitle);
                        p.setGrantId(grantId);
                      subgroupId= getGroupId(grantTitle, "subgroup");
                    }

                    if (colIndex == 2) {
                        name=cell.getStringCellValue();
                        p.setName(name);
                        p.setName_lc(name.toLowerCase());
                        if(!names.contains(name.toLowerCase())){
                            names.add(name.toLowerCase());
                        }
                    }
                    if (colIndex == 3) {

                        if (cell.getStringCellValue().equalsIgnoreCase("x"))
                            p.setStatus("approved");
                        else p.setStatus("processing");
                    }
                    if (colIndex == 4) {
                        String role1=cell.getStringCellValue().toLowerCase();
                        if(role1.contains("administrative")){
                            role1="administrative contact";
                        }
                       String role= getRole(role1);
                        if(!role.equalsIgnoreCase("member")){
                            roles.addAll(Arrays.asList(role, "member"));
                        }else{
                            roles.addAll(Arrays.asList(role));
                        }
                    /*    if (cell.getStringCellValue().equalsIgnoreCase("contact pi")) {
                           roles.addAll(Arrays.asList(cell.getStringCellValue().toLowerCase(), "member"));
                        } else {
                           roles.addAll(Arrays.asList("member"));
                        }*/
                    }
                    if (colIndex == 5) {

                        if (cell.getStringCellValue().equalsIgnoreCase("x")) {
                              roles.add("voting member");

                        }
                    }
                    if (colIndex == 7) {
                        p.setEmail(cell.getStringCellValue());
                        p.setEmail_lc(cell.getStringCellValue().toLowerCase());
                    }
                    if (colIndex == 8) {
                        institution=cell.getStringCellValue();
                      institutionId=insertOrUpdateInstitution(institution);
                        p.setInstitution(institutionId);
                    }
                    if (colIndex == 9) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                p.setPhone(String.valueOf(cell.getNumericCellValue()));
                                break;
                            case Cell.CELL_TYPE_STRING:
                                p.setPhone(cell.getStringCellValue());
                        }
                    }


                }

                p.setRoles(roles);
             //   persons.add(p);
            //   if(name.equalsIgnoreCase("Dianna Malvey")) {
                int personId = insertOrUpdate(p);
                int defaultGoupId=getGroupId("consortium group", "group");

          //      System.out.println("default Group: "+ defaultGoupId+"\tgroup: "+ groupId+"\tsubgroup:"+subgroupId);
                gdao.makeAssociations(defaultGoupId, groupId);
                insertPersonInfo(personId,Arrays.asList(1), defaultGoupId); //person added to default group as 'member'
                List<Integer> roleIds = getRolesIds(roles);
                if(groupId!=0 && personId!=0) {
                    insertPersonInfo(personId, Arrays.asList(1), groupId);
                }
                if(subgroupId!=0 && personId!=0)
                    insertPersonInfo(personId, roleIds, subgroupId);
                gdao.makeAssociations(groupId, subgroupId);
            }

           // }
        }
    fs.close();
        System.out.println(persons.size());

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
    public void insertPersonInfo(int personId, List<Integer> roleIds,int groupId ) throws Exception {
       for(int role:roleIds){
           if(!isPersonInfoExists(personId, role, groupId)){
               insertPersonInfo(personId, role, groupId);
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

    public void insertPersonInfo(int personId, int roleId,int groupId ) throws Exception {

            String sql="insert into person_info values(?,?,?)";
            try {
                update(sql, personId,  groupId, roleId);
            }catch (Exception e){
                System.err.println("person:"+ personId+"\n================================");
                e.printStackTrace();
            }

    }
    public int insertOrUpdate(Person p) throws Exception {
        int id=0;
        List<Person> members=getPerson(p);
        if(members==null || members.size()==0 ){
            id= getNextKey("person_seq");
            p.setId(id);
            try{
                insert(p);
            }catch (Exception e){
                System.out.println(p.getName()+"\t"+ p.getEmail());
                e.printStackTrace();
                return 0;
            }

        }else{
            id=members.get(0).getId();
        }
        return id;
    }
    public String getInstitutionName(int id) throws Exception {
        String sql="select institution_name from institution where institution_id=?";
        StringListQuery query= new StringListQuery(this.getDataSource(), sql);
        List<String> names=execute(query, id);
        if(names!=null || names.size()>0){
            return names.get(0);
        }else{
            return "";
        }
    }
    public static void main(String[] args) throws Exception {
       PersonDao dao=new PersonDao();
        try {
            dao.insertFromFile("data/directory.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!!");
    }
}
