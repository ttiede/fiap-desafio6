package br.com.fiapifood.pedido.Pedido.model.evt;

import lombok.Data;

@Data
public class OrderEvent {
    private Integer orderId;
    private Integer userId;
    private Integer price;
}