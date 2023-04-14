package com.project.cafe_management_system.service;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.dto.ProductDTO;
import com.project.cafe_management_system.exception.ResourceNotFoundException;
import com.project.cafe_management_system.model.Product;
import com.project.cafe_management_system.model.ProductCategory;
import com.project.cafe_management_system.repository.ProductRepository;
import com.project.cafe_management_system.utils.ResponseGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryService productCategoryService;
//    @Autowired
//    private ProductMapper productMapper;

    public ResponseGeneric<Object> createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductName(productDTO.getProductName());

        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());

//        product.setProductCategory(productCategoryService.getProductCategoryById(productDTO.getProductCategoryId()));


        productRepository.save(product);
        Long newId = product.getId();
        productDTO.setId(newId);
        return ResponseGeneric.builder()
                .data(ProductDTO.builder()
                        .productName(product.getProductName())

                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .image(product.getImage())
                        .description(product.getDescription())

                        .productCategoryId(product.getProductCategory().getId()))

                .build();
    }


//    public List<ProductDTO> getAllProduct() {
//        List<Product> products = productRepository.findAll();
//        List<ProductDTO> productDTOs = new ArrayList<>();
//
//        for (Product product : products) {
//            productDTOs.add(productMapper.convertModelToDTO(product));
//        }
//
//        return productDTOs;
////        return productRepository.findAll();
//    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        return product;
    }

    public ResponseGeneric<Object> updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product  not exist with id: " + id));
        product.setProductName((productDTO.getProductName()));
//        product.setProductCategory(productCategoryService.getProductCategoryById(productDTO.getProductCategoryId()));

        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());

        productRepository.save(product);
        Long newId = product.getId();
        productDTO.setId(newId);
        return ResponseGeneric.builder()
                .build();
    }

    public ResponseGeneric<Map<String, Boolean>> deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        ResponseGeneric<Map<String, Boolean>> rs = new ResponseGeneric<>();
        productRepository.delete(product);
        Map<String, Boolean> respon = new HashMap<>();
        respon.put("Deleted product: ", Boolean.TRUE);
        rs.setData(respon);
        return rs;

    }

}
