package br.com.fiapifood.pedido.Pedido.service.saga.shippingservice.eventhandlers;

import br.com.fiapifood.pedido.Pedido.domain.ShippingStatus;
import br.com.fiapifood.pedido.Pedido.model.evt.ShippingEvent;
import br.com.fiapifood.pedido.Pedido.model.evt.ShppingAfterPaymentEvent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class ShippingEventProcessorService {

	/*
	 * The below code can be further updated to use actual DB and make it working with DB.
	 */
    // user - pin code check
    ArrayList<Integer> zipcodes = new ArrayList<Integer>();
    
    @PostConstruct
    private void init() {
    zipcodes.add(202);
    zipcodes.add(20000);
    zipcodes.add(200);
    zipcodes.add(56006);
    zipcodes.add(5999);
    zipcodes.add(20772);
    zipcodes.add(9900);
    zipcodes.add(2660);
    zipcodes.add(96006);
    zipcodes.add(86004);
    zipcodes.add(34);
    }
    
    public ShippingEvent processShippedEvent(ShppingAfterPaymentEvent shippingafterpaymentevent){
        Integer pincode = shippingafterpaymentevent.getPincode();
        System.out.println("XXXXXXXXX Code came to this processShippedEvent: pincode= "+pincode);

        ShippingEvent shippingEvent = new ShippingEvent(shippingafterpaymentevent.getOrderId());
        shippingEvent.setPincode(shippingafterpaymentevent.getPincode());
        if(validatePinCodeDeliverySupported(pincode )) {
            shippingEvent.setStatus(ShippingStatus.SUPPORTED);
        }else{
            shippingEvent.setStatus(ShippingStatus.NOT_SUPPORTED);
        }
        System.out.println("XXXXXXXXX Code going out of processShippedEvent with shippingEvent"+ shippingEvent);
        System.out.println("********* Code going out of processShippedEvent with shippingafterpaymentevent: "+ shippingafterpaymentevent);

        return shippingEvent;
    }

    private boolean validatePinCodeDeliverySupported(Integer shippingPin) {
        System.out.println("XXXXXXXXX Code came to this validatePinCodeDeliverySupported:" + shippingPin);
        if(zipcodes.contains(shippingPin)) {
	        System.out.println("XXXXXXXXX Code came to this validatePinCodeDeliverySupported in true");
        	return true; 
        }
        else 
        	return false;
    }
}