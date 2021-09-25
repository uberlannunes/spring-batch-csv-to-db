package dev.uberlan.springbatchcsvtodb.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockQuote {
    private String symbol;
    private LocalDate day;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
}
