package ru.nicetu.predator.u.modules.launch.chart.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageResponse {
    private List<ProductResponse> products;
    private int total;
    private int limit;
    private int offset;
}
