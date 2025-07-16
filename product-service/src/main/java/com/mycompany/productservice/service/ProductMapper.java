package com.mycompany.productservice.service;

import com.mycompany.productservice.dto.ProductRequest;
import com.mycompany.productservice.dto.ProductResponse;
import com.mycompany.productservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest productRequest);
    ProductResponse toResponse(Product product);
    List<ProductResponse> toResponse(List<Product> products);

//If your field names differ, you can use @Mapping(source = "field1", target = "field2")

}
