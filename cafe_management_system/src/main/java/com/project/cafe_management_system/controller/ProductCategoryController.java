package com.project.cafe_management_system.controller;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.model.ProductCategory;
import com.project.cafe_management_system.service.ProductCategoryService;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product_category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ResponseGeneric<ProductCategoryDTO>> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {

        productCategoryService.createProductCategory(productCategoryDTO);
        ResponseGeneric<ProductCategoryDTO> responseGeneric = new ResponseGeneric<>(200, "success", productCategoryDTO);
//        ResponseGeneric<ProductCategoryDTO> responseGeneric = productCategoryService.createProductCategory(productCategoryDTO);
        return ResponseEntity.ok(responseGeneric);
    }

    @GetMapping
    public ResponseEntity<ResponseGeneric<List<ProductCategoryDTO>>> getAllProductCategory() {
        List<ProductCategoryDTO> productCategoryDTOs = productCategoryService.getAllProductCategory();
        ResponseGeneric<List<ProductCategoryDTO>> responseGeneric = new ResponseGeneric<>(200, "success", productCategoryDTOs);
        return ResponseEntity.ok(responseGeneric);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseGeneric<ProductCategoryDTO>> getProductCategoryById(@PathVariable Long id) {
        ProductCategoryDTO productCategoryDTO= productCategoryService.getProductCategoryById(id);
        ResponseGeneric<ProductCategoryDTO>  responseGeneric = new ResponseGeneric<>(200, "success", productCategoryDTO);
        return ResponseEntity.ok(responseGeneric);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseGeneric<List<ProductCategoryDTO>>> getProductCategoryByName(@PathVariable String name) {

        List<ProductCategoryDTO> productCategoryDTO= productCategoryService.getProductCategoryByName(name);
        ResponseGeneric<List<ProductCategoryDTO>>  responseGeneric = new ResponseGeneric<>(200, "success", productCategoryDTO);
        return ResponseEntity.ok(responseGeneric);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseGeneric<ProductCategoryDTO>> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        productCategoryService.updateProductCategory(id, productCategoryDTO);
        ResponseGeneric<ProductCategoryDTO> responseGeneric = new ResponseGeneric<>(200, "success", productCategoryDTO);
        return ResponseEntity.ok(responseGeneric);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProductCategory(@PathVariable Long id) {
        ResponseGeneric<Map<String, Boolean>> responseGeneric = productCategoryService.deleteProductCategory(id);
        return ResponseEntity.ok(responseGeneric.getData());
    }

}
