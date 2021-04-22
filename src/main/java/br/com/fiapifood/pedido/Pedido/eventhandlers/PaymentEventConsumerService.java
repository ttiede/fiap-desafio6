package br.com.fiapifood.pedido.Pedido.eventhandlers;

import br.com.fiapifood.pedido.Pedido.domain.OrderStatus;
import br.com.fiapifood.pedido.Pedido.domain.PaymentStatus;
import br.com.fiapifood.pedido.Pedido.model.evt.PaymentEvent;
import br.com.fiapifood.pedido.Pedido.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PaymentEventConsumerService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    
    @Autowired
    private ShippingEventPublisherService seps;

    @Transactional
    public void consumePaymentEvent(PaymentEvent paymentEvent){
        this.purchaseOrderRepository.findById(paymentEvent.getOrderId())
                    .ifPresent(purchaseOrder -> {
                        purchaseOrder.setStatus(paymentEvent.getStatus().equals(PaymentStatus.APPROVED) ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED);
                        this.purchaseOrderRepository.save(purchaseOrder);
                        System.out.println("Order Event Completed !!!");
                        System.out.println("Raise Shipping Event !!!");
                        seps.raiseShippingCreatedEvent(purchaseOrder);
                    });
        
    }

}