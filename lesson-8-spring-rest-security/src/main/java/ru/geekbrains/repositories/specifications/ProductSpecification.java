package ru.geekbrains.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.entities.Product;

import java.math.BigDecimal;


public class ProductSpecification {

    // where p.title like %titlePart%
    public static Specification<Product> titleLike(String titlePart) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + titlePart + "%");
    }

    // where p.price >= minPrice
    public static Specification<Product> priceGreaterOrEqualsThan(BigDecimal minPrice) {
        return ((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), minPrice));
    }

    //where p.price <= maxPrice
    public static Specification<Product> priceLessOrEqualsThan(BigDecimal maxPrice) {
        return ((root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), maxPrice));
    }


}
