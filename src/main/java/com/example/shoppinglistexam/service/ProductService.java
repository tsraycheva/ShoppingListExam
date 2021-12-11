package com.example.shoppinglistexam.service;

import com.example.shoppinglistexam.model.entity.CategoryEnum;
import com.example.shoppinglistexam.model.entity.Product;
import com.example.shoppinglistexam.model.service.ProductServiceModel;
import com.example.shoppinglistexam.model.view.ProductServiceViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel productServiceModel);

    List<ProductServiceViewModel> findAllProductsByCategory(CategoryEnum category);
    BigDecimal getTotalSum();

    void buyProduct(Long id);

    void buyAllProducts();
}
