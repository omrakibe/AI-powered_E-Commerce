package in.heuton.skipper.service;

import in.heuton.skipper.dto.ProductRequestDTO;
import in.heuton.skipper.dto.ProductResponseDTO;
import in.heuton.skipper.entity.Product;
import in.heuton.skipper.exception.ProductNotFoundException;
import in.heuton.skipper.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService implements IProductService
{
    private IProductRepository prodRepo;
    private ModelMapper modelMapper;
    private ChatClient chatClient;

    ChatMemory chatMemory = MessageWindowChatMemory.builder().build();

    public ProductService(IProductRepository prodRepo, ModelMapper modelMapper, ChatClient.Builder builder)
    {
        this.prodRepo = prodRepo;
        this.modelMapper = modelMapper;
        this.chatClient = builder
                .defaultAdvisors(
                        MessageChatMemoryAdvisor
                                .builder(chatMemory)
                                .build()
                )
                .build();
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

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO dto, MultipartFile image)
    {
        try
        {
            Product product = modelMapper.map(dto, Product.class);
            if (image != null && !image.isEmpty())
            {
                product.setImageName(image.getOriginalFilename());
                product.setImageType(image.getContentType());
                product.setImageData(image.getBytes());
            }

            prodRepo.save(product);

            ProductResponseDTO response = modelMapper.map(product, ProductResponseDTO.class);
            return response;
        } catch (IOException ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public String generateDescription(String name, String category)
    {
        String templ = """
                I want description for adding product in portal for product {name} and  category {category}.
                Give good, professional and simple description that even non-educated person could understand it that means customer friendly.
                include the features and benefits of product.
                Description should be in one para within 3-4 lines and don't mention in response that which category its related.
                """;
//        System.out.println(templ);
        PromptTemplate promptTemplate = new PromptTemplate(templ);
        Prompt prompt = promptTemplate.create(Map.of("name", name, "category", category));
//        System.out.println(prompt);
        return chatClient.prompt(prompt)
                .call()
                .content();
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto, MultipartFile image)
    {
        Product existingProduct = prodRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        try
        {
            modelMapper.map(dto, existingProduct);
            if (image != null && !image.isEmpty())
            {
                existingProduct.setImageName(image.getOriginalFilename());
                existingProduct.setImageType(image.getContentType());
                existingProduct.setImageData(image.getBytes());
            }

            prodRepo.save(existingProduct);

            return modelMapper.map(existingProduct, ProductResponseDTO.class);
        } catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
