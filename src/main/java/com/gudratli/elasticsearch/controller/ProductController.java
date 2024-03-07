package com.gudratli.elasticsearch.controller;

import com.gudratli.elasticsearch.document.Product;
import com.gudratli.elasticsearch.query.ProductQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductQuery productQuery;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productQuery.findAll());
        return "index";
    }

    @GetMapping("/by-desc/{desc}")
    public String findByDescription(@PathVariable String desc, Model model){
        model.addAttribute("products", productQuery.findByDescription(desc));
        return "index";
    }

    @PostMapping("/saveProduct")
    public String save(@ModelAttribute Product product) {
        productQuery.save(product);

        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String getForUpdate(@PathVariable String id, Model model) {
        Product product = productQuery.getById(id);
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
        productQuery.deleteById(id);

        return "redirect:/";
    }
}
