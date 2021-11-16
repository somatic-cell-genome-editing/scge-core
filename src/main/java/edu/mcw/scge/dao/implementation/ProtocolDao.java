package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.ProtocolQuery;
import edu.mcw.scge.datamodel.Protocol;

import java.util.List;

public class ProtocolDao extends AbstractDAO {
    public Protocol getProtocolById(long protocolId) throws Exception {
        String sql="select * from protocol where protocol_id=?";
        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        List<Protocol> protocols = execute(q, protocolId);

        if (protocols.size() == 0) {
            throw new Exception ("Protocol " + protocolId + " not found");
        }

        return protocols.get(0);
    }

	public List<Protocol> getProtocols() throws Exception {
        String sql="select * from protocol";
        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        return execute(q);
    }

    public List<Protocol> getProtocolsForObject(long protocolId) throws Exception {
        String sql="select * from protocol p, protocol_associations pa where p.protocol_id=pa.protocol_scge_id and pa.object_scge_id=?";
        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        return  execute(q, protocolId);

    }

}
