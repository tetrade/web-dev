package ru.nicetu.predator.u.modules.launch.chart.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.nicetu.predator.u.modules.launch.chart.dto.PageResponse;
import ru.nicetu.predator.u.modules.launch.chart.dto.ProductRequest;
import ru.nicetu.predator.u.modules.launch.chart.dto.ProductResponse;
import ru.nicetu.predator.u.modules.launch.chart.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(allowCredentials = "true", originPatterns = "*")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{id}")
    public ProductResponse getProduct(@PathVariable("id") int id) {
        return productService.getProduct(id) != null ? ProductResponse.fromEntity(productService.getProduct(id)) : null;
    }

    @PostMapping(path = "/goods", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse getProducts(@RequestBody ProductRequest request) {
        return productService.getProducts(
                request.getType(),
                request.getColor(),
                request.getFrom(),
                request.getTo(),
                request.getSortBy(),
                request.getLimit(),
                request.getOffset()
        );
    }

    @GetMapping("/goods/{tag}")
    public List<ProductResponse> getProduct(@PathVariable("tag") String tag) {
        return productService.getProducts(tag).stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
