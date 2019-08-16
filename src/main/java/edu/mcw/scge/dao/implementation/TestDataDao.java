package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.TestDataQuery;
import edu.mcw.scge.datamodel.TestData;

import java.util.List;

/**
 * Created by jthota on 8/16/2019.
 */
public class TestDataDao extends AbstractDAO {
    public List<TestData> getTestData() throws Exception {
        String sql="select * from rgd_test";
        TestDataQuery query=new TestDataQuery(this.getDataSource(), sql);
        List<TestData> testDataList=query.execute();
        System.out.println("TEST DATA SIZE: " +testDataList.size());
        return testDataList;
    }
    public void insert(String name, String symbol) throws Exception {
        String sql="insert into rgd_test values(?,?)";
        update(sql, name, symbol);

    }
}
