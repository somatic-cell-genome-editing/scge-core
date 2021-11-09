package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.DeliveryQuery;
import edu.mcw.scge.datamodel.Delivery;

import java.util.List;

public class DeliveryDao extends AbstractDAO {
    public List<Delivery> getDeliverySystemsById(long deliverySystemId) throws  Exception{
        String sql="select * from delivery_system where ds_id=?";
        DeliveryQuery q=new DeliveryQuery(this.getDataSource(), sql);
        return execute(q, deliverySystemId);
    }
	public List<Delivery> getDeliverySystems() throws  Exception{
        String sql="select * from delivery_system order by ds_name";
        DeliveryQuery q=new DeliveryQuery(this.getDataSource(), sql);
        return execute(q);
	}	
    public long insertDelivery(Delivery delivery) throws Exception{

        String sql = "insert into delivery_system ( ds_id, ds_type, ds_subtype, ds_name, ds_source, " +
                "ds_description, ds_lab_id, ds_annotated_map, ds_rrid, " +
                "ds_np_size, ds_mol_targeting_agent,tier ) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        long deliveryId = this.getNextKeyFromSequenceLong("delivery_seq");


        update(sql, deliveryId,delivery.getType(),delivery.getSubtype(),delivery.getName(),
                delivery.getSource(),delivery.getDescription(),
                delivery.getLabId(),delivery.getAnnotatedMap(),
                delivery.getRrid(),delivery.getNpSize(),delivery.getMolTargetingAgent(),delivery.getTier());

        return deliveryId;
    }

    public long getDeliveryId(Delivery delivery) throws Exception {

        String sql = "select * from delivery_system where ds_type =? and ds_name=?";

        List<Delivery> list = DeliveryQuery.execute(this,sql,delivery.getType(),delivery.getName() );
        return list.isEmpty() ? 0 : list.get(0).getId();
    }
    public boolean verifyDeliveryAccess(long deliveryId, int personId) throws Exception {
        String sql="(select d.* from delivery_system d left outer join experiment_record r on d.ds_id=r.ds_id " +
                "left outer join experiment x on x.experiment_id=r.experiment_id " +
                "left outer join study s on s.study_id =x.study_id " +
                "left outer join person_info p on p.group_id=s.group_id " +
                "where p.person_id=?  " +
                "and d.ds_id=?) union " +
                "(select ds.* from delivery_system ds  where ds.ds_id=? and ds.tier=4) ";
        DeliveryQuery q=new DeliveryQuery(this.getDataSource(), sql);
        List<Delivery> deliveryList=execute(q, personId,deliveryId, deliveryId);
        return deliveryList.size()>0;
    }
    public void updateDeliveryTier(int tier, long deliveryId) throws Exception{
        String sql="update delivery_system set tier=? where ds_id=?";
        update(sql, tier, deliveryId);
    }
}
