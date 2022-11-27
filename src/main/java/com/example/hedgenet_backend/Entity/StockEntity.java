package com.example.hedgenet_backend.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="stock")
public class StockEntity
{
    @Id
    @Column(name="ticker_id")
    private String tickerId;

    @Column(name="price")
    private float price;

    @Column(name="percent_change")
    private float pChange;

    @Column(name="Time")
    private Timestamp timestamp;

    @Column(name="DatesOfMonth")
    @ElementCollection
    List<Calendar> dates=new ArrayList<Calendar>();

    @Column(name="pricesOfMonth")
    @ElementCollection
    List<Float> stockPrices=new ArrayList<Float>();

    @Column(name="pricesOfMonth")
    @ElementCollection
    List<String> stockPricess;


    public void addDate(Calendar calendar)
    {
        dates.add(calendar);
    }

    public void addPrice(float price)
    {
        stockPrices.add(price);
    }
    public List<Calendar> getDates() {
        return dates;
    }

    public void setDates(List<Calendar> dates) {
        this.dates = dates;
    }

    public List<Float> getStockPrices() {
        return stockPrices;
    }

    public void setStockPrices(List<Float> stockPrices) {
        this.stockPrices = stockPrices;
    }

    public String getTickerId() {
        return tickerId;
    }

    public void setTickerId(String tickerId) {
        this.tickerId = tickerId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getpChange() {
        return pChange;
    }

    public void setpChange(float pChange) {
        this.pChange = pChange;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp){
        this.timestamp=timestamp;
    }

}

