package com.example.restapi.Product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductRepository;
import com.example.restapi.implement.product.ProductDto;
import com.example.restapi.implement.product.ProductResponse;
import com.example.restapi.implement.product.ProductServiceImplement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
// todo test code is yet
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImplement productServiceImplement;

    @Test
    @DisplayName("000.getProductByCategoryのPagenationの変数（PageNoとPageSize）をテスト")
    public void getProductByCategoryCheck(){
        int pageNo = 0;
        int pageSize = 4;
        int categoryId =1;

        // create List<Product>
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1,"Sardin","This is a Sardin",false, 1,200,0.01f,0,"aa"));
        productList.add(new Product(2,"Maguro","This is a Maguro",false, 1,200,0.01f,0,"maguro_image"));
        productList.add(new Product(3,"Salmon","This is a Salmon",false, 1,200,0.01f,0,"maguro_image"));
        productList.add(new Product(4,"Tuna","This is a Sardin",false, 1,200,0.01f,0,"maguro_image"));

        // convert List<Product> to Page<Product>
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), productList.size());
        Page<Product> productPage = new PageImpl<>(productList.subList(start,end),pageable,productList.size());

        // do service
        Mockito.doReturn(productPage).when(productRepository).getProductsByCategory(categoryId,pageable);
        ProductResponse actualResponse =  productServiceImplement.getProductsByCategory(pageNo,pageSize,categoryId);


        // create expected Data
        List<ProductDto> content = productList.stream().map(product -> productServiceImplement.mapToDTO(product)).collect(Collectors.toList());
        ProductResponse expectedReponse = new ProductResponse(content,pageNo,pageSize,4,1,true,0,null);
        //ProductResponse(List< ProductDto > content, int pageNo, int pageSize, long totalElements, int totalPages, boolean last, int categoryId, String categoryName)

        // compare actual and expected
        assert(actualResponse.equals(expectedReponse));

        // assetThat(productResponse.getContent().get(0).getCategoryId())
        // https://github.com/namhaminh/shopme/blob/master/ShopmeWebParent/ShopmeBackEnd/src/test/java/com/shopme/admin/category/CategoryServiceTests.java

    }



}
