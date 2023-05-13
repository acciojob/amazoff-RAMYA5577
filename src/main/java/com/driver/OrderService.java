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

    public void addOrder(Order order){  //1
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId) {  //2
        orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId,String partnerId) {  //3
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId){  //4
       return orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String id){  //5
        return orderRepository.getPartberById(id);
    }

    public int getOrderCountByPartnerId(String id){  //6
       return orderRepository.getOrderCountByPartnerId(id);
    }

    public List<String> getOrdersByPartnerId(String id){  //7
         return orderRepository.getOrdersByPartnerId(id);
    }
    public List<String> getAllOrders(){  //8
        return orderRepository.getAllOrders();
    }

    public int getCountOfUnassignedOrders(){  //9
        return orderRepository.getCountOfUnassignedOrders();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){  //10
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){  //11
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }


    public void deletePartnerById(String id){   //12
        orderRepository.deletePartnerById(id);
    }


    public void deleteOrderById(String id){  //13
         orderRepository.deleteOrderById(id);
    }


}
