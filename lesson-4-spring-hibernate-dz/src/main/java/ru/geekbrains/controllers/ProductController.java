package ru.geekbrains.controllers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.Product;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.services.ProductService;
import ru.geekbrains.util.ProductFilter;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public String indexProductPage(Model model,
                                   @RequestParam(defaultValue = "1", name = "p") Integer page,
                                   @RequestParam Map<String, String> params
                                   ) {
        logger.info("Product page update");

        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);

        Page<Product> products = productService.findAll(productFilter.getSpec(),page - 1, 5);
        model.addAttribute("product", products);
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        return "product";
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id: " + id+ "doesn't exists"));
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable(value = "id") Long id, Model model) {
        logger.info("Edit product with id {}", id);
        Product p = productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id: " + id+ "doesn't exists"));
        model.addAttribute("product", p);
        return "product_edit_form";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute(new Product());
        return "product_edit_form";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
        logger.info("Delete product with id {}", id);
        productService.deleteById(id);
        return "redirect:/product";
    }

    @DeleteMapping
    public void deleteAll(){
        productService.deleteAll();
    }
}
