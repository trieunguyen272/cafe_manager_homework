package com.project.cafe_management_system.service;

import com.project.cafe_management_system.dto.ProductCategoryDTO;
import com.project.cafe_management_system.dto.ProductDTO;
import com.project.cafe_management_system.exception.ResourceNotFoundException;
import com.project.cafe_management_system.mapper.ProductCategoryMapper;
import com.project.cafe_management_system.model.Product;
import com.project.cafe_management_system.model.ProductCategory;
import com.project.cafe_management_system.repository.ProductCategoryRepository;
import com.project.cafe_management_system.repository.ProductRepository;
import com.project.cafe_management_system.utils.ResponseGeneric;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service

public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    
    public ProductCategory retrievedById(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product category not exist with id: " + id));

        return productCategory;
    }


    public ResponseGeneric<ProductCategoryDTO> createProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryMapper.convertDTOToModel(productCategoryDTO);
        
        productCategory =  productCategoryRepository.save(productCategory);
        
        ProductCategoryDTO savedProductCategoryDTO = productCategoryMapper.convertModelToDTO(productCategory);
//        Long newId = productCategory.getId();
//        productCategoryDTO.setId(newId);
        
        return new ResponseGeneric<>(200, "success", savedProductCategoryDTO);

//        return ResponseGeneric.builder().message("success")
//                .data(ProductCategoryDTO.builder()
//                        .id(productCategory.getId())
//                        .productCategoryName(productCategory.getProductCategoryName()))
//                .build();

    }


    public ResponseGeneric<List<ProductCategoryDTO>> getAllProductCategory() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        
        List<ProductCategoryDTO> productCategoryDTOs = productCategories.stream()
        		.map(productCategory -> productCategoryMapper.convertModelToDTO(productCategory))
        		.collect(Collectors.toList());
        return new ResponseGeneric<>(200, "Success", productCategoryDTOs);

    }

    public ResponseGeneric<ProductCategoryDTO> getProductCategoryById(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product category not exist with id: " + id));

        ProductCategoryDTO productCategoryDTO = productCategoryMapper.convertModelToDTO(productCategory);
        return new ResponseGeneric<>(200, "Success", productCategoryDTO);
    }

//    public ResponseGeneric<List<ProductCategoryDTO>> getProductCategoryByName(String name) {
//        List<ProductCategory> productCategories = productCategoryRepository.findCategoryByName(name);
//        
//        if (productCategories.isEmpty()) {
//            throw new ResourceNotFoundException("Product category not exist with name: " + name);
//        }
//
//        List<ProductCategoryDTO> productCategoryDTOs = productCategories.stream()
//        		.map(productCategory -> productCategoryMapper.convertModelToDTO(productCategory))
//        		.collect(Collectors.toList());
//
//        return new ResponseGeneric<>(200, "Success", productCategoryDTOs);
//    }

    public ResponseGeneric<ProductCategoryDTO> updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product category not exist with id: " + id));
        
        productCategoryMapper.convertDTOToModel(productCategoryDTO);
        
        productCategory =  productCategoryRepository.save(productCategory);
        
        ProductCategoryDTO updatedProductCategoryDTO = productCategoryMapper.convertModelToDTO(productCategory);
        
        return new ResponseGeneric<>(200, "success", updatedProductCategoryDTO);
    }

    public ResponseGeneric<Map<String, Boolean>> deleteProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product category not exist with id: " + id));

        ResponseGeneric<Map<String, Boolean>> rs = new ResponseGeneric<>();
        productCategoryRepository.delete(productCategory);
        Map<String, Boolean> respon = new HashMap<>();
        respon.put("Delete product category", Boolean.TRUE);
        rs.setData(respon);
        return rs;

    }

}

