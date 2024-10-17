package uz.urinov.app.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.app.order.entity.OrderEntity;
import uz.urinov.app.order.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
    void deleteAllByOrderId(Long id);
}
