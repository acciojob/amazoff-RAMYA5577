package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private Map<String,Order> orderMap;  //orderId,order
    private  Map<String,DeliveryPartner> deliveryPartnerMap;  //partnerId,partner
    private Map<String,String> orderPartnerPairMap;     //orderId,partnerId

    private Map<String,Order> partnerOrderMap;     //partnerId,his order


    public OrderRepository() {
        this.orderMap=new HashMap<String,Order>();
        this.deliveryPartnerMap=new HashMap<String,DeliveryPartner>();
        this.orderPartnerPairMap=new HashMap<String,String>();
    }

    public void addOrder(Order order){
       orderMap.put(order.getId(),order);
    }

    public void addPartner(String partnerId) {
        DeliveryPartner deliveryPartner=deliveryPartnerMap.get(partnerId);
        deliveryPartnerMap.put(deliveryPartner.getId(),deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId,String partnerId) {
        if (orderMap.containsKey(orderId) && deliveryPartnerMap.containsKey(partnerId)) {
            orderPartnerPairMap.put(orderId, partnerId);
        }
    }

    public Order getOrderById(String id){
        return orderMap.get(id);
    }

    public DeliveryPartner getPartberById(String id){
        return deliveryPartnerMap.get(id);
    }

    public int getOrderCountByPartnerId(String partnerId){
        DeliveryPartner deliveryPartner=deliveryPartnerMap.get(partnerId);
        return deliveryPartner.getNumberOfOrders();
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> list=new ArrayList<>();
        for(String S: orderPartnerPairMap.values()){
            if (orderPartnerPairMap.containsKey(partnerId)){
                list.add(orderPartnerPairMap.get(partnerId));
            }
        }
        return list;
    }
    public List<String> getAllOrders(){
        return new ArrayList<>(orderMap.keySet());
    }

    public int getCountOfUnassignedOrders(){
        int count=0;

        return count;
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(){
        return 1;
    }

    public String getLastDeliveryTimeByPartnerId(){
        return " ";
    }


    public void deletePartnerById(String id){
       deliveryPartnerMap.remove(id);
    }

    public void deleteOrderById(String id){
          orderMap.remove(id);
    }


}
