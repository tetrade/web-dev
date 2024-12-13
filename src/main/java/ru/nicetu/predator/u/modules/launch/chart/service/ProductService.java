package ru.nicetu.predator.u.modules.launch.chart.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ru.nicetu.predator.u.modules.launch.chart.dto.PageResponse;
import ru.nicetu.predator.u.modules.launch.chart.dto.ProductResponse;
import ru.nicetu.predator.u.modules.launch.chart.entities.Attribute;
import ru.nicetu.predator.u.modules.launch.chart.entities.Product;
import ru.nicetu.predator.u.modules.launch.chart.repository.AttributeRepository;
import ru.nicetu.predator.u.modules.launch.chart.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final AttributeRepository attributeRepository;

    public Product getProduct(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public PageResponse getProducts(
            List<String> type,
            List<String> color,
            Integer from,
            Integer to,
            String sortBy,
            int limit,
            int offset) {
        int total;

        List<ProductResponse> products = productRepository.findAll()
                .stream()
                .filter(product -> (type == null || type.contains(product.getCategory())) &&
                        (color == null || color.contains(product.getColor())) &&
                        (product.getPrice() >= (from != null ? from : 0) && product.getPrice() <= (to != null ? to : Integer.MAX_VALUE)))
                .sorted(getComparator(sortBy))
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());

        total = products.size();
        List<ProductResponse> paginatedProducts = products.stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());

        return PageResponse.builder()
                .products(paginatedProducts)
                .total(total)
                .limit(limit)
                .offset(offset)
                .build();
    }

    public List<Product> getProducts(String tag) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getTags() != null &&
                        product.getTags().stream().anyMatch(t -> t.getName().equals(tag)))
                .collect(Collectors.toList());
    }

    private Comparator<Product> getComparator(String sortBy) {
        if (sortBy == null) {
            return Comparator.comparingInt(Product::getId);
        }

        switch (sortBy.toLowerCase()) {
            case "cheap":
                return Comparator.comparingInt(Product::getPrice);
            case "expensive":
                return Comparator.comparingInt(Product::getPrice).reversed();
            case "hit":
                return Comparator.comparing((Product p) -> p.getTags() != null &&
                        p.getTags().stream().anyMatch(tag -> "HIT".equalsIgnoreCase(tag.getName()))).reversed();
            case "new":
                return Comparator.comparing((Product p) -> p.getTags() != null &&
                        p.getTags().stream().anyMatch(tag -> "NEW".equalsIgnoreCase(tag.getName()))).reversed();
            default:
                return Comparator.comparingInt(Product::getId);
        }
    }
}

