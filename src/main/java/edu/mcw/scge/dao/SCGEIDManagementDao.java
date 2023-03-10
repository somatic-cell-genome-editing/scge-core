package edu.mcw.scge.dao;

import edu.mcw.scge.dao.spring.StringListQuery;

import java.util.List;

public class SCGEIDManagementDao extends AbstractDAO {
    public String getObjectTypeById(long scgeId) throws Exception {
        String idStr=String.valueOf(scgeId);
        String idKey=idStr.substring(0,2);
        String sql="select object_name from object_types where object_key=?";
        StringListQuery query=new StringListQuery(this.getDataSource(), sql);
        List<String> idTypes=execute(query, Integer.parseInt(idKey));
        if(idTypes.size()>0){
            return idTypes.get(0);
        }else{
            return "";
        }
    }
}
