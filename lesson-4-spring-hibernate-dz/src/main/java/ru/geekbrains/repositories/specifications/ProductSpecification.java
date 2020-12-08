package ru.geekbrains.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.entities.Product;

public class ProductSpecification {

    // where p.title like %titlePart%
    public static Specification<Product> titleLike(String titlePart) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + titlePart + "%");
    }

    // TODO добавить спецификации для условий согласно ДЗ
}
