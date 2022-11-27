package com.example.hedgenet_backend.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StockData
{
    List<Calendar> dates;
    List<Float> stockPrices;

    public StockData()
    {
        dates=new ArrayList<Calendar>();
        stockPrices=new ArrayList<Float>();
    }

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

    public float getLastPrice()
    {
        return stockPrices.get(stockPrices.size()-1);
    }

    public void setStockPrices(List<Float> stockPrices) {
        this.stockPrices = stockPrices;
    }
}
