package ru.nicetu.predator.u.modules.launch.chart.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "product")
@Entity
@Data
public class Product extends Ident {

    private String name;

    @Column(name = "desc")
    private String description;

    private Integer price;

    private String category;

    private String img;

    private Integer rating;

    private String color;

    @ManyToMany(mappedBy = "products")
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Attribute> attributes = new HashSet<>();
}
