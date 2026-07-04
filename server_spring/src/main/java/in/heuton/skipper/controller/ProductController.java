package in.heuton.skipper.controller;

import in.heuton.skipper.entity.dto.ProductRequestDTO;
import in.heuton.skipper.entity.dto.ProductResponseDTO;
import in.heuton.skipper.entity.Product;
import in.heuton.skipper.service.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        Product product = service.getProductEntity(id);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(product.getImageData());
    }

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestPart ProductRequestDTO product, @RequestPart MultipartFile imageFile)
    {
//        System.out.println(product);
        ProductResponseDTO prod = service.addProduct(product, imageFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }

    @PostMapping("/product/generate-description")
    public ResponseEntity<String> generateDescription(@RequestParam String name, @RequestParam String category, @RequestParam String brand)
    {
        String resp = service.generateDescription(name, category, brand);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestPart ProductRequestDTO product, @RequestPart MultipartFile imageFile, @PathVariable Long id)
    {
        return ResponseEntity.ok(service.updateProduct(id, product, imageFile));
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long prodId)
    {
        service.deleteProduct(prodId);
        return ResponseEntity.ok("Deleted");
    }
}
