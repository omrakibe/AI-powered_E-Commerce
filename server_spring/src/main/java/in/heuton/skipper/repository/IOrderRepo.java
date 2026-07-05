package in.heuton.skipper.repository;

import in.heuton.skipper.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepo extends JpaRepository<Order, Long>
{
    Optional<Order> findByOrderId(String orderId);
}
