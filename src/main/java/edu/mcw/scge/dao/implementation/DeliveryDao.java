package edu.mcw.scge.dao.implementation;

import edu.mcw.scge.dao.AbstractDAO;
import edu.mcw.scge.dao.spring.DeliveryQuery;
import edu.mcw.scge.datamodel.Delivery;

import java.util.List;

public class DeliveryDao extends AbstractDAO {
    public List<Delivery> getDeliverySystemsById(int deliverySystemId) throws  Exception{
        String sql="select * from delivery_system where ds_id=?";
        DeliveryQuery q=new DeliveryQuery(this.getDataSource(), sql);
        return execute(q, deliverySystemId);
    }
    public List<Delivery> getDeliverySystems() throws  Exception{
        String sql="select * from delivery_system";
        DeliveryQuery q=new DeliveryQuery(this.getDataSource(), sql);
        return execute(q);
    }

}
