package ru.geekbrains.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private BigDecimal pricePerProduct;

    @Column(name = "price")
    private BigDecimal price;

    public OrderItem(Product p) {
        this.product = p;
        this.quantity = 1;
        this.price = p.getPrice();
        this.pricePerProduct = p.getPrice();
    }

    public void incrementQuantity() {
        quantity++;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }

    public void decrementQuantity() {
        quantity--;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }
}
