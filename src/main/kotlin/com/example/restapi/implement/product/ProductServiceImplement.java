package com.example.restapi.implement.product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductRepository;
import com.example.restapi.domain.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @brief: This Class is Service class in Architecture Controller-Service-Repository Pattern
 *
 * @description　Add business logic to data obtained from ProductRepositories.
 *               The data transfer to ProductRestController.
 *
 * @Auther RYA234
 *
 * @Entity: {@link  Product}
 * @UseCase: {@link ProductService}
 */
@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    ProductRepository productRepository;


    @Override
    public ProductResponse getProductsByCategory(int pageNo,int pageSize,int CategoryId){
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Product> products = productRepository.getProductsByCategory(CategoryId,pageable);
        List<Product> productsList = products.getContent();

        // convert to ProductDto to ProductResponse
        List<ProductDto> content = productsList.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());

        return productResponse;
    }

    // convert Entity into DTO
    public ProductDto mapToDTO(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setInStock(product.isInStock());
        productDto.setCategoryId(product.getCategoryId());
        productDto.setPrice(product.getPrice());
        productDto.setTaxRate(product.getTaxRate());
        productDto.setDiscountPercent(product.getDiscountPercent());
        productDto.setImage(product.getImage());

        return productDto;
    }

    // convert DTO to Entity
    private Product mapToEntity(ProductDto productDto){
        Product product = new Product();

        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
       // product.setInStock(productDto.isInStock());
        product.setInStock(true);
        product.setCategoryId(productDto.getCategoryId());
        product.setPrice(productDto.getPrice());
        product.setTaxRate(productDto.getTaxRate());
        product.setDiscountPercent(productDto.getDiscountPercent());
        product.setImage(productDto.getImage());

        return product;
    }
}
