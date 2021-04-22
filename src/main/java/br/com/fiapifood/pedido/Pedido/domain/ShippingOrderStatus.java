package br.com.fiapifood.pedido.Pedido.domain;

public enum ShippingOrderStatus {

    SHIPPING_CREATE_ORDER_REQUESTED,
    ORDER_SHIPPABLE,
    ORDER_NOT_SHIPPABLE,
    ORDER_NOT_COMPLETE_THUS_NOT_SHIPPABLE
}