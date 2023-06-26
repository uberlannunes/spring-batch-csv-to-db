package dev.uberlan.springbatchcsvtodb.jobs.importcsvtodb.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "stock_prices")
public class StockPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticketPair;
    private BigInteger openTime;
    @Column(precision = 24, scale = 8)
    private BigDecimal open;
    @Column(precision = 24, scale = 8)
    private BigDecimal high;
    @Column(precision = 24, scale = 8)
    private BigDecimal low;
    @Column(precision = 24, scale = 8)
    private BigDecimal close;
    private BigInteger volume;
    private BigInteger closeTime;
    private BigInteger quoteVolume;
    private BigInteger count;
    private BigInteger takerBuyVolume;
    private BigInteger takerBuyQuoteVolume;
    private BigInteger ignore;

    public Long getId() {
        return id;
    }

    public String getTicketPair() {
        return ticketPair;
    }

    public void setTicketPair(String ticketPair) {
        this.ticketPair = ticketPair;
    }

    public BigInteger getOpenTime() {
        return openTime;
    }

    public void setOpenTime(BigInteger openTime) {
        this.openTime = openTime;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigInteger getVolume() {
        return volume;
    }

    public void setVolume(BigInteger volume) {
        this.volume = volume;
    }

    public BigInteger getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(BigInteger closeTime) {
        this.closeTime = closeTime;
    }

    public BigInteger getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(BigInteger quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public BigInteger getTakerBuyVolume() {
        return takerBuyVolume;
    }

    public void setTakerBuyVolume(BigInteger takerBuyVolume) {
        this.takerBuyVolume = takerBuyVolume;
    }

    public BigInteger getTakerBuyQuoteVolume() {
        return takerBuyQuoteVolume;
    }

    public void setTakerBuyQuoteVolume(BigInteger takerBuyQuoteVolume) {
        this.takerBuyQuoteVolume = takerBuyQuoteVolume;
    }

    public BigInteger getIgnore() {
        return ignore;
    }

    public void setIgnore(BigInteger ignore) {
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "id=" + id +
                ", ticketPair=" + ticketPair +
                ", openTime=" + openTime +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", closeTime=" + closeTime +
                ", quoteVolume=" + quoteVolume +
                ", count=" + count +
                ", takerBuyVolume=" + takerBuyVolume +
                ", takerBuyQuoteVolume=" + takerBuyQuoteVolume +
                ", ignore=" + ignore +
                '}';
    }
}
