package in.heuton.skipper.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductResponseDTO
{
    private Long id;
    private String name;
    private String description;
    private String brand;
    private Double price;
    private String category;
    private LocalDate releaseDate;
    private Boolean productAvailable;
    private Integer stockQuantity;
    private String imageUrl;
}
