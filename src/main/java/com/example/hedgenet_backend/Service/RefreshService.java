package com.example.hedgenet_backend.Service;

import com.example.hedgenet_backend.Entity.FundEntity;
import com.example.hedgenet_backend.Entity.StockEntity;
import com.example.hedgenet_backend.Repository.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class RefreshService
{
    List<StockEntity> allStocks;

    @Autowired
    StockRepo stockRepo;

    @Autowired
    YahooService yservice;

    public void shouldRefreshEvery20Minutes()
    {
        long day=24*60*60*1000;
        allStocks=stockRepo.findAll();
        allStocks.forEach(stock->{
            Timestamp time=stock.getTimestamp();
            if (time!=null){
                if(System.currentTimeMillis()-time.getTime()>day)
               {
               updatePrice(stock);
                   try {
                       updateStockData(stock);
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
            }
            else
                updatePrice(stock);
        });

    }

    public void shouldRefreshEvery30Minutes()
    {
        long day=24*60*60*1000;
        allStocks=stockRepo.findAll();
        allStocks.forEach(stock->{
            Timestamp time=stock.getTimestamp();
            if(System.currentTimeMillis()-time.getTime()>day)
            {

                try {
                    updateStockData(stock);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void updateStockData(StockEntity stock) throws IOException {
        String tickerid=stock.getTickerId();
        List<HistoricalQuote> quotes=yservice.stockHistory(tickerid);
        List<Calendar> dates=new ArrayList<Calendar>();
        List<Float> stockPrices=new ArrayList<Float>();

        for(HistoricalQuote quote:quotes)
        {
            dates.add(quote.getDate());
            if (quote.getClose()!=null)
            {

                stockPrices.add(quote.getClose().floatValue());
            }
            else
            {
                float lastprice= stockPrices.get(stockPrices.size()-1);
                stockPrices.add(lastprice);
            }

        }
        stock.setStockPrices(stockPrices);
        stock.setDates(dates);

        stockRepo.save(stock);
    }

    private void updatePrice(StockEntity stock)
    {

        String ticker=stock.getTickerId();
        Stock ystock=yservice.findStock(ticker);
        try {
            stock.setPrice(ystock.getQuote(true).getPrice().floatValue());
            stock.setpChange(ystock.getQuote(true).getChangeInPercent().floatValue());
            stock.setTimestamp(new Timestamp(System.currentTimeMillis()));
            stockRepo.save(stock);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
