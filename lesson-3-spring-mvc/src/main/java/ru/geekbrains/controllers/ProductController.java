package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.math.BigDecimal;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String indexProductPage(Model model) {
        logger.info("Product page update");
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {
        logger.info("Edit product with id {}", id);
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        // TODO дописать добавление аттрибута
        logger.info("Add product");
        model.addAttribute(new Product());
        return "product_form";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        if (product.getId() == null) {
            productRepository.insert(product);
        } else {
            productRepository.update(product);
        }
        return "redirect:/product";
    }

    @DeleteMapping("/delete")
    public String deleteProduct(@RequestParam (value = "id") Long id,  Model model) {
        // TODO дописать удаление продукта
        logger.info("Delete product with id {}", id);
        productRepository.delete(id);
        return "redirect:/product";
    }
}
