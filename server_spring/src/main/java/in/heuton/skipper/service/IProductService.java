package in.heuton.skipper.service;

import in.heuton.skipper.dto.ProductRequestDTO;
import in.heuton.skipper.dto.ProductResponseDTO;
import in.heuton.skipper.entity.Product;

import java.util.List;

public interface IProductService
{
    public List<ProductResponseDTO> getAllProducts();

    public ProductResponseDTO getProductById(Long id);

    public Product getProductEntity(Long id);
}
