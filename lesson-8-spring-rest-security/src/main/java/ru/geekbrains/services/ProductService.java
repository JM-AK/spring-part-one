package ru.geekbrains.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Product;
import ru.geekbrains.repositories.ProductRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }

    public Product saveOrUpdate(Product product){
        return productRepository.save(product);
    }

    public Page<Product> findAll(Specification<Product> spec, Optional<Integer> page, Optional<Integer> size, Optional<String> sortField, Optional<String> sortOrder) {
        if (sortField.isPresent() && sortOrder.isPresent()) {
            return productRepository.findAll(spec, PageRequest.of(
                    page.orElse(1) - 1, size.orElse(5),
                    Sort.by(Sort.Direction.fromString(sortOrder.get()), sortField.get()))
            );
        }
        return productRepository.findAll(spec, PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
    }

}
