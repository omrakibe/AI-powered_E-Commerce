package in.heuton.skipper.entity.dto;

import lombok.Data;

@Data
public class ProductImageDTO
{
    private String imageName;
    private String imageType;
    private byte[] imageData;
}
