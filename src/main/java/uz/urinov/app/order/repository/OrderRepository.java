package uz.urinov.app.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.urinov.app.order.entity.OrderEntity;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> , JpaSpecificationExecutor<OrderEntity> {

    Optional<OrderEntity> findByIdAndCreatedBy(Long id, String ownerId);
}
