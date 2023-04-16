package com.project.cafe_management_system.repository;

import com.project.cafe_management_system.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;

@Controller
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT s FROM Products s WHERE s.productName like %?1%")
	List<Product> findProductByName(String name);
}
