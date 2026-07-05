package in.heuton.skipper.entity.dto;

public record OrderItemRequest(
        long productId,
        int quantity
)
{
}
