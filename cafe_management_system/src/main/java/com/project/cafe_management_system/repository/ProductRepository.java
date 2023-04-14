package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface ProductRepository extends JpaRepository<Product, Long> {
}
