package br.com.fiapifood.pedido.Pedido.repository;

import br.com.fiapifood.pedido.Pedido.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}