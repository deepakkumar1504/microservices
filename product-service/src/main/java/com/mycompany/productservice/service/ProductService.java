package com.mycompany.productservice.service;

import com.mycompany.productservice.dto.ProductRequest;
import com.mycompany.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse saveProduct(ProductRequest productRequest);
    List<ProductResponse> getProduct();
    ProductResponse getProductById(Integer productId);
    ProductResponse updateProduct(ProductRequest productRequest, Integer id);
    ProductResponse partialUpdate(ProductRequest productRequest, Integer id);
    void deleteProductById(Integer productId);


}
