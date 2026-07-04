package in.heuton.skipper.entity.dto;

public record OrderItemRequest(
        int productId,
        int quantity
)
{
}
