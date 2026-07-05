package in.heuton.skipper.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private LocalDate releaseDate;
    private Boolean productAvailable;
    private Integer stockQuantity;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

    public Product(Long id)
    {
        this.id = id;
    }
}
