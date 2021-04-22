package br.com.fiapifood.pedido.Pedido.service.saga.paymentservice.configuration;

import br.com.fiapifood.pedido.Pedido.model.evt.OrderEvent;
import br.com.fiapifood.pedido.Pedido.model.evt.PaymentEvent;
import br.com.fiapifood.pedido.Pedido.service.saga.paymentservice.eventhandlers.OrderEventProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class PaymentServiceConfig {

    @Autowired
    private OrderEventProcessorService orderEventProcessorService;

    @Bean
    public Function<OrderEvent, PaymentEvent> orderEventProcessor(){
        return orderEventProcessorService::processOrderEvent;
    }

}