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


    public ResponseGeneric<Object> createProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(productCategoryDTO.getId());
        productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());
        productCategoryRepository.save(productCategory);
        Long newId = productCategory.getId();
        productCategoryDTO.setId(newId);

        return ResponseGeneric.builder().message("success")
                .data(ProductCategoryDTO.builder()
                        .id(productCategory.getId())
                        .productCategoryName(productCategory.getProductCategoryName()))
                .build();

    }


    public List<ProductCategoryDTO> getAllProductCategory() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        List<ProductCategoryDTO> productCategoryDTOs = new ArrayList<>();

        for (ProductCategory productCategory : productCategories) {
            productCategoryDTOs.add(productCategoryMapper.convertModelToDTO(productCategory));
        }

        return productCategoryDTOs;
    }

    public ProductCategoryDTO getProductCategoryById(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product category not exist with id: " + id));

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();

        productCategoryDTO = productCategoryMapper.convertModelToDTO(productCategory);

        return productCategoryDTO;
    }

    public List<ProductCategoryDTO> getProductCategoryByName(String name) {
        List<ProductCategory> productCategories = productCategoryRepository.findCategoryByName(name);

        if (productCategories.isEmpty()) {
            new ResourceNotFoundException("Product category not exist with name: " + name);
        }

        List<ProductCategoryDTO> productCategoryDTO = productCategories.stream().map(productCategory -> productCategoryMapper.convertModelToDTO(productCategory)).collect(Collectors.toList());

        return productCategoryDTO;

    }

    public ResponseGeneric<Object> updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product category not exist with id: " + id));
        productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());

        productCategoryRepository.save(productCategory);
        Long newId = productCategory.getId();
        productCategoryDTO.setId(newId);
        return ResponseGeneric.builder()
//                .data(ProductCategoryDTO.builder()
//                        .id(productCategory.getId())
//                        .productCategoryName(productCategory.getProductCategoryName()))
                .build();
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

