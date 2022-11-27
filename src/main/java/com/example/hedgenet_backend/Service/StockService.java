package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.StockEntity;
import com.example.hedgenet_backend.Models.StockData;
import com.example.hedgenet_backend.Repository.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    YahooService yservice;
    @Autowired
    StockRepo stockRepo;

    @Autowired
    RefreshService refreshService;

    public List<StockEntity> getStocks()
    {
        refreshService.shouldRefreshEvery20Minutes();
        return stockRepo.findAll();
    }


    public StockEntity getStock(String tickerid)
    {
        StockEntity stock=stockRepo.findByTickerId(tickerid);
        return stock;
    }
}

