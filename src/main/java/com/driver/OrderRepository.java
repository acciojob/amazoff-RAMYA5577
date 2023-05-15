package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private Map<String,Order> ordersDb;  //orderId,order
    private  Map<String,DeliveryPartner> deliveryPartnersDb;  //partnerId,partner
    private Map<String,String> orderPartnerDb;     //orderId,partnerId

    private Map<String,List<String>> partnerOrdersDb;     //partnerId,ListOfOrderIds


    public OrderRepository() {
        this.ordersDb=new HashMap<String,Order>();
        this.deliveryPartnersDb=new HashMap<String,DeliveryPartner>();
        this.orderPartnerDb=new HashMap<String,String>();
        this.partnerOrdersDb=new HashMap<String,List<String>>();
    }

    public void addOrder(Order order){  //1
       ordersDb.put(order.getId(),order);
    }

    public void addPartner(String partnerId) {  //2
        deliveryPartnersDb.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPair(String orderId,String partnerId) {  //3
        if(ordersDb.containsKey(orderId) && deliveryPartnersDb.containsKey(partnerId)){
            orderPartnerDb.put(orderId,partnerId);
            List<String> currentOrders=new ArrayList<>();
            if(partnerOrdersDb.containsKey(partnerId)){
                currentOrders=partnerOrdersDb.get(partnerId);
            }
            currentOrders.add(orderId);
            partnerOrdersDb.put(partnerId,currentOrders);

            //increase the no.of orders of partner
            DeliveryPartner deliveryPartner=deliveryPartnersDb.get(partnerId);
            deliveryPartner.setNumberOfOrders(currentOrders.size());
        }
    }

    public Order getOrderById(String id){  //4
        return ordersDb.get(id);
    }

    public DeliveryPartner getPartberById(String id){  //5
        return deliveryPartnersDb.get(id);
    }

    public int getOrderCountByPartnerId(String partnerId){   //6
        return partnerOrdersDb.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId){  //7
        return partnerOrdersDb.get(partnerId);
    }
    public List<String> getAllOrders(){   //8
        List<String> orders=new ArrayList<>();
       for(String order:ordersDb.keySet()){
           orders.add(order);
       }
        return orders;
    }

    public int getCountOfUnassignedOrders(){  //9
        return ordersDb.size()-orderPartnerDb.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){  //10
       int count=0;
       List<String> orders=partnerOrdersDb.get(partnerId);

       for(String orderId: orders){
           int deliveryTime=ordersDb.get(orderId).getDeliveryTime();
           int a=Integer.parseInt(time);
           if(deliveryTime>a)
               count++;
       }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String id){  //11
        int maxTime=0;
        List<String> orders=partnerOrdersDb.get(id);
        for(String orderId:orders){
            int currentTime=ordersDb.get(orderId).getDeliveryTime();
            maxTime=Math.max(maxTime,currentTime);
        }
        String s=Integer.toString(maxTime);
        return s;
    }


    public void deletePartnerById(String id){  //12
       deliveryPartnersDb.remove(id);
       List<String> listOfOrders=partnerOrdersDb.get(id);
       partnerOrdersDb.remove(id);

       for(String order: listOfOrders){
           orderPartnerDb.remove(order);
       }
    }

    public void deleteOrderById(String id){  //13
          ordersDb.remove(id);
          String partnerId=orderPartnerDb.get(id);
          orderPartnerDb.remove(id);

          partnerOrdersDb.get(id).remove(id);

          deliveryPartnersDb.get(id).setNumberOfOrders(partnerOrdersDb.get(id).size());
    }
}
