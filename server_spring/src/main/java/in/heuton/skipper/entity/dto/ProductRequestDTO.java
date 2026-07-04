package in.heuton.skipper.entity.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductRequestDTO
{
    private String name;
    private String description;
    private String brand;
    private Double price;
    private String category;
    private LocalDate releaseDate;
    private Boolean productAvailable;
    private Integer stockQuantity;
}
