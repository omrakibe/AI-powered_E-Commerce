package in.heuton.skipper.service;

import in.heuton.skipper.dto.ProductRequestDTO;
import in.heuton.skipper.dto.ProductResponseDTO;

import java.util.List;

public interface IProductService
{
    public List<ProductResponseDTO> getAllProducts();

    public ProductResponseDTO getProduct(Long id);
}
