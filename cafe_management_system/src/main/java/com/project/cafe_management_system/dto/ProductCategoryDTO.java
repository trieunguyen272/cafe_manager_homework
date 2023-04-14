package com.project.cafe_management_system.dto;

import com.project.cafe_management_system.model.Product;
import com.project.cafe_management_system.model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDTO {
    private Long id;
    private String productCategoryName;

}
