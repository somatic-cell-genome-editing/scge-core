package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.datamodel.Person;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;

public class PersonDaoTest {
    @Test
    public void getAllActiveMembersTest() throws Exception {
        PersonDao personDao=new PersonDao();
        List<Person> personList=personDao.getAllActiveMembers();
        Assert.assertFalse(personList.isEmpty());
    }

}
