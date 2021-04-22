package br.com.fiapifood.pedido.Pedido.controller;

import br.com.fiapifood.pedido.Pedido.model.dto.OrderRequestDTO;
import br.com.fiapifood.pedido.Pedido.model.dto.OrderResponseDTO;
import br.com.fiapifood.pedido.Pedido.entity.PurchaseOrder;
import br.com.fiapifood.pedido.Pedido.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDTO dto){
        return this.orderService.createOrder(dto);
    }

    @GetMapping("/all")
    public List<OrderResponseDTO> getOrders(){
        return this.orderService.getAll();
    }


    @GetMapping(
            path = "{id}"
    )
    public ResponseEntity<Optional<PurchaseOrder>> details(@PathVariable("id") Integer orderId) {
        Optional<PurchaseOrder> order = orderService.findById(orderId);
        if (order.isPresent()) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

}