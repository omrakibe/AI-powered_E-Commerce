package in.heuton.skipper.service;

import in.heuton.skipper.dto.ProductRequestDTO;
import in.heuton.skipper.dto.ProductResponseDTO;
import in.heuton.skipper.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService
{
    public List<ProductResponseDTO> getAllProducts();

    public ProductResponseDTO getProductById(Long id);

    public Product getProductEntity(Long id);

    public ProductResponseDTO addProduct(ProductRequestDTO dto, MultipartFile file);

    String generateDescription(String name, String category);

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO product, MultipartFile imageFile);

    void deleteProduct(Long prodId);
}
