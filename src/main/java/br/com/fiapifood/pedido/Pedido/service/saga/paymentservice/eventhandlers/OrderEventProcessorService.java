package br.com.fiapifood.pedido.Pedido.service.saga.paymentservice.eventhandlers;

import br.com.fiapifood.pedido.Pedido.domain.PaymentStatus;
import br.com.fiapifood.pedido.Pedido.model.evt.OrderEvent;
import br.com.fiapifood.pedido.Pedido.model.evt.PaymentEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderEventProcessorService {

    // user - credit limit
    public static final Map<Integer, Integer> userMap = new HashMap<>();

    static {
        userMap.put(1, 1000);
        userMap.put(2, 2000);
        userMap.put(3, 1000);
        userMap.put(4, 1000);
        userMap.put(5, 1000);
    }

    public PaymentEvent processOrderEvent(OrderEvent orderEvent){
        var price = orderEvent.getPrice();
        var creditLimit = userMap.get(orderEvent.getUserId());
        PaymentEvent paymentEvent = new PaymentEvent(orderEvent.getOrderId());
        if(creditLimit >= price){
            paymentEvent.setStatus(PaymentStatus.APPROVED);
            userMap.computeIfPresent(orderEvent.getUserId(), (k, v) -> v - price);
        }else{
            paymentEvent.setStatus(PaymentStatus.REJECTED);
        }
        System.out.println("XXXXXXXXX Code going out of processOrderEvent with paymentEvent"+ paymentEvent);
        System.out.println("XXXXXXXXX Code going out of processOrderEvent with orderEvent"+ orderEvent);

        return paymentEvent;
    }

}