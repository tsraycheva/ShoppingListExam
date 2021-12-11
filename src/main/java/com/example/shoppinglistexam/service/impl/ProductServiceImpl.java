package com.example.shoppinglistexam.service.impl;

import com.example.shoppinglistexam.model.entity.CategoryEnum;
import com.example.shoppinglistexam.model.entity.Product;
import com.example.shoppinglistexam.model.service.ProductServiceModel;
import com.example.shoppinglistexam.model.view.ProductServiceViewModel;
import com.example.shoppinglistexam.repository.ProductRepository;
import com.example.shoppinglistexam.service.CategoryService;
import com.example.shoppinglistexam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {

        Product product = modelMapper.map(productServiceModel, Product.class);

        product.setCategory(categoryService.findByCategoryNameEnum(productServiceModel.getCategory()));
        productRepository.save(product);

    }

    @Override
    public List<ProductServiceViewModel> findAllProductsByCategory(CategoryEnum category) {
       return productRepository
               .findAllByCategory_Name(category)
               .stream()
               .map(product -> modelMapper
                       .map(product, ProductServiceViewModel.class))
               .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getTotalSum() {
        return productRepository.findProductsPriceTotalSum();
    }

    @Override
    public void buyProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAllProducts() {
        productRepository.deleteAll();
    }
}
