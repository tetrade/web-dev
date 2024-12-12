package ru.nicetu.predator.u.modules.launch.chart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.nicetu.predator.u.modules.launch.chart.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
