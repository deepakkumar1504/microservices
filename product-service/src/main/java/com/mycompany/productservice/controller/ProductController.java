package com.mycompany.productservice.controller;

import com.mycompany.productservice.dto.ProductRequest;
import com.mycompany.productservice.dto.ProductResponse;
import com.mycompany.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mycompany.productservice.constants.ProductServiceConstants.ROOT_PATH;

@RestController
@RequestMapping(value = ROOT_PATH)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProduct() {
        return productService.getProduct();
    }

    @GetMapping("/get/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Integer productId) {
        return productService.updateProduct(productRequest, productId);
    }

    @PatchMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse partialUpdateProduct(@RequestBody ProductRequest productRequest, @PathVariable Integer productId) {
        return productService.partialUpdate(productRequest, productId);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //// 204 No Content because delete usually doesn't return a body
    public void deleteEmployee(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
    }


}