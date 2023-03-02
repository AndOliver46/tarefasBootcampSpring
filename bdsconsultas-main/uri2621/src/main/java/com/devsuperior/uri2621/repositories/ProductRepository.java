package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.dtos.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "SELECT products.name "
			+ "FROM products "
			+ "INNER JOIN providers ON products.id_providers = providers.id "
			+ "WHERE providers.name LIKE :beginName% AND products.amount BETWEEN :min AND :max")
	List<ProductMinProjection> search1(Integer min, Integer max, String beginName);
	
	@Query("SELECT new com.devsuperior.uri2621.dtos.ProductMinDTO(obj.name) "
			+ "FROM Product obj "
			+ "WHERE obj.provider.name LIKE CONCAT(:beginName, '%') "
			+ "AND obj.amount BETWEEN :min AND :max")
	List<ProductMinDTO> search2(Integer min, Integer max, String beginName);
}
