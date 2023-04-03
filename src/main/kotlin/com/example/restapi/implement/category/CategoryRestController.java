package com.example.restapi.implement.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "${frontend.URL}")
@Api(value = "OrderRestController", tags = "カテゴリー機能に関するAPI　認可は不要")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/api/category/all", method = RequestMethod.GET,produces = "application/json")
    @ApiOperation(value = "カテゴリー一覧API。",notes="カテゴリー情報を入手するAPIです。フロントエンド上ではカテゴリーバーの表示に使われます。")
    public List<CategoryDto> listAll(){
        return categoryService.listAll();
    }
}
