package in.heuton.skipper.service;

import in.heuton.skipper.dto.ProductRequestDTO;
import in.heuton.skipper.dto.ProductResponseDTO;
import in.heuton.skipper.entity.Product;
import in.heuton.skipper.exception.ProductNotFoundException;
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

        if (products.isEmpty())
        {
            throw new ProductNotFoundException("No products found");
        }

        return products.stream()
                .map(product ->
                {
                    ProductResponseDTO dto =
                            modelMapper.map(product, ProductResponseDTO.class);

                    return dto;
                })
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(Long id)
    {
        Product product = prodRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        ProductResponseDTO response = modelMapper.map(product, ProductResponseDTO.class);

        return response;
    }

    @Override
    public Product getProductEntity(Long id)
    {

        return prodRepo.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id));
    }
}
