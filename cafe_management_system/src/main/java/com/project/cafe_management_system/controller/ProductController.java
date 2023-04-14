package com.project.cafe_management_system.controller;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.dto.ProductDTO;
import com.project.cafe_management_system.model.Product;
import com.project.cafe_management_system.service.ProductService;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseGeneric<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO) {

        productService.createProduct(productDTO);
        ResponseGeneric<ProductDTO> responseGeneric = new ResponseGeneric<>(200, "success", productDTO);
        return ResponseEntity.ok(responseGeneric);
    }


//    public List<Product> getAllProduct() {
//        return productService.getAllProduct();
//    }

//    @GetMapping
//    public ResponseEntity<ResponseGeneric<List<ProductDTO>>> getAllProduct() {
//        List<ProductDTO> productDTOs = productService.getAllProduct();
//        ResponseGeneric<List<ProductDTO>> responseGeneric = new ResponseGeneric<>(200, "success", productDTOs);
//        return ResponseEntity.ok(responseGeneric);
//    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseGeneric<ProductDTO>> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        ResponseGeneric<ProductDTO> responseGeneric = new ResponseGeneric<>(200, "success", productDTO);
        return ResponseEntity.ok(responseGeneric);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
        ResponseGeneric<Map<String, Boolean>> responseGeneric = productService.deleteProduct(id);
        return ResponseEntity.ok(responseGeneric.getData());
    }
}
