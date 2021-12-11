package com.example.shoppinglistexam.web;

import com.example.shoppinglistexam.model.entity.CategoryEnum;
import com.example.shoppinglistexam.service.ProductService;
import com.example.shoppinglistexam.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final ProductService productService;

    public HomeController(CurrentUser currentUser, ProductService productService) {
        this.currentUser = currentUser;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.getId()==null) {
            return  "index";
        }

        model.addAttribute("totalSum", productService.getTotalSum());
        model.addAttribute("drinks", productService
                .findAllProductsByCategory(CategoryEnum.DRINK));
        model.addAttribute("food", productService
                .findAllProductsByCategory(CategoryEnum.FOOD));
        model.addAttribute("other", productService
                .findAllProductsByCategory(CategoryEnum.OTHER));
        model.addAttribute("household", productService
                .findAllProductsByCategory(CategoryEnum.HOUSEHOLD));
         return "home";
    }


}
