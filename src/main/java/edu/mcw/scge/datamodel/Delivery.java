package edu.mcw.scge.datamodel;

public class Delivery {
   private int deliverySystemId;
    private String        deliverySystemType;
    private String deliverySystemSubtype;

    public int getDeliverySystemId() {
        return deliverySystemId;
    }

    public void setDeliverySystemId(int deliverySystemId) {
        this.deliverySystemId = deliverySystemId;
    }

    public String getDeliverySystemType() {
        return deliverySystemType;
    }

    public void setDeliverySystemType(String deliverySystemType) {
        this.deliverySystemType = deliverySystemType;
    }

    public String getDeliverySystemSubtype() {
        return deliverySystemSubtype;
    }

    public void setDeliverySystemSubtype(String deliverySystemSubtype) {
        this.deliverySystemSubtype = deliverySystemSubtype;
    }
}
