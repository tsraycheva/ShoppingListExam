package com.example.shoppinglistexam.web;

import com.example.shoppinglistexam.model.binding.ProductAddBindingModel;
import com.example.shoppinglistexam.model.service.ProductServiceModel;
import com.example.shoppinglistexam.model.view.ProductServiceViewModel;
import com.example.shoppinglistexam.service.ProductService;
import com.example.shoppinglistexam.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public ProductController(ProductService productService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @PostMapping("/add")
    public String products(@Valid ProductAddBindingModel productAddBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add";

        }
        productService.addProduct(modelMapper.map(productAddBindingModel, ProductServiceModel.class));

        List<ProductServiceViewModel> products = productService.findAllProductsByCategory(productAddBindingModel.getCategory());

        redirectAttributes.addFlashAttribute("products", products);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String productAdd() {
        if(currentUser.getId()==null) {
            return "redirect:/";
        }
        return "product-add";
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id) {
        productService.buyProduct(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAllProducts(){
        productService.buyAllProducts();
        return "redirect:/";
    }

    @ModelAttribute()
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }
}
