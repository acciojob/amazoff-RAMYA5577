package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId) {
        orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId,String partnerId) {
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId){
       return orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String id){
        return orderRepository.getPartberById(id);
    }

    public int getOrderCountByPartnerId(String id){
       return orderRepository.getOrderCountByPartnerId(id);
    }

    public List<String> getOrdersByPartnerId(String id){
         return orderRepository.getOrdersByPartnerId(id);
    }
    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId();
    }

    public String getLastDeliveryTimeByPartnerId(){
        return orderRepository.getLastDeliveryTimeByPartnerId();
    }


    public void deletePartnerById(String id){
        orderRepository.deletePartnerById(id);
    }


    public void deleteOrderById(String id){
         orderRepository.deleteOrderById(id);
    }


}
