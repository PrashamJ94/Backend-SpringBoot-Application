package com.example.hedgenet_backend.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@Service
public class YahooService
{

    public Stock findStock(String ticker)
    {
        try
        {
            Stock stock= YahooFinance.get(ticker);
            return stock;
        }
        catch(IOException e)
        {
            System.out.println("error");
        }
        return null;
    }

    public List<HistoricalQuote> stockHistory(String ticker) throws IOException {
        Stock stock=YahooFinance.get(ticker);
        Calendar from= Calendar.getInstance();
        Calendar to=Calendar.getInstance();
        from.add(Calendar.MONTH,-1);
        to.add(Calendar.DATE,2);

        System.out.println(from.getTime());
        List<HistoricalQuote> quotes= stock.getHistory(from,to, Interval.DAILY);

        return quotes;

    }

}
