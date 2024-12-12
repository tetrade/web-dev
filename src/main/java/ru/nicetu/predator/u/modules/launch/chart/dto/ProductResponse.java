package ru.nicetu.predator.u.modules.launch.chart.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.nicetu.predator.u.modules.launch.chart.entities.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private int id;
    private String name;
    private String desc;
    private int price;
    private String category;
    private String img;
    private int rating;
    private String color;
    private List<String> tags;
    private List<String> characteristic;

    public static ProductResponse fromEntity(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .desc(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .img(product.getImg())
                .rating(product.getRating())
                .color(product.getColor())
                .tags(product.getTags() != null ?
                        product.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()) :
                        new ArrayList<>()
                )
                .characteristic(product.getAttributes() != null ?
                        product.getAttributes().stream().map(charac -> charac.getName() + ": " + charac.getValue()).collect(Collectors.toList()) :
                        new ArrayList<>()
                )
                .build();
    }
}
