package in.heuton.skipper.service;

import in.heuton.skipper.dto.ProductRequestDTO;
import in.heuton.skipper.dto.ProductResponseDTO;
import in.heuton.skipper.entity.Product;
import in.heuton.skipper.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService
{
    private IProductRepository prodRepo;
    private ModelMapper modelMapper;

    public ProductService(IProductRepository prodRepo, ModelMapper modelMapper)
    {
        this.prodRepo = prodRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts()
    {
        List<Product> products = prodRepo.findAll();
        return products.stream()
                .map(product ->
                {
                    ProductResponseDTO dto =
                            modelMapper.map(product, ProductResponseDTO.class);

                    dto.setImageUrl("http://localhost:8080/products/"
                            + product.getId() + "/image");

                    return dto;
                })
                .toList();
    }

    @Override
    public ProductResponseDTO getProduct(Long id)
    {
        return null;
    }
}
