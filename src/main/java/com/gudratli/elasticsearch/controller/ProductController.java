package com.gudratli.elasticsearch.controller;

import com.gudratli.elasticsearch.document.Product;
import com.gudratli.elasticsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ProductController {
    private final ProductService productService;

    public ProductController(@Qualifier("productElasticsearchOperationService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }

    @GetMapping("/by-desc")
    public String findByDescription(@RequestParam String desc, Model model) {
        model.addAttribute("products", productService.findByDescription(desc));
        return "index";
    }

    @PostMapping("/saveProduct")
    public String save(@ModelAttribute Product product) {
        productService.save(product);

        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String getForUpdate(@PathVariable String id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);

        return "updateProduct";
    }

    @GetMapping("/newProductForm")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "newProduct";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        productService.deleteById(id);

        return "redirect:/";
    }
}
