package in.heuton.skipper.controller;

import in.heuton.skipper.dto.ProductResponseDTO;
import in.heuton.skipper.entity.Product;
import in.heuton.skipper.service.IProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController
{
    private IProductService service;

    public ProductController(IProductService service)
    {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct()
    {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id)
    {

        System.out.println("HIT IMAGE API: " + id);

        Product product = service.getProductEntity(id);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(product.getImageData());
    }
}
