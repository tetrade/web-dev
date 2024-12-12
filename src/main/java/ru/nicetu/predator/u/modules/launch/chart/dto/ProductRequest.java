package ru.nicetu.predator.u.modules.launch.chart.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private List<String> type;
    private List<String> color;
    private Integer from;
    private Integer to;
    private String sortBy;
    private int limit;
    private int offset;
}
