package com.mycompany.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.mycompany.productservice.dao.Product;

@Repository
public interface ProductRepository extends MongoRepository <Product, Long> {

	Product findByBrand(String brandName);
	
}
