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

    public List<Protocol> getProtocolsForObject(long objectId) throws Exception {
        String sql="select * from protocol p, protocol_associations pa where p.protocol_id=pa.protocol_scge_id and pa.object_scge_id=?";
        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        return  execute(q, objectId);

    }

    public List<Protocol> getProtocolsNotAssociatedToObject(long objectId) throws Exception {
        //String sql="select * from protocol p, protocol_associations pa where p.protocol_id=pa.protocol_scge_id and pa.object_scge_id=?";
        String sql = "select * from protocol p where p.protocol_id not in ( ";

         sql += " select p.protocol_id from protocol p, protocol_associations pa where p.protocol_id=pa.protocol_scge_id and pa.object_scge_id=? ";

        sql += ")";



        ProtocolQuery q= new ProtocolQuery(this.getDataSource(), sql);
        return  execute(q, objectId);

    }

    public long insertProtocol(Protocol protocol) throws Exception{

        String sql = "insert into protocol ( title,description,protocol_id,tier," +
                "filename,xref,keywords )" +
                "values (?,?,?,?,?,?,?)";

        long protocolId = this.getNextKeyFromSequence("protocol_seq");

        update(sql, protocol.getTitle(),protocol.getDescription(),protocolId,protocol.getTier(),protocol.getFilename(),
                protocol.getXref(),protocol.getKeywords());

        return protocolId;
    }

    public long insertProtocolAssociation(long protocolId, long objectId) throws Exception{

        String sql = "insert into protocol_associations ( object_scge_id, protocol_scge_id )" +
                "values (?,?)";

        update(sql, objectId, protocolId);

        return protocolId;
    }

    public long deleteProtocolAssociation(long protocolId, long objectId) throws Exception{

        String sql = "delete from protocol_associations where object_scge_id=? and protocol_scge_id=?";

        update(sql, objectId, protocolId);

        return protocolId;
    }


    public void updateProtocol(Protocol protocol) throws Exception{

        String sql = "update protocol set title=?,description=?,tier=?," +
                "filename=?,xref=?,keywords=? where protocol_id = ?";



        update(sql, protocol.getTitle(),protocol.getDescription(),protocol.getTier(),protocol.getFilename(),
                protocol.getXref(),protocol.getKeywords(),protocol.getId());

    }

    public long getProtocolId(Protocol protocol) throws Exception {

        String sql = "select * from protocol where title =? and filename=?";

        List<Protocol> list = ProtocolQuery.execute(this,sql,protocol.getTitle(), protocol.getFilename() );
        return list.isEmpty() ? 0 : list.get(0).getId();
    }

}
