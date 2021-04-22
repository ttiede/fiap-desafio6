package br.com.fiapifood.pedido.Pedido.model.dto;

import lombok.Data;

@Data
public class OrderRequestDTO {

    private Integer userId;
    private Integer productId;
    private Integer pincode;

}