package br.com.fiapifood.pedido.Pedido.entity;

import br.com.fiapifood.pedido.Pedido.domain.OrderStatus;
import br.com.fiapifood.pedido.Pedido.domain.ShippingOrderStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer price;
    private OrderStatus status;
    private Integer pincode;
    private ShippingOrderStatus shippingstatus;

}