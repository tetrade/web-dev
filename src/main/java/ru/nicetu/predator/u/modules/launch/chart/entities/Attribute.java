package ru.nicetu.predator.u.modules.launch.chart.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "characteristics")
@Entity
@Data
public class Attribute extends Ident {

    private String name;

    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
