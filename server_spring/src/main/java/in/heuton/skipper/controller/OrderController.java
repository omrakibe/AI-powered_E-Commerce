package in.heuton.skipper.controller;

import in.heuton.skipper.entity.dto.OrderRequest;
import in.heuton.skipper.entity.dto.OrderResponse;
import in.heuton.skipper.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController
{

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest order)
    {
        OrderResponse response = orderService.placeOrder(order);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders()
    {
        return ResponseEntity.ok(orderService.getAllOrderResponses());
    }

}
