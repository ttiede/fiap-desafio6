package br.com.fiapifood.pedido.Pedido.model.dto;

import br.com.fiapifood.pedido.Pedido.domain.OrderStatus;
import br.com.fiapifood.pedido.Pedido.domain.ShippingOrderStatus;
import lombok.Data;

@Data
public class OrderResponseDTO {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus status;
    private Integer pincode;
    private ShippingOrderStatus shippingstatus;
    
}