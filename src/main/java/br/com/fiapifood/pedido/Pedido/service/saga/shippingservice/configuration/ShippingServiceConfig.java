package br.com.fiapifood.pedido.Pedido.service.saga.shippingservice.configuration;

import br.com.fiapifood.pedido.Pedido.model.evt.ShippingEvent;
import br.com.fiapifood.pedido.Pedido.model.evt.ShppingAfterPaymentEvent;
import br.com.fiapifood.pedido.Pedido.service.saga.shippingservice.eventhandlers.ShippingEventProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ShippingServiceConfig {

    @Autowired
    private ShippingEventProcessorService shippingEventProcessorService;

    @Bean
    public Function<ShppingAfterPaymentEvent, ShippingEvent> shippingEventProcessor(){
        return shippingEventProcessorService::processShippedEvent;
    }

}