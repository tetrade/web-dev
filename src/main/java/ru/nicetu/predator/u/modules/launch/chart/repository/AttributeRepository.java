package ru.nicetu.predator.u.modules.launch.chart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.nicetu.predator.u.modules.launch.chart.entities.Attribute;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
