package com.example.restapi.Product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductRepository;
import com.example.restapi.implement.product.ProductDto;
import com.example.restapi.implement.product.ProductResponse;
import com.example.restapi.implement.product.ProductServiceImplement;
import org.junit.jupiter.api.BeforeEach;
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


    List<Product> productList = new ArrayList<>();

    @BeforeEach
    public void prepareAlldata(){
        // create all data by ProductDto
        // create List<Product>
        // category_id
        // 1= Fish
        // 2= Meat
        // 3= vegetable
      //  List<Product> productList = new ArrayList<>();
        productList.add(new Product(1,"Sardin","This is a Sardin",false, 1,200,0.01f,0,"aa"));
        productList.add(new Product(2,"Maguro","This is a Maguro",false, 1,200,0.01f,0,"maguro_image"));
        productList.add(new Product(3,"Salmon","This is a Salmon",false, 1,200,0.01f,0,"maguro_image"));
        productList.add(new Product(4,"Tuna","This is a Tuna",false, 1,200,0.01f,0,"maguro_image"));
        productList.add(new Product(5,"Pork","This is a Pork",false, 2,330,0.01f,0,"porkImage"));
        productList.add(new Product(6,"Beef","This is a Beef",false, 2,400,0.01f,0,"Beef_image"));
        productList.add(new Product(7,"Chicken","This is a chicken",false, 2,800,0.01f,0,"Chicken_image"));
        productList.add(new Product(8,"Spam","This is a Spam",false, 2,200,0.01f,0,"Spam_image"));
        productList.add(new Product(9,"Cabbage","This is a Cabbage",false, 3,100,0.01f,0,"Cabbage_image"));
        productList.add(new Product(10,"Carrot","This is a Carrot",false, 3,100,0.01f,0,"Carrot_image"));
        productList.add(new Product(11,"EggPlant","This is a EggPlant",false, 3,100,0.01f,0,"EggPlant_image"));
        productList.add(new Product(12,"Potato","This is a Potato",false, 3,100,0.01f,0,"Potato_image"));
        productList.add(new Product(13,"Bean","This is a Bean",false, 3,100,0.01f,0,"Bean_image"));
        productList.add(new Product(14,"Tomato","This is a Tomato",false, 3,100,0.01f,0,"Tomato_image"));
        productList.add(new Product(15,"Nuts","This is a Nuts",false, 3,100,0.01f,0,"Nuts_image"));

    }

    @Test
    @DisplayName("000.getProductByCategoryのテスト：ページサイズ2，categoryId=1(肉類)の最初ページが表示されるか")
    public void getProductsByCategory_pageSize2_category1_first_page_Test(){
        int pageNo = 0;
        int pageSize = 2;
        int categoryId =3;

        // convert List<Product> to Page<Product>
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), productList.size());
        List<Product> actualContent = new ArrayList<>();
        actualContent.add(new Product(1,"Sardin","This is a Sardin",false, 1,200,0.01f,0,"aa"));
        actualContent.add(new Product(2,"Maguro","This is a Maguro",false, 1,200,0.01f,0,"maguro_image"));
        Page<Product> productPage = new PageImpl<>( actualContent.subList(start,end),pageable,productList.size());
        //Page<Product> products = productRepository. findByCategoryId( CategoryId, pageable);
        // do service
        Mockito.doReturn(productPage).when(productRepository).findByCategoryId( categoryId, pageable);
        ProductResponse actualResponse =  productServiceImplement.getProductsByCategory(pageNo,pageSize,categoryId);

//         Mockito.doReturn(productPage).when(productRepository).getProductsByCategory(categoryId,pageable);
//        ProductResponse actualResponse =  productServiceImplement.getProductsByCategory(pageNo,pageSize,categoryId);
        // todo why categoryId is decrease?

        // create expected Data
        List<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(new Product(1,"Sardin","This is a Sardin",false, 1,200,0.01f,0,"aa"));
        expectedProductList.add(new Product(2,"Maguro","This is a Maguro",false, 1,200,0.01f,0,"maguro_image"));
        List<ProductDto> content = expectedProductList.stream().map(product -> productServiceImplement.mapToDTO(product)).collect(Collectors.toList());




        ProductResponse expectedReponse = new ProductResponse(content,pageNo,pageSize,4,2,false,1,null);
        //ProductResponse(List< ProductDto > content, int pageNo, int pageSize, long totalElements, int totalPages, boolean last, int categoryId, String categoryName)

        // compare actual and expected
        assert(actualResponse.equals(expectedReponse));

        // assetThat(productResponse.getContent().get(0).getCategoryId())
        // https://github.com/namhaminh/shopme/blob/master/ShopmeWebParent/ShopmeBackEnd/src/test/java/com/shopme/admin/category/CategoryServiceTests.java

    }



}
