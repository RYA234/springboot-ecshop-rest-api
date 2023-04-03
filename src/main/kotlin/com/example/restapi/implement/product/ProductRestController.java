package com.example.restapi.implement.product;

import com.example.restapi.domain.product.Product;
import com.example.restapi.domain.product.ProductService;
import com.example.restapi.implement.category.CategoryService;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.websocket.server.PathParam;


/**
 *
 * &#064;brief:  This Class is RestController class in Architecture Controller-Service-Repository Pattern
 *  &#064;description　
 *
 *  &#064;Auther  RYA234
 *
 * &#064;Entity:  {@link  Product}
 * &#064;UseCase:  {@link ProductService}
 */


@Api(value = "ProductRestController", tags = "商品情報に関するAPI　認証は不要")
@RestController
@CrossOrigin(origins =  "${frontend.URL}")
public class ProductRestController {

    @Autowired
     ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/api/products", method = RequestMethod.GET,produces = "application/json")
    @ApiOperation(value = "カテゴリー毎の商品一覧取得API。",notes="カテゴリー毎の商品を表示します。ページネーション対応しています。", response = ProductResponse.class)
    public ProductResponse getProductByCategory(
            @ApiParam(name="pageNo",value = "ページ数 0以上の整数")
            @RequestParam(value ="pageNo", defaultValue = "1", required = false ) int pageNo,
            @ApiParam(name="pageSize",value = "ページサイズ 0以上の整数")
            @RequestParam(value = "pageSize", defaultValue ="4", required = false) int pageSize,
            @ApiParam(name="category",value = "カテゴリーのID値")
            @RequestParam(value = "category", defaultValue="1", required = false) int categoryId
    ){
        ProductResponse productResponse;
        productResponse = productService.getProductsByCategory(pageNo,pageSize,categoryId);
        productResponse.setCategoryId(categoryId);
        productResponse.setCategoryName(categoryService.findById(categoryId).getName());

        return productResponse;
    }

    @ApiIgnore
    @ApiOperation(value = "商品情報を一件表示するAPI。",notes="商品詳細画面を表示するAPIです。尚フロントエンドが側では未実装です。")
    @RequestMapping(value = "/api/products/", method = RequestMethod.GET,produces = "application/json")
    public ProductDto getProductById(
            @ApiParam(name="id",value = "商品ID")
            @RequestParam(value ="id",defaultValue = "1", required = false) int productId
    ){
        ProductDto productDto;
        productDto = productService.getProductById(productId);
        return productDto;
    }

}
