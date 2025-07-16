package com.mycompany.productservice.service;

import com.mycompany.productservice.dto.ProductRequest;
import com.mycompany.productservice.dto.ProductResponse;
import com.mycompany.productservice.entity.Product;
import com.mycompany.productservice.exception.ResourceNotFoundException;
import com.mycompany.productservice.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Override
    public List<ProductResponse> getProduct() {
        List<Product> saved = productRepository.findAll();
        return productMapper.toResponse(saved);
    }

    @Override
    public ProductResponse getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Integer productId) {
        Product existedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
       //
        Product updatedProduct = productRepository.save(existedProduct);
        return productMapper.toResponse(updatedProduct);
    }

    @Override
    public ProductResponse partialUpdate(ProductRequest productRequest, Integer productId) {
        Product existedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
       // existedProduct.set
        Product updatedProduct = productRepository.save(existedProduct);
        return productMapper.toResponse(updatedProduct);
    }

    @Override
    public void deleteProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
        productRepository.delete(product);
    }
}