package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ProtocolQuery;
import edu.mcw.scge.datamodel.Protocol;

import java.util.List;

public class ProtocolDao extends AbstractDAO {
    public List<Protocol> getProtocolById(long protocolId) throws Exception {
        String sql="select * from from protocol where protocol_id=?";
        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        return execute(q, protocolId);
    }

	public List<Protocol> getProtocols() throws Exception {
        String sql="select * from protocol";
        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        return execute(q);
    }

}
