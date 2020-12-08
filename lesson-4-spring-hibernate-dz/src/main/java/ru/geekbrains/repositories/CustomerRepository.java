package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
