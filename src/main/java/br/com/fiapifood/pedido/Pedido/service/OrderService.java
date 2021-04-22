package br.com.fiapifood.pedido.Pedido.service;

import br.com.fiapifood.pedido.Pedido.model.dto.OrderRequestDTO;
import br.com.fiapifood.pedido.Pedido.model.dto.OrderResponseDTO;
import br.com.fiapifood.pedido.Pedido.domain.OrderStatus;
import br.com.fiapifood.pedido.Pedido.domain.ShippingOrderStatus;
import br.com.fiapifood.pedido.Pedido.entity.PurchaseOrder;
import br.com.fiapifood.pedido.Pedido.eventhandlers.OrderEventPublisherService;
import br.com.fiapifood.pedido.Pedido.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
	/*
	 * The below code can be further updated to use actual DB and make it working with DB.
	 */
    // product price map
    private static final Map<Integer, Integer> PRODUCT_PRICE =  Map.of(
            1, 100,
            2, 200,
            3, 300
    );

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private OrderEventPublisherService eventPublisherService;

    public PurchaseOrder createOrder(OrderRequestDTO orderRequestDTO){
        PurchaseOrder purchaseOrder = this.purchaseOrderRepository.save(this.dtoToEntity(orderRequestDTO));
        this.eventPublisherService.raiseOrderCreatedEvent(purchaseOrder);
        return purchaseOrder;
    }

    public List<OrderResponseDTO> getAll() {
        return this.purchaseOrderRepository.findAll()
                                .stream()
                                .map(this::entityToDto)
                                .collect(Collectors.toList());
    }

    public Optional<PurchaseOrder> findById(Integer id) {
        return this.purchaseOrderRepository.findById(id);
    }

    private PurchaseOrder dtoToEntity(final OrderRequestDTO dto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(PRODUCT_PRICE.get(purchaseOrder.getProductId()));
        purchaseOrder.setPincode(dto.getPincode());
        purchaseOrder.setShippingstatus(ShippingOrderStatus.SHIPPING_CREATE_ORDER_REQUESTED);
        return purchaseOrder;
    }

    private OrderResponseDTO entityToDto(final PurchaseOrder purchaseOrder){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(purchaseOrder.getId());
        dto.setProductId(purchaseOrder.getProductId());
        dto.setUserId(purchaseOrder.getUserId());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setPrice(purchaseOrder.getPrice());
        dto.setPincode(purchaseOrder.getPincode());
        dto.setShippingstatus(purchaseOrder.getShippingstatus());
        return dto;
    }

}